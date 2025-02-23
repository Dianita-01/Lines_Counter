package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

public class LambdasValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public LambdasValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if(isLambdaStructure(lines.get(0))){
            this.codeValidationContext.addLogicalAndPhysicalLine();
            return true;
        }
        return false;
    }
        
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
