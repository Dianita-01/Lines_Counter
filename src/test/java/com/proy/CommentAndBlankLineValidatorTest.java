package com.proy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import main.java.com.proy.exceptions.CodeStandarException;
import main.java.com.proy.validator.concreteValidators.CommentAndBlankLineValidator;
import main.java.com.proy.validator.validatorContext.CodeValidationContext;
import org.junit.Before;
import org.junit.Test;

public class CommentAndBlankLineValidatorTest {

    private CommentAndBlankLineValidator commentAndBlankLineValidator;
    private CodeValidationContext codeValidationContext;

    @Before
    public void setUp() {
        codeValidationContext = new CodeValidationContext();
        commentAndBlankLineValidator = new CommentAndBlankLineValidator(codeValidationContext);
    }

    @Test
    public void testIgnoreCommentsAndBlankLines() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("public class MyClass {");
        lines.add("    // This is a comment");
        lines.add("");
        lines.add("    public void myMethod() {");
        lines.add("    }");
        lines.add("}");

        int count = commentAndBlankLineValidator.countValidLines(lines);

        assertEquals(3, count); // Solo cuenta líneas de código válidas
    }
}