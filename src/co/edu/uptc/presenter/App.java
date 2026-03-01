package co.edu.uptc.presenter;

import co.edu.uptc.model.State;
import co.edu.uptc.view.ConsoleView;

public class App {
    private State state;
    private ConsoleView cv;

    public App(){
        this.state = new State();
        this.cv = new ConsoleView();
    }

    public void mainMenu(){
        String option;
        do {
            option = cv.getInput("Select an option:\n1. Admin menu\n2. Search menu\n3. grades menu\n4. Exit");
            switch (option) {
                case "1":
                    adminMenu();
                    continue;
                case "2":
                    searchMenu();
                    continue;
                case "3":
                    gradesMenu();
                    continue;
                case "4":
                    cv.showMessage("Exiting...");
                    break;
                default:
                    cv.showMessage("Invalid option. Please try again.");
            }
        } while (!option.equals("4"));
    }
    public void adminMenu(){
        String option;
        do {
            option = cv.getInput("Select an option:\n1. Add town\n2. Add institute\n3. Add facility\n4. Exit");
            switch (option) {
                case "1":
                    addTown();
                    continue;
                case "2":
                    addInstitute();
                    continue;
                case "3":
                    addFacility();
                    continue;
                case "4":
                    cv.showMessage("Exiting...");
                    break;
                default:
                    cv.showMessage("Invalid option. Please try again.");
            }
        } while (!option.equals("4"));
    }
    public void addTown(){
        String townName = cv.getInput("Enter the town name:");
        state.addTown(townName);
    }
    public void addInstitute(){
        String townName = cv.getInput("Enter the town name:");
        String instituteName = cv.getInput("Enter the institute name:");
        String instituteType = cv.getInput("Enter the institute type: OFICIAL - NO OFICIAL" );
        state.addInstitute(townName, instituteName, instituteType);
    }
    public void addFacility(){
        String townName = cv.getInput("Enter the town name:");
        String instituteName = cv.getInput("Enter the institute name:");
        String facilityName = cv.getInput("Enter the facility name:");
        String facilityType = cv.getInput("Enter the facility type: rural - urban" );
        state.addFacility(townName, instituteName, facilityName, facilityType);
    }
    public void searchMenu(){
        String option;
        do {
            option = cv.getInput("Select an option:\n1. Search town\n2. Search institute\n3. Search facility\n4. Exit");
            switch (option) {
                case "1":
                    searchTown();
                    continue;
                case "2":
                    searchInstitute();
                    continue;
                case "3":
                    searchFacility();
                    continue;
                case "4":
                    cv.showMessage("Exiting...");
                    break;
                default:
                    cv.showMessage("Invalid option. Please try again.");
            }
        } while (!option.equals("4"));
    }
    public void searchTown(){
        String townName = cv.getInput("Enter the town name:");
        cv.showMessage(state.showInstitutes(townName).toString());
    }
    public void searchInstitute(){
        String townName = cv.getInput("Enter the town name:");
        String instituteName = cv.getInput("Enter the institute name:");
        cv.showMessage(state.showFacilities(townName, instituteName).toString());
    }
    public void searchFacility(){
        String townName = cv.getInput("Enter the town name:");
        String instituteName = cv.getInput("Enter the institute name:");
        String facilityName = cv.getInput("Enter the facility name:");
        cv.showMessage(state.showRegistrationsGradesByFacility(townName, instituteName, facilityName).toString());
    }

    public void gradesMenu(){
        String option;
        do {
            option = cv.getInput("Select an option:\n1. Registrations by town \n2. Registrations by institute \n3. Registrations by facility \n4. Exit");
            switch (option) {
                case "1":
                    gradesByTown();
                    continue;
                case "2":
                    gradesByInstitute();
                    continue;
                case "3":
                    gradesByFacility();
                    continue;
                case "4":
                    cv.showMessage("Exiting...");
                    break;
                default:
                    cv.showMessage("Invalid option. Please try again.");
            }
        } while (!option.equals("4"));
    }

    public void gradesByTown(){
        String townName = cv.getInput("Enter the town name:");
        cv.showMessage(state.showRegistrationsGradesByTown(townName).toString());
    }

    public void gradesByInstitute(){
        String townName = cv.getInput("Enter the town name:");
        String instituteName = cv.getInput("Enter the institute name:");
        cv.showMessage(state.showRegistrationsGradesByInstitute(townName, instituteName).toString());
    }

    public void gradesByFacility(){
        String townName = cv.getInput("Enter the town name:");
        String instituteName = cv.getInput("Enter the institute name:");
        String facilityName = cv.getInput("Enter the facility name:");
        cv.showMessage(state.showRegistrationsGradesByFacility(townName, instituteName, facilityName).toString());
     }

}
