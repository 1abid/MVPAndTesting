package app.demo.skill.home.com.mvpandtesting.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public interface NoteRepository {

  interface LoadNotesCallback{

    void onNotesLoaded(List<Note> notes);

  }

  interface GetNoteCallback{

    void onNoteLoaded(Note note);

  }

  void getNotes(@NonNull LoadNotesCallback loadNotesCallback) ;

  void getNote(@NonNull String noteId , @NonNull GetNoteCallback getNoteCallback) ;

  void saveNote(@Nullable Note note) ;

  void refreshData() ;
}
