package com.proy;
import java.io.FileNotFoundException;

import com.proy.readers.HandleInput;

public class Main {
    public static void main(String[] args) {
        HandleInput handleInput = new HandleInput();
        try {
            handleInput.getInput(args);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}