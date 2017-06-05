package app.demo.skill.home.com.mvpandtesting.data;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class Note {

  private final String mID;

  @Nullable private final String mNoteTitle;

  @NonNull private final String mNoteDetail;

  @Nullable private final String mImagePth;

  public Note(@NonNull String mNoteTitle, @NonNull String mNoteDetail) {
    this(mNoteTitle, mNoteDetail, null);
  }

  public Note(@NonNull String mNoteTitle, @NonNull String mNoteDetail, @Nullable String mImagePth) {
    mID = UUID.randomUUID().toString();
    this.mNoteTitle = mNoteTitle;
    this.mNoteDetail = mNoteDetail;
    this.mImagePth = mImagePth;
  }

  public String getmID() {
    return mID;
  }

  @Nullable public String getmNoteTitle() {
    return mNoteTitle;
  }

  @NonNull public String getmNoteDetail() {
    return mNoteDetail;
  }

  @Nullable public String getmImagePth() {
    return mImagePth;
  }

  public boolean isEmpty() {
    return (mNoteTitle == null || "".equals(mNoteTitle)) && (mNoteDetail == null || "".equals(
        mNoteDetail));
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT) @Override public boolean equals(Object obj) {
    if (obj == this) return true;

    if (obj == null || obj.getClass() != getClass()) return false;

    Note note = (Note) obj;

    return Objects.equals(note.getmID(), mID)
        && Objects.equals(note.getmNoteTitle(), mNoteTitle)
        && Objects.equals(note.getmNoteDetail(), mNoteDetail)
        && Objects.equals(note.getmImagePth(), mImagePth);
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT) @Override public int hashCode() {
    return Objects.hashCode(this);
  }
}
