package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.ValidatorContext.CodeValidationContext;
import validator.ValidatorContext.StandardValidator;

/**
 * La clase "CallFunctionOrObjectValidator" proporciona los métodos para validar una formato de llamada a un método o objeto para poder hacer la suma de lineas lógicas
 *  y físicas solo en caso de ser un estructura de llamado a un método o objeto.
 * @version 1.0
 */

public class CallFunctionOrObjectValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public CallFunctionOrObjectValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    /*
     * Cuenta una línea de código física si la linea o lineas representan una llamada a un método o método de objeto
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de llamada a método o método de objeto y está en el formato
     * @throws CodeStandarException si es una estrucura de llamada a método o método de objeto y no está completa, es decir no está en el formato
     */

    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (isCallFuctionOrObject(lines.get(0)) ||isIncompleteCallFuctionOrObject(lines)) {
            this.codeValidationContext.addLogicalAndPhysicalLine();
            return true;
        }
        return false;
    }

        /*
     * Revisa si la linea es una llamada a método o método de objeto
     * 
     * @param line representa la linea de código a validar
     * @return si es una llamada a método o método de objeto completa
     */

    private boolean isCallFuctionOrObject(String line) {
        String structure = "^[\\w\\s*\\.\\s*]+(\\(\\.*\\)\\s*\\.\\s*)*\\s*\\w+\\s*\\(.*\\)\\s*;$";
        return matchesPattern(line.trim(), structure);
    }

     /*
     * Revisa si la primera linea del código es una llamada a método o método de objeto incompleta, es decir que ocurrió un salto de línea, 
     * para validar la llamada a método o método de objeto completa
     * 
     * @param lines representa la lineas de código a validar
     * @return si es una llamada a método o método de objeto con salto de línea
     * @throws CodeStandarException si es llamada a método o método de objeto y no está en el formato
     */
    private boolean isIncompleteCallFuctionOrObject(List<String> lines) throws CodeStandarException{
        String structure = "^[\\w\\s*\\.\\s*]+(\\(\\.*\\)\\s*\\.\\s*)*\\s*\\w+\\s*\\(.*\\)?\\s*[^;]$";
        if (matchesPattern(lines.get(0).trim(), structure) && !lines.get   (0).trim().endsWith(";")) {
            return findEndOfLine(lines);
        }
        return false;
    }
    
    /*
     * Revisa las lineas de código hasta encontrar el final de linea de la llamada a método o método de objeto 
     * 
     * @param lines representa la lineas de código a validar
     * @return si es una llamada a método o método de objeto  con salto de línea con formato correcto
     * @throws CodeStandarException si es una estrucura de llamada a método o método de objeto  y no está en el formato
     */
    public boolean findEndOfLine(List<String> lines) throws CodeStandarException{
        lines.remove(0);
        while (lines.size()>0) {
            if (lines.get(0).trim().endsWith(";")) {
                if (lines.get(0).trim().startsWith(";")) throw new CodeStandarException("No se cumple el formato de codigo");
                this.codeValidationContext.addPhysicalLine();
                return true;
            }
            if(lines.size()>0) lines.remove(0);
            this.codeValidationContext.addPhysicalLine();
        }
        if(lines.size()<=0) throw new CodeStandarException("No se cumple el formato de codigo");
        return false;
    }
}
