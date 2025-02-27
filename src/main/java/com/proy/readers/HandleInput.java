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

        if (file.isDirectory()) {
            DirectoryFileCounter directoryFileCounter = new DirectoryFileCounter(file);
            Directory directory = directoryFileCounter.countLinesInDirectory();
            showResults(directory);
        } else if (file.isFile()) {
            System.out.printf("%-30s %-30s %-30s %-30s%n", "", "Clase", "Líneas físicas", "Líneas lógicas");
            FileCounter fileCounter = new FileCounter(file);
            fileCounter.countLinesInFile();
            CodeSegment codeSegment = fileCounter.getCodeSegment();
            Directory directory = new Directory("");
            directory.getCodeSegments().add(codeSegment);
            showResults(directory);

        } else {
            System.out.println("La ruta proporcionada no es valida.");
        }
    }

    private void showResults(Directory directory){
        System.out.printf("%-30s %-30s %-30s %-30s%n", "programa", "archivo", "Líneas físicas", "Líneas lógicas");
        showIndividualResults(directory);
    }

    private void showIndividualResults(Directory directory){
        List<CodeSegment> codeSegments = directory.getCodeSegments();

        if (codeSegments.size()>0) {
            for(int i = 0; i <codeSegments.size(); i++) {
                if (i == 0) {
                    System.out.printf("%-30s %-30s %-30s %-30s%n", directory.getName(), codeSegments.get(i).getTitle(), codeSegments.get(i).getPhysicalLines(), codeSegments.get(i).getLogicalLines());
                    
                }else{
                    System.out.printf("%-30s %-30s %-30s %-30s%n"," ", codeSegments.get(i).getTitle(), codeSegments.get(i).getPhysicalLines(), codeSegments.get(i).getLogicalLines());
                }
                directory.addPhysicalLine(codeSegments.get(i).getPhysicalLines());
                directory.addLogicalLine(codeSegments.get(i).getLogicalLines());
            }
            System.out.printf("%-30s %-30s %-30s %-30s%n", "Total", "", directory.getPysicalLines(), directory.getLogicalLines());
        }

       


        List<Directory> directories = directory.getDirectories();

        if (directories.size()>0) {
            for (int i = 0; i < directories.size(); i++) {
                showIndividualResults(directories.get(i));
            }
        }

    }
}
