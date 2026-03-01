package co.edu.uptc.model;

import java.util.List;

import co.edu.uptc.model.structures.SimpleList;

public class Facility {
    private String name;
    private FacilityType type;
    private List<Grade> grades;
    

    public Facility(String name, FacilityType type) {
        this.name = name;
        this.type = type;
        this.grades = new SimpleList<>();
    }

    public String getName() {
        return name;
    }
    public String setName(String name) {
        this.name = name;
        return name;
    }
    public FacilityType getType() {
        return type;
    }
    public List<Grade> getGrades() {
        return grades;
    }
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

}
