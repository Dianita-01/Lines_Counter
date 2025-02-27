package main.java.com.proy.files;
import java.io.File;
import java.util.List;

import main.java.com.proy.model.CodeSegment;
import main.java.com.proy.readers.ReaderFile;
import main.java.com.proy.validator.validatorContext.CodeValidationContext;
import main.java.com.proy.validator.validatorControlers.FileStructureValidator;

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
       this.codeSegment = new CodeSegment();
    }

    /*
     * Llama al CodeValidationContext para el conteo de líneas físicas y lógicas siempre y cuando sea válido el formato
     * Instanciando el primer validador de FileStructureValidator que solo permite estructuras de organización y de estructuras de definición
     */
    public void countLinesInFile() {
        ReaderFile readerFile = new ReaderFile();
        List<String> lines = readerFile.readFileLines(file);
        if (this.file.isFile() && this.file.getName().endsWith(".java")) {
            try {
                codeValidationContext = new CodeValidationContext();
                codeValidationContext.setStandardValidator(new FileStructureValidator(codeValidationContext));
                codeValidationContext.validate(lines);
                this.codeSegment = new CodeSegment(codeValidationContext.getPhysicalLines(), codeValidationContext.getLogicalLines());
                this.codeSegment.setTitle(file.getName());
                
            } catch (Exception e) {
                System.out.println(this.file.getName());
                System.err.println(e.getMessage());
            }
        } else{
            System.out.println(this.file.getName() + " no es un archivo con extensión válida");
        }
        
        
        
    }

    public CodeSegment getCodeSegment(){
        return this.codeSegment;
    }
}
