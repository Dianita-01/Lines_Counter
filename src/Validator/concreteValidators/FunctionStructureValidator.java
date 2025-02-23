package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

public class FunctionStructureValidator extends StandardValidator{
    
    private CodeValidationContext codeValidationContext;

    public FunctionStructureValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (isFunction(lines.get(0)) || isIncompleteFunction(lines)) {
            this.codeValidationContext.addPhysicalLine();
            return true;
        } else  {
            return false;
        }

    }

    private boolean isFunction(String line) throws CodeStandarException{
        String structureFunction ="^(\\w+\\s+)+\\w+\\s*\\(.*\\)\\s*.*\\{?";
        String structureStaticFunction ="^(\\w+\\s+)+\\w+\\s*\\(.*\\)\\s*.*\\;?";
        if (matchesPattern(line.trim(), structureFunction) || matchesPattern(line.trim(), structureStaticFunction)){
            if (line.trim().endsWith("{") || line.trim().endsWith(";")) {
                return true;
            }
            throw new CodeStandarException("Las funciones no cumplen con el formato de código");
        } else {
           return false;
        }
    }

    private boolean isIncompleteFunction(List<String> lines) throws CodeStandarException{
        String structure = "(\\w+\\s+)+\\w+\\s*\\(.*\\s*";
        if (matchesPattern(lines.get(0).trim(), structure)) {
           return findEndOfLine(lines);
        }
        return false;
    }
        
    public boolean findEndOfLine(List<String> lines) throws CodeStandarException{
        lines.remove(0);
        String structureFunction = ".*\\)\\s*.*\\{?";
        String structureStaticFunction = ".*\\)\\s*.*\\;?";
        while (lines.size()>0) {
            if (matchesPattern(lines.get(0).trim(), structureFunction) || matchesPattern(lines.get(0).trim(), structureStaticFunction)) {
                    if (lines.get(0).trim().endsWith("{") || lines.get(0).trim().endsWith(";")) {
                        if (lines.get(0).trim().startsWith("{") || lines.get(0).trim().startsWith(";")) throw new CodeStandarException("No se cumple el formato de codigo");
                        this.codeValidationContext.addPhysicalLine();
                        return true;
                    } else {
                        throw new CodeStandarException("Estructura de control invalida");
                    }
            }
                if(lines.size()>0) lines.remove(0);
                this.codeValidationContext.addPhysicalLine();
        }
        if(lines.size()<0) throw new CodeStandarException("No se cumple el formato de codigo");
        return false;
    }
    
}
