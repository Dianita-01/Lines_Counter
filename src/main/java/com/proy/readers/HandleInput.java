package com.proy.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.proy.files.FileCounter;
import com.proy.model.CodeSegment;
import com.proy.model.Directory;

/**
 * La clase HandleInput gestiona la obtención y procesamiento
 * de rutas de archivos o directorios desde los argumentos del programa
 * o ingresadas manualmente por el usuario.
 * @version 1.0
 */
public class HandleInput {

    /**
     * Obtiene y procesa las rutas desde los argumentos del programa
     * o solicita al usuario una ruta si no se proporcionan argumentos.
     * 
     * @param args Argumentos pasados al programa que contienen rutas de archivos o directorios.
     * @throws FileNotFoundException Si no se encuentra el archivo o directorio especificado.
     */
    public void getInput(String[] args) throws FileNotFoundException {
        if (didntReceiveArgs(args)) {
            String path = askForFilepath();
            this.processPath(path);
        } else if (FileReceivedByParameter(args)) {
            for (int i = 0; i < args.length; i++) {
                this.processPath(args[i]);
            }
        } else {
            throw new FileNotFoundException("No se encontró el archivo o directorio con la ruta especificada");
        }
    }

    /**
     * Procesa una ruta dada, ya sea archivo o directorio.
     * Si es un directorio, cuenta las líneas de todos los archivos dentro.
     * Si es un archivo, cuenta las líneas físicas y lógicas.
     * 
     * @param path Ruta del archivo o directorio a procesar.
     * @throws FileNotFoundException Si el archivo o directorio no existe.
     */
    private void processPath(String path) throws FileNotFoundException {
        File file = new File(path);
        if (file.isDirectory()) {
            processDirectory(file);
        } else if (file.isFile()) {
            processFile(file);
        } else {
            System.out.println("La ruta proporcionada no es valida.");
        }
    }

    /**
     * Procesa un directorio dado, contando las líneas de código de los archivos
     * contenidos y mostrando los resultados.
     *
     * @param directoryFile El directorio a procesar.
     * @throws FileNotFoundException Si ocurre un error al acceder a los archivos dentro del directorio.
    */
    private void processDirectory(File directoryFile) throws FileNotFoundException {
        DirectoryFileCounter directoryFileCounter = new DirectoryFileCounter(directoryFile);
        Directory directory = directoryFileCounter.countLinesInDirectory();
        new PrintResults().printResults(directory);
    }
    
        /**
     * Procesa un archivo individual, contando las líneas físicas y lógicas del código
     * y mostrando los resultados.
     *
     * @param file El archivo a procesar.
     * @throws FileNotFoundException Si ocurre un error al acceder al archivo.
     */
    private void processFile(File file) throws FileNotFoundException {
        System.out.printf("%-30s %-30s %-30s %-30s%n", "", "Clase", "Lineas fisicas", "Lineas logicas");
        FileCounter fileCounter = new FileCounter(file);
        fileCounter.countLinesInFile();
        CodeSegment codeSegment = fileCounter.getCodeSegment();
        Directory directory = new Directory("");
        directory.getCodeSegments().add(codeSegment);
        new PrintResults().printResults(directory);
    }

    /**
     * Solicita al usuario que ingrese una ruta de archivo o directorio por consola.
     * 
     * @return La ruta ingresada por el usuario.
     */
    private String askForFilepath() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la ruta del archivo o directorio: ");
        String path = scanner.nextLine();
        scanner.close();
        return path;
    }

    /**
     * Verifica si no se recibieron argumentos.
     * 
     * @param args Argumentos del programa.
     * @return {@code true} si no se recibió ningún argumento, {@code false} en caso contrario.
     */
    private boolean didntReceiveArgs(String[] args) {
        return args.length == 0;
    }

    /**
     * Verifica si se recibió exactamente un argumento.
     * 
     * @param args Argumentos del programa.
     * @return {@code true} si se recibió un solo argumento, {@code false} en caso contrario.
     */
    private boolean FileReceivedByParameter(String[] args) {
        return args.length == 1;
    }
}
