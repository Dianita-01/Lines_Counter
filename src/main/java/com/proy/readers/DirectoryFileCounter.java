package main.java.com.proy.readers;
import java.io.File;
import java.io.FileNotFoundException;

import main.java.com.proy.files.FileCounter;

public class DirectoryFileCounter {
    private File directory;
    private FileCounter fileCounter;

    public DirectoryFileCounter(File directory){
        setDirectory(directory);
    }

    public void countLinesInDirectory() throws FileNotFoundException {
    
        File[] files = this.directory.listFiles();
        if (doesntHaveFiles(files)) {
            throw new FileNotFoundException("No se pudo leer el directorio :(");
        } else{
            countLinesInFiles(files);
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

    private boolean doesntHaveFiles(File[] files){
        return files == null || files.length == 0;
    }

    private void countLinesInFiles(File[] files) throws FileNotFoundException{
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
}