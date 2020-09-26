

public class Patient {

    //This class is for storing patient information


    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private String info;

    public Patient(int id, String name, String surname, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;

        info = getId()+"\t" + getName()+" "+getSurname() +"\t"+ getPhoneNumber()+"\t"+ "Address: "+getAddress() +"\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getInfo(){
        return info;
    }
}
