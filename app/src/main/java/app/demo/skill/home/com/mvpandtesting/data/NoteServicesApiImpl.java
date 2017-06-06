package app.demo.skill.home.com.mvpandtesting.data;

import android.os.Handler;
import android.support.v4.util.ArrayMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class NoteServicesApiImpl implements NoteServicesApi {

  private static final int SERVICE_LATENCY_IN_MILLIS = 2000 ;
  private static final ArrayMap<String , Note> NOTES_SERVICE_DATA = NoteServiceApiEndPoint.loadPersistedNotes();


  @Override public void getAllNotes(final NoteServiceCallback<List<Note>> noteCallback) {
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override public void run() {
        noteCallback.onLoaded(new ArrayList<Note>(NOTES_SERVICE_DATA.values()));
      }
    } , SERVICE_LATENCY_IN_MILLIS);
  }

  @Override public void getNote(String noteId, NoteServiceCallback<Note> noteCallback) {
      Note note = NOTES_SERVICE_DATA.get(noteId);
      noteCallback.onLoaded(note);
  }

  @Override public void saveNote(Note note) {
      NOTES_SERVICE_DATA.put(note.getmID() , note);
  }
}
