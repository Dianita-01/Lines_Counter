package readers;
import java.io.File;

import files.FileCounter;

public class DirectoryFileCounter {
    public void countLinesInDirectory(File directory) {
        if (!directory.isDirectory()) {
            System.out.println("La ruta proporcionada no es un directorio.");
            return;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("No se pudo leer el directorio.");
            return;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                FileCounter fileCounter = new FileCounter(file);
                fileCounter.countLinesInFile();
            }
        }
    }
}