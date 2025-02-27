package com.proy.readers;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.proy.files.FileCounter;
import com.proy.model.CodeSegment;
import com.proy.model.Directory;

public class HandleInput {
    
    public void getInput(String[] args) throws FileNotFoundException {
        
        if(didntReceiveArgs(args)){
            String path = askForFilepath();
            this.processPath(path);
        }else if(FileReceivedByParameter(args)){
            for(int i = 0; i < args.length; i++){
                this.processPath(args[i]);
            }    
        }else {
            throw new FileNotFoundException("No se encontró el archivo o directorio con la ruta especificada");
        }
    }

    private void processPath(String path) throws FileNotFoundException{
        File file = new File(path);
        PrintResults results = new PrintResults();
        if (file.isDirectory()) {
            DirectoryFileCounter directoryFileCounter = new DirectoryFileCounter(file);
            Directory directory = directoryFileCounter.countLinesInDirectory();
            results.printResults(directory);
        } else if (file.isFile()) {
            System.out.printf("%-30s %-30s %-30s %-30s%n", "", "Clase", "Lineas fisicas", "Lineas logicas");
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

    private String askForFilepath(){        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la ruta del archivo o directorio: ");
        String path = scanner.nextLine();
        scanner.close();
        return path;
    }
    private boolean didntReceiveArgs(String[] args){
        return args.length == 0;
    }

    private boolean FileReceivedByParameter(String[] args){
        return args.length == 1;
    }

}