package app.demo.skill.home.com.mvpandtesting.noteDetail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import app.demo.skill.home.com.mvpandtesting.data.Note;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 6/6/17.
 * mail : la4508@gmail.com
 */

public class NoteDetailPresenter implements NoteDetailContract.userActionListener {

  private NoteRepository mNoteRepo ;
  private NoteDetailContract.view mNoteDetailView ;

  public NoteDetailPresenter(@NonNull NoteRepository mNoteRepo, @NonNull NoteDetailContract.view mNoteDetailView) {
    this.mNoteRepo = checkNotNull(mNoteRepo);
    this.mNoteDetailView = checkNotNull(mNoteDetailView);
  }

  @Override public void openNote(@Nullable String noteId) {
    if(noteId == null || noteId.isEmpty()) {
      mNoteDetailView.showMissingNote();
      return;
    }


    mNoteDetailView.setProgressIndicator(true);

     mNoteRepo.getNote(noteId, new NoteRepository.GetNoteCallback() {
      @Override public void onNoteLoaded(Note note) {
        mNoteDetailView.setProgressIndicator(false);

        if(null == note)
          return;

        shoeNoteDetail(note);
      }
    });
  }

  private void shoeNoteDetail(Note note) {

    String noteTitle = note.getmNoteTitle();
    String noteDetail = note.getmNoteDetail();
    String imgUrl = note.getmImagePth();

    if(noteTitle == null || noteTitle.isEmpty())
      mNoteDetailView.hideTitle();
    else
      mNoteDetailView.showTitle(noteTitle);

    if(noteDetail.isEmpty() || noteDetail.isEmpty())
      mNoteDetailView.hideDescription();
    else
      mNoteDetailView.showDescription(noteDetail);

    if(imgUrl == null || imgUrl.isEmpty())
      mNoteDetailView.hideImage();
    else
      mNoteDetailView.showImage(imgUrl);
  }
}
