package kmitl.project.surasee2012.eatrightnow;

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import kmitl.project.surasee2012.eatrightnow.activity.MainActivity;
import kmitl.project.surasee2012.eatrightnow.preference.CommonSharePreference;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

public class ProfileFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup(){
        CommonSharePreference preference = new CommonSharePreference(InstrumentationRegistry.getTargetContext());
        preference.clear();

        onView(withId(R.id.container)).perform(swipeLeft());
        onView(withId(R.id.container)).perform(swipeLeft());
        SystemClock.sleep(500);
    }

    @Test
    public void emptyProfileTest() {
        onView(withId(R.id.action_save)).perform(click());
        SystemClock.sleep(500);

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void emptyWeightProfileTest() {
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(175)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(21)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นั่งทำงานอยู่กับที่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นั่งทำงานอยู่กับที่"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void zeroWeightProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(0)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(175)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(21)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นั่งทำงานอยู่กับที่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นั่งทำงานอยู่กับที่"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void over635WeightProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(636)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(175)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(21)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นั่งทำงานอยู่กับที่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นั่งทำงานอยู่กับที่"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void nullHeightProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(63)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(21)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นั่งทำงานอยู่กับที่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นั่งทำงานอยู่กับที่"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void under55HeightProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(63)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(54)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(21)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นั่งทำงานอยู่กับที่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นั่งทำงานอยู่กับที่"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void over272HeightProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(63)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(273)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(21)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นั่งทำงานอยู่กับที่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นั่งทำงานอยู่กับที่"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void nullAgeProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(63)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(174)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นั่งทำงานอยู่กับที่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นั่งทำงานอยู่กับที่"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void zeroAgeProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(63)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(174)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(0)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นั่งทำงานอยู่กับที่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นั่งทำงานอยู่กับที่"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void over123AgeProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(63)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(174)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(124)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นั่งทำงานอยู่กับที่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นั่งทำงานอยู่กับที่"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void abnormalGenderProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(63)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(174)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(23)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ยังไม่ระบุ"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ยังไม่ระบุ"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นั่งทำงานอยู่กับที่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นั่งทำงานอยู่กับที่"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void undefinedActivityProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(63)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(174)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(23)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ยังไม่ระบุ"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("ยังไม่ระบุ"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("ระวัง")).check(matches(isDisplayed()));
        onView(withText("ตกลง")).perform(click());
    }

    @Test
    public void maleNormalProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(63)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(174)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(23)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("เคลื่อนไหวตลอดเวลา"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("เคลื่อนไหวตลอดเวลา"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("บันทึกแล้ว")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void femaleNormalProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(44)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(162)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(19)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("หญิง"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("หญิง"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("เดินบ้างเล็กน้อย ทำงานออฟฟิศ"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("เดินบ้างเล็กน้อย ทำงานออฟฟิศ"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("บันทึกแล้ว")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void userActivity1ProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(78)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(180)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(30)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นั่งทำงานอยู่กับที่"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นั่งทำงานอยู่กับที่"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("บันทึกแล้ว")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void userActivity2ProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(60)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(170)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(26)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("หญิง"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("หญิง"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("เดินบ้างเล็กน้อย ทำงานออฟฟิศ"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("เดินบ้างเล็กน้อย ทำงานออฟฟิศ"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("บันทึกแล้ว")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void userActivity3ProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(49)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(159)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(20)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("หญิง"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("หญิง"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("เคลื่อนไหวตลอดเวลา"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("เคลื่อนไหวตลอดเวลา"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("บันทึกแล้ว")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void userActivity4ProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(55)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(175)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(21)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("เล่นกีฬาอย่างหนัก"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("เล่นกีฬาอย่างหนัก"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("บันทึกแล้ว")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void userActivity5ProfileTest() {
        onView(withId(R.id.weightEdt)).perform(replaceText(String.valueOf(80)));
        onView(withId(R.id.heightEdt)).perform(replaceText(String.valueOf(179)));
        onView(withId(R.id.ageEdt)).perform(replaceText(String.valueOf(22)));

        onView(withId(R.id.genderSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ชาย"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.genderSpinner)).check(matches(withSpinnerText(containsString("ชาย"))));

        onView(withId(R.id.activitySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("นักกีฬา ทำงานที่ใช้แรงงานมาก"))).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.activitySpinner)).check(matches(withSpinnerText(containsString("นักกีฬา ทำงานที่ใช้แรงงานมาก"))));

        onView(withId(R.id.action_save)).perform(click());

        onView(withText("บันทึกแล้ว")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
