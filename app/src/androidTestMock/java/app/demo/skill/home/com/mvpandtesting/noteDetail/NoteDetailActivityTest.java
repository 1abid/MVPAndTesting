package app.demo.skill.home.com.mvpandtesting.noteDetail;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import app.demo.skill.home.com.mvpandtesting.R;
import app.demo.skill.home.com.mvpandtesting.data.FakeNoteServiceApiImpl;
import app.demo.skill.home.com.mvpandtesting.data.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static app.demo.skill.home.com.mvpandtesting.ImageViewHasDrawableMatcher.hasDrawable;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by VutkaBilai on 6/9/17.
 * mail : la4508@gmail.com
 */

@RunWith(AndroidJUnit4.class) @LargeTest public class NoteDetailActivityTest {

  private static final String TEST_NOTE_TITLE = "demo title";
  private static final String TEST_NOTE_DES = "demo description";
  private static final String TEST_NOTE_IMG = "file:///android_asset/atsl-logo.png";

  private static final Note note = new Note(TEST_NOTE_TITLE, TEST_NOTE_DES, TEST_NOTE_IMG);

  @Rule public ActivityTestRule<NoteDetailActivity> noteDetailActivityActivityTestRule =
      new ActivityTestRule<>(NoteDetailActivity.class, true, false);

  @Before public void initNoteDetailActivityTestWithStubbedNote() {

    FakeNoteServiceApiImpl.addNote(note);

    Intent startIntent = new Intent();
    startIntent.putExtra(NoteDetailActivity.EXTRA_NOTE_ID, note.getmID());

    noteDetailActivityActivityTestRule.launchActivity(startIntent);

    registerIdelingResource();
  }

  @Test public void notesDetailIsShownInUI() {

    onView(withId(R.id.note_detail_title)).check(matches(withText(TEST_NOTE_TITLE)));
    onView(withId(R.id.note_detail_description)).check(matches(withText(TEST_NOTE_DES)));
    onView(withId(R.id.note_detail_image)).check(matches(allOf(hasDrawable(), isDisplayed())));
  }

  @After public void unregisterIdleingResources() {
    Espresso.unregisterIdlingResources(
        noteDetailActivityActivityTestRule.getActivity().getCountingIdlingResource());
  }

  private void registerIdelingResource() {
    Espresso.registerIdlingResources(
        noteDetailActivityActivityTestRule.getActivity().getCountingIdlingResource());
  }
}
