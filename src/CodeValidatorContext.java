public class CodeValidatorContext {
    private SpecificRules validator;

    public void setValidator(SpecificRules validator) {
        this.validator = validator;
    }

    public boolean validateCode(String code) {
        if (validator == null) {
            System.out.println("No se ha establecido un validador.");
            return false;
        }
        return validator.validate(code);
    }
}