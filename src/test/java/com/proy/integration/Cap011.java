package com.proy.integration;

import java.io.FileNotFoundException;
import com.proy.readers.HandleInput;

public class Cap011 {
    private final String[] PATH = {
        "src\\test\\java\\com\\proy\\integration\\integration_test_resources\\Cap11File.java"
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
        Cap011 cap11 = new Cap011();
        cap11.test();
    }
}