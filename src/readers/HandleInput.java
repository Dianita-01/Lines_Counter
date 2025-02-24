package readers;
import java.io.File;
import java.util.Scanner;

import files.FileCounter;

public class HandleInput {
    public void processInput(String[] args) {
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
            directoryFileCounter.countLinesInDirectory();
        } else if (file.isFile()) {
            FileCounter fileCounter = new FileCounter(file);
            fileCounter.countLinesInFile();
        } else {
            System.out.println("La ruta proporcionada no es valida.");
        }
    }
}