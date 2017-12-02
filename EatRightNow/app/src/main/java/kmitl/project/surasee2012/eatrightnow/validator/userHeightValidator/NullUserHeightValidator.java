package kmitl.project.surasee2012.eatrightnow.validator.userHeightValidator;

public class NullUserHeightValidator implements UserHeightValidator {
    @Override
    public boolean isValid(Double input) {
        return input != null;
    }
}
