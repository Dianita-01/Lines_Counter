package com.proy.model;

/**
 * La clase "CodeSegment" representa un segmento de código que tienen líneas lógicas y físicas y guarda la cantidad de líneas físicas y lógicas contadas en ese segmento
 * 
 * @version 1.0
 */

public class CodeSegment {
    private int physicalLines;
    private int logicalLines;
    private String title;

    public CodeSegment() {
        this.physicalLines = 0;
        this.logicalLines = 0;
    }

    public CodeSegment(int physicalLines, int logicalLines) {
        this.physicalLines = physicalLines;
        this.logicalLines = logicalLines;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLogicalLines() {
        return logicalLines;
    }

    public void setLogicalLines(int logicalLines) {
        this.logicalLines = logicalLines;
    }

    public int getPhysicalLines() {
        return physicalLines;
    }

    public void setPhysicalLines(int physicalLines) {
        this.physicalLines = physicalLines;
    }

    public void addPhysicalLines(int lines){
        setPhysicalLines(getPhysicalLines()+lines);
    }

    public void addLogicalLines(int lines){
        setLogicalLines(getLogicalLines()+lines);
    }

    public void addLogicalAndPhysicalLines(int lines){
        setPhysicalLines(getPhysicalLines()+lines);
        setLogicalLines(getLogicalLines()+lines);
    }


    public void addPhysicalLine(){
        setPhysicalLines(getPhysicalLines()+1);
    }

    public void addLogicalLine(){
        setLogicalLines(getLogicalLines()+1);
    }

    public void addLogicalAndPhysicalLine(){
        setPhysicalLines(getPhysicalLines()+1);
        setLogicalLines(getLogicalLines()+1);
    }
}
