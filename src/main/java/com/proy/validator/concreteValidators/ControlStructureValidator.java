package main.java.com.proy.validator.concreteValidators;

import java.util.List;

import main.java.com.proy.exceptions.CodeStandarException;
import main.java.com.proy.validator.validatorContext.CodeValidationContext;
import main.java.com.proy.validator.validatorContext.StandardValidator;

/**
 * La clase "ControlStructureValidator" proporciona los métodos para validar una formato de estructura de control para poder hacer la suma de lineas lógicas y físicas solo
 * en caso de ser un estructura de estructura de control
 * @version 1.0
 */

public class ControlStructureValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public ControlStructureValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

     /*
     * Cuenta una línea de código física si la linea o lineas representan una estructura de control y está en el formato
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de control y está en el formato
     * @throws CodeStandarException si es una estrucura control y no está en el formato
     */
    
    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (isControlStructure(lines.get(0)) || isIncompleteControlStructure(lines)) {
            if(lines.get(0).trim().startsWith("switch")){
                this.codeValidationContext.addPhysicalLine();
            }else {
                this.codeValidationContext.addLogicalAndPhysicalLine();
            }
            return true;
        } else {
            return false;
        }
    }

    /*
     * Revisa si la linea es una estructura de control
     * 
     * @param line representa la linea de código a validar
     * @return si es una estructura de control completa
     * @throws CodeStandarException si es una estrucura de control y no está en el formato
     */


    private boolean isControlStructure(String line) throws CodeStandarException{
        String structure ="(if|for|switch|while)\\s*\\(.*\\)\\s*\\{?\\s*(//.*)?";
        return matchesPattern(line.trim(), structure);
    }

       /*
     * Revisa si la primera linea del código es una estructura de control incompleta, es decir que ocurrió un salto de línea, 
     * para validar la estructura de control completa
     * 
     * @param lines representa la lineas de código a validar
     * @return si es una estructura de control con salto de línea
     * @throws CodeStandarException si es una estrucura de control y no está en el formato
     */

    private boolean isIncompleteControlStructure(List<String> lines) throws CodeStandarException{
        String onlyWord = "(if|for|switch|while)\\s*(//.*)?";
        String structure = "(if|for|switch|while)\\s*\\(.*\\s*(//.*)?";
        if (matchesPattern(lines.get(0).trim(), onlyWord)) throw new CodeStandarException("No se cumple el formato de codigo");
        if (matchesPattern(lines.get(0).trim(), structure)) {
           return findEndOfLine(lines);
        }
        return false;
    }
        
        /*
     * Revisa las lineas de código hasta encontrar el final de linea de la estrcutrura de control
     * 
     * @param lines representa la lineas de código a validar
     * @return si es una estructura de control con salto de línea con formato correcto
     * @throws CodeStandarException si es una estrucura de control y no está en el formato
     */
    public boolean findEndOfLine(List<String> lines) throws CodeStandarException{
        lines.remove(0);
        String structure ="^.*?\\{\\s*(//.*)?$";
        while (lines.size()>0) {
            if (matchesPattern(lines.get(0).trim(), structure)) {
                if (lines.get(0).trim().startsWith("{")) throw new CodeStandarException("No se cumple el formato de codigo");
                this.codeValidationContext.addPhysicalLine();
                return true;
            }
            if(lines.size()>0) lines.remove(0);
            this.codeValidationContext.addPhysicalLine();
            }
            if(lines.size()<=0) throw new CodeStandarException("No se cumple el formato de codigo");
            return false;
    }
}


