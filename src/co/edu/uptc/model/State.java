package co.edu.uptc.model;

import co.edu.uptc.model.persistance.CsvReader;
import co.edu.uptc.model.structures.SimpleList;
import java.util.List;

public class State {
    private SimpleList<Town> towns;
    private CsvReader reader;

    public State(){
        this.reader = new CsvReader();
        this.towns = (SimpleList<Town>) reader.initTowns();
     }
     public Town searchTown(String name){
        for (int i = 0; i < towns.size(); i++) {
            Town town = towns.get(i);
            if (town.getName().equalsIgnoreCase(name)) {
                return town;
            }
        }
        return null;
     }
     public Institute searchInstitute(String townName,String name){
            Town town = searchTown(townName);
            if (town == null) {
                return null;
            }
            for (int j = 0; j < town.getInstitutes().size(); j++) {
                Institute institute = town.getInstitutes().get(j);
                if (institute.getName().equalsIgnoreCase(name)) {
                    return institute;
                }
            }
        return null;
     }
     public Facility searchFacility(String townName,String instituteName,String name){
        Institute institute = searchInstitute(townName,instituteName);
        if (institute == null) {
            return null;
        }
        for (int k = 0; k < institute.getFacilities().size(); k++) {
            Facility facility = institute.getFacilities().get(k);
            if (facility.getName().equalsIgnoreCase(name)) {
                return facility;
            }
        }
        return null;
     } 
     public Grade searchGrade(String townName,String instituteName,String facilityName,String name){
        Facility facility = searchFacility(townName,instituteName,facilityName);
        if (facility == null) {
            return null;
        }
        for (int l = 0; l < facility.getGrades().size(); l++) {
            Grade grade = facility.getGrades().get(l);
            if (grade.getName().equalsIgnoreCase(name)) {
                return grade;
            }
        }
        return null;
     }
     public void addTown(String name){
        this.towns.add(new  Town(name));
     }
     public void addInstitute(String townName,String instituteName, String instituteType){
        Town town = searchTown(townName);
        if (town != null) {
            town.getInstitutes().add(new Institute(instituteName,(instituteType.equalsIgnoreCase("OFICIAL"))?InstituteType.PUBLIC:InstituteType.PRIVATE));
        }
     }
     public void addFacility(String townName,String instituteName,String facilityName, String facilityType){
        Institute institute = searchInstitute(townName,instituteName);
        if (institute != null) {
            institute.getFacilities().add(new Facility(facilityName,(facilityType.equalsIgnoreCase("RURAL"))?FacilityType.RURAL:FacilityType.URBAN));
        }
     }
     public StringBuilder showTowns(){
        StringBuilder sb = new StringBuilder();
        sb.append("Towns:\n");
        for (int i = 0; i < towns.size(); i++) {
            Town town = towns.get(i);
            sb.append("\n").append(town.getName());
        }
        return sb;
     }

     public StringBuilder showInstitutes(String townName){
        Town town = searchTown(townName);
        StringBuilder sb = new StringBuilder();
        if (town != null) {
            sb.append("Institutes in ").append(town.getName()).append(":\n");
            for (int j = 0; j < town.getInstitutes().size(); j++) {
                Institute institute = town.getInstitutes().get(j);
                sb.append("\n").append(institute.getName()).append(" - ").append(institute.getType());
            }
        } else {
            sb.append("Town not found.");
        }
        return sb;
     }

     public StringBuilder showFacilities(String townName,String instituteName){
        Institute institute = searchInstitute(townName,instituteName);
        StringBuilder sb = new StringBuilder();
        if (institute != null) {
            sb.append("Facilities in ").append(institute.getName()).append(":\n");
            for (int k = 0; k < institute.getFacilities().size(); k++) {
                Facility facility = institute.getFacilities().get(k);
                sb.append("\n").append(facility.getName()).append(" - ").append(facility.getType());
            }
        } else {
            sb.append("Institute not found.");
        }
        return sb;
     }

     public StringBuilder showRegistrationsGradesByTown(String townName){
        Town town = searchTown(townName);
        StringBuilder sb = new StringBuilder();
        List<Grade> grades = new SimpleList();
        reader.getGrades().forEach(gradeName -> grades.add(new Grade(gradeName, 0)));
        if (town != null) {
            sb.append("Registrations in ").append(town.getName()).append(":\n");
            for (int j = 0; j < town.getInstitutes().size(); j++) {
                Institute institute = town.getInstitutes().get(j);
                for (int k = 0; k < institute.getFacilities().size(); k++) {
                    Facility facility = institute.getFacilities().get(k);
                    for (int l = 0; l < grades.size(); l++) {
                        Grade grade = facility.getGrades().get(l);
                        grades.get(l).setNumberOfRegistrations(grades.get(l).getNumberOfRegistrations() + grade.getNumberOfRegistrations());
                      }
                }
            }
            for (int m = 0; m < grades.size(); m++) {
                Grade grade = grades.get(m);
                sb.append("\n").append(grade.getName()).append(": ").append(grade.getNumberOfRegistrations());
            }
        } else {
            sb.append("Town not found.");
        }
        return sb;
     }
     public StringBuilder showRegistrationsGradesByInstitute(String townName,String instituteName){
        Institute institute = searchInstitute(townName,instituteName);
        StringBuilder sb = new StringBuilder();
        List<Grade> grades = new SimpleList();
        reader.getGrades().forEach(gradeName -> grades.add(new Grade(gradeName, 0)));
        if (institute != null) {
            sb.append("Registrations in ").append(institute.getName()).append(":\n");
            for (int k = 0; k < institute.getFacilities().size(); k++) {
                Facility facility = institute.getFacilities().get(k);
                for (int l = 0; l < grades.size(); l++) {
                    Grade grade = facility.getGrades().get(l);
                    grades.get(l).setNumberOfRegistrations(grades.get(l).getNumberOfRegistrations() + grade.getNumberOfRegistrations());
                  }
            }
            for (int m = 0; m < grades.size(); m++) {
                Grade grade = grades.get(m);
                sb.append("\n").append(grade.getName()).append(": ").append(grade.getNumberOfRegistrations());
            }
        } else {
            sb.append("Institute not found.");
        }
        return sb;
     }
     public StringBuilder showRegistrationsGradesByFacility(String townName,String instituteName, String facility){
        Facility facilityAux = searchFacility(townName,instituteName,facility);
        StringBuilder sb = new StringBuilder();
        if (facilityAux != null) {
            sb.append("Registrations in ").append(facilityAux.getName()).append(":\n");
            for (int l = 0; l < facilityAux.getGrades().size(); l++) {
                Grade grade = facilityAux.getGrades().get(l);
                sb.append("\n").append(grade.getName()).append(": ").append(grade.getNumberOfRegistrations());
              }
        } else {
            sb.append("Facility not found.");
        }
        return sb;
     }


     public void setGrade(String townName,String instituteName,String facilityName,String gradeName,int numberOfRegistrations){
        Grade grade = searchGrade(townName,instituteName,facilityName,gradeName);
        if (grade != null) {
            grade.setNumberOfRegistrations(numberOfRegistrations);
        }
     }
     
     public SimpleList<Town> getTowns() {
        return towns;
     }
     public void setTowns(SimpleList<Town> towns) {
        this.towns = towns;
     }


    
    }

    