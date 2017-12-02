package kmitl.project.surasee2012.eatrightnow.validator.userWeightValidator;

public class ZeroUserWeightValidator implements UserWeightValidator {
    @Override
    public boolean isValid(Double input) {
        return input != 0;
    }
}
