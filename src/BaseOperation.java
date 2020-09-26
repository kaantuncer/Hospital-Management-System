
public class BaseOperation implements Operation{

    //This class is the first part of every operation. Using this class enables us to use decoretor pattern

    private String name ="";
    private int cost;

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

    public BaseOperation() {
    }
}
