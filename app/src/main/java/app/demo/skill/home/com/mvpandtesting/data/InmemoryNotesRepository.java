package app.demo.skill.home.com.mvpandtesting.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class InmemoryNotesRepository implements NoteRepository {

  private final NoteServicesApi mNoteServiceApi ;

  @VisibleForTesting List<Note> mCachedNotes;

  public InmemoryNotesRepository(NoteServicesApi noteServicesApi) {
    mNoteServiceApi = checkNotNull(noteServicesApi);
  }

  @Override public void getNotes(@NonNull final LoadNotesCallback loadNotesCallback) {
    checkNotNull(loadNotesCallback);

    if(mCachedNotes == null){

      mNoteServiceApi.getAllNotes(new NoteServicesApi.NoteServiceCallback<List<Note>>() {
        @Override public void onLoaded(List<Note> notes) {
          mCachedNotes = ImmutableList.copyOf(notes);
          loadNotesCallback.onNotesLoaded(mCachedNotes);
        }
      });

      return;
    }

    loadNotesCallback.onNotesLoaded(mCachedNotes);
  }

  @Override public void getNote(@NonNull String noteId ,@NonNull final GetNoteCallback getNoteCallback) {
    checkNotNull(noteId);
    checkNotNull(getNoteCallback);

    mNoteServiceApi.getNote(noteId, new NoteServicesApi.NoteServiceCallback<Note>() {
      @Override public void onLoaded(Note notes) {
        getNoteCallback.onNoteLoaded(notes);
      }
    });

  }

  @Override public void saveNote(@Nullable Note note) {
    checkNotNull(note);
    mNoteServiceApi.saveNote(note);
    refreshData();
  }

  @Override public void refreshData() {
    mCachedNotes = null ;
  }
}
