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
            if (file.isFile()) {
                FileCounter fileCounter = new FileCounter();
                fileCounter.countLinesInFile(file);
            }
        }
    }
}