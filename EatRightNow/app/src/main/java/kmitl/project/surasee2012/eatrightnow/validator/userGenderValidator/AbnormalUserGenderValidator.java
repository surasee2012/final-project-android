package kmitl.project.surasee2012.eatrightnow.validator.userGenderValidator;

public class AbnormalUserGenderValidator implements UserGenderValidator {

    @Override
    public boolean isValid(String input) {
        return input.equals("ชาย") || input.equals("หญิง");
    }
}
