package co.edu.uptc.model.persistance;

import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import co.edu.uptc.model.Town;
import co.edu.uptc.model.structures.DoubleList;
import co.edu.uptc.model.structures.SimpleList;
import co.edu.uptc.model.InstituteType;
import co.edu.uptc.model.Facility;
import co.edu.uptc.model.FacilityType;
import co.edu.uptc.model.Grade;
import co.edu.uptc.model.Institute;



public class CsvReader {
       private final String PATH = "Files\\Matrícula_Instituciones_Educativas_oficiales_y_no_oficiales_-_DEPARTAMENTO_DE_BOYACÁ_20260301.csv";
       private final String splitRegex = ",";
       private final String YEAR_REQUIRED = "2.022";
       private BufferedReader br;
       private String line;
       private List<String> grades;

    public CsvReader(){
           try {
               br = new BufferedReader(new FileReader(PATH));
               grades = initGradeList();
           } catch (FileNotFoundException ex) {
               System.getLogger(CsvReader.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
           }

    }

     public List<Town> initTowns(){
         List<Town> towns = new SimpleList<Town>();
         Boolean exit = false;
        try{
            line = br.readLine();
            while ((line != null)&&(!exit)) {
                String[] col = line.split(splitRegex);
                String townString = col[1].split("\"")[1];
                String yearString = col[0].split("\"")[1];
                Boolean exist = false;
                exit = (!yearString.equalsIgnoreCase(YEAR_REQUIRED));
                Town townAux = new Town(townString);
                    if (towns.isEmpty()) {
                        towns.add(townAux);
                        townAux.setInstitutes((DoubleList<Institute>) initInstitutes(line, townAux));
                    } else{
                        int i=0;
                        while ((i < towns.size())) {
                            if (towns.get(i).getName().equals(townString)) {
                                exist= true;  
                                townAux = towns.get(i);
                        }
                        i++;
                        }
                        if (!exist) {
                            towns.add(townAux);
                            townAux.setInstitutes((DoubleList<Institute>) initInstitutes(line, townAux));
                        }else{
                            townAux.setInstitutes((DoubleList<Institute>) initInstitutes(line, townAux));
                        }
                        } 
                         line = br.readLine(); 
                        }
                        
            } catch (IOException e) {
            e.printStackTrace();
        }
        return towns;
    }
    public List<Institute> initInstitutes(String line, Town Town ){
        String[] strings = line.split(splitRegex);
        String instituteString = strings[3].split("\"")[1];
        String typeString = strings[7].split("\"")[1];
        InstituteType type = typeString.equalsIgnoreCase("OFICIAL")? InstituteType.PUBLIC: InstituteType.PRIVATE;
        Institute InstituteAux = new Institute(instituteString,type);
        DoubleList<Institute> institutes = (DoubleList<Institute>) Town.getInstitutes();
        Boolean exist = false;
        if (institutes.isEmpty()) {
                        institutes.add(InstituteAux);
                            initFacilities(line, InstituteAux);
                    } else{
                        int i=0;
                        while ((i < institutes.size())) {
                            if (institutes.get(i).getName().equals(instituteString)) {
                                exist= true;  
                                InstituteAux = institutes.get(i);
                        }
                        i++;
                        }
                        if (!exist) {
                            institutes.add(InstituteAux);
                            initFacilities(line, InstituteAux);
                        }else{
                            initFacilities(line, InstituteAux);
                        }
                        } 
        return institutes;
    }
    public List<Facility> initFacilities(String line, Institute Institute ){
        String[] strings = line.split(splitRegex);
        String facilityString = strings[5].split("\"")[1];
        String typeString = strings[6].split("\"")[1];
        FacilityType type = typeString.equalsIgnoreCase("RURAL")? FacilityType.RURAL: FacilityType.URBAN;
        Facility FacilityAux = new Facility(facilityString,type);
        List<Facility> facilities = (List<Facility>) Institute.getFacilities();
        Boolean exist = false;
        if (facilities.isEmpty()) {
                        facilities.add(FacilityAux);
                            initGrades(FacilityAux, line);
                    } else{
                        int i=0;
                        while ((i < facilities.size())) {
                            if (facilities.get(i).getName().equals(facilityString)) {
                                exist= true;  
                                FacilityAux = facilities.get(i);
                        }
                        i++;
                        }
                        if (!exist) {
                            facilities.add(FacilityAux);
                            initGrades(FacilityAux, line);
                        }else{
                            initGrades(FacilityAux, line);
                        }
                        } 
        return facilities;
    }
    public void initGrades(Facility facility, String stringGrades){
        List<Grade> gradesAux = facility.getGrades();
        String[] col = stringGrades.split(splitRegex);
        int a = 0;
                     for (int j=8;j<20;j++) {
                        int aux;
                        if ("".equals(col[j])){
                            aux=0;
                        }else{
                            aux = Integer.parseInt(col[j].split("\"")[1]);
                        }
                        String grade = grades.get(a);
                        a++;
                        gradesAux.add(new Grade(grade, aux));
                    }
        }

     public List<String> initGradeList() {
         List<String> grades = new SimpleList<String>();
        try{
            line = br.readLine();
                String[] col = line.split(splitRegex);
                     for (int j=8;j<20;j++) {
                        grades.add(col[j].split("\"")[1]);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grades;
    }
    public List<String> getGrades(){
        return grades;
    }


}
