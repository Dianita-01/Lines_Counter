package com.proy;

import static org.junit.Assert.*;

import java.util.List;

import main.java.com.proy.exceptions.CodeStandarException;
import main.java.com.proy.validator.concreteValidators.DirectoryReaderValidator;
import main.java.com.proy.validator.validatorContext.CodeValidationContext;
import org.junit.Before;
import org.junit.Test;

public class DirectoryReaderValidatorTest {

    private DirectoryReaderValidator directoryReaderValidator;
    private CodeValidationContext codeValidationContext;

    @Before
    public void setUp() {
        codeValidationContext = new CodeValidationContext();
        directoryReaderValidator = new DirectoryReaderValidator(codeValidationContext);
    }

    @Test
    public void testReadDirectorySuccess() throws CodeStandarException {
        String directoryPath = "src/test/resources";
        List<String> files = directoryReaderValidator.readDirectory(directoryPath);

        assertNotNull(files);
        assertFalse(files.isEmpty());
    }

    @Test(expected = CodeStandarException.class)
    public void testReadDirectoryNotFound() throws CodeStandarException {
        String directoryPath = "src/test/nonExistentDirectory";
        directoryReaderValidator.readDirectory(directoryPath);
    }
}
