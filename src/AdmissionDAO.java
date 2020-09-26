

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class AdmissionDAO implements IAdmissionDAO {
    private HashMap<Integer,ArrayList<Examination>> admissionHashMap = new HashMap<Integer, ArrayList<Examination>>();
    private HashMap<Integer,Integer> admissionNumbertoPatientIdHashMap = new HashMap<>();
    private String admissionPath;

    public AdmissionDAO(String admissionPath,ArrayList<Patient> patientList) {
        this.admissionPath = admissionPath;
        fillAdmissionHashmap(patientList);
    }

    public HashMap<Integer, ArrayList<Examination>> getAdmissionHashMap(){
        return admissionHashMap;
    }
    public HashMap<Integer,Integer> getAdmissionNumbertoPatientIdHashMap(){
        return admissionNumbertoPatientIdHashMap;
    }

    //This method writes admissionHasmap's content to admission.txt file

    public void writeToAdmission(String path,boolean append){

        String content = "";
        ArrayList<Integer> sortedAdmissionNumbers = new ArrayList<>();
        for(Integer admissionNumber : admissionNumbertoPatientIdHashMap.keySet()){
            sortedAdmissionNumbers.add(admissionNumber);
        }
        Collections.sort(sortedAdmissionNumbers);

        for(Integer admissionNumber : sortedAdmissionNumbers){

            content+=admissionNumber+"\t"+admissionNumbertoPatientIdHashMap.get(admissionNumber)+"\n";
            for(int j= 0 ; j<admissionHashMap.get(admissionNumber).size();j++){
                content+=admissionHashMap.get(admissionNumber).get(j).getName()+"\n";
            }

        }
        content = content.substring(0,content.length()-1);
        if(append){
            FileWriter.AppendToFile(path,content);
        }
        else {
            FileWriter.writeToNewFile(path,content);
        }


    }


    //This method converts an operation string to an operation object

    public Examination StringToExaminationObject(String string){

        String[] stringArray = null;
        String[] operationArray =null;

        try{
            stringArray = string.split("\t");
            operationArray = stringArray[1].split(" ");
        }
        catch (Exception e){

            stringArray = string.split(" ");
            stringArray=  Arrays.copyOfRange(stringArray,2,stringArray.length);
            operationArray = Arrays.copyOfRange(stringArray,1,stringArray.length);

        }

        Operation operation = new BaseOperation();
        Examination examination;



        for(int i = 0 ; i<operationArray.length;i++){
            switch (operationArray[i]){

                case "imaging":
                    operation = new Imaging(operation);
                    break;

                case "measurements":
                    operation = new Measurements(operation);
                    break;

                case "tests":
                    operation = new Tests(operation);
                    break;
                case "doctorvisit":
                    operation = new DoctorVisit(operation);
                    break;
            }

        }
        if(stringArray[0].equals("Inpatient")){
            examination = new InpatientExamination(operation,null);
        }
        else {
            examination = new OutpatientExamination(operation,null);
        }

        return examination;

    }



    //This method fills the admissionHashmap with the admission.txt's content.

    public void fillAdmissionHashmap(ArrayList<Patient> patientList){
        ArrayList<String[]> admissionString = FileReader.readToList(admissionPath);

        int admissionNumber = 0;
        int patientId = 0;
        for(int i = 0 ; i<admissionString.size();i++){

            try{

                admissionNumber = Integer.parseInt(admissionString.get(i)[0]);
                patientId = Integer.parseInt(admissionString.get(i)[1]);
                ArrayList<Examination> examinationArrayList = new ArrayList<>();
                admissionHashMap.put(admissionNumber,examinationArrayList);
                admissionNumbertoPatientIdHashMap.put(admissionNumber,patientId);


            }
            catch (Exception e){

                String fullString = admissionString.get(i)[0]+"\t"+admissionString.get(i)[1];
                Examination examination = StringToExaminationObject(fullString);
                for(int j = 0 ; j<patientList.size();j++){
                    if(patientList.get(j).getId()==patientId){ examination.setPatient(patientList.get(j)); }
                }


                admissionHashMap.get(admissionNumber).add(examination);

            }
        }

    }

    //This method is used to create a new admission and updating both admissionHashMap and admission.txt file.

    public void createAdmission(int admissionNumber , int patientId){

        ArrayList<Examination> admissionArrayList = new ArrayList<>();
        admissionHashMap.put(admissionNumber,admissionArrayList);
        admissionNumbertoPatientIdHashMap.put(admissionNumber,patientId);
        writeToAdmission(admissionPath,false);


    }

    //This method is used to remove an admission and updating both admissionHashMap and admission.txt file.

    public void removeAdmission(int admissionNumber){
        admissionHashMap.remove(admissionNumber);
        admissionNumbertoPatientIdHashMap.remove(admissionNumber);
        writeToAdmission(admissionPath,false);
    }


    //This method is used to add a new examination to the admissionHashMap.


    public void addExamination(int admissionNumber , Examination examination){


        admissionHashMap.get(admissionNumber).add(examination);
        writeToAdmission(admissionPath,false);

    }

}
