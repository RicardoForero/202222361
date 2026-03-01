package co.edu.uptc.model;

public class Grade {
    private String name;
    private int numberOfRegistrations;

    public Grade(String name, int numberOfRegistrations) {
        this.name = name;
        this.numberOfRegistrations = numberOfRegistrations;
    }

    public String getName() {
        return name;
    }
    public String setName(String name) {
        this.name = name;
        return name;
    }
    public int getNumberOfRegistrations() {
        return numberOfRegistrations;
    }
    public int setNumberOfRegistrations(int numberOfRegistrations) {
        this.numberOfRegistrations = numberOfRegistrations;
        return numberOfRegistrations;
    }
}
