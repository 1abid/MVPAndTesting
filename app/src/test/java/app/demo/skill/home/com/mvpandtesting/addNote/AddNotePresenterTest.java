package app.demo.skill.home.com.mvpandtesting.addNote;

import app.demo.skill.home.com.mvpandtesting.data.Note;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepository;
import app.demo.skill.home.com.mvpandtesting.util.ImgFile;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by VutkaBilai on 6/8/17.
 * mail : la4508@gmail.com
 */

public class AddNotePresenterTest {

  private static String TEST_NOTE_TITLE = "test title" ;
  private static String TEST_NOTE_DES = "test description" ;

  @Mock private NoteRepository mockNoteRepository ;

  @Mock private AddNoteContract.view mockAddNoteView ;

  @Mock private ImgFile mockImageFile ;

  private AddNotePresenter testAddNotePresenter ;

  @Before
  public void setUpAddNotePresenterTest(){

    MockitoAnnotations.initMocks(this);

    testAddNotePresenter = new AddNotePresenter(mockNoteRepository , mockAddNoteView , mockImageFile);

  }


  @Test
  public void addNotePresenterSaveANewNonEmptyNote(){

    testAddNotePresenter.saveNote(TEST_NOTE_TITLE , TEST_NOTE_DES);

    InOrder inOrder =  Mockito.inOrder(mockNoteRepository , mockAddNoteView);
    inOrder.verify(mockNoteRepository).saveNote(any(Note.class));
    inOrder.verify(mockAddNoteView).showNoteList();
  }

  @Test
  public void addNotePresenterShowErrorForEmptyNote(){

    testAddNotePresenter.saveNote("" , "");
    verify(mockAddNoteView).showEmptyNoteError();
  }

  @Test
  public void testAddNotePresenterCaptureANdSaveImage() throws IOException {

    testAddNotePresenter.captureImage();
    verify(mockImageFile).create(anyString() , anyString());
    verify(mockAddNoteView).openCamera(anyString());
  }


  @Test
  public void testAddNotePresenterShowImagePreviewForAValidImageFile(){
    String path = "mock/path/to/image";
    when(mockImageFile.exists()).thenReturn(true);
    when(mockImageFile.getPath()).thenReturn(path);

    testAddNotePresenter.imageAvailable();
    verify(mockAddNoteView).showImagePreview(mockImageFile.getPath());

  }

  @Test
  public void testAddNotePresenterShowErrorForInvalidImage(){

    when(mockImageFile.exists()).thenReturn(false);

    testAddNotePresenter.imageAvailable();

    verify(mockImageFile).delete();
    verify(mockAddNoteView).showImageError();
  }

}
