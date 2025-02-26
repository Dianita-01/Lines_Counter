package com.proy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import main.java.com.proy.exceptions.CodeStandarException;
import main.java.com.proy.validator.concreteValidators.BraceValidator;
import main.java.com.proy.validator.validatorContext.CodeValidationContext;
import org.junit.Before;
import org.junit.Test;

public class BraceValidatorTest {

    private BraceValidator braceValidator;
    private CodeValidationContext codeValidationContext;

    @Before
    public void setUp() {
        codeValidationContext = new CodeValidationContext();
        braceValidator = new BraceValidator(codeValidationContext);
    }

    @Test
    public void testIgnoreClosingBraces() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("public class MyClass {");
        lines.add("    public void myMethod() {");
        lines.add("    }");
        lines.add("}");

        int count = braceValidator.countLinesWithoutBraces(lines);

        assertEquals(2, count); // Ignora las líneas de cierre de llaves
    }
}