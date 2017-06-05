package app.demo.skill.home.com.mvpandtesting.notes;

import android.support.annotation.NonNull;
import app.demo.skill.home.com.mvpandtesting.data.Note;
import java.util.List;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class NoteContract {

  public interface view {
    void showProgress(boolean show);

    void loadNotes(List<Note> notes);

    void showAddNote();
  }

  public interface userActionListener {
    void loadNote(boolean forceUpdate);
    void addNewNote();
    void showNoteDetail(@NonNull Note requestedNote);
  }
}
