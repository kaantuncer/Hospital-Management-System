

public class Measurements implements Operation {

    //This class is for adding one of the specific operation objects to another operation object

    private String name;
    private int cost;

    public Measurements(Operation operation) {
        super();
        setName(operation.getName()+"measurements ");
        setCost(operation.getCost()+5);

    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCost() {
        return cost;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCost(int cost){
        this.cost=cost;
    }

}
