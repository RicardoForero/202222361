package co.edu.uptc.model;



import co.edu.uptc.model.structures.DoubleList;

public class Town {
    private String name;
    private DoubleList<Institute> institutes;
    
    public Town(String name) {
        this.name = name;
        this.institutes = new DoubleList<>();
    }
    public String getName() {
        return name;
    }
    public String setName(String name) {
        this.name = name;
        return name;
    }
    public DoubleList<Institute> getInstitutes() {
        return institutes;
    }
    public void setInstitutes(DoubleList<Institute> institutes) {
        this.institutes = institutes;
    }
}
