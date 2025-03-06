package com.proy.validator.concreteValidators;

import java.util.List;

import com.proy.exceptions.CodeStandarException;
import com.proy.validator.validatorContext.CodeValidationContext;
import com.proy.validator.validatorContext.StandardValidator;

public class DoStaticStructureValidator extends StandardValidator{

    public DoStaticStructureValidator(CodeValidationContext codeValidationContext){
        super(codeValidationContext);
    }

     /*
     * Cuenta una línea de código física si la linea o lineas representan una estructura de control y está en el formato
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de control y está en el formato
     * @throws CodeStandarException si es una estrucura control y no está en el formato
     */
    
    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (isTryDoStaticStructure(lines.get(0))) {
            getCodeValidationContext().addLogicalAndPhysicalLine();
            return true;
        } else {
            return false;
        }
    }

    public boolean isTryDoStaticStructure(String line) throws CodeStandarException{
        if (line.trim().startsWith("do ")||line.trim().startsWith("static ")) {
            String tryDoStructure = "(static|do)\\s*\\{\\s*(//.*)?";
            if (matchesPattern(line.trim(), tryDoStructure)) {
                return true;
            } else {
                throw new CodeStandarException("El codigo no cumple el formato de codigo");
            }
        }
        return false;
    }
    
}
