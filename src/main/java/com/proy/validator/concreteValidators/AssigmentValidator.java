package com.proy.validator.concreteValidators;

import java.util.List;

import com.proy.exceptions.CodeStandarException;
import com.proy.validator.validatorContext.CodeValidationContext;
import com.proy.validator.validatorContext.StandardValidator;

/**
 * La clase "AssigmentValidator" proporciona los métodos para validar una formato de asignación para poder hacer la suma de lineas lógicas y físicas solo
 * en caso de ser un estructura de asignación
 * @version 1.0
 */

public class AssigmentValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public AssigmentValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    /*
     * Cuenta una línea de código física y lógica si la linea o lineas representan una asignación y está en el formato
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de asignación y está en el formato
     * @throws CodeStandarException si es una estrucura de asignación y no está en el formato
     */
    
    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (isAssigment(lines.get(0)) || isIncompleteAssigment(lines)) {
            this.codeValidationContext.addLogicalAndPhysicalLine();
            return true; 
        }else {
            return false;
        }
    }

    /*
     * Revisa si la linea es una asignación
     * 
     * @param line representa la linea de código a validar
     * @return si es una asignación completa
     */

    public boolean isAssigment(String line) {
        String structure ="^.+\\s*=\\s*.+;\\s*(//.*)?";
        
        return matchesPattern(line.trim(), structure);
    }

     /*
     * Revisa si la primera linea del código es una asignación incompleta, es decir que ocurrió un salto de línea, para validar la asignación completa
     * 
     * @param lines representa la lineas de código a validar
     * @return si es una asignación con salto de línea
     * @throws CodeStandarException si es una estrucura de asignación y no está en el formato
     */

    public boolean isIncompleteAssigment(List<String> lines) throws CodeStandarException {
        String structure ="^.+\\s*=\\s*.+";
        if (matchesPattern(lines.get(0), structure)) {
            return findEndOfLine(lines);
        }
        return false;
    }

     /*
     * Revisa las lineas de código hasta encontrar el final de linea de la asignación
     * 
     * @param lines representa la lineas de código a validar
     * @return si es una asignación con salto de línea con formato correcto
     * @throws CodeStandarException si es una estrucura de asignación y no está en el formato
     */

    public boolean findEndOfLine(List<String> lines) throws CodeStandarException{
        if(lines.size()>0) lines.remove(0);
        String structure ="^.*?;\\s*(//.*)?$";
        while (lines.size()>0) {
            if (matchesPattern(lines.get(0).trim(), structure)) {
                if (lines.get(0).trim().startsWith(";")) throw new CodeStandarException("No se cumple el formato de codigo");
                this.codeValidationContext.addPhysicalLine();
                return true;
            }
            this.codeValidationContext.addPhysicalLine();
            if(lines.size()>0) lines.remove(0);
        }
        if(lines.size()<=0) throw new CodeStandarException("No se cumple el formato de codigo de asignación");
        return false;
        
    }
}
