

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PatientDAO implements IPatientDAO {
    //This class is one of the Data Access Objects that this program uses.Its used to organize patient information.

    private ArrayList<Patient> patientList = new ArrayList<>();
    private String patientDataPath;

    public PatientDAO(String patientDataPath) {
        this.patientDataPath = patientDataPath;
        fillPatientArrayList();
    }


    public ArrayList<Patient> getPatientList() { return patientList; }


    //This method fills the patientList arraylist from patient.txt.
    public void fillPatientArrayList(){
        ArrayList<String[]> patientStringList =  FileReader.readToList(patientDataPath);
        patientList = new ArrayList<>();
        for(int i = 0 ; i<patientStringList.size();i++){
            int id;
            String name;
            String surname;
            String phoneNumber;
            String address;
            id=Integer.parseInt(patientStringList.get(i)[0]);
            name = patientStringList.get(i)[1].split(" ")[0];
            surname = patientStringList.get(i)[1].split(" ")[1];
            phoneNumber=patientStringList.get(i)[2];
            address = patientStringList.get(i)[3].split(": ")[1];
            patientList.add(new Patient(id,name,surname,phoneNumber,address));

        }

    }

    //This method writes contents of patientList arraylist to the patient.txt file.
    public void writePatients(String path , boolean append){
        String content ="";

        for(int i = 0 ; i<patientList.size();i++){
            content = content + patientList.get(i).getInfo();
        }
        content = content.substring(0,content.length()-1);
        if(append){ FileWriter.AppendToFile(path,content); }
        else { FileWriter.writeToNewFile(path,content); }




    }


    //This method adds a patient to patientList arraylist and then calls writePatients method to update the patient.txt file.
    public void addPatient(int id , String name , String surname, String phoneNumber , String address){

        Patient patient = new Patient(id,name,surname,phoneNumber,address);

        if(patientList.size()==0){ patientList.add(patient); }
        else if(patientList.get(patientList.size()-1).getId()<patient.getId()){ patientList.add(patient);}
        else {
            for(int i = 0 ; i<patientList.size();i++){
                if(patient.getId()<patientList.get(i).getId()){ patientList.add(i,patient); break; }
                else if(patient.getId()==patientList.get(i).getId()){ break;}
            }
        }
        writePatients(patientDataPath,false);

    }


    //This method removes a patient from patientList arraylist and then calls writePatients method to update the patient.txt file.
    public void removePatient(int id){
        for(int i = 0 ; i<patientList.size();i++){
            if(patientList.get(i).getId() == id){
                patientList.remove(i);
                writePatients(patientDataPath,false);
                break;
            }
        }




    }

    //This method is used for getting a name ordered version of the patientList arraylsit.

    public ArrayList<Patient> getNameOrderedPatientList(){

        ArrayList<String> names = new ArrayList<>();
        for(int i = 0 ; i<patientList.size();i++){ names.add(patientList.get(i).getName()); }
        Collections.sort(names);

        ArrayList<Patient> nameOrderedPatientList = new ArrayList<>();

        for(int i =0 ; i< names.size();i++){

            for(int j = 0 ; j<patientList.size();j++){


                if(patientList.get(j).getName().equals(names.get(i))){
                    nameOrderedPatientList.add(patientList.get(j));
                }
            }

        }
        return nameOrderedPatientList;

    }

}
