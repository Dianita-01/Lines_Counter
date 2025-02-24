package Validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import Validator.ValidatorContext.CodeValidationContext;
import Validator.ValidatorContext.StandardValidator;

/**
 * La clase "FunctionStructureValidator" proporciona los métodos para validar una formato de firmas de funciones para poder hacer la suma de lineas físicas solo
 * en caso de ser un estructura de función
 * @version 1.0
 */

public class FunctionStructureValidator extends StandardValidator{
    
    private CodeValidationContext codeValidationContext;

    public FunctionStructureValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

       /*
     * Cuenta una línea de código física si la linea o lineas representan una función y está en el formato
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de función y está en el formato
     * 
     */
    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (isFunction(lines.get(0)) || isIncompleteFunction(lines)) {
            this.codeValidationContext.addPhysicalLine();
            return true;
        } else  {
            return false;
        }

    }

    /*
     * Revisa si la linea es una función
     * 
     * @param line representa la linea de código a validar
     * @return si es una función completa
     * @throws CodeStandarException si es una estrucura de función y no está en el formato
     */


    private boolean isFunction(String line) throws CodeStandarException{
        String structureFunction ="^(\\w+\\s+)+\\w+\\s*\\(.*\\)\\s*.*\\{?";
        String structureStaticFunction ="^(\\w+\\s+)+\\w+\\s*\\(.*\\)\\s*.*\\;?";
        if (matchesPattern(line.trim(), structureFunction) || matchesPattern(line.trim(), structureStaticFunction)){
            if (line.trim().endsWith("{") || line.trim().endsWith(";")) {
                return true;
            }
            throw new CodeStandarException("Las funciones no cumplen con el formato de código");
        } else {
           return false;
        }
    }

     /*
     * Revisa si la primera linea del código es una función incompleta, es decir que ocurrió un salto de línea, para validar la función completa
     * 
     * @param lines representa la lineas de código a validar
     * @return si es una función con salto de línea
     * @throws CodeStandarException si es una estrucura de función y no está en el formato
     */

    private boolean isIncompleteFunction(List<String> lines) throws CodeStandarException{
        String structure = "(\\w+\\s+)+\\w+\\s*\\(.*\\s*";
        if (matchesPattern(lines.get(0).trim(), structure)) {
           return findEndOfLine(lines);
        }
        return false;
    }

     /*
     * Revisa las lineas de código hasta encontrar el final de linea de la función
     * 
     * @param lines representa la lineas de código a validar
     * @return si es una función con salto de línea con formato correcto
     * @throws CodeStandarException si es una estrucura de función y no está en el formato
     */
        
    public boolean findEndOfLine(List<String> lines) throws CodeStandarException{
        lines.remove(0);
        String structureFunction = ".*\\)\\s*.*\\{?";
        String structureStaticFunction = ".*\\)\\s*.*\\;?";
        while (lines.size()>0) {
            if (matchesPattern(lines.get(0).trim(), structureFunction) || matchesPattern(lines.get(0).trim(), structureStaticFunction)) {
                    if (lines.get(0).trim().endsWith("{") || lines.get(0).trim().endsWith(";")) {
                        if (lines.get(0).trim().startsWith("{") || lines.get(0).trim().startsWith(";")) throw new CodeStandarException("No se cumple el formato de codigo");
                        this.codeValidationContext.addPhysicalLine();
                        return true;
                    } else {
                        throw new CodeStandarException("Estructura de control invalida");
                    }
            }
                if(lines.size()>0) lines.remove(0);
                this.codeValidationContext.addPhysicalLine();
        }
        if(lines.size()<0) throw new CodeStandarException("No se cumple el formato de codigo");
        return false;
    }
    
}
