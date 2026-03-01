package co.edu.uptc.presenter;

import co.edu.uptc.view.ConsoleView;

public class Run {
    public static void main(String[] args){ 
        App app = new App();
        ConsoleView cv = new ConsoleView();
        app.mainMenu();
    }
}
