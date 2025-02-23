import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileCounter {
    public void countLinesInFile(File file) {
        int physicalLines = 0;
        int logicalLines = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                physicalLines++;
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("//") && !line.startsWith("/*") && !line.startsWith("*")) {
                    logicalLines++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + file.getName());
            return;
        }

        System.out.println("Archivo: " + file.getName());
        System.out.println("Lineas fisicas: " + physicalLines);
        System.out.println("Lineas logicas: " + logicalLines);
    }
}