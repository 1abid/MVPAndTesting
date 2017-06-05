package app.demo.skill.home.com.mvpandtesting.data;

import java.util.List;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public interface NoteServicesApi {

  interface NoteServiceCallback<T> {
    void onLoaded(T notes);
  }

  void getAllNotes(NoteServiceCallback<List<Note>> noteCallback);

  void getNote(String noteId, NoteServiceCallback<Note> noteCallback);

  void saveNote(Note note);
}
