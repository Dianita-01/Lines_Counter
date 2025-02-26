package com.proy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import main.java.com.proy.exceptions.CodeStandarException;
import main.java.com.proy.validator.concreteValidators.FileReaderValidator;
import main.java.com.proy.validator.validatorContext.CodeValidationContext;
import org.junit.Before;
import org.junit.Test;

public class FileReaderValidatorTest {

    private FileReaderValidator fileReaderValidator;
    private CodeValidationContext codeValidationContext;

    @Before
    public void setUp() {
        codeValidationContext = new CodeValidationContext();
        fileReaderValidator = new FileReaderValidator(codeValidationContext);
    }

    @Test
    public void testReadFileSuccess() throws CodeStandarException {
        String filePath = "src/test/resources/testFile.java";
        List<String> lines = fileReaderValidator.readFile(filePath);

        assertNotNull(lines);
        assertFalse(lines.isEmpty());
    }

    @Test(expected = CodeStandarException.class)
    public void testReadFileNotFound() throws CodeStandarException {
        String filePath = "src/test/resources/nonExistentFile.java";
        fileReaderValidator.readFile(filePath);
    }
}