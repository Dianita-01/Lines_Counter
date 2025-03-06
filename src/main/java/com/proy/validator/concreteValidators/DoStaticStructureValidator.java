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

    /**
     * Verifica si una línea de código contiene una estructura do o static
     * seguida de una apertura de bloque de código, y valida si cumple con el formato establecido.
     * Si no se cumple el formato, se lanza una excepción {@code CodeStandarException}.
     * 
     * @param line La línea de código a verificar.
     * @return true si la línea cumple con el formato de {@code do} o {@code static} seguido de una apertura de bloque;
     *         false si la línea no comienza con {@code do} o {@code static}.
     * @throws CodeStandarException Si la línea comienza con {@code do} o {@code static} pero no cumple con el formato esperado.
     */
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
