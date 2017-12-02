package kmitl.project.surasee2012.eatrightnow.validator.heightValidator;

public class NullHeightValidator implements HeightValidator {
    @Override
    public boolean isValid(Double input) {
        return input != null;
    }
}
