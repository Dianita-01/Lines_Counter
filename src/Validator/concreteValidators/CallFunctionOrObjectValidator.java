package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

public class CallFunctionOrObjectValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public CallFunctionOrObjectValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (isCallFuctionOrObject(lines) ||isIncompleteCallFuctionOrObject(lines)) {
            this.codeValidationContext.addLogicalAndPhysicalLine();
            return true;
        }
        return false;
    }

    private boolean isCallFuctionOrObject(List<String> lines) {
        String structure = "^[\\w\\s*\\.\\s*]+(\\(\\.*\\)\\s*\\.\\s*)*\\s*\\w+\\s*\\(.*\\)\\s*;$";
        return matchesPattern(lines.get(0).trim(), structure);
    }

    private boolean isIncompleteCallFuctionOrObject(List<String> lines) throws CodeStandarException{
        String structure = "^[\\w\\s*\\.\\s*]+(\\(\\.*\\)\\s*\\.\\s*)*\\s*\\w+\\s*\\(.*\\)?\\s*[^;]$";
        if (matchesPattern(lines.get(0).trim(), structure) && !lines.get   (0).trim().endsWith(";")) {
            return findEndOfLine(lines);
        }
        return false;
    }
    
    public boolean findEndOfLine(List<String> lines) throws CodeStandarException{
        lines.remove(0);
        while (lines.size()>0) {
            if (lines.get(0).trim().endsWith(";")) {
                if (lines.get(0).trim().startsWith(";")) throw new CodeStandarException("No se cumple el formato de codigo");
                this.codeValidationContext.addPhysicalLine();
                return true;
            }
            if(lines.size()>0) lines.remove(0);
            this.codeValidationContext.addPhysicalLine();
        }
        if(lines.size()<=0) throw new CodeStandarException("No se cumple el formato de codigo");
        return false;
    }
}
