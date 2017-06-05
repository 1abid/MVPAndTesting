package app.demo.skill.home.com.mvpandtesting.util;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.VisibleForTesting;
import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class ImgFileImp implements ImgFile {

  @VisibleForTesting File mImageFile;

  @Override public void create(String name, String extension) throws IOException {
    File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

    mImageFile = File.createTempFile(name, extension, storageDir);
  }

  @Override public void delete() {
      mImageFile = null ;
  }

  @Override public boolean exists() {
    return mImageFile != null && mImageFile.exists();
  }

  @Override public String getPath() {
    return Uri.fromFile(mImageFile).toString();
  }
}
