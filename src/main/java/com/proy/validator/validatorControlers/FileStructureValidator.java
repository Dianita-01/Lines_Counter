package com.proy.validator.validatorControlers;
import java.util.List;

import com.proy.exceptions.CodeStandarException;
import com.proy.validator.concreteValidators.OrganizationalStructureValidator;
import com.proy.validator.concreteValidators.StructureDefinitionValidator;
import com.proy.validator.validatorContext.CodeValidationContext;
import com.proy.validator.validatorContext.StandardValidator;

/**
 * La clase "FileStructureValidator" proporciona los métodos para validar un archivo cuente con la estrucura de un archivo como lo descrito en el formato para después validar su contenido
 * Un archivo solo debe tener una estructura de definición y las estructuras organizacionales deben estár sobre la estructura de definción
 * @version 1.0
 */

public class FileStructureValidator extends StandardValidator{
    
    private CodeValidationContext codeValidationContext;
    private boolean structureWasFound;

    public FileStructureValidator(){
    }

    public FileStructureValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
        this.structureWasFound = false;
    }

     /*
     * Valida que haya una estructura de definición y que las estructuras organizacionales estén sobre la estructura de definción para el conteo de líneas
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de definición y está en el formato
     * @throws CodeStandarException si no está en el formato
     */
    @Override
    
    public boolean validate(List<String> lines) throws CodeStandarException {
        while (lines.size()>0) {
            if (isCommentLine(lines.get(0)) || lines.get(0).isBlank() || isOrganizationalStructure(lines)){
                lines.remove(0);
            } else if (isStructureDefinition(lines)){
               continue;
            }else{
                throw new CodeStandarException("El archivo no cumple con una estructura válida.");
            }
        }
        return true;
    }

     /*
     * Valida que el formato de una estructura organizacional 
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de organización y está en el formato
     * @throws CodeStandarException si no está en el formato
     */
    private boolean isOrganizationalStructure(List<String> lines) throws CodeStandarException {
        this.codeValidationContext.setStandardValidator(new OrganizationalStructureValidator(this.codeValidationContext));
        return this.codeValidationContext.validate(lines);
    }

    private boolean isStructureDefinition(List<String> lines) throws CodeStandarException {
        this.codeValidationContext.setStandardValidator(new StructureDefinitionValidator(codeValidationContext));
        if (this.codeValidationContext.validate(lines)) {
            lines.remove(0);
            this.codeValidationContext.setStandardValidator(new StructureCountValidator(this.codeValidationContext));
            return this.codeValidationContext.validate(lines);
        }else{
            throw new CodeStandarException("El archivo no cumple con una estructura válida de definición.");
        }
    }
    
    /*
     * Asigna si la estructura de definciión ha sido encontrada
     * 
     * @param lines que representa las lineas de un código java
     */
    public void setStructureWasFound(boolean structureWasFound) {
        this.structureWasFound = structureWasFound;
    }

    public boolean getStructureWasFound() {
        return structureWasFound;
    }   
    
}


