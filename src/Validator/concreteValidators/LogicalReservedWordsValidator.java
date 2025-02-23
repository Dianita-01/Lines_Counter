package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

public class LogicalReservedWordsValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public LogicalReservedWordsValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (hasLogicalReserverWords(lines.get(0))) {
            this.codeValidationContext.addLogicalAndPhysicalLine();
            return true;
        }
        return false;
    }

    public boolean hasLogicalReserverWords(String line){
        String reservedKeywords = "^(case|break|continue|return|throw)\\s*.*$";
        return matchesPattern(line, reservedKeywords) ? true : false;
    }

    
}