package com.proy.readers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderFile {    

    public List<String> readFileLines(File file) {
        List<String> lines = getLinesOfCode(file);
        return lines;
    }

    private List<String> getLinesOfCode(File file){
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + file.getName());
        }
        return lines;
    }
}