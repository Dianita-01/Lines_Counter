package main.java.com.proy.readers;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.java.com.proy.files.FileCounter;

public class HandleInput {
    
    public void getInput(String[] args) throws FileNotFoundException {
        if (didntReceiveArgs(args)) {
            String path = askForFilepath();
            this.processPath(path);
        } else {
            throw new FileNotFoundException("No se encontró el archivo o directorio con la ruta especificada");
        }
    }

    private void processPath(String path) throws FileNotFoundException{
        File file = new File(path);

        if (file.isDirectory()) {
            DirectoryFileCounter directoryFileCounter = new DirectoryFileCounter(file);
            directoryFileCounter.countLinesInDirectory();
        }    
        else if (file.isFile()) {
            FileCounter fileCounter = new FileCounter(file);
            fileCounter.countLinesInFile();
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
}