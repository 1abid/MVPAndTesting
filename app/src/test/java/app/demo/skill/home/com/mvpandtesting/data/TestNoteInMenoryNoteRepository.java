package app.demo.skill.home.com.mvpandtesting.data;

import com.google.common.collect.Lists;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by VutkaBilai on 6/7/17.
 * mail : la4508@gmail.com
 */

public class TestNoteInMenoryNoteRepository {

  @Mock private NoteServicesApi mockNoteService;

  @Mock private NoteRepository.LoadNotesCallback mockLoadNotesCallback ;

  @Mock private NoteRepository.GetNoteCallback mockGetNoteCallBack ;

  @Captor private ArgumentCaptor<NoteServicesApi.NoteServiceCallback> noteServiceCallbackArgumentCaptor ;

  private static String TEST_NOTE_TITLE = "Test note tilte";
  private static String TEST_NOTE_DES = "Test note description";

  private static List<Note> TEST_NOTES =
      Lists.newArrayList(new Note(TEST_NOTE_TITLE + " 1", TEST_NOTE_DES + " 2"),
          new Note(TEST_NOTE_TITLE + "2", TEST_NOTE_DES + " 2"));

  private InmemoryNotesRepository testRepository ;

  @Before
  public void setUpNoteRepository(){
    MockitoAnnotations.initMocks(this);

    testRepository = new InmemoryNotesRepository(mockNoteService) ;
  }

  @Test
  public void testNoteRepositoryReturnsASingleNote(){

    testRepository.getNote(TEST_NOTE_TITLE , mockGetNoteCallBack);
    verify(mockNoteService).getNote(eq(TEST_NOTE_TITLE) , any(
        NoteServicesApi.NoteServiceCallback.class));
  }

  @Test
  public void testNoteReposSaveAndInvalidateCache(){

    testRepository.saveNote(TEST_NOTES.get(0));
    assertThat(testRepository.mCachedNotes , is(nullValue()));

  }

  @Test
  public void testNoteReposReturnsAllNotes(){
    twoCallToNotesRepoforGettingNotes(mockLoadNotesCallback);

    verify(mockNoteService).getAllNotes(any(NoteServicesApi.NoteServiceCallback.class));
  }

  @Test
  public void invalidateCache_DoesNotCallTheServiceApi(){

    twoCallToNotesRepoforGettingNotes(mockLoadNotesCallback);

    testRepository.refreshData();
    testRepository.getNotes(mockLoadNotesCallback);

    verify(mockNoteService , times(2)).getAllNotes(any(NoteServicesApi.NoteServiceCallback.class));
  }


  private void twoCallToNotesRepoforGettingNotes(NoteRepository.LoadNotesCallback callback){

    testRepository.getNotes(callback);

    verify(mockNoteService).getAllNotes(noteServiceCallbackArgumentCaptor.capture());

    noteServiceCallbackArgumentCaptor.getValue().onLoaded(TEST_NOTES);

    testRepository.getNotes(callback);
  }

}
