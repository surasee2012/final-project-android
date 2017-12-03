package kmitl.project.surasee2012.eatrightnow.validator.userHeightValidator;

public class Under55UserHeightValidator implements UserHeightValidator {

    @Override
    public boolean isValid(Double input) {
        return input >= 55;
    }
}
