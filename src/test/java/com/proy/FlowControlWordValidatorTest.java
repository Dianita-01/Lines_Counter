package com.proy;

import main.java.com.proy.exceptions.CodeStandarException;
import main.java.com.proy.validator.concreteValidators.FlowControlWordValidator;
import main.java.com.proy.validator.validatorContext.CodeValidationContext;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class FlowControlWordValidatorTest {

    /**
     * Test para validar una estructura de control completa "else if" con formato correcto.
     * 
     * @throws CodeStandarException si hay un error en el formato
     */
    @Test
    public void testValidate_ValidFlowControl() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("} else if (condition) {");
        CodeValidationContext context = new CodeValidationContext();
        FlowControlWordValidator validator = new FlowControlWordValidator(context);
        boolean result = validator.validate(lines);
        assertTrue(result);
       
    }

    /**
     * Test para verificar que se rechace una estructura de control con formato incorrecto "else if" sin paréntesis.
     * 
     * @throws CodeStandarException si hay un error en el formato
     */
    @Test
    public void testValidate_InvalidFlowControl() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("} else if condition {");
        CodeValidationContext context = new CodeValidationContext();
        FlowControlWordValidator validator = new FlowControlWordValidator(context);
        boolean result = validator.validate(lines);
        assertFalse(result);
    
    }

    /**
     * Test para validar una estructura de flujo de control incompleta, es decir, una línea de código con un salto de línea pendiente.
     * 
     * @throws CodeStandarException si hay un error en el formato
     */
    @Test
    public void testValidate_IncompleteFlowControl() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("} else if (condition)");  
        CodeValidationContext context = new CodeValidationContext();
        FlowControlWordValidator validator = new FlowControlWordValidator(context);
        boolean result = validator.validate(lines);
        assertTrue(result);
        
    }

    /**
     * Test para verificar que una estructura de control "else if" con comentario en la misma línea sea aceptada.
     * 
     * @throws CodeStandarException si hay un error en el formato
     */
    @Test
    public void testValidate_FlowControlWithInlineComment() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("} else if (condition) { // Esto es un comentario");
        CodeValidationContext context = new CodeValidationContext();
        FlowControlWordValidator validator = new FlowControlWordValidator(context);
        boolean result = validator.validate(lines);
        assertTrue(result);
        
    }

    /**
     * Test para verificar que una estructura de control "else if" que no termina correctamente lance una excepción.
     * 
     * @throws CodeStandarException si se lanza una excepción debido a un formato incorrecto
     */
    @Test(expected = CodeStandarException.class)
    public void testFindEndOfLine_InvalidFlowControl() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("} else if (condition)");  // Incompleta, no hay cierre de bloque
        CodeValidationContext context = new CodeValidationContext();
        FlowControlWordValidator validator = new FlowControlWordValidator(context);
        validator.findEndOfLine(lines); // Esto debería lanzar una CodeStandarException
    }
}
