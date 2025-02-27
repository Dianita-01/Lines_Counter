package com.proy.readers;
import java.io.File;
import java.io.FileNotFoundException;

import com.proy.files.FileCounter;
import com.proy.model.CodeSegment;
import com.proy.model.Directory;

public class DirectoryFileCounter {
    private File directory;
    private FileCounter fileCounter;

    public DirectoryFileCounter(File directory){
        setDirectory(directory);
    }

    public Directory countLinesInDirectory() throws FileNotFoundException {
        Directory directory=  new Directory(this.directory.getName());
        File[] files = this.directory.listFiles();
        if (doesntHaveFiles(files)) {
            return directory;
        } else{
            countLinesInFiles(files, directory);
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

    private boolean doesntHaveFiles(File[] files){
        return files == null || files.length == 0;
    }

    private Directory countLinesInFiles(File[] files, Directory directory) throws FileNotFoundException{
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
}
