package axell.com.androidmvpwithfirebasedatabase;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.UUID;

import axell.com.androidmvpwithfirebasedatabase.view.HomeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    public HomeActivity homeActivity;

    @Before
    public void setUp() throws Exception {
        homeActivity = homeActivityActivityTestRule.getActivity();
        Thread.sleep(3000);
    }

    @Test
    public void homeActivityOpenedForFirstTimeAndShowDataToRecyclerView(){
        onView(withId(R.id.recyclerViewUser)).check(matches(hasDescendant(withText("sip"))));
    }

    @Test
    public void addNewUser(){
        final String randomStringUsername = UUID.randomUUID().toString();
        onView(withId(R.id.btnShowDialogAddUser)).perform(click());
        onView(withId(R.id.txtInputUsername)).check(matches(isDisplayed()));
        onView(withId(R.id.txtInputUsername)).perform(typeText(randomStringUsername), closeSoftKeyboard());
        onView(withId(R.id.btnAddUser)).perform(click());
        onView(withId(R.id.recyclerViewUser)).check(matches(hasDescendant(withText(randomStringUsername))));
    }

    @Test
    public void clickItem() {
        onView(withId(R.id.recyclerViewUser)).perform(actionOnItem(hasDescendant(withText("sip")), click()));
        onView(withText("Clicked item : sip")).inRoot(withDecorView(not(is(homeActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
