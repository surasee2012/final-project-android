package kmitl.project.surasee2012.eatrightnow.validator.weightValidator;

public class NullWeightValidator implements WeightValidator {
    @Override
    public boolean isValid(Double input) {
        return input != null;
    }
}
