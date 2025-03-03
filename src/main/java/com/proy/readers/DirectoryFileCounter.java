package com.proy.readers;
import java.io.File;
import java.io.FileNotFoundException;

import com.proy.files.FileCounter;
import com.proy.model.CodeSegment;
import com.proy.model.Directory;

/**
 * La clase {@code DirectoryFileCounter} se encarga de contar las líneas de código 
 * en archivos Java dentro de un directorio y sus subdirectorios.
 * Recorre recursivamente todos los archivos y subdirectorios, utilizando 
 * FileCounter para contar las líneas de cada archivo Java 
 * @version 1.0
 */
public class DirectoryFileCounter {
    private File directory;
    private FileCounter fileCounter;

    /**
     * Constructor que inicializa el contador con el directorio especificado.
     * 
     * @param directory el directorio raíz donde se buscarán los archivos Java.
     */
    public DirectoryFileCounter(File directory){
        setDirectory(directory);
    }

    /**
     * Recorre el directorio y cuenta las líneas de todos los archivos Java (.java)
     * encontrados, incluyendo los de subdirectorios.
     * 
     * @return Directory que contiene los segmentos de código y 
     *         subdirectorios con sus respectivos conteos.
     * @throws FileNotFoundException si algún archivo no puede ser encontrado al intentar contar sus líneas.
     */
    public Directory countLinesInDirectory() throws FileNotFoundException {
        Directory directory = new Directory(this.directory.getName());
        File[] files = this.directory.listFiles();
        if (doesntHaveFiles(files)) {
            return directory;
        } else {
            countLinesInFiles(files, directory);
        }
        return directory;
    }

    /**
     * Establece el directorio a analizar.
     * 
     * @param directory el nuevo directorio raíz.
     */
    public void setDirectory(File directory){
        this.directory = directory;
    }

    /**
     * Obtiene el directorio que se está analizando.
     * 
     * @return el directorio actual.
     */
    public File getDirectory(){
        return this.directory;
    }

    /**
     * Obtiene el contador de líneas utilizado para el último archivo procesado.
     * 
     * @return el objeto {@code FileCounter}.
     */
    public FileCounter getFileCounter(){
        return this.fileCounter;
    }

    /**
     * Verifica si el arreglo de archivos está vacío o es nulo.
     * 
     * @param files el arreglo de archivos a verificar.
     * @return {@code true} si no hay archivos; {@code false} en caso contrario.
     */
    private boolean doesntHaveFiles(File[] files){
        return files == null || files.length == 0;
    }

    /**
     * Recorre los archivos del directorio, contando las líneas de los archivos
     * Java y procesando recursivamente los subdirectorios.
     * 
     * @param files los archivos del directorio actual.
     * @param directory el objeto {@code Directory} donde se almacenarán los resultados.
     * @return el objeto {@code Directory} con los conteos actualizados.
     * @throws FileNotFoundException si algún archivo no puede ser encontrado.
     */
    private Directory countLinesInFiles(File[] files, Directory directory) throws FileNotFoundException {
        for (File file : files) {
            if (fileIsValid(file)) {
                processFile(file, directory);
            } else if(file.isDirectory()){
                processDirectory(file, directory);
            }   
        }
        return directory;
    }

    private boolean fileIsValid(File file){
        return file.isFile() && file.getName().endsWith(".java");
    }

    private void setFileCounter(FileCounter fileCounter){
        this.fileCounter = fileCounter;
    }

    private void processFile(File file, Directory directory){
        setFileCounter(new FileCounter(file));
        this.fileCounter.countLinesInFile();
        CodeSegment codeSegment = fileCounter.getCodeSegment();
        directory.getCodeSegments().add(codeSegment);
    }

    private void processDirectory(File file, Directory directory) throws FileNotFoundException{
        DirectoryFileCounter directoryFileCounter = new DirectoryFileCounter(file);
        directory.getDirectories().add(directoryFileCounter.countLinesInDirectory());
    }
}
