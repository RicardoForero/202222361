package co.edu.uptc.model;

import co.edu.uptc.model.structures.DoubleList;

public class Institute {
    private String name;
    private InstituteType type;
    private DoubleList<Facility> facilities;

    public Institute(String name, InstituteType type) {
        this.name = name;
        this.type = type;
        this.facilities = new DoubleList<>();
    }

    public String getName() {
        return name;
    }
    public String setName(String name) {
        this.name = name;
        return name;
    }
    public InstituteType getType() {
        return type;
    }
    public InstituteType setType(InstituteType type) {
        this.type = type;
        return type;
    }
    public DoubleList<Facility> getFacilities() {
        return facilities;
    }
    public void setFacilities(DoubleList<Facility> facilities) {
        this.facilities = facilities;
    }
}
