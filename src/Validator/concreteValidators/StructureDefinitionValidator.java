package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

public class StructureDefinitionValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public StructureDefinitionValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (hasInterfaceOrEnumDefinition(lines) || hasClassDefinition(lines) || hasRecordDefinition(lines) || hasNestedDefinition(lines.get(0))){
            this.codeValidationContext.addPhysicalLine();
            return true;
        } else {
            return false;
        }
    }

    public boolean hasInterfaceOrEnumDefinition(List<String> lines) throws CodeStandarException {
        String structureKeywords = "^\\b(?:\\w+\\s)+\\s*(?:interface|enum)\\s\\w+(\\s+\\w+)*\\s*\\{?$";
        return hasCorrestStructure(lines, structureKeywords);
    }

    public boolean hasClassDefinition(List<String> lines) throws CodeStandarException {
        String structureKeywords = "\\w+(\\s+\\w+)*\\s+class\\s+\\w+(\\s+\\w+)*\\s*\\{?";
        return hasCorrestStructure(lines, structureKeywords);
    } 

    public boolean hasRecordDefinition(List<String> lines) throws CodeStandarException {
        String structureKeywords = "\\b\\w+(?:\\s+\\w+)*\\s+record\\s*\\([^)]*\\)(?:\\s+\\w+(?:\\s+\\w+)*)?(?:\\s+[^{]+)?\\s*\\{?";
        return hasCorrestStructure(lines, structureKeywords);
    }

    public boolean hasNestedDefinition(String line) throws CodeStandarException {
        if(line.trim().startsWith("class") ||line.trim().startsWith("interface")||line.trim().startsWith("enum"))
        throw new CodeStandarException("No se pemite estructuras de control anidadas en el código");
        return false;
    }

    public boolean hasCorrestStructure(List<String> lines, String pattern) throws CodeStandarException {
        if (matchesPattern(lines.get(0), pattern)){
            if (!lines.get(0).trim().endsWith("{")){
                return findEndOfLine(lines);
            }
            return true;
        } else {
           return false;
        }
    }

    public boolean findEndOfLine(List<String> lines) throws CodeStandarException{
        lines.remove(0);
        while (lines.size()>0) {
            if (lines.get(0).trim().endsWith("{")) {
                if (lines.get(0).trim().startsWith("{")) throw new CodeStandarException("No se cumple el formato de codigo");
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
