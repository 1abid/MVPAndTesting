package app.demo.skill.home.com.mvpandtesting.data;

import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ArrayMap;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class FakeNoteServiceApiImpl implements NoteServicesApi {

  // TODO replace this with a new test specific data set.
  private static final ArrayMap<String, Note> NOTES_SERVICE_DATA = new ArrayMap();

  @Override public void getAllNotes(NoteServiceCallback<List<Note>> noteCallback) {
    noteCallback.onLoaded((Lists.newArrayList(NOTES_SERVICE_DATA.values())));
  }

  @Override public void getNote(String noteId, NoteServiceCallback<Note> noteCallback) {
    Note note = NOTES_SERVICE_DATA.get(noteId);
    noteCallback.onLoaded(note);
  }

  @Override public void saveNote(Note note) {
    NOTES_SERVICE_DATA.put(note.getmID(), note);
  }

  @VisibleForTesting public static void addNote(Note... notes) {
    for (Note note : notes) {
      NOTES_SERVICE_DATA.put(note.getmID() , note);
    }
  }
}
