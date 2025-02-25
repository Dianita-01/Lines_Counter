package main.java.com.proy.validator.concreteValidators;

import java.util.List;

import main.java.com.proy.exceptions.CodeStandarException;
import main.java.com.proy.validator.validatorContext.CodeValidationContext;
import main.java.com.proy.validator.validatorContext.StandardValidator;

/**
 * La clase "StructureDefinitionValidator" proporciona los métodos para validar una formato de estructuras de definción para poder hacer la suma de lineas físicas solo
 * en caso de ser un estructura de definción
 * @version 1.0
 */

public class StructureDefinitionValidator extends StandardValidator{

    private CodeValidationContext codeValidationContext;

    public StructureDefinitionValidator(CodeValidationContext codeValidationContext){
        this.codeValidationContext = codeValidationContext;
    }

      /*
     * Cuenta una línea de código física si la linea o lineas representan una definición y está en el formato
     * 
     * @param lines que representa las lineas de un código java
     * @return si es una estructura de definción y está en el formato
     * @throws CodeStandarException si es una estrucura de definción y no está en el formato
     */

    @Override
    public boolean validate(List<String> lines) throws CodeStandarException {
        if (hasInterfaceOrEnumDefinition(lines) || hasClassDefinition(lines) || hasRecordDefinition(lines) || hasNestedDefinition(lines.get(0))){
            this.codeValidationContext.addPhysicalLine();
            return true;
        } else {
            return false;
        }
    }

    /*
     * Revisa si la linea es una definición de tipo Interface o enum
     * 
     * @param line representa la linea de código a validar
     * @return si es una definición completa
     */
    public boolean hasInterfaceOrEnumDefinition(List<String> lines) throws CodeStandarException {
        String structureKeywords = "^\\b(?:\\w+\\s)+\\s*(?:interface|enum)\\s\\w+(\\s+\\w+)*\\s*\\{?\\s*(//.*)?$";
        return hasCorrestStructure(lines, structureKeywords);
    }

    /*
     * Revisa si la linea es una definición de tipo class
     * 
     * @param line representa la linea de código a validar
     * @return si es una definición completa
     */
    public boolean hasClassDefinition(List<String> lines) throws CodeStandarException {
        String structureKeywords = "\\w+(\\s+\\w+)*\\s+class\\s+\\w+(\\s+\\w+)*\\s*\\{?\\s*(//.*)?$";
        return hasCorrestStructure(lines, structureKeywords);
    } 

    /*
     * Revisa si la linea es una definición de tipo record
     * 
     * @param line representa la linea de código a validar
     * @return si es una definición completa
     */
    public boolean hasRecordDefinition(List<String> lines) throws CodeStandarException {
        String structureKeywords = "\\b\\w+(?:\\s+\\w+)*\\s+record\\s*\\([^)]*\\)(?:\\s+\\w+(?:\\s+\\w+)*)?(?:\\s+[^{]+)?\\s*\\{?\\s*(//.*)?";
        return hasCorrestStructure(lines, structureKeywords);
    }

    /*
     * Revisa si la linea es una estructura de definición anidada
     * 
     * @param line representa la linea de código a validar
     * @return si es una definición completa
     */
    public boolean hasNestedDefinition(String line) throws CodeStandarException {
        if(line.trim().startsWith("class") ||line.trim().startsWith("interface")||line.trim().startsWith("enum"))
        throw new CodeStandarException("No se pemite estructuras de control anidadas en el código");
        return false;
    }

    /*
     * Revisa si la linea cumpple con la la estrcutura de definición, en caso de parecer un salto de línea, revisar su final
     * 
     * @param line representa la linea de código a validar
     * @param pattern representa la regla para validar
     * @return si es una definición completa
     * @throws CodeStandarException si es una estrucura de definición y no está en el formato
     */
    public boolean hasCorrestStructure(List<String> lines, String pattern) throws CodeStandarException {
        String structure ="^.*?\\{\\s*(//.*)?$";
        if (matchesPattern(lines.get(0).trim(), pattern)){
            if (!matchesPattern(lines.get(0).trim(), structure)){
                return findEndOfLine(lines);
            }
            return true;
        } else {
           return false;
        }
    }

    /*
     * Revisa si la linea es una definición cumple el formato con su salto de línea
     * 
     * @param line representa la linea de código a validar
     * @return si es una definición completa
     * @throws CodeStandarException si es una estrucura de definición y no está en el formato
     */
    public boolean findEndOfLine(List<String> lines) throws CodeStandarException{
        lines.remove(0);
        String structure ="^.*?\\{\\s*(//.*)?$";
        while (lines.size()>0) {
            if (matchesPattern(lines.get(0).trim(), structure)) {
                if (lines.get(0).trim().startsWith("{")) throw new CodeStandarException("No se cumple el formato de codigo");
                this.codeValidationContext.addPhysicalLine();
                return true;
            }
            this.codeValidationContext.addPhysicalLine();
            if(lines.size()>0) lines.remove(0);
        }
        if(lines.size()<=0) throw new CodeStandarException("No se cumple el formato de codigo");
        return false;
        
    }
    
}
