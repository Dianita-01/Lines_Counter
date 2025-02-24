package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

/**
 * La clase "OrganizationalStructureValidator" proporciona los métodos para validar una formato de estructura organizacional para poder hacer la suma de lineas físicas solo
 * en caso de ser una estructura organizacional
 * @version 1.0
 */

public class OrganizationalStructureValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public OrganizationalStructureValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }
    /*
     * Cuenta una línea de código física si la linea o lineas representan una estructura organizacional
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de estructura organizacional
     * @throws CodeStandarException 
     */

    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if(isOrganizationalStructure(lines.get(0))){
            this.codeValidationContext.addPhysicalLine();
            return true;
        }else{
            return false;
        }
    }

    /*
     * Revisa si la linea es una estructura organizacional
     * 
     * @param line representa la linea de código a validar
     * @return si es una estructura organizacional
     */

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