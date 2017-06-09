package app.demo.skill.home.com.mvpandtesting.notes;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;
import app.demo.skill.home.com.mvpandtesting.R;
import app.demo.skill.home.com.mvpandtesting.notes.NotesActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by VutkaBilai on 6/9/17.
 * mail : la4508@gmail.com
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AppNavigationTest {

  @Rule public ActivityTestRule<NotesActivity> notesActivityActivityTestRule = new ActivityTestRule<>(NotesActivity.class);

  @Before
  public void setUpTest(){
    Espresso.registerIdlingResources(notesActivityActivityTestRule.getActivity().getCountingIdlingResource());
  }


  @Test
  public void clickOnHomeButtonOpenNavigationBar(){

    onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)));

    String navigationDrawerContentDes = notesActivityActivityTestRule.getActivity().getString(android.support.v7.appcompat.R.string.abc_action_bar_up_description);

    onView(withContentDescription(navigationDrawerContentDes)).perform(click());

    onView(withId(R.id.drawer_layout)).check(matches(isOpen(Gravity.LEFT)));

  }

  @After
  public void tireDownTest(){
    Espresso.unregisterIdlingResources(notesActivityActivityTestRule.getActivity().getCountingIdlingResource());
  }
}
