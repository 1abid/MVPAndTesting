package app.demo.skill.home.com.mvpandtesting.noteDetail;

import android.support.annotation.Nullable;

/**
 * Created by Vutka Bilai on 6/6/17.
 * email : la4508@gmail.com
 * project Name : MVPAndTesting
 */

public interface NoteDetailContract {

  interface view {
    void setProgressIndicator(boolean active);

    void showMissingNote();

    void hideTitle();

    void showTitle(String title);

    void showImage(String imageUrl);

    void hideImage();

    void hideDescription();

    void showDescription(String description);
  }

  interface userActionListener{
    void openNote(@Nullable String noteId);
  }
}
