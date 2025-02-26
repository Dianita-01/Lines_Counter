package com.proy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import main.java.com.proy.exceptions.CodeStandarException;
import main.java.com.proy.validator.concreteValidators.ImportAndPackageValidator;
import main.java.com.proy.validator.validatorContext.CodeValidationContext;
import org.junit.Before;
import org.junit.Test;

public class ImportAndPackageValidatorTest {

    private ImportAndPackageValidator importAndPackageValidator;
    private CodeValidationContext codeValidationContext;

    @Before
    public void setUp() {
        codeValidationContext = new CodeValidationContext();
        importAndPackageValidator = new ImportAndPackageValidator(codeValidationContext);
    }

    @Test
    public void testValidateImportsAndPackage() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("package com.proy;");
        lines.add("import java.util.List;");
        lines.add("public class MyClass {");

        boolean result = importAndPackageValidator.validate(lines);

        assertTrue(result);
    }

    @Test
    public void testInvalidImportsAndPackage() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("package com.proy;");
        lines.add("invalid import java.util.List;");
        lines.add("public class MyClass {");

        boolean result = importAndPackageValidator.validate(lines);

        assertFalse(result);
    }
}
