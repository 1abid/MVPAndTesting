package app.demo.skill.home.com.mvpandtesting.addNote;

import android.support.annotation.NonNull;
import java.io.IOException;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public interface AddNoteContract {

  interface view{

    void showEmptyNoteError();

    void showNoteList();

    void openCamera(String saveTo);

    void showImagePreview(@NonNull String uri);

    void showImageError();

  }

  interface userActionListener{

    void saveNote(String noteTile , String noteDes);

    void captureImage() throws IOException ;

    void imageAvailable();

    void imageCapturedFailed();
  }
}
