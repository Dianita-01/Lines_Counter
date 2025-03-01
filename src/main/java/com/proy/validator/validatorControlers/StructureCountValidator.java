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

    public StructureCountValidator(CodeValidationContext codeValidationContext){
        super(codeValidationContext);
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
        if (isCorrectFileStructure(lines)) {
            return true;
        }
        return false;
    }

    private boolean isCorrectFileStructure(List<String> lines) throws CodeStandarException{
        while (lines.size()>0) {
            if (isCommentLine(lines.get(0)) || lines.get(0).isBlank() || isOrganizationalStructure(lines)){
                if(lines.size()>0){
                    lines.remove(0);
                }
            } else if (isStructureDefinition(lines)){
                if(lines.size()>0){
                    lines.remove(0);
                }
                isCorrectContent(lines);
            }else {
                throw new CodeStandarException("El archivo no cumple con una estructura válida.");
            }
        }
        return true;
    }

    private boolean isCorrectContent(List<String> lines) throws CodeStandarException {
        while (lines.size()>0) {
            if (isStructureDefinition(lines)){
                throw new CodeStandarException("No se pemite mas de una estructura en un archivo");
            } else if (isCommentLine(lines.get(0)) || lines.get(0).isBlank() || isStructuresCorrectFormat(lines)) {
                if(lines.size()>0){
                    lines.remove(0);
                }
            } else {
                getCodeValidationContext().addPhysicalLine();
                if(lines.size()>0){
                    lines.remove(0);
                }
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
        if (isControlStructure(lines) || isFunctionStructure(lines) ||isTryDoStatic(lines)||isFlowControlWord(lines)||isLambdaStructure(lines)||isTernaryStructure(lines)){
            return true;
        } else if(isPhysicalCorrectFormat(lines)){
            return true;
        }
        return false;
    }
    
    /*
     * Verifica si las lineas pertecenen a una estrucura y cumple las reglas de acuerdo al formato cambiando a validadores concretos
     * 
     * @param lines que representa las lineas de un código java
     * @return si pertenece a una estrucura y cumple las reglas de acuerdo al formato
     * @throws CodeStandarException si no está en el formato
     */                 
    private boolean isPhysicalCorrectFormat(List<String> lines) throws CodeStandarException{
        getCodeValidationContext().setStandardValidator(new PhysicalFormatValidator(getCodeValidationContext()));
         return getCodeValidationContext().validate(lines) ? true : false;
    }

    private boolean isStructureDefinition(List<String> lines) throws CodeStandarException{
        getCodeValidationContext().setStandardValidator(new StructureDefinitionValidator(getCodeValidationContext()));
         return getCodeValidationContext().validate(lines) ? true : false;
    }

    private boolean isTryDoStatic(List<String> lines) throws CodeStandarException{
        getCodeValidationContext().setStandardValidator(new DoStaticStructureValidator(getCodeValidationContext()));
         return getCodeValidationContext().validate(lines) ? true : false;
    }

    private boolean isFunctionStructure(List<String> lines) throws CodeStandarException{
        getCodeValidationContext().setStandardValidator(new FunctionStructureValidator(getCodeValidationContext()));
        return getCodeValidationContext().validate(lines) ? true : false;
    }

    private boolean isFlowControlWord(List<String> lines) throws CodeStandarException{
        getCodeValidationContext().setStandardValidator(new FlowControlWordValidator(getCodeValidationContext()));
        return getCodeValidationContext().validate(lines) ? true : false;
    }

    private boolean isControlStructure(List<String> lines) throws CodeStandarException {
        getCodeValidationContext().setStandardValidator(new ControlStructureValidator(getCodeValidationContext()));
        return getCodeValidationContext().validate(lines) ? true : false;
    }

    private boolean isOrganizationalStructure(List<String> lines) throws CodeStandarException {
        getCodeValidationContext().setStandardValidator(new OrganizationalStructureValidator(getCodeValidationContext()));
        return getCodeValidationContext().validate(lines) ? true : false;
    }

    private boolean isLambdaStructure(List<String> lines) throws CodeStandarException {
        getCodeValidationContext().setStandardValidator(new LambdasValidator(getCodeValidationContext()));
        return getCodeValidationContext().validate(lines) ? true : false;
    }

    private boolean isTernaryStructure(List<String> lines) throws CodeStandarException {
        getCodeValidationContext().setStandardValidator(new TernaryStructureValidator(getCodeValidationContext()));
        return getCodeValidationContext().validate(lines) ? true : false;
    }

    public void setCodeValidationContext(CodeValidationContext codeValidationContext) {
        setCodeValidationContext(codeValidationContext);
    }
}
