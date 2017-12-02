package kmitl.project.surasee2012.eatrightnow.validator.userActivityValidator;

public class UndefinedUserActivityValidator implements UserActivityValidator {
    @Override
    public boolean isValid(String input) {
        return input.equals("นั่งทำงานอยู่กับที่") || input.equals("เดินบ้างเล็กน้อย ทำงานออฟฟิศ")
                || input.equals("เคลื่อนไหวตลอดเวลา") || input.equals("เล่นกีฬาอย่างหนัก")
                || input.equals("นักกีฬา ทำงานที่ใช้แรงงานมาก");
    }
}
