package kmitl.project.surasee2012.eatrightnow.validator.ageValidator;

public class Over123AgeValidator implements AgeValidator {
    @Override
    public boolean isValid(Integer input) {
        return input <= 123;
    }
}
