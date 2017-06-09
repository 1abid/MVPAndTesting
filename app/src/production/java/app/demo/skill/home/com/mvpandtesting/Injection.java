package app.demo.skill.home.com.mvpandtesting;

import app.demo.skill.home.com.mvpandtesting.data.NoteRepositories;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepository;
import app.demo.skill.home.com.mvpandtesting.data.NoteServicesApiImpl;
import app.demo.skill.home.com.mvpandtesting.util.ImgFile;
import app.demo.skill.home.com.mvpandtesting.util.ImgFileImp;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class Injection {

  public static ImgFile provideImageFile() {
    return new ImgFileImp();
  }

  public static NoteRepository provideNotesRepository() {
    return NoteRepositories.getInMemoryRepoInstance(new NoteServicesApiImpl());
  }
}
