package kmitl.project.surasee2012.eatrightnow;

import org.junit.Test;

import kmitl.project.surasee2012.eatrightnow.model.UserProfile;

import static org.junit.Assert.assertEquals;

public class UserProfileTest {
    @Test(expected = Exception.class)
    public void nullWeightSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setWeight(null);
    }

    @Test(expected = Exception.class)
    public void zeroWeightSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setWeight(0.0);
    }

    @Test(expected = Exception.class)
    public void over635WeightSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setWeight(636.0);
    }

    @Test
    public void normalWeightSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setWeight(63.0);
        Double weight = 63.0;
        assertEquals(weight, userProfile.getWeight());
    }

    @Test(expected = Exception.class)
    public void nullHeightSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setHeight(null);
    }

    @Test(expected = Exception.class)
    public void under55HeightSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setHeight(43.0);
    }

    @Test(expected = Exception.class)
    public void over272HeightSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setHeight(273.0);
    }

    @Test
    public void normalHeightSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setHeight(174.0);
        Double height = 174.0;
        assertEquals(height, userProfile.getHeight());
    }

    @Test(expected = Exception.class)
    public void nullAgeSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setAge(null);
    }

    @Test(expected = Exception.class)
    public void zeroAgeSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setAge(0);
    }

    @Test(expected = Exception.class)
    public void over123AgeSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setAge(124);
    }

    @Test
    public void normalAgeSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setAge(24);
        Integer age = 24;
        assertEquals(age, userProfile.getAge());
    }

    @Test(expected = Exception.class)
    public void nullGenderSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setGender(null);
    }

    @Test(expected = Exception.class)
    public void abnormalGenderSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setGender("กระเทย");
    }

    @Test
    public void normalGenderSetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setGender("ชาย");
        String gender = "ชาย";
        assertEquals(gender, userProfile.getGender());
    }

    @Test(expected = Exception.class)
    public void nullUserActivitySetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserActivity(null);
    }

    @Test(expected = Exception.class)
    public void undefinedUserActivitySetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserActivity("นอนเฉย ๆ ไปวัน ๆ");
    }

    @Test
    public void normalUserActivitySetTest() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserActivity("เคลื่อนไหวตลอดเวลา");
        String userActivity = "เคลื่อนไหวตลอดเวลา";
        assertEquals(userActivity, userProfile.getUserActivity());
    }
}
