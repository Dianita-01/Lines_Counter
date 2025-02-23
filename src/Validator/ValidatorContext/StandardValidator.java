package validator.ValidatorContext;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.CodeStandarException;
import model.CodeSegment;

public abstract class StandardValidator {

    protected CodeSegment codeSegment;

    public abstract boolean validate(List<String> lines) throws CodeStandarException;

    public boolean isCommentLine(String line){
        return line.trim().startsWith("//") 
            || line.trim().startsWith("*")
            || line.trim().startsWith("/*");
    }

    public boolean matchesPattern(String line, String structure){
        Pattern pattern = Pattern.compile(structure);
        Matcher matcher = pattern.matcher(line.trim());
        return matcher.matches();
    }

    public CodeSegment getCodeSegment() {
        return codeSegment;
    }

    public void setCodeSegment(CodeSegment codeSegment) {
        this.codeSegment = codeSegment;
    }


}
