package kmitl.project.surasee2012.eatrightnow;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import kmitl.project.surasee2012.eatrightnow.activity.MainActivity;
import kmitl.project.surasee2012.eatrightnow.sqliteDB.FoodDbAdapter;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

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
    }
}
