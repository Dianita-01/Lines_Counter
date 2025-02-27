
import com.proy.validator.concreteValidators.AssigmentValidator;
import com.proy.validator.validatorContext.CodeValidationContext;
import com.proy.exceptions.CodeStandarException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class AssigmentValidatorTest {

    private CodeValidationContext context;
    private AssigmentValidator validator;

    /**
     * Configura el contexto antes de cada prueba.
     */
    @Before
    public void setUp() {
        context = new CodeValidationContext();
        validator = new AssigmentValidator(context);
    }

    /**
     * Verifica que `isAssigment` reconozca correctamente las asignaciones válidas.
     */
    @Test
    public void testIsAssigment_ValidAssignment() {
        assertTrue(validator.isAssigment("int x = 10;"));
        assertTrue(validator.isAssigment("String s = \"Hola\";"));
        assertTrue(validator.isAssigment("double pi = 3.14; // Comentario"));
    }

    /**
     * Verifica que `isAssigment` retorne `false` para líneas que no son asignaciones válidas.
     */
    @Test
    public void testIsAssigment_InvalidAssignment() {
        assertFalse(validator.isAssigment("if (x == 10) {")); // No es una asignación
        assertFalse(validator.isAssigment("x =")); // Asignación incompleta
        assertFalse(validator.isAssigment("return x;")); // No es una asignación
    }

    /**
     * Prueba `isIncompleteAssigment` con una asignación dividida en varias líneas.
     * Debe retornar `true` si detecta una asignación en varias líneas correctamente.
     */
    @Test
    public void testIsIncompleteAssigment_ValidIncompleteAssignment() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("asignacion = 4");
        lines.add("  * 4");
        lines.add("}6;"); 
        assertTrue(validator.isIncompleteAssigment(lines));   
    }

    /**
     * Prueba `isIncompleteAssigment` cuando la línea no es una asignación incompleta.
     * Debe retornar `false`.
     */
    @Test
    public void testIsIncompleteAssigment_NotAnAssignment() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("asignacion 4;");
        lines.add("public class{");
        lines.add("8");         
        
        assertFalse(validator.isIncompleteAssigment(lines));
    }

    /**
     * Verifica que `findEndOfLine` detecte correctamente el final de una asignación en varias líneas.
     */
    @Test
    public void testFindEndOfLine_Valid() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("\"Hola");
        lines.add("Mundo\";");
        assertTrue(validator.findEndOfLine(lines));
    }

    /**
     * Prueba `findEndOfLine` cuando no hay un `;` al final de la asignación.
     * Debe lanzar `CodeStandarException`.
     */
    public void testFindEndOfLine_Invalid() throws CodeStandarException {
        List<String> lines = new ArrayList<>();
        lines.add("\"Hola");
        lines.add("Mundo\"");
        assertFalse(validator.findEndOfLine(lines));
    }
}
