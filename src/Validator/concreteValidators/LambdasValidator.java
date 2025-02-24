package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

/**
 * La clase "LambdasValidator" proporciona los métodos para validar una formato de lambdas para poder hacer la suma de lineas lógicas y físicas solo
 * en caso de ser un estructura de lambdas
 * @version 1.0
 */

public class LambdasValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public LambdasValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    /*
     * Cuenta una línea de código física y lógica si la linea o lineas representan una lambda y está en el formato
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de lambdas y está en el formato
     * @throws CodeStandarException si es una estrucura de lambdas y no está en el formato
     */
    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if(isLambdaStructure(lines.get(0))){
            this.codeValidationContext.addLogicalAndPhysicalLine();
            return true;
        }
        return false;
    }
        
    /*
     * Revisa si la linea es una lambda
     * 
     * @param line representa la linea de código a validar
     * @return si es una lambda completa
     */
    private boolean isLambdaStructure(String line) {
        String lambdaInLine = "^\\s*\\(\\.\\)\\s*->\\s*\\.\\s*$"; //noprobado
        String lambdaBlock = "^\\s*\\(\\.\\)\\s*->\\s*\\{*$"; 
        if (matchesPattern(line, lambdaBlock) || matchesPattern(line, lambdaInLine)){
           return true;           
        } else {
           return false;
        }
    }
    
}
