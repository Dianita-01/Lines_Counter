package com.proy.validator.concreteValidators;

import java.util.List;

import com.proy.exceptions.CodeStandarException;
import com.proy.validator.validatorContext.CodeValidationContext;
import com.proy.validator.validatorContext.StandardValidator;

/**
 * La clase "LogicalReservedWordsValidator" proporciona los métodos para validar a la línea le pertecene palabras reservadas para poder hacer 
 * la suma de lineas lógicas y físicas solo en caso de ser encontrarse una palabra reservada lógica
 * @version 1.0
 */

public class LogicalReservedWordsValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public LogicalReservedWordsValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    /*
     * Cuenta una línea de código física y lógica si la linea o lineas representan una palabras reservada lógica
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una palabras reservada lógica de y está en el formato
     * @throws CodeStandarException
     */
    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (hasLogicalReserverWords(lines.get(0))) {
            this.codeValidationContext.addLogicalAndPhysicalLine();
            return true;
        }
        return false;
    }
    /*
     * Revisa si la linea es una palabras reservada lógica
     * 
     * @param line representa la linea de código a validar
     * @return si es una palabras reservada lógica
     */
    public boolean hasLogicalReserverWords(String line){
        String reservedKeywords = "^(case|break|continue|return|throw)\\s*.*\\s*(//.*)?$";
        return matchesPattern(line, reservedKeywords) ? true : false;
    }

    
}