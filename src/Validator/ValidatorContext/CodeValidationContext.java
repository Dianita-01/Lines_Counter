package Validator.ValidatorContext;

import java.util.List;

import exceptions.CodeStandarException;
import model.CodeSegment;

/**
 * La clase "CodeValidationContext" almacena una referencia a uno de los objetos de los validadores concretos
 * CodeValidationContext se comunica con el objeto de StandardValidator
 * @version 1.0
 */


public class CodeValidationContext {

    private StandardValidator standardValidator;
    private CodeSegment codeSegment;

    public CodeValidationContext(){
        this.codeSegment = new CodeSegment();
    }

    public CodeValidationContext(StandardValidator standardValidator){
        this.standardValidator = standardValidator;
        this.codeSegment = new CodeSegment();
    }

    public boolean validate(List<String> lines) throws CodeStandarException{
        return standardValidator.validate(lines);
    }

    public StandardValidator getStandardValidator() {
        return standardValidator;
    }

    public void setStandardValidator(StandardValidator standardValidator) {
        this.standardValidator = standardValidator;
    }

    public CodeSegment getCodeSegment() {
        return codeSegment;
    }

    public void setCodeSegment(CodeSegment codeSegment) {
        this.codeSegment = codeSegment;
    }

    public int getLogicalLines(){
        return this.codeSegment.getLogicalLines();
    }

    public int getPhysicalLines(){
        return this.codeSegment.getPhysicalLines();
    }

    public void addLogicalLine(){
        this.codeSegment.addLogicalLine();
    }

    public void addPhysicalLine(){
        this.codeSegment.addPhysicalLine();
    }

    public void addLogicalAndPhysicalLine(){
        this.codeSegment.addLogicalAndPhysicalLine();
    }

    public void addLogicalLine(int num){
        this.codeSegment.addLogicalLines(num);;
    }

    public void addPhysicalLine(int num){
        this.codeSegment.addPhysicalLines(num);
    }

}