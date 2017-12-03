package kmitl.project.surasee2012.eatrightnow.validator.userHeightValidator;

public class Over272UserHeightValidator implements UserHeightValidator {

    @Override
    public boolean isValid(Double input) {
        return input <= 272;
    }
}
