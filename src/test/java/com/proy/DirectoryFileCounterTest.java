package com.proy;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.java.com.proy.readers.DirectoryFileCounter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase de prueba para la clase DirectoryFileCounter.
 * Estas pruebas aseguran que el método countLinesInDirectory funcione correctamente
 * en diferentes condiciones de directorios y archivos.
 */
public class DirectoryFileCounterTest {
    
    private File validDirectory;
    private File emptyDirectory;
    private List<File> filesInValidDirectory = new ArrayList<>();
    // private File validFile;

    /**
     * Configura los archivos y directorios necesarios para las pruebas.
     * 
     * @throws IOException Si ocurre un error al crear archivos o directorios.
     */
    @Before
    public void setUp() throws IOException {

        createValidDirectory();
        createValidFiles();      
        createEmptyDirectory();  

        // Crea un archivo de prueba .java
        

      

        // // Crea una ruta no válida
        // nonDirectoryPath = new File("nonDirectory.txt");
        // if (!nonDirectoryPath.exists()) {
        //     nonDirectoryPath.createNewFile();
        // }
    }

    
    /**
     * Prueba el comportamiento cuando el directorio contiene archivos Java.
     * Verifica que el método countLinesInDirectory no lance excepciones en este caso.
     */
    @Test
    public void testCountLinesInDirectory_validDirectory() {
        int sumLogicalLines = 0, sumPhysicalLines = 0;
        DirectoryFileCounter counter = new DirectoryFileCounter(this.validDirectory);
        try {
            counter.countLinesInDirectory();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        for(File file : this.filesInValidDirectory){
            int logicalLines = counter.getFileCounter().getCodeSegment().getLogicalLines();
            int physicalLines = counter.getFileCounter().getCodeSegment().getPhysicalLines();
            sumLogicalLines += logicalLines;
            sumPhysicalLines += physicalLines;
        }
        assertEquals(0, sumLogicalLines);
        assertEquals(4, sumPhysicalLines);
     
    }

    /**
     * Prueba el comportamiento cuando el directorio está vacío.
     * Verifica que el método countLinesInDirectory no cause errores.
     */
    @Test
    public void testCountLinesInDirectory_emptyDirectory() {
        DirectoryFileCounter counter = new DirectoryFileCounter(this.emptyDirectory);
        try {
            counter.countLinesInDirectory();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        assertNull(counter.getFileCounter());        
    }

    /**
     * Prueba el comportamiento cuando la ruta proporcionada no es un directorio.
     * Verifica que se maneje correctamente el caso de archivos no directorios.
     */
 

    private void writeFile(File testFile) throws IOException{
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("public class TestTesting{\n");
            writer.write("\n");
            writer.write("}\n");
        }
    }

    private void createValidDirectory(){
        this.validDirectory = new File("testDirectory");
        if (!validDirectory.exists()) {
            validDirectory.mkdir();
        }
    }

    private void createValidFiles() throws IOException{
        File validFile = new File(validDirectory, "TestFile.java");
        File validFile2 = new File(validDirectory, "TestFile2.java");
        this.filesInValidDirectory.add(validFile);
        this.filesInValidDirectory.add(validFile2);

        for(File file : this.filesInValidDirectory){
            if (!file.exists()) {
                file.createNewFile();
                this.writeFile(file);
            }
        }
    }

    private void createEmptyDirectory(){
        // Crea un directorio vacío
        this.emptyDirectory = new File("/src/emptyDirectory");
        if (!emptyDirectory.exists()) {
            emptyDirectory.mkdir();
        }
    }
}
