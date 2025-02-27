package main.java.com.proy.readers;
import java.io.File;
import java.util.List;
import java.util.Scanner;

import main.java.com.proy.files.FileCounter;
import main.java.com.proy.model.CodeSegment;
import main.java.com.proy.model.Directory;


public class HandleInput {
    public void getInput(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (args.length == 0) {
            System.out.print("Introduce la ruta del archivo o directorio: ");
            String path = scanner.nextLine();
            processPath(path);
        } else {
            processPath(args[0]);
        }
    }

    private void processPath(String path) {
        File file = new File(path);
        PrintResults results = new PrintResults();

        if (file.isDirectory()) {
            DirectoryFileCounter directoryFileCounter = new DirectoryFileCounter(file);
            Directory directory = directoryFileCounter.countLinesInDirectory();
            results.printResults(directory);
        } else if (file.isFile()) {
            System.out.printf("%-30s %-30s %-30s %-30s%n", "", "Clase", "Líneas físicas", "Líneas lógicas");
            FileCounter fileCounter = new FileCounter(file);
            fileCounter.countLinesInFile();
            CodeSegment codeSegment = fileCounter.getCodeSegment();
            Directory directory = new Directory("");
            directory.getCodeSegments().add(codeSegment);
            results.printResults(directory);

        } else {
            System.out.println("La ruta proporcionada no es valida.");
        }
    }
    
}
