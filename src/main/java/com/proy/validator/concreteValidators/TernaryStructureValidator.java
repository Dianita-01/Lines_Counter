package com.proy.validator.concreteValidators;

import java.util.List;

import com.proy.exceptions.CodeStandarException;
import com.proy.validator.validatorContext.CodeValidationContext;
import com.proy.validator.validatorContext.StandardValidator;

public class TernaryStructureValidator extends StandardValidator{

    public TernaryStructureValidator(CodeValidationContext codeValidationContext){
        super(codeValidationContext);
    }

    /*
     *
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de lambdas y está en el formato
     * @throws CodeStandarException si es una estrucura de lambdas y no está en el formato
     */
    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if(isTernaryStructure(lines.get(0))){
            getCodeValidationContext().addLogicalAndPhysicalLine();
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
    private boolean isTernaryStructure(String line) {
        String TernaryInLine = "^((?:[^\"\']*|\"[^\"]*\"|\'[^\']*\')*)\\?.*\\s*(//.*)?$";
        if (matchesPattern(line.trim(), TernaryInLine)){
           return true;           
        } else {
           return false;
        }
    }
    
}
