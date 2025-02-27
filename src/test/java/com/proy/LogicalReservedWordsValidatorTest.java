package com.proy;

import com.proy.exceptions.CodeStandarException;
import com.proy.validator.concreteValidators.LogicalReservedWordsValidator;
import com.proy.validator.validatorContext.CodeValidationContext;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase LogicalReservedWordsValidator
 */
public class LogicalReservedWordsValidatorTest {

    private LogicalReservedWordsValidator validator;
    private CodeValidationContext context;

    /**
     * Configuración inicial antes de cada prueba
     */
    @Before
    public void setUp() {
        context = new CodeValidationContext();
        validator = new LogicalReservedWordsValidator(context);
    }

    /**
     * Verifica que se valide correctamente una palabra reservada lógica
     */
    @Test
    public void testValidateWithLogicalReservedWord() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("return 5;");
        boolean result = validator.validate(lines);
        assertTrue(result);
    }

    /**
     * Verifica que una línea sin palabra reservada lógica no sea validada
     */
    @Test
    public void testValidateWithoutLogicalReservedWord() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("int x ? 10");
        lines.add("public void()");
        boolean result = validator.validate(lines);
        assertFalse(result);
    }

    /**
     * Verifica que una línea vacía no sea validada
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testValidateEmptyListThrowsException() throws CodeStandarException {
        validator.validate(Arrays.asList());
    }

    /**
     * Verifica que la detección de palabras reservadas lógicas funcione correctamente
     */
    @Test
    public void testHasLogicalReservedWords() {
        assertTrue(validator.hasLogicalReserverWords("break;"));
        assertTrue(validator.hasLogicalReserverWords("return x + 2;"));
        assertFalse(validator.hasLogicalReserverWords("int a = 10;"));
        assertFalse(validator.hasLogicalReserverWords("System.out.println(\"Hello\");"));
    }
}
