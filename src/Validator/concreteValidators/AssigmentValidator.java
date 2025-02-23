package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

public class AssigmentValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public AssigmentValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }
    
    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (isAssigment(lines) || isIncompleteAssigment(lines)) {
            this.codeValidationContext.addLogicalAndPhysicalLine();
            return true; 
        }else {
            return false;
        }
    }

    public boolean isAssigment(List<String> lines) {
        String structure ="^.+\\s*=\\s*.+;";
        return matchesPattern(lines.get(0).trim(), structure);
    }


    public boolean isIncompleteAssigment(List<String> lines) throws CodeStandarException {
        String structure ="^.+\\s*=\\s*.+";
        if (matchesPattern(lines.get(0), structure)) {
            return findEndOfLine(lines);
        }
        return false;
    }

    public boolean findEndOfLine(List<String> lines) throws CodeStandarException{
        if(lines.size()>0) lines.remove(0);
        while (lines.size()>0) {
            if (lines.get(0).trim().endsWith(";")) {
                if (lines.get(0).trim().startsWith(";")) throw new CodeStandarException("No se cumple el formato de codigo");
                this.codeValidationContext.addPhysicalLine();
                return true;
            }
            this.codeValidationContext.addPhysicalLine();
            if(lines.size()>0) lines.remove(0);
        }
        if(lines.size()<=0) throw new CodeStandarException("No se cumple el formato de codigo");
        return false;
        
    }
}
