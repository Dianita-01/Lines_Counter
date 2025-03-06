package com.proy.integration;


import java.io.FileNotFoundException;
import com.proy.readers.HandleInput;

public class Cap010 {
    private final String[] PATH = {
        "src\\test\\java\\com\\proy\\integration\\integration_test_resources\\Cap010File.java"
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
        Cap010 cap10 = new Cap010();
        cap10.test();
    }
}
