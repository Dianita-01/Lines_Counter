package files;
import java.io.File;
import java.util.List;

import model.CodeSegment;
import readers.ReaderFile;

import validator.ValidatorContext.CodeValidationContext;
import validator.validatorControlers.FileStructureValidator;

/**
 * La clase "FileCounter" proporciona los métodos que se necesitan para empezar el conteo de lineas y validación de un archivo
 * 
 * @version 1.0
 */

public class FileCounter {

    private File file;
    private CodeValidationContext codeValidationContext;
    private CodeSegment codeSegment;

    public FileCounter(File file) {
       this.file = file;
    }

    /*
     * Llama al CodeValidationContext para el conteo de líneas físicas y lógicas siempre y cuando sea válido el formato
     * Instanciando el primer validador de FileStructureValidator que solo permite estructuras de organización y de estructuras de definición
     */
    public void countLinesInFile() {
        ReaderFile readerFile = new ReaderFile();
        List<String> lines = readerFile.readFileLines(file);
        try {
            codeValidationContext = new CodeValidationContext();
            codeValidationContext.setStandardValidator(new FileStructureValidator(codeValidationContext));
            codeValidationContext.validate(lines);
            this.codeSegment = new CodeSegment(codeValidationContext.getPhysicalLines(), codeValidationContext.getLogicalLines());
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        showResults(); 
    }

    public void showResults(){
        System.out.println(this.file.getName());
        System.out.println("Lineas físicas: " + codeSegment.getPhysicalLines());
        System.out.println("Lineas lógicas: " + codeSegment.getLogicalLines());
    }
}
