package kmitl.project.surasee2012.eatrightnow.validator.ageValidator;

public class NullAgeValidator implements AgeValidator {
    @Override
    public boolean isValid(Integer input) {
        return input != null;
    }
}
