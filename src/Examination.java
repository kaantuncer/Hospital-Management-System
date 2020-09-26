

public abstract class Examination {

    //This class is for organizing examinations

    private Operation operation;
    private Patient patient;
    private String name;
    private int cost;

    public Examination(Operation operation,Patient patient) {
        this.operation = operation;
        this.patient = patient;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
