package com.proy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import main.java.com.proy.exceptions.CodeStandarException;
import main.java.com.proy.validator.concreteValidators.ClassDefinitionValidator;
import main.java.com.proy.validator.validatorContext.CodeValidationContext;
import org.junit.Before;
import org.junit.Test;

public class ClassDefinitionValidatorTest {

    private ClassDefinitionValidator classDefinitionValidator;
    private CodeValidationContext codeValidationContext;

    @Before
    public void setUp() {
        codeValidationContext = new CodeValidationContext();
        classDefinitionValidator = new ClassDefinitionValidator(codeValidationContext);
    }

    @Test
    public void testCountClassDefinitions() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("public class MyClass {");
        lines.add("    class InnerClass {");
        lines.add("    }");
        lines.add("}");

        int count = classDefinitionValidator.countClassDefinitions(lines);

        assertEquals(2, count); // Cuenta las definiciones de clase
    }
}