

public class InpatientExamination extends Examination {

    //This class is for creating one of the specific examination types

    public InpatientExamination(Operation operation,Patient patient) {
        super(operation,patient);
        setCost(operation.getCost()+10);
        setName("Inpatient\t"+operation.getName());
    }
}
