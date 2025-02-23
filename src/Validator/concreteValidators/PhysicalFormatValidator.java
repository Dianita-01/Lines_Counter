package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

public class PhysicalFormatValidator extends StandardValidator
{

    private CodeValidationContext codeValidationContext;

    public PhysicalFormatValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (hasOnlyPhysicalLines(lines.get(0))) {
            this.codeValidationContext.addPhysicalLine();
            return true;
        }
        return false;
    }

    public boolean hasOnlyPhysicalLines(String line) throws CodeStandarException{
        if (isCorrectFormat(line)) {
            if (hasCorrectPhysicLine(line) || hasCorrectDeclaration(line) || hasCorrectEnumLit(line)){
                return true;
            }
        }
        return false;
    }

    private boolean isCorrectFormat(String line) throws CodeStandarException{
        String multipleStatements = "\\b[a-zA-Z_]\\w*\\s+[a-zA-Z_]\\w*\\s*(?:,\\s*[a-zA-Z_]\\w*\\s*)+;?";
        if (line.startsWith("{")) throw new CodeStandarException("No se cumple el formato de codigo");
        if (line.startsWith("do")) throw new CodeStandarException("No se cumple el formato de codigo");
        if(matchesPattern(line.trim(), multipleStatements)) throw new CodeStandarException("No se cumple el formato de codigo");
        if(line.trim().startsWith("else") || line.trim().startsWith("finally")) throw new CodeStandarException("No se cumple el formato de codigo");
        if(line.trim().startsWith("}")) return validateMiddleOfFlowControl(line);
        return true;
    }

    private boolean validateMiddleOfFlowControl(String line) throws CodeStandarException{
        if (!line.trim().equals("}")) {
            String structure = "^\\s*\\}\\s*(else|finally)\\s*\\{$";
            if (matchesPattern(line, structure)) return true;
            throw new CodeStandarException("No se cumple el formato de codigo");
        }
        return true;
    }

    private boolean hasCorrectPhysicLine(String line) throws CodeStandarException{
        String anonotation ="^@.+[^\\s]$"; 
        String tryDoStructure = "(try|static)\\s*\\{";
        if(hasCorrestStructure(line, anonotation, "@") || hasCorrestStructure(line, tryDoStructure, "try") |hasCorrestStructure(line, tryDoStructure, "do") ||hasCorrestStructure(line, tryDoStructure, "static"))
            return true;
        return false;
    }

    private boolean hasCorrectDeclaration(String line) throws CodeStandarException{
        String declaration ="^[\\w<>\\[\\]]+\\s+[\\w<>\\[\\]]+\\s*;?$";
        if(matchesPattern(line, declaration)){
            if (line.trim().endsWith(";")) {
                return true;
            }
            throw new CodeStandarException("Las declaraciones no cumplen el formato de codigo");
        }
            
        return false;
    }

    public boolean hasCorrectEnumLit(String line) throws CodeStandarException{
        String enumItems = "\\w+(?:,\\s*\\w+)*?;?";
        if(matchesPattern(line, enumItems)){
            if (!line.trim().endsWith(";")) {
                throw new CodeStandarException("Las declaraciones no cumplen el formato de codigo");
            }
        return true;
        }
        return false;
    }


    public boolean hasCorrestStructure(String line, String pattern, String start) throws CodeStandarException {
        if (line.trim().startsWith(start)){
            if (matchesPattern(line.trim(), pattern)) {
                return true;
            }
            throw new CodeStandarException("Las lineas de código no cumplen el formato");
        } else {
           return false;
        }
    }
    
}
