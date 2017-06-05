package app.demo.skill.home.com.mvpandtesting.notes;

import android.support.annotation.NonNull;
import app.demo.skill.home.com.mvpandtesting.data.Note;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepository;
import app.demo.skill.home.com.mvpandtesting.notes.NoteContract.userActionListener;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class NotePresenter implements userActionListener {

  private NoteRepository mNoteRepository ;
  private NoteContract.view mNotesView ;

  public NotePresenter(@NonNull NoteRepository mNoteRepository, @NonNull NoteContract.view mNotesView) {
    this.mNoteRepository = checkNotNull(mNoteRepository);
    this.mNotesView = checkNotNull(mNotesView);
  }

  @Override public void loadNote(boolean forceUpdate) {
      mNotesView.showProgress(true);
  }

  @Override public void addNewNote() {

  }

  @Override public void showNoteDetail(@NonNull Note requestedNote) {

  }
}
