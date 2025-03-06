package com.proy.model;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase "Directory" es usada para representar los directorios que contienen archivos en el programa. Proporciona los getters y setters necesarios para acceder a los datos del conteo a través de los CodeSegment asignados al directorio
 * @version 1.0
 */
public class Directory {
    private List<CodeSegment> codeSegments;
    private List<Directory> directories;
    private String name;
    private int physicalLines;
    private int logicalLines;

    public Directory(String name) {
        this.name = name;
        this.codeSegments = new ArrayList<>();
        this.directories = new ArrayList<>();
    }

    public void addCodeSegment(CodeSegment codeSegment) {
        codeSegments.add(codeSegment);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<CodeSegment> getCodeSegments() {
        return codeSegments;
    }

    public void setCodeSegments(List<CodeSegment> codeSegments) {
        this.codeSegments = codeSegments;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Directory> directories) {
        this.directories = directories;
    }

    public int getLogicalLines() {
        return logicalLines;
    }

    public int getPysicalLines() {
        return physicalLines;
    }

    public void addLogicalLine(int lines) {
        this.logicalLines = getLogicalLines() +lines;
    }

    public void addPhysicalLine(int lines) {
        this.physicalLines = getPysicalLines() + lines;
    }

    
}