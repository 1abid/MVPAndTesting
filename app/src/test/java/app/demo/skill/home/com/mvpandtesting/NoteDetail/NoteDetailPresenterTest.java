package app.demo.skill.home.com.mvpandtesting.NoteDetail;

import app.demo.skill.home.com.mvpandtesting.data.Note;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepository;
import app.demo.skill.home.com.mvpandtesting.noteDetail.NoteDetailContract;
import app.demo.skill.home.com.mvpandtesting.noteDetail.NoteDetailPresenter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by VutkaBilai on 6/8/17.
 * mail : la4508@gmail.com
 */

public class NoteDetailPresenterTest {

  public static final String INVALID_ID = "INVALID_ID";

  public static final String TITLE_TEST = "title";

  public static final String DESCRIPTION_TEST = "description";

  @Mock private NoteRepository mockRepository ;

  @Mock private NoteDetailContract.view mockNoteDetailView ;

  @Captor private ArgumentCaptor<NoteRepository.GetNoteCallback> getNoteArgCaptor ;

  private NoteDetailPresenter testNoteDetailPresenter ;


  @Before
  public void setUpNoteDetailPresenterForTest(){

    MockitoAnnotations.initMocks(this);

    testNoteDetailPresenter = new NoteDetailPresenter(mockRepository , mockNoteDetailView);
  }


  @Test
  public void testNoteDetailPresenterShowsErrorforEmptyOrNullNote(){

    testNoteDetailPresenter.openNote("");

    verify(mockNoteDetailView).showMissingNote();

  }

  @Test
  public void testNoteDetailPresenterShowNoteDetailUI(){

    Note testNote = new Note(TITLE_TEST , DESCRIPTION_TEST);

    testNoteDetailPresenter.openNote(testNote.getmID());
    verify(mockNoteDetailView).setProgressIndicator(true);
    verify(mockRepository).getNote(eq(testNote.getmID()) , getNoteArgCaptor.capture());

    getNoteArgCaptor.getValue().onNoteLoaded(testNote);
    verify(mockNoteDetailView).setProgressIndicator(false);

    verify(mockNoteDetailView).showTitle(TITLE_TEST);
    verify(mockNoteDetailView).showDescription(DESCRIPTION_TEST);

  }


  @Test
  public void testDetailPresenterShowErrorUIforNotFoundNote(){

    testNoteDetailPresenter.openNote(INVALID_ID);
    verify(mockNoteDetailView).setProgressIndicator(true);

    verify(mockRepository).getNote(eq(INVALID_ID) , getNoteArgCaptor.capture());
    getNoteArgCaptor.getValue().onNoteLoaded(null);

    verify(mockNoteDetailView).setProgressIndicator(false);
    verify(mockNoteDetailView).showMissingNote();
  }

}
