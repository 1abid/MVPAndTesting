package app.demo.skill.home.com.mvpandtesting.notes;

import android.support.annotation.NonNull;
import app.demo.skill.home.com.mvpandtesting.data.Note;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepository;
import app.demo.skill.home.com.mvpandtesting.notes.NoteContract.userActionListener;
import app.demo.skill.home.com.mvpandtesting.util.EspressoIdlingResource;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class NotePresenter implements userActionListener {

  private NoteRepository mNoteRepository;
  private NoteContract.view mNotesView;

  public NotePresenter(@NonNull NoteRepository mNoteRepository,
      @NonNull NoteContract.view mNotesView) {
    this.mNoteRepository = checkNotNull(mNoteRepository);
    this.mNotesView = checkNotNull(mNotesView);
  }

  @Override public void loadNote(boolean forceUpdate) {
    mNotesView.showProgress(true);
    if (forceUpdate) mNoteRepository.refreshData();

    EspressoIdlingResource.increment();

    mNoteRepository.getNotes(new NoteRepository.LoadNotesCallback() {
      @Override public void onNotesLoaded(List<Note> notes) {
        EspressoIdlingResource.decrement();

        mNotesView.showProgress(false);
        mNotesView.showNotes(notes);
      }
    });
  }

  @Override public void addNewNote() {

  }

  @Override public void showNoteDetail(@NonNull Note requestedNote) {

  }
}
