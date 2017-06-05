package app.demo.skill.home.com.mvpandtesting.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class NoteRepositories {

  private NoteRepositories() {
  }

  private static NoteRepository repository = null;

  public synchronized static NoteRepository getInMemoryRepoInstance(
      @NonNull NoteServicesApi noteServicesApi) {
    checkNotNull(noteServicesApi);
    if (null == repository) repository = new InmemoryNotesRepository(noteServicesApi);

    return repository;
  }
}
