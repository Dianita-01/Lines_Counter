package validator.validatorControlers;
import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;
import validator.concreteValidators.OrganizationalStructureValidator;
import validator.concreteValidators.StructureDefinitionValidator;

public class FileStructureValidator extends StandardValidator{
    
    private CodeValidationContext codeValidationContext;
    private boolean structureWasFound;

    public FileStructureValidator(){
    }

    public FileStructureValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
        this.structureWasFound = false;
    }

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
            throw new CodeStandarException("El archivo no cumple con una estructura válida.");
        }
    }
    
    public void setStructureWasFound(boolean structureWasFound) {
        this.structureWasFound = structureWasFound;
    }

    public boolean getStructureWasFound() {
        return structureWasFound;
    }   
    
}


