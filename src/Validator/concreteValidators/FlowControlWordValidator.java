package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

public class FlowControlWordValidator extends StandardValidator{
    
    private CodeValidationContext codeValidationContext;

    public FlowControlWordValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    public boolean validate(List<String> lines) throws CodeStandarException {
        if (hasLogicalFlowControlWords(lines.get(0)) || isIncompleteFlowControlWords(lines)) {
            this.codeValidationContext.addLogicalAndPhysicalLine();
            return true;
        } 
        return false;
    }

    public boolean hasLogicalFlowControlWords(String line) throws CodeStandarException{
        String structure ="^\\}[\\s]*(else if|catch)[\\s]*\\(.*\\)\\s*\\{?"; 
        if (matchesPattern(line.trim(), structure)){
            if (line.trim().endsWith("{")) {
                return true;
            }
            throw new CodeStandarException("Las estructuras de control no cumplen el formato de codigo");
        } else {
           return false;
        }
    }

    private boolean isIncompleteFlowControlWords(List<String> lines) throws CodeStandarException{
        String structure ="^\\}[\\s]*(else if|catch)[\\s]*\\(.*"; 
        if (matchesPattern(lines.get(0).trim(), structure)) {
           return findEndOfLine(lines);
        }
        return false;
    }
        
    public boolean findEndOfLine(List<String> lines) throws CodeStandarException{
        lines.remove(0);
        String structure = ".*\\)\\s*\\{?";
        while (lines.size()>0) {
            if (matchesPattern(lines.get(0).trim(), structure)) {
                    if (lines.get(0).trim().endsWith("{")) {
                        if (lines.get(0).trim().startsWith("{")) throw new CodeStandarException("No se cumple el formato de codigo");
                        this.codeValidationContext.addPhysicalLine();
                        return true;
                    } else {
                        throw new CodeStandarException("Estructura de control invalida");
                    }
            }
                if(lines.size()>0) lines.remove(0);
                this.codeValidationContext.addPhysicalLine();
            }
            if(lines.size()<=0) throw new CodeStandarException("No se cumple el formato de codigo");
            return false;
    }
    
}
