import java.util.ArrayList;

public class Patient {
    private int id;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private ArrayList<Examination> examinations = new ArrayList<Examination>();
    private int totalCost = 0; 

    public Patient(String id, String name, String surname, String phoneNumber, String address) {
        this.id = Integer.valueOf(id);
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public String info() {
        return String.format("%s\t%s %s\t%s\tAddress: %s"
                ,id,name,surname,phoneNumber,address);
    }

    public void setExamination(Examination examination) {
        examinations.add(examination);
    }
    public ArrayList<Examination> getExamination() {
        return examinations;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    } 
    public void setTotalCost(int totalCost) {
        this.totalCost += totalCost;
    }

    public int getTotalCost() {
        return totalCost;
    }
}