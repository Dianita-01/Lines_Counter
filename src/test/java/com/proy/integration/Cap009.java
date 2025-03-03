package com.proy.integration;


import java.io.FileNotFoundException;
import com.proy.readers.HandleInput;

public class Cap009 {
    private final String[] PATH = {
        "src\\test\\java\\com\\proy\\integration\\integration_test_resources\\Cap009File.java"
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
        Cap009 cap09 = new Cap009();
        cap09.test();
    }
}
