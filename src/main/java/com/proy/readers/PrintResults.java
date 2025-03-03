package com.proy.readers;

import java.util.List;

import com.proy.model.CodeSegment;
import com.proy.model.Directory;

/**
 * La clase se encarga de imprimir en consola
 * los resultados del conteo de líneas físicas y lógicas de código
 * de archivos dentro de un directorio, así como los resultados acumulados
 * de subdirectorios de manera recursiva.
 *
 * @version 1.0
 */
public class PrintResults {

    /**
     * Imprime en consola el encabezado de la tabla y los resultados
     * individuales de un directorio, incluyendo archivos y subdirectorios.
     *
     * @param directory El objeto Directory que contiene los resultados
     *                  del conteo de líneas a imprimir.
     */
    public void printResults(Directory directory) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-35s %-40s %-20s %-20s%n", "programa", "archivo", "Lineas fisicas", "Lineas logicas");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        showIndividualResults(directory);
    }

    /**
     * Imprime los resultados individuales de cada archivo dentro del directorio,
     * sumando las líneas físicas y lógicas al total del directorio.
     * También procesa recursivamente los subdirectorios que contiene.
     *
     * @param directory El objeto Directory del cual se mostrarán los resultados
     *                  de sus archivos y subdirectorios.
     */
    private void showIndividualResults(Directory directory) {
        List<CodeSegment> codeSegments = directory.getCodeSegments();

        if (codeSegments.size() > 0) {
            for (int i = 0; i < codeSegments.size(); i++) {
                if (codeSegments.get(i).getTitle() == null) {
                    continue;
                } else if (i == 0) {
                    System.out.printf("%-35s %-40s %-20s %-20s%n", directory.getName(),
                            codeSegments.get(i).getTitle(),
                            codeSegments.get(i).getPhysicalLines(),
                            codeSegments.get(i).getLogicalLines());

                } else {
                    System.out.printf("%-35s %-40s %-20s %-20s%n", " ",
                            codeSegments.get(i).getTitle(),
                            codeSegments.get(i).getPhysicalLines(),
                            codeSegments.get(i).getLogicalLines());
                }
                directory.addPhysicalLine(codeSegments.get(i).getPhysicalLines());
                directory.addLogicalLine(codeSegments.get(i).getLogicalLines());
            }
            if (directory.getPysicalLines() > 0) {
                System.out.printf("%-35s %-40s %-20s %-20s%n", "Total", "", directory.getPysicalLines(), directory.getLogicalLines());
            }
        }

        List<Directory> directories = directory.getDirectories();

        if (directories.size() > 0) {
            for (int i = 0; i < directories.size(); i++) {
                showIndividualResults(directories.get(i));
            }
        }
    }
}

