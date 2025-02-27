package main.java.com.proy.readers;
import java.io.File;
import java.io.FileNotFoundException;

import main.java.com.proy.files.FileCounter;
import main.java.com.proy.model.CodeSegment;
import main.java.com.proy.model.Directory;

public class DirectoryFileCounter {
    private File directory;
    private FileCounter fileCounter;

    public DirectoryFileCounter(File directory){
        setDirectory(directory);
    }

    public Directory countLinesInDirectory() {

        Directory directory=  new Directory(this.directory.getName());

        if (!this.directory.isDirectory()) {
            return directory;
        }

        File[] files = this.directory.listFiles();
        if (files == null || files.length == 0) {
            return directory;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                this.fileCounter = new FileCounter(file);
                fileCounter.countLinesInFile();
                CodeSegment codeSegment = fileCounter.getCodeSegment();
                directory.getCodeSegments().add(codeSegment);
            } else if(file.isDirectory()){
                DirectoryFileCounter directoryFileCounter = new DirectoryFileCounter(file);
                directory.getDirectories().add(directoryFileCounter.countLinesInDirectory());
            }
        }

        return directory;
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