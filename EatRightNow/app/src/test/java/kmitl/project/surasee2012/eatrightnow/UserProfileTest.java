package kmitl.project.surasee2012.eatrightnow;

import org.junit.Test;

import kmitl.project.surasee2012.eatrightnow.model.UserProfile;

import static org.junit.Assert.assertEquals;

public class UserProfileTest {
    @Test(expected = Exception.class)
    public void nullWeightSetTest() throws Exception{
        UserProfile userProfile = new UserProfile();
        Double weight = null;
        userProfile.setWeight(weight);
    }

    @Test(expected = Exception.class)
    public void zeroWeightTest() throws Exception{
        UserProfile userProfile = new UserProfile();
        Double weight = 0.0;
        userProfile.setWeight(weight);
    }

    @Test(expected = Exception.class)
    public void over635WeightTest() throws Exception{
        UserProfile userProfile = new UserProfile();
        Double weight = 636.0;
        userProfile.setWeight(weight);
    }

    @Test
    public void normalWeightTest() throws Exception{
        UserProfile userProfile = new UserProfile();
        Double weight = 63.0;
        userProfile.setWeight(weight);
        assertEquals(weight, userProfile.getWeight());
    }

    @Test(expected = Exception.class)
    public void nullHeightSetTest() throws Exception{
        UserProfile userProfile = new UserProfile();
        Double height = null;
        userProfile.setHeight(height);
    }

    @Test(expected = Exception.class)
    public void under55HeightTest() throws Exception{
        UserProfile userProfile = new UserProfile();
        Double height = 43.0;
        userProfile.setHeight(height);
    }

    @Test(expected = Exception.class)
    public void over272HeightTest() throws Exception{
        UserProfile userProfile = new UserProfile();
        Double height = 273.0;
        userProfile.setHeight(height);
    }

    @Test
    public void normalHeightTest() throws Exception{
        UserProfile userProfile = new UserProfile();
        Double height = 174.0;
        userProfile.setHeight(height);
        assertEquals(height, userProfile.getHeight());
    }
}
