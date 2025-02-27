package com.proy.validator.validatorControlers;

import java.util.List;

import com.proy.exceptions.CodeStandarException;
import com.proy.validator.concreteValidators.*;
import com.proy.validator.validatorContext.CodeValidationContext;
import com.proy.validator.validatorContext.StandardValidator;

/**
 * La clase "StructureCountValidator" proporciona los métodos para validar el contenido de una estrucutra de definción para el conteo de líneas
 * @version 1.0
 */

public class StructureCountValidator extends StandardValidator {
    
    CodeValidationContext codeValidationContext;

    public StructureCountValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext =codeValidationContext;
    }

    public StructureCountValidator(){
    }

    /*
     * Valida que se cumplan las reglas de formato para el conteo de líneas físicas y lógicas de todos los validadores concretos.
     * Cuenta una línea física al codeSegment del contexto en el caso que no pertenezca a una línea lógica y que no rompa regla de formato de las líneas físicas
     * 
     * @param lines que representa las lineas de un código java
     * @return si logró validar todas las líneas del contenido de una estructura de definición
     * @throws CodeStandarException si no está en el formato
     */
    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        while (lines.size()>0) {

            if (isStructureDefinition(lines)) throw new CodeStandarException("No se pemite mas de una estructura en el código");
            if (isOrganizationalStructure(lines)) throw new CodeStandarException("No se pemite estructuras organizacionales dentro del código");

            if (isCommentLine(lines.get(0)) || lines.get(0).isBlank()
            || isStructuresCorrectFormat(lines)
            || isPhysicalCorrectFormat(lines) ) {
                if(lines.size()>0) lines.remove(0);
            } else {
                this.codeValidationContext.addPhysicalLine();
               if(lines.size()>0) lines.remove(0);
            }
        }
        return true;
    } 
    
    /*
     * Verifica si las lineas pertecenen a una estrucura y cumple las reglas de acuerdo al formato
     * 
     * @param lines que representa las lineas de un código java
     * @return si pertenece a una estrucura y cumple las reglas de acuerdo al formato
     * @throws CodeStandarException si no está en el formato
     */
    private boolean isStructuresCorrectFormat(List<String> lines) throws CodeStandarException{ 
        if (isLogicalReserverdWord(lines)) return true;
        if (isControlStructure(lines)) return true; 
        if (isFunctionStructure(lines)) return true; 
        if (isCallFunctionOrObject(lines)) return true; 
        if (isFlowControlWord(lines)) return true; 
        if (isLambdaStructure(lines)) return true; 
        if (isAssigmentDefinition(lines)) return true; 
        return false;
    }
    
    /*
     * Verifica si las lineas pertecenen a una estrucura y cumple las reglas de acuerdo al formato cambiando a validadores concretos
     * 
     * @param lines que representa las lineas de un código java
     * @return si pertenece a una estrucura y cumple las reglas de acuerdo al formato
     * @throws CodeStandarException si no está en el formato
     */
    private boolean isCallFunctionOrObject(List<String> lines) throws CodeStandarException{
        this.codeValidationContext.setStandardValidator(new CallFunctionOrObjectValidator(codeValidationContext));
         return this.codeValidationContext.validate(lines) ? true : false;

    }
            
    private boolean isLambdaStructure(List<String> lines) throws CodeStandarException {
        this.codeValidationContext.setStandardValidator(new LambdasValidator(codeValidationContext));
        return this.codeValidationContext.validate(lines) ? true : false;
   }          
    private boolean isPhysicalCorrectFormat(List<String> lines) throws CodeStandarException{
        this.codeValidationContext.setStandardValidator(new PhysicalFormatValidator(codeValidationContext));
         return this.codeValidationContext.validate(lines) ? true : false;
    }

    private boolean isLogicalReserverdWord(List<String> lines) throws CodeStandarException {
        this.codeValidationContext.setStandardValidator(new LogicalReservedWordsValidator(codeValidationContext));
         return this.codeValidationContext.validate(lines) ? true : false;
    }

    private boolean isStructureDefinition(List<String> lines) throws CodeStandarException{
         this.codeValidationContext.setStandardValidator(new StructureDefinitionValidator(codeValidationContext));
         return this.codeValidationContext.validate(lines) ? true : false;
    }

    private boolean isFunctionStructure(List<String> lines) throws CodeStandarException{
        this.codeValidationContext.setStandardValidator(new FunctionStructureValidator(codeValidationContext));
        return this.codeValidationContext.validate(lines) ? true : false;
    }

    private boolean isFlowControlWord(List<String> lines) throws CodeStandarException{
        this.codeValidationContext.setStandardValidator(new FlowControlWordValidator(codeValidationContext));
        return this.codeValidationContext.validate(lines) ? true : false;
    }

    private boolean isAssigmentDefinition(List<String> lines) throws CodeStandarException{
        codeValidationContext.setStandardValidator(new AssigmentValidator(codeValidationContext));
        return codeValidationContext.validate(lines) ? true : false;
    }

    private boolean isControlStructure(List<String> lines) throws CodeStandarException {
        codeValidationContext.setStandardValidator(new ControlStructureValidator(codeValidationContext));
        return codeValidationContext.validate(lines) ? true : false;
    }

    private boolean isOrganizationalStructure(List<String> lines) throws CodeStandarException {
        codeValidationContext.setStandardValidator(new OrganizationalStructureValidator(codeValidationContext));
        return codeValidationContext.validate(lines) ? true : false;
    }

    public void setCodeValidationContext(CodeValidationContext codeValidationContext) {
        this.codeValidationContext = codeValidationContext;
    }

}
