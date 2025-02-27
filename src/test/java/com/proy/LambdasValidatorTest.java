package com.proy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import com.proy.exceptions.CodeStandarException;
import com.proy.validator.concreteValidators.LambdasValidator;
import com.proy.validator.validatorContext.CodeValidationContext;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase de prueba para la clase LambdasValidator.
 */
public class LambdasValidatorTest {

    private LambdasValidator lambdasValidator;
    private CodeValidationContext codeValidationContext;

    /**
     * Configuración inicial para cada prueba.
     */
    @Before
    public void setUp() {
        codeValidationContext = new CodeValidationContext();
        lambdasValidator = new LambdasValidator(codeValidationContext);
    }

    /**
     * Test que valida una lambda en línea.
     * 
     * @throws CodeStandarException Si ocurre un error durante la validación.
     */
    @Test
    public void testValidateLambdaInLine() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("(x) -> x * 2;");
        
        boolean result = lambdasValidator.validate(lines);
        assertTrue(result);
    }

    /**
     * Test que valida una lambda en bloque.
     * 
     * @throws CodeStandarException Si ocurre un error durante la validación.
     */
    @Test
    public void testValidateLambdaBlock() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("(x) -> {");
        lines.add("return x * 2;");
        lines.add("}");
        
        boolean result = lambdasValidator.validate(lines);

        assertTrue(result);
    }

    /**
     * Test que valida que no sea una lambda.
     * 
     * @throws CodeStandarException Si ocurre un error durante la validación.
     */
    @Test
    public void testInvalidLambda() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("int x = 5;");
        
        boolean result = lambdasValidator.validate(lines);

        assertFalse(result);
    }

    /**
     * Test para una lambda con formato incorrecto.
     * 
     * @throws CodeStandarException Si ocurre un error durante la validación.
     */
    @Test
    public void testInvalidLambdaFormat() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("(x -> x * 2");
        assertFalse(lambdasValidator.validate(lines));
        
    }
}
