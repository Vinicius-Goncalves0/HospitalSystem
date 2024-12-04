package com.hospitalsystem;

import com.hospitalsystem.View.View;

public class Main {
    private static int MAIN_DATABASE = 0;
    
    public static void setTestMode(){
        MAIN_DATABASE = 1;
    }
    public static int getDataBaseMode(){
        return MAIN_DATABASE;
    }
    public static void main(String[] args) throws Exception {
        View view = new View();
        view.displayMainMenu();
    }
}