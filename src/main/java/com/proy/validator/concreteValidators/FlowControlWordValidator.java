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

    public FlowControlWordValidator(CodeValidationContext codeValidationContext){
        super(codeValidationContext);
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
            getCodeValidationContext().addPhysicalLine();
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

}
