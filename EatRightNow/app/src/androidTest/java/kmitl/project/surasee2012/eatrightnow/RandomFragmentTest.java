package kmitl.project.surasee2012.eatrightnow;

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import kmitl.project.surasee2012.eatrightnow.activity.MainActivity;
import kmitl.project.surasee2012.eatrightnow.model.UserProfile;
import kmitl.project.surasee2012.eatrightnow.preference.CommonSharePreference;
import kmitl.project.surasee2012.eatrightnow.sqliteDB.FoodDbAdapter;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

public class RandomFragmentTest {

    private FoodDbAdapter foodDbAdapter;
    private CommonSharePreference preference;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup(){
        foodDbAdapter = new FoodDbAdapter(InstrumentationRegistry.getTargetContext());
        foodDbAdapter.revertDefaultDatabase();

        preference = new CommonSharePreference(InstrumentationRegistry.getTargetContext());
        preference.clear();
    }

    @Test
    public void noFilterRandomTest(){
        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void favoriteFilterRandomTest(){
        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ของโปรด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("ของโปรด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void caloriesFilterRandomTest(){
        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("แคลอรี่ที่เหมาะสม"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("แคลอรี่ที่เหมาะสม"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void eggFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ไข่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ไข่"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void eggFavoriteFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ไข่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ไข่"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ของโปรด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("ของโปรด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void eggCaloriesFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ไข่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ไข่"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("แคลอรี่ที่เหมาะสม"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("แคลอรี่ที่เหมาะสม"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void chickenDuckFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ไก่, เป็ด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ไก่, เป็ด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void chickenDuckFavoriteFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ไก่, เป็ด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ไก่, เป็ด"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ของโปรด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("ของโปรด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void chickenDuckCaloriesFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ไก่, เป็ด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ไก่, เป็ด"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("แคลอรี่ที่เหมาะสม"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("แคลอรี่ที่เหมาะสม"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void pockFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("หมู"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("หมู"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void pockFavoriteFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("หมู"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("หมู"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ของโปรด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("ของโปรด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void pockCaloriesFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("หมู"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("หมู"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("แคลอรี่ที่เหมาะสม"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("แคลอรี่ที่เหมาะสม"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void beefFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("เนื้อ"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("เนื้อ"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void beefFavoriteFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("เนื้อ"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("เนื้อ"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ของโปรด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("ของโปรด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void beefCaloriesFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("เนื้อ"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("เนื้อ"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("แคลอรี่ที่เหมาะสม"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("แคลอรี่ที่เหมาะสม"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void fishFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ปลา"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ปลา"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void fishFavoriteFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ปลา"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ปลา"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ของโปรด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("ของโปรด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void fishCaloriesFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ปลา"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ปลา"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("แคลอรี่ที่เหมาะสม"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("แคลอรี่ที่เหมาะสม"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void seaFoodFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("อาหารทะเลอื่น ๆ"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("อาหารทะเลอื่น ๆ"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void seaFoodFavoriteFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("อาหารทะเลอื่น ๆ"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("อาหารทะเลอื่น ๆ"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ของโปรด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("ของโปรด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void seaFoodCaloriesFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("อาหารทะเลอื่น ๆ"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("อาหารทะเลอื่น ๆ"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("แคลอรี่ที่เหมาะสม"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("แคลอรี่ที่เหมาะสม"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void vegetablesFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ผัก, ผลไม้"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ผัก, ผลไม้"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void vegetablesFavoriteFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ผัก, ผลไม้"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ผัก, ผลไม้"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ของโปรด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("ของโปรด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void vegetablesCaloriesFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ผัก, ผลไม้"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ผัก, ผลไม้"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("แคลอรี่ที่เหมาะสม"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("แคลอรี่ที่เหมาะสม"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void riceFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ข้าว"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ข้าว"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void riceFavoriteFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ข้าว"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ข้าว"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ของโปรด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("ของโปรด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void riceCaloriesFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ข้าว"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("ข้าว"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("แคลอรี่ที่เหมาะสม"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("แคลอรี่ที่เหมาะสม"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void noodleFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("เส้นก๋วยเตี๋ยว"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("เส้นก๋วยเตี๋ยว"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void noodleFavoriteFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("เส้นก๋วยเตี๋ยว"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("เส้นก๋วยเตี๋ยว"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ของโปรด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("ของโปรด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void noodleCaloriesFilterRandomTest(){
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("เส้นก๋วยเตี๋ยว"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tagSpinner)).check(matches(withSpinnerText(containsString("เส้นก๋วยเตี๋ยว"))));

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("แคลอรี่ที่เหมาะสม"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("แคลอรี่ที่เหมาะสม"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void savedFavoriteFilterRandomTest(){
        foodDbAdapter.setFavorite(1, 1);

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ของโปรด"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("ของโปรด"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void savedProfileCaloriesFilterRandomTest(){
        UserProfile userProfile = new UserProfile();
        try {
            userProfile.setWeight(56.0);
            userProfile.setHeight(176.0);
            userProfile.setAge(25);
            userProfile.setGender("ชาย");
            userProfile.setUserActivity("เคลื่อนไหวตลอดเวลา");
        } catch (Exception e) {

        }

        preference.save("UserProfile", userProfile);

        onView(withId(R.id.specialSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("แคลอรี่ที่เหมาะสม"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.specialSpinner)).check(matches(withSpinnerText(containsString("แคลอรี่ที่เหมาะสม"))));

        try {
            onView(withId(R.id.randomBtn)).perform(click());
        } catch (NoMatchingViewException e) {
            onView(withText(R.string.random)).perform(click());
        }

        onView(withId(R.id.randomItemLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.findMoreBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.recomResBtn)).check(matches(isDisplayed()));
    }
}
