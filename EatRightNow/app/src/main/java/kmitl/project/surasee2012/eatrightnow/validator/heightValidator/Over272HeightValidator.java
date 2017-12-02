package kmitl.project.surasee2012.eatrightnow.validator.heightValidator;

public class Over272HeightValidator implements HeightValidator {
    @Override
    public boolean isValid(Double input) {
        return input <= 272;
    }
}
