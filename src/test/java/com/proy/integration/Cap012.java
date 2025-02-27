package com.proy.integration;

import java.io.FileNotFoundException;
import main.java.com.proy.readers.HandleInput;

public class Cap012 {
    private final String[] PATH = {
        "src\\test\\java\\com\\proy\\integration\\integration_test_resources\\Cap12File.java"
    };

    public void test() {
        HandleInput reader = new HandleInput();
        try {
            reader.getInput(this.PATH);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Cap012 cap12 = new Cap012();
        cap12.test();
    }
}
