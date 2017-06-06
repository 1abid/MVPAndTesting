package app.demo.skill.home.com.mvpandtesting.addNote;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class AddNoteFragment extends Fragment implements AddNoteContract.view {

  private static AddNoteFragment instance ;

  public AddNoteFragment() {
  }

  public static AddNoteFragment getInstance(){

    if(instance == null)
      instance = new AddNoteFragment();


    return instance ;
  }

  @Override public void showEmptyNoteError() {

  }

  @Override public void showNoteList() {

  }

  @Override public void openCamera(String saveTo) {

  }

  @Override public void showImagePreview(@NonNull String uri) {

  }

  @Override public void showImageError() {

  }
}
