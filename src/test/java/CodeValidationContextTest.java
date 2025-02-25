import static org.junit.Assert.*;
import org.junit.Test;

import exceptions.CodeStandarException;
import model.CodeSegment;
import validator.validatorContext.CodeValidationContext;
import validator.validatorContext.StandardValidator;
import validator.validatorControlers.FileStructureValidator;

import java.util.List;

public class CodeValidationContextTest {

    /**
     * Prueba para el constructor sin parámetros. Verifica que el objeto `codeSegment`
     * se inicializa correctamente con valores predeterminados.
     */
    @Test
    public void testConstructorSinParametros() {
        CodeValidationContext context = new CodeValidationContext();
        assertNotNull(context.getCodeSegment());
        assertEquals(0, context.getLogicalLines());
        assertEquals(0, context.getPhysicalLines());
    }

    /**
     * Prueba para el constructor con un `StandardValidator`. Verifica que se 
     * inicialice correctamente el `StandardValidator` dentro del contexto.
     */
    @Test
    public void testConstructorConValidator() {
        FileStructureValidator validator = new FileStructureValidator();
        CodeValidationContext context = new CodeValidationContext(validator);
        assertNotNull(context.getStandardValidator());
    }

    /**
     * Prueba para el método `addLogicalLine`. Verifica que el número de líneas lógicas
     * se incremente correctamente al agregar una línea lógica.
     */
    @Test
    public void testAddLogicalLine() {
        CodeValidationContext context = new CodeValidationContext();
        context.addLogicalLine();
        assertEquals(1, context.getLogicalLines());
    }

    /**
     * Prueba para el método `addPhysicalLine`. Verifica que el número de líneas físicas
     * se incremente correctamente al agregar una línea física.
     */
    @Test
    public void testAddPhysicalLine() {
        CodeValidationContext context = new CodeValidationContext();
        context.addPhysicalLine();
        assertEquals(1, context.getPhysicalLines());
    }

    /**
     * Prueba para el método `addLogicalAndPhysicalLine`. Verifica que se incremente
     * tanto el número de líneas lógicas como físicas.
     */
    @Test
    public void testAddLogicalAndPhysicalLine() {
        CodeValidationContext context = new CodeValidationContext();
        context.addLogicalAndPhysicalLine();
        assertEquals(1, context.getLogicalLines());
        assertEquals(1, context.getPhysicalLines());
    }

    /**
     * Prueba para el método `addLogicalLine(int num)`. Verifica que se agreguen múltiples
     * líneas lógicas correctamente.
     */
    @Test
    public void testAddLogicalLineConNumero() {
        CodeValidationContext context = new CodeValidationContext();
        context.addLogicalLine(3);
        assertEquals(3, context.getLogicalLines());
    }

    /**
     * Prueba para el método `addPhysicalLine(int num)`. Verifica que se agreguen múltiples
     * líneas físicas correctamente.
     */
    @Test
    public void testAddPhysicalLineConNumero() {
        CodeValidationContext context = new CodeValidationContext();
        context.addPhysicalLine(5);
        assertEquals(5, context.getPhysicalLines());
    }

    /**
     * Prueba para los setters y getters de `StandardValidator` y `CodeSegment`.
     * Verifica que los valores establecidos se obtengan correctamente.
     */
    @Test
    public void testSettersYGetters() {
        FileStructureValidator validator = new FileStructureValidator();
        CodeValidationContext context = new CodeValidationContext();
        CodeSegment segment = new CodeSegment(10, 20);

        context.setStandardValidator(validator);
        context.setCodeSegment(segment);

        assertEquals(validator, context.getStandardValidator());
        assertEquals(segment, context.getCodeSegment());
    }

}
