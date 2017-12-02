package kmitl.project.surasee2012.eatrightnow.validator.weightValidator;

public class ZeroWeightValidator implements WeightValidator {
    @Override
    public boolean isValid(Double input) {
        return input != 0;
    }
}
