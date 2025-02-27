package com.proy.integration;

import java.io.FileNotFoundException;
import main.java.com.proy.readers.HandleInput;

public class Cap007 {
    private final String[] PATH = {
        "src\\test\\java\\com\\proy\\integration\\integration_test_resources\\Cap07File.java"
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
        Cap007 cap07 = new Cap007();
        cap07.test();
    }
}
