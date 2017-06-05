package app.demo.skill.home.com.mvpandtesting;

import app.demo.skill.home.com.mvpandtesting.data.FakeNoteServiceApiImpl;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepositories;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepository;
import app.demo.skill.home.com.mvpandtesting.util.FakeImageFileImpl;
import app.demo.skill.home.com.mvpandtesting.util.ImgFile;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class Injection {

  public static ImgFile provideImageFile() {
    return new FakeImageFileImpl();
  }

  public static NoteRepository provideNotesRepository() {
    return NoteRepositories.getInMemoryRepoInstance(new FakeNoteServiceApiImpl());
  }
}
