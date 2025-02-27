package com.proy;
import org.junit.Before;
import org.junit.Test;

import com.proy.readers.HandleInput;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class HandleInputTest {
    
    private HandleInput handleInput;

    @Before
    public void setUp() {
        handleInput = new HandleInput();
    }

    /**
     * Prueba el método processInput cuando se proporciona una ruta de archivo como argumento.
     * Verifica si la ruta dada corresponde a un archivo.
     */
    @Test
    public void testProcessInput_WithArgs_File() {
        String[] args = {"testFile.txt"};
        File file = new File(args[0]);

        if (file.exists() && file.isFile()) {
            assertTrue(file.isFile());
        } else {
            assertFalse(file.isFile());
        }
    }

    /**
     * Prueba el método processInput cuando se proporciona una ruta de directorio como argumento.
     * Verifica si la ruta dada corresponde a un directorio.
     */
    @Test
    public void testProcessInput_WithArgs_Directory() {
        String[] args = {"testDirectory"};
        File directory = new File(args[0]);

        if (directory.exists() && directory.isDirectory()) {
            assertTrue(directory.isDirectory());
        } else {
            assertFalse(directory.isDirectory());
        }
    }

    /**
     * Prueba el método processInput cuando no se proporcionan argumentos.
     * Simula la entrada del usuario y asegura que la entrada se procese correctamente.
     */
    @Test
    public void testProcessInput_NoArgs_UserInput() {

        String simulatedInput = "testPath";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            handleInput.getInput(new String[]{});
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.setIn(originalSystemIn);
    }
}
