
public class OutpatientExamination extends Examination {

    //This class is for creating one of the specific examination types

    public OutpatientExamination(Operation operation,Patient patient) {
        super(operation,patient);
        setCost(operation.getCost()+15);
        setName("Outpatient\t"+operation.getName());
    }
}
