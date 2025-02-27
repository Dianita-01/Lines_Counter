package com.proy;

import com.proy.exceptions.CodeStandarException;
import com.proy.validator.concreteValidators.ControlStructureValidator;
import com.proy.validator.validatorContext.CodeValidationContext;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ControlStructureValidatorTest {

    /**
     * Test para verificar una estructura de control válida (if) en una sola línea.
     */
    @Test
    public void testValidate_ValidControlStructure() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("if (x > 0) {");
        CodeValidationContext context = new CodeValidationContext();
        ControlStructureValidator validator = new ControlStructureValidator(context);
        boolean result = validator.validate(lines);
        assertTrue(result);

    }

    /**
     * Test para verificar una estructura de control válida (for) en varias líneas.
     */
    @Test
    public void testValidate_ValidIncompleteControlStructure() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("for (int i = 0; i < 10; i++){");
        lines.add("  System.out.println(i);");
        lines.add("}");
        CodeValidationContext context = new CodeValidationContext();
        ControlStructureValidator validator = new ControlStructureValidator(context);
        boolean result = validator.validate(lines);
        assertTrue(result);

    }

    /**
     * Test para verificar que una estructura de control incompleta se complete correctamente.
     */
    @Test(expected = CodeStandarException.class)
    public void testValidate_IncompleteControlStructure() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("if (x > 0");
        CodeValidationContext context = new CodeValidationContext();
        ControlStructureValidator validator = new ControlStructureValidator(context);
        validator.validate(lines);  
    }

    /**
     * Test para verificar que una estructura de control con error de formato (sin paréntesis) sea rechazada.
     */
    @Test
    public void testValidate_InvalidControlStructure() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("if x > 0 {");
        CodeValidationContext context = new CodeValidationContext();
        ControlStructureValidator validator = new ControlStructureValidator(context);
        boolean result = validator.validate(lines);
        assertFalse(result);
 
    }

    /**
     * Test para verificar que se lance una excepción cuando el formato de la estructura de control es incorrecto.
     */
    @Test(expected = CodeStandarException.class)
    public void testFindEndOfLine_InvalidFormat() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("if (x > 0) // Incomplete");
        CodeValidationContext context = new CodeValidationContext();
        ControlStructureValidator validator = new ControlStructureValidator(context);
        validator.findEndOfLine(lines); 
    }

    /**
     * Test para verificar una estructura de control con comentario en línea.
     */
    @Test
    public void testValidate_ControlStructureWithInlineComment() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("if (x > 0) { // Comment");
        CodeValidationContext context = new CodeValidationContext();
        ControlStructureValidator validator = new ControlStructureValidator(context);
        boolean result = validator.validate(lines);
        assertTrue(result);
    }
}
