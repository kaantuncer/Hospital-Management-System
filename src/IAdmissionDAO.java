

import java.util.ArrayList;
import java.util.HashMap;

public interface IAdmissionDAO {
    HashMap<Integer, ArrayList<Examination>> getAdmissionHashMap();
    HashMap<Integer,Integer> getAdmissionNumbertoPatientIdHashMap();
    void writeToAdmission(String path,boolean append);
    Examination StringToExaminationObject(String string);
    void fillAdmissionHashmap(ArrayList<Patient> patientList);
    void createAdmission(int admissionNumber , int patientId);
    void removeAdmission(int admissionNumber);
    void addExamination(int admissionNumber , Examination examination);
}
