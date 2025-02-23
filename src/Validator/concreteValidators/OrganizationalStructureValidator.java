package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

public class OrganizationalStructureValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public OrganizationalStructureValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if(isOrganizationalStructure(lines.get(0))){
            this.codeValidationContext.addPhysicalLine();
            return true;
        }else{
            return false;
        }
    }

    private boolean isOrganizationalStructure(String line) throws CodeStandarException {
        String organizationalKeywords = "^(package|import)\\s+[\\w\\.]+\\*?;?$";
        if (matchesPattern(line, organizationalKeywords)){
            if (line.trim().endsWith(";")) {
                return true;
            }
            throw new CodeStandarException("Los imports y package no cumplen el formato de codigo");
        } else {
           return false;
        }
    }

}