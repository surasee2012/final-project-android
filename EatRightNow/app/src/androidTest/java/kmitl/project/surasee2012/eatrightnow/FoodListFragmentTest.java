package kmitl.project.surasee2012.eatrightnow;

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import kmitl.project.surasee2012.eatrightnow.activity.MainActivity;
import kmitl.project.surasee2012.eatrightnow.sqliteDB.FoodDbAdapter;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

public class FoodListFragmentTest  {

    private FoodDbAdapter foodDbAdapter;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup(){
        foodDbAdapter = new FoodDbAdapter(InstrumentationRegistry.getTargetContext());
        foodDbAdapter.revertDefaultDatabase();

        onView(withId(R.id.container)).perform(swipeLeft());
    }

    @Test
    public void addFavoriteFoodTest() {
        onView(withId(R.id.foodList)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.favoriteImg)));

        onView(withText("เพิ่มก๋วยจั๊บในของโปรดเรียบร้อยแล้ว")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        foodDbAdapter.revertDefaultDatabase();
    }

    @Test
    public void nullAddFoodTest() {
        SystemClock.sleep(500);
        onView(withId(R.id.action_add)).perform(click());

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void nullFoodNameAddFoodTest() {
        SystemClock.sleep(500);
        onView(withId(R.id.action_add)).perform(click());

        onView(withId(R.id.foodCalEt)).perform(replaceText(String.valueOf(350)));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void nullFoodCalAddFoodTest() {
        SystemClock.sleep(500);
        onView(withId(R.id.action_add)).perform(click());

        onView(withId(R.id.foodNameEt)).perform(replaceText("test"));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void normalAddFoodTest() {
        SystemClock.sleep(1000);
        onView(withId(R.id.action_add)).perform(click());

        onView(withId(R.id.foodNameEt)).perform(replaceText("test1"));
        onView(withId(R.id.foodCalEt)).perform(replaceText(String.valueOf(350)));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("เพิ่มtest1สำเร็จแล้ว")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void nullFoodCalEditFoodTest() {
        onView(withId(R.id.foodList)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.longClickChildViewWithId(R.id.foodNameItem)));

        onView(withText("แก้ไขอาหาร")).perform(click());

        onView(withId(R.id.foodCalEt)).perform(replaceText(""));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void normalFoodCalEditFoodTest() {
        onView(withId(R.id.foodList)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.longClickChildViewWithId(R.id.foodNameItem)));

        onView(withText("แก้ไขอาหาร")).perform(click());

        onView(withId(R.id.foodCalEt)).perform(replaceText(String.valueOf(750)));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("บันทึกก๋วยจั๊บสำเร็จแล้ว")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
