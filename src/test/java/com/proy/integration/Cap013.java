package com.proy.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.com.proy.readers.HandleInput;

public class Cap013 {
    private final String[] PATH = {
        "src\\test\\java\\com\\proy\\integration\\integration_test_resources\\InterfacesTest.java"
    };

    public void test(){
        HandleInput reader = new HandleInput();
        reader.processInput(this.PATH);
    }

    public static void main(String[] args) {
        Cap013 cap013 = new Cap013();
        cap013.test();
    }
    
}
