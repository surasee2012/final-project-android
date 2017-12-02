package kmitl.project.surasee2012.eatrightnow.validator.userAgeValidator;

public class NullUserAgeValidator implements UserAgeValidator {
    @Override
    public boolean isValid(Integer input) {
        return input != null;
    }
}
