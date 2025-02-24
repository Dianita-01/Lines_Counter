// import static org.junit.Assert.*;

// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;

// import files.FileCounter;

// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;

// /**
//  * Clase de prueba para la clase FileCounter.
//  * Estas pruebas aseguran que el método countLinesInFile funcione correctamente
//  * en diferentes condiciones de archivos y estructuras de código.
//  */
// public class FileCounterTest {
    
//     private File validFile;
//     private File invalidFile;
//     private File nonJavaFile;
//     private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//     private final PrintStream originalSystemOut = System.out;

//     /**
//      * Configura los archivos necesarios para las pruebas y redirige la salida estándar.
//      * 
//      * @throws IOException Si ocurre un error al crear archivos.
//      */
//     @Before
//     public void setUp() throws IOException {
//         // Redirige la salida estándar para capturar las impresiones en consola
//         System.setOut(new PrintStream(outputStream));

//         // Crea un archivo válido con extensión .java
//         validFile = new File("validTestFile.java");
//         if (!validFile.exists()) {
//             Files.write(Paths.get(validFile.getPath()), "public class Test {} // some code".getBytes());
//         }

//         // Crea un archivo inválido (vacío, pero con extensión .java)
//         invalidFile = new File("invalidTestFile.java");
//         if (!invalidFile.exists()) {
//             Files.write(Paths.get(invalidFile.getPath()), "".getBytes());
//         }

//         // Crea un archivo no Java
//         nonJavaFile = new File("nonJavaFile.txt");
//         if (!nonJavaFile.exists()) {
//             Files.write(Paths.get(nonJavaFile.getPath()), "Some random content.".getBytes());
//         }
//     }

//     /**
//      * Limpia el flujo de salida estándar después de cada prueba.
//      */
//     @After
//     public void tearDown() {
//         System.setOut(originalSystemOut);
//     }

//     /**
//      * Prueba el comportamiento cuando se pasa un archivo válido con extensión .java.
//      * Verifica que el conteo de líneas físicas y lógicas se realice correctamente.
//      */
//     @Test
//     public void testCountLinesInFile_validJavaFile() {
//         // Crea el objeto FileCounter y llama a countLinesInFile
//         FileCounter counter = new FileCounter(validFile);
//         counter.countLinesInFile();
        
//         // Verifica que la salida estándar contiene la información esperada
//         String output = outputStream.toString();
//         assertTrue("Debe contener el nombre del archivo en la salida.", output.contains("validTestFile.java"));
//         assertTrue("Debe mencionar 'Lineas físicas' en la salida.", output.contains("Lineas físicas"));
//         assertTrue("Debe mencionar 'Lineas lógicas' en la salida.", output.contains("Lineas lógicas"));
//     }

//     /**
//      * Prueba el comportamiento cuando se pasa un archivo vacío con extensión .java.
//      * Verifica que no se cause una excepción y que el conteo se maneje correctamente.
//      */
//     @Test
//     public void testCountLinesInFile_invalidJavaFile() {
//         // Crea el objeto FileCounter y llama a countLinesInFile con un archivo vacío
//         FileCounter counter = new FileCounter(invalidFile);
//         counter.countLinesInFile();
        
//         // Verifica que la salida estándar contenga el mensaje esperado para un archivo vacío
//         String output = outputStream.toString();
//         assertTrue("Debe contener el nombre del archivo en la salida.", output.contains("invalidTestFile.java"));
//         assertTrue("Debe mencionar 'Lineas físicas' en la salida.", output.contains("Lineas físicas"));
//         assertTrue("Debe mencionar 'Lineas lógicas' en la salida.", output.contains("Lineas lógicas"));
//     }

//     /**
//      * Prueba el comportamiento cuando se pasa un archivo con una extensión no .java.
//      * Verifica que se imprima el mensaje adecuado indicando que el archivo no es válido.
//      */
//     @Test
//     public void testCountLinesInFile_nonJavaFile() {
//         // Crea el objeto FileCounter y llama a countLinesInFile con un archivo no Java
//         FileCounter counter = new FileCounter(nonJavaFile);
//         counter.countLinesInFile();
        
//         // Verifica que la salida estándar contenga un mensaje indicando que el archivo no es válido
//         String output = outputStream.toString();
//         assertTrue("Debe indicar que no es un archivo con extensión válida.", output.contains("no es un archivo con extensión válida"));
//     }

//     /**
//      * Prueba el comportamiento cuando el archivo no es un archivo válido (es decir, un directorio o no se puede leer).
//      * Verifica que no se cause una excepción y que el sistema maneje adecuadamente este caso.
//      */
//     @Test
//     public void testCountLinesInFile_invalidFile() {
//         // Usando un directorio como archivo (no válido)
//         File invalidDir = new File("invalidDirectory");
//         if (!invalidDir.exists()) {
//             invalidDir.mkdir();
//         }
        
//         // Crea el objeto FileCounter y llama a countLinesInFile
//         FileCounter counter = new FileCounter(invalidDir);
//         counter.countLinesInFile();
        
//         // Verifica que la salida estándar no cause errores con directorios
//         String output = outputStream.toString();
//         assertTrue("Debe manejar correctamente un directorio.", output.contains("invalidDirectory no es un archivo con extensión válida"));
//     }
// }
