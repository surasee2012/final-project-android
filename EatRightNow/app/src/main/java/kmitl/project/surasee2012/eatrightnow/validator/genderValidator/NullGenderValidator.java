package kmitl.project.surasee2012.eatrightnow.validator.genderValidator;

public class NullGenderValidator implements GenderValidator {
    @Override
    public boolean isValid(String input) {
        return input != null;
    }
}
