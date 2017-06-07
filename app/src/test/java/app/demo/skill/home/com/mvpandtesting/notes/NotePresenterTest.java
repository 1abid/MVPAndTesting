package app.demo.skill.home.com.mvpandtesting.notes;

import app.demo.skill.home.com.mvpandtesting.data.Note;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepository;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by VutkaBilai on 6/7/17.
 * mail : la4508@gmail.com
 */

public class NotePresenterTest {

  private static List<Note> TEST_LIST_NOTES =
      Lists.newArrayList(new Note("test note 1", "test note description 1"),
          new Note("test note 2 ", "test note description 2"));

  private static List<Note> EMPTY_TEST_NOTES = new ArrayList<>(0);


  @Mock
  private NoteRepository mockNoteRepository ;


  @Mock
  private NoteContract.view mockNoteView;

  @Captor
  private ArgumentCaptor<NoteRepository.LoadNotesCallback> mCallbackCaptor ;

  private NotePresenter testNotepresenter ;

  @Before
  public void setupNotePresenter(){

    MockitoAnnotations.initMocks(this);

    testNotepresenter = new NotePresenter(mockNoteRepository , mockNoteView) ;


  }

  @Test
  public void clickOnNoteOpenDetailNoteUI(){
    //let's create a stubbed note
    Note mockNote = new Note("test detail note" , "description for this note");

    testNotepresenter.showNoteDetail(mockNote);
    verify(mockNoteView).showNoteDetailUi(any(String.class));
  }

  @Test
  public void clickOnFabOpensNewNoteUI(){
    testNotepresenter.addNewNote();

    verify(mockNoteView).showAddNote();
  }

  @Test
  public void loadNoteShowProgressIndicatorProperly(){
    boolean show = true ;

    testNotepresenter.loadNote(show);
    verify(mockNoteView , atLeastOnce()).showProgress(show);
  }

  @Test
  public void loadNotesFromRepositoryAndLoadIntoView(){
    //fore update called and cashed notes are null
    testNotepresenter.loadNote(true);
    verify(mockNoteRepository).refreshData();

    //callback is captured and invoke the stubbed method of NoteRepository
    verify(mockNoteRepository).getNotes(mCallbackCaptor.capture());
    mCallbackCaptor.getValue().onNotesLoaded(TEST_LIST_NOTES);

    InOrder inorder = Mockito.inOrder(mockNoteView);
    inorder.verify(mockNoteView).showProgress(true);
    inorder.verify(mockNoteView).showProgress(false);
    verify(mockNoteView).showNotes(TEST_LIST_NOTES);

  }

}
