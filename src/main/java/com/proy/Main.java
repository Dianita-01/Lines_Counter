package main.java.com.proy;
import java.io.FileNotFoundException;

import main.java.com.proy.readers.HandleInput;

public class Main {
    public static void main(String[] args) {
        HandleInput handleInput = new HandleInput();
        try {
            handleInput.getInput(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        while(true){

        }
    }
}