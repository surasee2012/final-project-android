package kmitl.project.surasee2012.eatrightnow.validator.weightValidator;

public class Over635WeightValidator implements WeightValidator {
    @Override
    public boolean isValid(Double input) {
        return input <= 635;
    }
}
