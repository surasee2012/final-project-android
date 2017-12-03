package kmitl.project.surasee2012.eatrightnow.validator.userActivityValidator;

public class NullUserActivityValidator implements UserActivityValidator{

    @Override
    public boolean isValid(String input) {
        return input != null;
    }
}
