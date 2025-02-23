package files;
import java.io.File;
import java.util.List;

import model.CodeSegment;
import readers.ReaderFile;

import validator.ValidatorContext.CodeValidationContext;
import validator.validatorControlers.FileStructureValidator;

public class FileCounter {

    private File file;
    private CodeValidationContext codeValidationContext;
    private CodeSegment codeSegment;

    public FileCounter(File file) {
       this.file = file;
    }

    public void countLinesInFile() {
        ReaderFile readerFile = new ReaderFile();
        List<String> lines = readerFile.readFileLines(file);
        countLogicalAndPyshicalLines(lines);
        showResults(); 
    }

    public void countLogicalAndPyshicalLines(List<String> lines) {
        try {
            codeValidationContext = new CodeValidationContext();
            codeValidationContext.setStandardValidator(new FileStructureValidator(codeValidationContext));
            codeValidationContext.validate(lines);
            this.codeSegment = new CodeSegment(codeValidationContext.getPhysicalLines(), codeValidationContext.getLogicalLines());
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void showResults(){
        System.out.println(this.file.getName());
        System.out.println("Lineas físicas: " + codeSegment.getPhysicalLines());
        System.out.println("Lineas lógicas: " + codeSegment.getLogicalLines());
    }
}
