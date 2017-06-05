package app.demo.skill.home.com.mvpandtesting.util;

import java.io.IOException;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class FakeImageFileImpl extends ImgFileImp {

  @Override public void create(String name, String extension) throws IOException {

  }

  @Override public void delete() {

  }

  @Override public boolean exists() {
    return true;
  }

  @Override public String getPath() {
    return "file:///android_asset/atsl-logo.png";
  }
}
