

import java.util.ArrayList;

public class OutputManager {

    //This class is one of the Data Access Objects that this program uses.Its used for reading input.txt and producing output.txt.

    private String outputPath;
    private String inputPath;
    private PatientDAO patientDAO;
    private AdmissionDAO admissionDAO;


    public OutputManager(String outputPath, String inputPath, PatientDAO patientDAO,AdmissionDAO  admissionDAO) {
        this.outputPath = outputPath;
        this.inputPath = inputPath;
        this.patientDAO = patientDAO;
        this.admissionDAO=admissionDAO;
    }

    //Methods in this class call their respective PatientDao and AdmissionDAO methods and writes their outputs to the output file.


    //Uses PatientDAO's add patient method
    public void addPatient(String[] commandArray){

        String address ="";
        for(int i = 5 ; i<commandArray.length;i++){
            address=address+commandArray[i]+" ";
        }

        patientDAO.addPatient(Integer.parseInt(commandArray[1]),commandArray[2],commandArray[3],commandArray[4],address);
        FileWriter.AppendToFile(outputPath,"Patient "+commandArray[1]+" "+commandArray[2]+" added\n");

    }
    //Uses PatientDAO's remove patient and AdmissioNDAO's remove admission methods
    public void removePatient(String[] commandArray){

        Patient removedPatient = null;
        for(Patient patient : patientDAO.getPatientList()){
            if(patient.getId()==Integer.parseInt(commandArray[1])){
                removedPatient = patient;
            }

        }
        boolean found = false;
        int foundAdmissionNumber = 0;
        for(Integer admissionNumber : admissionDAO.getAdmissionNumbertoPatientIdHashMap().keySet()){
            try{
                if(admissionDAO.getAdmissionNumbertoPatientIdHashMap().get(admissionNumber)==removedPatient.getId()){
                    found = true;
                    foundAdmissionNumber = admissionNumber;
                }

            }
            catch (Exception e){

            }
        }
        if(found){ admissionDAO.removeAdmission(foundAdmissionNumber); }
        patientDAO.removePatient(Integer.parseInt(commandArray[1]));

        try {
            FileWriter.AppendToFile(outputPath,"Patient "+commandArray[1]+" "+removedPatient.getName()+" removed\n");

        }
        catch (Exception e){
            FileWriter.AppendToFile(outputPath,"Patient not found\n");

        }

    }
    //Uses AdmissionDAO's create admission method
    public void createAdmission(String[] commandArray){

        admissionDAO.createAdmission(Integer.parseInt(commandArray[1]),Integer.parseInt(commandArray[2]));
        FileWriter.AppendToFile(outputPath,"Admission "+commandArray[1]+" created\n");

    }
    //Uses AdmissionDAO's addExamination and StringToExaminationObject methods
    public void addExamination(String[] commandArray){

        String command = "";
        for(String str : commandArray){
            command+=str+" ";
        }
        command = command.substring(0,command.length()-1);
        Examination examination =  admissionDAO.StringToExaminationObject(command);

        try{
            admissionDAO.addExamination(Integer.parseInt(commandArray[1]),examination);
            FileWriter.AppendToFile(outputPath,commandArray[2]+" examination added to admission "+commandArray[1]+"\n");
        }
        catch (Exception e){
            FileWriter.AppendToFile(outputPath,"Admission not found"+"\n");
        }



    }
    //Uses AdmissionDAO's admissionHashMap to calculate total cost for a given patient
    public void totalCost(String[] commandArray){
        int totalCost = 0;
        FileWriter.AppendToFile(outputPath,"TotalCost for admission "+commandArray[1]+"\n");
        for(int i = 0 ; i<admissionDAO.getAdmissionHashMap().get(Integer.parseInt(commandArray[1])).size();i++){

            int cost = admissionDAO.getAdmissionHashMap().get(Integer.parseInt(commandArray[1])).get(i).getCost();
            String[] nameArray =admissionDAO.getAdmissionHashMap().get(Integer.parseInt(commandArray[1])).get(i).getName().split("\t");
            String name ="";
            for(String eachStr:nameArray){
                name= name +eachStr +" ";
            }
            name = name.substring(0,name.length()-1);

            FileWriter.AppendToFile(outputPath,"\t"+name+cost+"$"+"\n");

            totalCost+=cost;
        }
        FileWriter.AppendToFile(outputPath,"\tTotal: "+totalCost+"$"+"\n");


    }

    //Uses PatientDAO's getNameOrderedPatientList method
    public void listPatients(String[] commandArray){
        FileWriter.AppendToFile(outputPath,"Patient List:\n");
        ArrayList<Patient> orderedPatientList = patientDAO.getNameOrderedPatientList();

        for(Patient patient : orderedPatientList){


            String[] infoArray = patient.getInfo().split("\t");
            for(int i = 0 ; i<infoArray.length;i++){
                if(i!=infoArray.length-1){
                    FileWriter.AppendToFile(outputPath,infoArray[i]+" ");
                }
                else {
                    FileWriter.AppendToFile(outputPath,infoArray[i]);
                }
            }
        }


    }



    //This method reads the input file and uses required methods.

    public void createOutput(){

        ArrayList<String[]> inputArray =  FileReader.readToListSpace(inputPath);

        for(String[] commandArray : inputArray){

            switch (commandArray[0]){

                case "AddPatient":
                    addPatient(commandArray);
                    break;

                case "RemovePatient":
                    removePatient(commandArray);
                    break;

                case "CreateAdmission":
                    createAdmission(commandArray);
                    break;

                case "AddExamination":
                    addExamination(commandArray);
                    break;

                case "TotalCost":
                    totalCost(commandArray);
                    break;

                case "ListPatients":
                    listPatients(commandArray);
                    break;

            }



        }


    }


}
