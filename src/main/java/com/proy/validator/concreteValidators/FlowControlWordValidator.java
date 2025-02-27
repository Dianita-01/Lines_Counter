package com.proy.validator.concreteValidators;

import java.util.List;

import com.proy.exceptions.CodeStandarException;
import com.proy.validator.validatorContext.CodeValidationContext;
import com.proy.validator.validatorContext.StandardValidator;

/**
 * La clase "FlowControlWordValidator" proporciona los métodos para validar el formato de los flujos de control de las estructuras de control lógicas
 *  para poder hacer la suma de lineas lógicas y físicas solo en caso de ser un flujo de control de las estructuras de control
 * 
 * @version 1.0
 */

public class FlowControlWordValidator extends StandardValidator{
    
    private CodeValidationContext codeValidationContext;

    public FlowControlWordValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

     /*
     * Cuenta una línea de código física si la linea o lineas representan un flujo de control de las estructuras de control lógicas y está en el formato
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de flujo de control de las estructuras de control y está en el formato
     * @throws CodeStandarException si es una estrucura de flujo de control lógicas de las estructuras de control y no está en el formato
     */

    public boolean validate(List<String> lines) throws CodeStandarException {
        if (hasLogicalFlowControlWords(lines.get(0)) || isIncompleteFlowControlWords(lines)) {
            this.codeValidationContext.addLogicalAndPhysicalLine();
            return true;
        } 
        return false;
    }

        /*
     * Revisa si la linea es un flujo de control de las estructuras de control
     * 
     * @param line representa la linea de código a validar
     * @return si es una flujo de control de las estructuras de control completa
     * @throws CodeStandarException si es una estrucura de flujo de control lógicas de las estructuras de control lógicas y no está en el formato
     */

    public boolean hasLogicalFlowControlWords(String line) throws CodeStandarException{
        String structure ="^\\}[\\s]*(else if|catch)[\\s]*\\(.*\\)\\s*\\{?\\s*(//.*)?"; 
        return matchesPattern(line.trim(), structure);
    }

         /* 
     * Revisa si la primera linea del código es una flujo de control de las estructuras de control lógicas incompleta, es decir que ocurrió un salto de línea, 
     * para validar el flujo de control de las estructuras de control completa
     * 
     * @param lines representa la lineas de código a validar
     * @return si es un flujo de control de las estructuras de control con salto de línea
     * @throws CodeStandarException si es una estrucura de flujo de control lógicas de las estructuras de control y no está en el formato
     */

    private boolean isIncompleteFlowControlWords(List<String> lines) throws CodeStandarException{
        String structure ="^\\}[\\s]*(else if|catch)[\\s]*\\(.*"; 
        if (matchesPattern(lines.get(0).trim(), structure)) {
           return findEndOfLine(lines);
        }
        return false;
    }

       /*
     * Revisa las lineas de código hasta encontrar el final de linea de la flujo de control lógicas de las estructuras de control
     * 
     * @param lines representa la lineas de código a validar
     * @return si es una asignación con salto de línea con formato correcto
     * @throws CodeStandarException si es una estrucura de flujo de control lógicas de las estructuras de control y no está en el formato
     */
        
    public boolean findEndOfLine(List<String> lines) throws CodeStandarException{
        lines.remove(0);
        String structure ="^.*?\\{\\s*(//.*)?$";
        while (lines.size()>0) {
            if (matchesPattern(lines.get(0).trim(), structure)) {
                    if (lines.get(0).trim().startsWith("{")) {
                        if (lines.get(0).trim().startsWith("{")) throw new CodeStandarException("No se cumple el formato de codigo");
                        this.codeValidationContext.addPhysicalLine();
                        return true;
                    }
            }
                if(lines.size()>0) lines.remove(0);
                this.codeValidationContext.addPhysicalLine();
            }
            if(lines.size()<=0) throw new CodeStandarException("No se cumple el formato de codigo");
            return false;
    }
    
}
