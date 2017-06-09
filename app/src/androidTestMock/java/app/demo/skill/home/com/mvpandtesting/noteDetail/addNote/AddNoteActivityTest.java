package app.demo.skill.home.com.mvpandtesting.noteDetail.addNote;

import android.app.Instrumentation;
import android.provider.MediaStore;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import app.demo.skill.home.com.mvpandtesting.ImageViewHasDrawableMatcher;
import app.demo.skill.home.com.mvpandtesting.R;
import app.demo.skill.home.com.mvpandtesting.addNote.AddNoteActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static app.demo.skill.home.com.mvpandtesting.ImageViewHasDrawableMatcher.hasDrawable;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by VutkaBilai on 6/9/17.
 * mail : la4508@gmail.com
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddNoteActivityTest {

  @Rule public IntentsTestRule<AddNoteActivity> addNoteActivityIntentsTestRule = new IntentsTestRule<>(AddNoteActivity.class);

  @Before
  public void initAddNoteActivityForTest(){
    Espresso.registerIdlingResources(addNoteActivityIntentsTestRule.getActivity().getCountingIdlingResource());
  }


  @Test
  public void test_add_note_with_empty_note_content_shows_error(){

    onView(withId(R.id.fab_add_notes)).perform(click());

    onView(withId(R.id.add_note_title)).perform(typeText(""));
    onView(withId(R.id.add_note_description)).perform(typeText(""));

    onView(withId(R.id.fab_add_notes)).perform(click());

    //verify snakBar has shown the error msg as expected
    //String errorMsg = getTargetContext().getString(R.string.empty_note_message);
    //onView(withText(errorMsg)).check(matches(isDisplayed()));
  }

  @Test
  public void add_Photo_To_Note_Stub_A_Intent_And_Add_Image_Thumb(){

    Instrumentation.ActivityResult result = createActivityResultStub();

    intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);

    onView(withId(R.id.add_note_image_thumbnail)).check((matches(not(isDisplayed()))));

    onView(withId(R.id.add_note_title)).perform(typeText("testing intent result") , closeSoftKeyboard());
    onView(withId(R.id.add_note_description)).perform(typeText("testing intent result description") , closeSoftKeyboard());

    selectOverFlowMenuAndPerformClick();

    onView(withId(R.id.add_note_image_thumbnail))
        .check(matches(allOf(hasDrawable() , isDisplayed())));
  }


  @After
  public void tierDownAddNoteActivityAfterTest(){
    Espresso.unregisterIdlingResources(addNoteActivityIntentsTestRule.getActivity().getCountingIdlingResource());
  }


  private void selectOverFlowMenuAndPerformClick(){
    openActionBarOverflowOrOptionsMenu(getTargetContext());
    onView(withText(getTargetContext().getString(R.string.take_picture))).perform(click());
  }

  private Instrumentation.ActivityResult createActivityResultStub(){
    return new Instrumentation.ActivityResult(AddNoteActivity.RESULT_OK , null);
  }
}
