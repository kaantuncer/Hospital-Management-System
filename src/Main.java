

public class Main {

    public static void main(String[] args) {
        PatientDAO patientDAO = new PatientDAO("patient.txt");
        AdmissionDAO admissionDAO = new AdmissionDAO("admission.txt",patientDAO.getPatientList());
        OutputManager outputManager = new OutputManager("output.txt",args[0],patientDAO,admissionDAO);
        outputManager.createOutput();

    }
}
