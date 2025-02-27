package com.proy.readers;

import java.util.List;

import com.proy.model.CodeSegment;
import com.proy.model.Directory;

public class PrintResults {
    public void printResults(Directory directory){
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-30s %-30s%n", "programa", "archivo", "Lineas fisicas", "Lineas logicas");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        showIndividualResults(directory);
    }

    private void showIndividualResults(Directory directory){
        List<CodeSegment> codeSegments = directory.getCodeSegments();

        if (codeSegments.size()>0) {
            for(int i = 0; i <codeSegments.size(); i++) {
                if(codeSegments.get(i).getTitle() == null) continue;
                if (i == 0) {
                    System.out.printf("%-30s %-30s %-30s %-30s%n", directory.getName(), codeSegments.get(i).getTitle(), codeSegments.get(i).getPhysicalLines(), codeSegments.get(i).getLogicalLines());
                    
                }else{
                    System.out.printf("%-30s %-30s %-30s %-30s%n"," ", codeSegments.get(i).getTitle(), codeSegments.get(i).getPhysicalLines(), codeSegments.get(i).getLogicalLines());
                }
                directory.addPhysicalLine(codeSegments.get(i).getPhysicalLines());
                directory.addLogicalLine(codeSegments.get(i).getLogicalLines());
            }
            System.out.printf("%-30s %-30s %-30s %-30s%n", "Total", "", directory.getPysicalLines(), directory.getLogicalLines());
        }

        List<Directory> directories = directory.getDirectories();

        if (directories.size()>0) {
            for (int i = 0; i < directories.size(); i++) {
                showIndividualResults(directories.get(i));
            }
        }

    }
}
