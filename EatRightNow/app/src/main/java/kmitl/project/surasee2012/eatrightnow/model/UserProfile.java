package kmitl.project.surasee2012.eatrightnow.model;

import java.util.ArrayList;
import java.util.List;

import kmitl.project.surasee2012.eatrightnow.validator.userAgeValidator.UserAgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userAgeValidator.NullUserAgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userAgeValidator.Over123UserAgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userAgeValidator.ZeroUserAgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userGenderValidator.AbnormalUserGenderValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userGenderValidator.UserGenderValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userGenderValidator.NullUserGenderValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userHeightValidator.UserHeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userHeightValidator.NullUserHeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userHeightValidator.Over272UserHeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userHeightValidator.Under55UserHeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userActivityValidator.NullUserActivityValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userActivityValidator.UndefinedUserActivityValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userActivityValidator.UserActivityValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userWeightValidator.NullUserWeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userWeightValidator.Over635UserWeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userWeightValidator.UserWeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userWeightValidator.ZeroUserWeightValidator;

public class UserProfile {

    private Double weight;
    private Double height;
    private Integer age;
    private String gender;
    private String userActivity;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) throws Exception {
        List<UserWeightValidator> userWeightValidators = new ArrayList<>();
        userWeightValidators.add(new NullUserWeightValidator());
        userWeightValidators.add(new ZeroUserWeightValidator());
        userWeightValidators.add(new Over635UserWeightValidator());
        for (UserWeightValidator userWeightValidator : userWeightValidators) {
            if (!userWeightValidator.isValid(weight)) {
                throw new Exception();
            }
        }
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) throws Exception {
        List<UserHeightValidator> userHeightValidators = new ArrayList<>();
        userHeightValidators.add(new NullUserHeightValidator());
        userHeightValidators.add(new Under55UserHeightValidator());
        userHeightValidators.add(new Over272UserHeightValidator());
        for (UserHeightValidator userHeightValidator : userHeightValidators) {
            if (!userHeightValidator.isValid(height)) {
                throw new Exception();
            }
        }
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) throws Exception{
        List<UserAgeValidator> userAgeValidators = new ArrayList<>();
        userAgeValidators.add(new NullUserAgeValidator());
        userAgeValidators.add(new ZeroUserAgeValidator());
        userAgeValidators.add(new Over123UserAgeValidator());
        for (UserAgeValidator userAgeValidator: userAgeValidators) {
            if (!userAgeValidator.isValid(age)) {
                throw new Exception();
            }
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws Exception {
        List<UserGenderValidator> userGenderValidators = new ArrayList<>();
        userGenderValidators.add(new NullUserGenderValidator());
        userGenderValidators.add(new AbnormalUserGenderValidator());
        for (UserGenderValidator userGenderValidator : userGenderValidators) {
            if (!userGenderValidator.isValid(gender)) {
                throw new Exception();
            }
        }
        this.gender = gender;
    }

    public String getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(String userActivity) throws Exception {
        List<UserActivityValidator> userActivityValidators = new ArrayList<>();
        userActivityValidators.add(new NullUserActivityValidator());
        userActivityValidators.add(new UndefinedUserActivityValidator());
        for (UserActivityValidator userActivityValidator: userActivityValidators) {
            if (!userActivityValidator.isValid(userActivity)) {
                throw new Exception();
            }
        }
        this.userActivity = userActivity;
    }
}
