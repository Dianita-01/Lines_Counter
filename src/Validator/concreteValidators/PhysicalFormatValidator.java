package validator.concreteValidators;

import java.util.List;

import exceptions.CodeStandarException;
import validator.validatorContext.CodeValidationContext;
import validator.validatorContext.StandardValidator;
/**
 * La clase "PhysicalFormatValidator" proporciona los métodos para validar una formato de líneas físicas para poder hacer la suma de físicas solo
 * en caso de ser una línea física que necesite validarse
 * @version 1.0
 */

public class PhysicalFormatValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public PhysicalFormatValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

    /*
     * Cuenta una línea de código física si cumple con el formato
     * 
     * @param lines que representa las lineas de un código java
     * @return si una línea está en el formato
     * @throws CodeStandarException si una línea no está en el formato
     */

    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (hasOnlyPhysicalLines(lines.get(0))) {
            this.codeValidationContext.addPhysicalLine();
            return true;
        }
        return false;
    }

    /*
     * Revisa primero si la linea es una no permitida en el formato y despues valida los formatos de los tpos de líneas físicas
     * 
     * @param line representa la linea de código a validar
     * @return si es una linea sin faltas en el formato físico
     * @throws CodeStandarException si una línea no está en el formato
     */


    public boolean hasOnlyPhysicalLines(String line) throws CodeStandarException{
        if (isCorrectFormat(line)) {
            if (hasCorrectPhysicLine(line) || hasCorrectDeclaration(line) || hasCorrectEnumLit(line)){
                return true;
            }
        }
        return false;
    }

    /*
     * Revisa primero si es una línea no válida en el formato
     * 
     * @param line representa la linea de código a validar
     * @return si es una linea sin faltas en el formato físico
     * @throws CodeStandarException si una línea no está en el formato
     */

    private boolean isCorrectFormat(String line) throws CodeStandarException{
        String multipleStatements = "\\b[a-zA-Z_]\\w*\\s+[a-zA-Z_]\\w*\\s*(?:,\\s*[a-zA-Z_]\\w*\\s*)+;?";
        if (line.startsWith("{")) throw new CodeStandarException("No se cumple el formato de codigo");
        if (line.startsWith("do")) throw new CodeStandarException("No se cumple el formato de codigo");
        if(matchesPattern(line.trim(), multipleStatements)) throw new CodeStandarException("No se cumple el formato de codigo");
        if(line.trim().startsWith("else") || line.trim().startsWith("finally")) throw new CodeStandarException("No se cumple el formato de codigo");
        if(line.trim().startsWith("}")) return validateMiddleOfFlowControl(line);
        return true;
    }

    /*
     * Revisa el formato de las lineas físicas de las estructuras de control
     * 
     * @param line representa la linea de código a validar
     * @return si es una linea sin faltas en el formato físico
     * @throws CodeStandarException si una línea no está en el formato
     */

    private boolean validateMiddleOfFlowControl(String line) throws CodeStandarException{
        if (!line.trim().equals("}")) {
            String structure = "^\\s*\\}\\s*(else|finally)\\s*\\{$";
            if (matchesPattern(line, structure)) return true;
            throw new CodeStandarException("No se cumple el formato de codigo");
        }
        return true;
    }

    /*
     * Revisa que las anotaciones y el formato de las palabras clave try y static
     * 
     * @param line representa la linea de código a validar
     * @return si es una linea sin faltas en el formato físico
     * @throws CodeStandarException si una línea no está en el formato
     */

    private boolean hasCorrectPhysicLine(String line) throws CodeStandarException{
        String anonotation ="^@.+[^\\s]$"; 
        String tryDoStructure = "(try|static)\\s*\\{";
        if(hasCorrestStructure(line, anonotation, "@") || hasCorrestStructure(line, tryDoStructure, "try") |hasCorrestStructure(line, tryDoStructure, "do") ||hasCorrestStructure(line, tryDoStructure, "static"))
            return true;
        return false;
    }

    /*
     * Revisa que las las declaraciones tengan el formato correcto
     * 
     * @param line representa la linea de código a validar
     * @return si es una linea sin faltas en el formato físico
     * @throws CodeStandarException si una línea no está en el formato
     */

    private boolean hasCorrectDeclaration(String line) throws CodeStandarException{
        String declaration ="^[\\w<>\\[\\]]+\\s+[\\w<>\\[\\]]+\\s*;?$";
        if(matchesPattern(line, declaration)){
            if (line.trim().endsWith(";")) {
                return true;
            }
            throw new CodeStandarException("Las declaraciones no cumplen el formato de codigo");
        }
            
        return false;
    }

    /*
     * Revisa que en caso de tener una lísta de enums termine en punto y coma
     * 
     * @param line representa la linea de código a validar
     * @return si es una linea sin faltas en el formato físico
     * @throws CodeStandarException si una línea no está en el formato
     */

    public boolean hasCorrectEnumLit(String line) throws CodeStandarException{
        String enumItems = "\\w+(?:,\\s*\\w+)*?;?";
        if(matchesPattern(line, enumItems)){
            if (!line.trim().endsWith(";")) {
                throw new CodeStandarException("Las declaraciones no cumplen el formato de codigo");
            }
        return true;
        }
        return false;
    }

    /*
     * Permite validar una estrcutura basandose en como inicia
     * 
     * @param line representa la linea de código a validar
     * @param pattern representa la regla
     * @param start primer elemento de la linea
     * @return si es una linea sin faltas en el formato físico
     * @throws CodeStandarException si una línea no está en el formato
     */

    public boolean hasCorrestStructure(String line, String pattern, String start) throws CodeStandarException {
        if (line.trim().startsWith(start)){
            if (matchesPattern(line.trim(), pattern)) {
                return true;
            }
            throw new CodeStandarException("Las lineas de código no cumplen el formato");
        } else {
           return false;
        }
    }
    
}
