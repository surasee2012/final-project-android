package kmitl.project.surasee2012.eatrightnow.validator.userGenderValidator;

public class NullUserGenderValidator implements UserGenderValidator {

    @Override
    public boolean isValid(String input) {
        return input != null;
    }
}
