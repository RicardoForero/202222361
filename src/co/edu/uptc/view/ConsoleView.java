package co.edu.uptc.view;

import java.util.Scanner;

public class ConsoleView{
    private Scanner scan;
    
    public ConsoleView(){
        this.scan=new Scanner(System.in);
    }
    public void showMessage(String message){
        System.out.println(message);
    }
    public String getInput(String message){
        System.out.println(message);
        return scan.nextLine();
    }
}