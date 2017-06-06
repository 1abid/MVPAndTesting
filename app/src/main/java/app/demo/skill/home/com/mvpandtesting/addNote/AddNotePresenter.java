package app.demo.skill.home.com.mvpandtesting.addNote;

import android.support.annotation.NonNull;
import app.demo.skill.home.com.mvpandtesting.data.Note;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepository;
import app.demo.skill.home.com.mvpandtesting.util.ImgFile;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class AddNotePresenter implements AddNoteContract.userActionListener {


  @NonNull private final NoteRepository mNotesRepo ;
  @NonNull private final AddNoteContract.view mAddNoteView ;
  @NonNull private final ImgFile mImageFile ;

  public AddNotePresenter(@NonNull NoteRepository mNotesRepo,
      @NonNull AddNoteContract.view mAddNoteView, @NonNull ImgFile mImageFile) {
    this.mNotesRepo = checkNotNull(mNotesRepo);
    this.mAddNoteView = checkNotNull(mAddNoteView);
    this.mImageFile = checkNotNull(mImageFile);
  }



  @Override public void saveNote(String noteTile, String noteDes) {
      String imageUrl = null ;
      if(mImageFile.exists())
        imageUrl = mImageFile.getPath();

      Note newNote = new Note(noteTile , noteDes , imageUrl);
      if(newNote.isEmpty()) {
        mAddNoteView.showEmptyNoteError();
        return;
      }

      mNotesRepo.saveNote(newNote);
      mAddNoteView.showNoteList();
  }

  @Override public void captureImage() throws IOException {
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_" +timeStamp + "_" ;
    mImageFile.create(imageFileName , ".jpg");
    mAddNoteView.openCamera(mImageFile.getPath());
  }

  @Override public void imageAvailable() {
      if(mImageFile.exists()) {
        mAddNoteView.showImagePreview(mImageFile.getPath());
        return;
      }
      imageCapturedFailed();
  }

  @Override public void imageCapturedFailed() {
      mImageFile.delete();
      mAddNoteView.showImageError();
  }
}
