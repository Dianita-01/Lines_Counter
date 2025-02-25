package com.proy;

import main.java.com.proy.exceptions.CodeStandarException;
import main.java.com.proy.validator.concreteValidators.CallFunctionOrObjectValidator;
import main.java.com.proy.validator.validatorContext.CodeValidationContext;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallFunctionOrObjectValidatorTest {

    /**
     * Test para verificar una llamada de función válida en una sola línea.
     */
    @Test
    public void testValidate_ValidFunctionCall() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("myObject.someMethod();");
        lines.add("myObject.someMethod();");
        lines.add("thing().someMethod();");

        CodeValidationContext context = new CodeValidationContext();
        CallFunctionOrObjectValidator validator = new CallFunctionOrObjectValidator(context);
        boolean result = validator.validate(lines);
        assertTrue(result);
        
    }

    /**
     * Test para verificar una llamada de función incompleta que se distribuye en varias líneas.
     */
    @Test
    public void testValidate_IncompleteFunctionCall() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("myObject.someMethod(");
        lines.add("arg1 a, arg2 b");

        CodeValidationContext context = new CodeValidationContext();
        CallFunctionOrObjectValidator validator = new CallFunctionOrObjectValidator(context);
        
        CodeStandarException exception = assertThrows(CodeStandarException.class, () ->
        validator.validate(lines));
        assertEquals("No se cumple el formato de codigo", exception.getMessage());
    }

    /**
     * Test para verificar una llamada de función incorrecta (formato no válido).
     */
    @Test
    public void testValidate_InvalidFunctionCall() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("fun()");
        CodeValidationContext context = new CodeValidationContext();
        CallFunctionOrObjectValidator validator = new CallFunctionOrObjectValidator(context);
        
        CodeStandarException exception = assertThrows(CodeStandarException.class, () ->
        validator.validate(lines));
        assertEquals("No se cumple el formato de codigo", exception.getMessage());
    }

    /**
     * Test para verificar que se lance una excepción si el formato de la llamada de función es incorrecto.
     */
    public void testFindEndOfLine_InvalidFormat() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("mi_fun(");
        CodeValidationContext context = new CodeValidationContext();
        CallFunctionOrObjectValidator validator = new CallFunctionOrObjectValidator(context);
        validator.findEndOfLine(lines); // Esto debe lanzar CodeStandarException
    }

    /**
     * Test para verificar una llamada de función con comentario en línea.
     */
    @Test
    public void testValidate_FunctionCallWithInlineComment() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("dog.bark() //The dog will bark");
        CodeValidationContext context = new CodeValidationContext();
        CallFunctionOrObjectValidator validator = new CallFunctionOrObjectValidator(context);
        
        CodeStandarException exception = assertThrows(CodeStandarException.class, () ->
        validator.validate(lines));
        assertEquals("No se cumple el formato de codigo", exception.getMessage());
    }
}
