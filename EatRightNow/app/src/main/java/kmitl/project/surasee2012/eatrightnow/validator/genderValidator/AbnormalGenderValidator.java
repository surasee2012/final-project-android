package kmitl.project.surasee2012.eatrightnow.validator.genderValidator;

public class AbnormalGenderValidator implements GenderValidator{
    @Override
    public boolean isValid(String input) {
        return input.equals("ชาย") || input.equals("หญิง");
    }
}
