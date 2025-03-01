package com.proy.validator.concreteValidators;

import java.util.List;

import com.proy.exceptions.CodeStandarException;
import com.proy.validator.validatorContext.CodeValidationContext;
import com.proy.validator.validatorContext.StandardValidator;

/**
 * La clase "ControlStructureValidator" proporciona los métodos para validar una formato de estructura de control para poder hacer la suma de lineas lógicas y físicas solo
 * en caso de ser un estructura de estructura de control
 * @version 1.0
 */

public class ControlStructureValidator extends StandardValidator{

    public ControlStructureValidator(CodeValidationContext codeValidationContext){
        super(codeValidationContext);
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
        if (isTryWithoutParenthesis(lines.get(0)) ||isIncorrectStructure(lines.get(0)) || isControlStructure(lines.get(0)) || isIncompleteControlStructure(lines)) {
            getCodeValidationContext().addLogicalAndPhysicalLine();
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
        String structure ="(if|for|switch|while|try)\\s*\\(.*\\)\\s*\\{?\\s*(//.*)?";
        return matchesPattern(line.trim(), structure);
    }

    private boolean isTryWithoutParenthesis(String line){
        String structure ="(try)\\s*\\{\\s*(//.*)?";
        return matchesPattern(line.trim(), structure);
    }

    private boolean isIncorrectStructure(String line) throws CodeStandarException{
        String structure ="(if|for|switch|while|try)\\s*\\(.*\\)\\s*\\{.*\\}\\s*(//.*)?";
        String structure2 ="(if|for|switch|while|try)\\s*\\(.*\\)\\s*[\\w.\\s]+;?\\s*(//.*)?";
        if(matchesPattern(line.trim(), structure) || matchesPattern(line.trim(), structure2)){
            throw new CodeStandarException("No se cumple el formato de codigo de estructuras de control");
        }
        return false;
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
        String onlyWord = "(if|for|switch|while|try)\\s*(//.*)?";
        String structure = "(if|for|switch|while|try)\\s*\\(.*\\s*(//.*)?";
        if (matchesPattern(lines.get(0).trim(), onlyWord)){
            throw new CodeStandarException("No se cumple el formato de codigo de estructuras de control");
        } else if (matchesPattern(lines.get(0).trim(), structure)) {
            return findEndOfLine(lines);
        }
        return false;
    }
        
     
 
}


