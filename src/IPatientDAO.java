

import java.util.ArrayList;

public interface IPatientDAO {
    ArrayList<Patient> getPatientList();
    void fillPatientArrayList();
    void writePatients(String path , boolean append);
    void addPatient(int id , String name , String surname, String phoneNumber , String address);
    void removePatient(int id);
    ArrayList<Patient> getNameOrderedPatientList();
}
