package kmitl.project.surasee2012.eatrightnow.validator.userAgeValidator;

public class ZeroUserAgeValidator implements UserAgeValidator {

    @Override
    public boolean isValid(Integer input) {
        return input != 0;
    }
}
