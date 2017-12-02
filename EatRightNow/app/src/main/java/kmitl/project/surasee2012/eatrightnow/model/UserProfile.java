package kmitl.project.surasee2012.eatrightnow.model;

import java.util.ArrayList;
import java.util.List;

import kmitl.project.surasee2012.eatrightnow.validator.ageValidator.AgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.ageValidator.NullAgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.ageValidator.Over123AgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.ageValidator.ZeroAgeValidator;
import kmitl.project.surasee2012.eatrightnow.validator.genderValidator.AbnormalGenderValidator;
import kmitl.project.surasee2012.eatrightnow.validator.genderValidator.GenderValidator;
import kmitl.project.surasee2012.eatrightnow.validator.genderValidator.NullGenderValidator;
import kmitl.project.surasee2012.eatrightnow.validator.heightValidator.HeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.heightValidator.NullHeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.heightValidator.Over272HeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.heightValidator.Under55HeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userActivityValidator.NullUserActivityValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userActivityValidator.UndefinedUserActivityValidator;
import kmitl.project.surasee2012.eatrightnow.validator.userActivityValidator.UserActivityValidator;
import kmitl.project.surasee2012.eatrightnow.validator.weightValidator.NullWeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.weightValidator.Over635WeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.weightValidator.WeightValidator;
import kmitl.project.surasee2012.eatrightnow.validator.weightValidator.ZeroWeightValidator;

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
        List<WeightValidator> weightValidators = new ArrayList<>();
        weightValidators.add(new NullWeightValidator());
        weightValidators.add(new ZeroWeightValidator());
        weightValidators.add(new Over635WeightValidator());
        for (WeightValidator weightValidator: weightValidators) {
            if (!weightValidator.isValid(weight)) {
                throw new Exception();
            }
        }
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) throws Exception {
        List<HeightValidator> heightValidators = new ArrayList<>();
        heightValidators.add(new NullHeightValidator());
        heightValidators.add(new Under55HeightValidator());
        heightValidators.add(new Over272HeightValidator());
        for (HeightValidator heightValidator: heightValidators) {
            if (!heightValidator.isValid(height)) {
                throw new Exception();
            }
        }
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) throws Exception{
        List<AgeValidator> ageValidators = new ArrayList<>();
        ageValidators.add(new NullAgeValidator());
        ageValidators.add(new ZeroAgeValidator());
        ageValidators.add(new Over123AgeValidator());
        for (AgeValidator ageValidator: ageValidators) {
            if (!ageValidator.isValid(age)) {
                throw new Exception();
            }
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws Exception {
        List<GenderValidator> genderValidators = new ArrayList<>();
        genderValidators.add(new NullGenderValidator());
        genderValidators.add(new AbnormalGenderValidator());
        for (GenderValidator genderValidator: genderValidators) {
            if (!genderValidator.isValid(gender)) {
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
