package app.demo.skill.home.com.mvpandtesting.notes;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import app.demo.skill.home.com.mvpandtesting.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.base.Preconditions.checkArgument;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by VutkaBilai on 6/9/17.
 * mail : la4508@gmail.com
 */

@RunWith(AndroidJUnit4.class) @LargeTest public class NotesActivityTest {

  @Rule public ActivityTestRule<NotesActivity> mNotesActivityTestRule =
      new ActivityTestRule<>(NotesActivity.class);

  /**
   * A custom {@link Matcher} which matches an item in a {@link RecyclerView} by its text.
   *
   * <p>
   * View constraints:
   * <ul>
   * <li>View must be a child of a {@link RecyclerView}
   * <ul>
   *
   * @param itemText the text to match
   * @return Matcher that matches text in the given view
   */
  //TODO create a custom matcher which will find a item from RecyclerView by it's text
  private Matcher<View> withItemTet(final String itemText) {
    checkArgument(!TextUtils.isEmpty(itemText), "item text can not be empty");

    return new TypeSafeMatcher<View>() {
      @Override protected boolean matchesSafely(View item) {
        return allOf(isDescendantOfA(isAssignableFrom(RecyclerView.class)),
            withText(itemText)).matches(item);
      }

      @Override public void describeTo(Description description) {
        description.appendText("is isDecendantofA RV with text" + itemText);
      }
    };
  }

  @Test public void clickOnFabOpenAddNoteScreen() {

    onView(withId(R.id.fab_add_notes)).perform(click());

    onView(withId(R.id.add_note_title)).check(matches(isDisplayed()));
  }

  @Test public void clickOnFabOpenAddNoteAndCreateSaveNote() {

    String newNoteTitle = "Espresso";
    String newNoteDescription = "UI testing for Android";

    onView(withId(R.id.fab_add_notes)).perform(click());

    onView(withId(R.id.add_note_title)).check(matches(isDisplayed()));

    onView(withId(R.id.add_note_title)).perform(typeText(newNoteTitle), closeSoftKeyboard());
    onView(withId(R.id.add_note_description)).perform(typeText(newNoteDescription),
        closeSoftKeyboard());

    onView(withId(R.id.fab_add_notes)).perform(click());

    onView(withItemTet(newNoteDescription)).check(matches(isDisplayed()));
  }

}
