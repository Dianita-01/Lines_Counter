package main.java.com.proy.readers;
import java.io.File;

import main.java.com.proy.files.FileCounter;

public class DirectoryFileCounter {
    private File directory;
    private FileCounter fileCounter;

    public DirectoryFileCounter(File directory){
        setDirectory(directory);
    }

    public void countLinesInDirectory() {
        if (!this.directory.isDirectory()) {
            System.out.println("La ruta proporcionada no es un directorio.");
            return;
        }

        File[] files = this.directory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No se pudo leer el directorio.");
            return;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                this.fileCounter = new FileCounter(file);
                fileCounter.countLinesInFile();
            } else if(file.isDirectory()){
                DirectoryFileCounter directoryFileCounter = new DirectoryFileCounter(file);
                directoryFileCounter.countLinesInDirectory();
            }
        }
    }

    public void setDirectory(File directory){
        this.directory = directory;
    }

    public File getDirectory(){
        return this.directory;
    }

    public FileCounter getFileCounter(){
        return this.fileCounter;
    }
}