package app.demo.skill.home.com.mvpandtesting.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.demo.skill.home.com.mvpandtesting.Injection;
import app.demo.skill.home.com.mvpandtesting.R;
import app.demo.skill.home.com.mvpandtesting.addNote.AddNoteActivity;
import app.demo.skill.home.com.mvpandtesting.data.Note;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepositories;
import app.demo.skill.home.com.mvpandtesting.data.NoteServicesApiImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class NotesFragment extends Fragment implements NoteContract.view {

  private static final int REQUEST_ADD_NOTE = 1;

  private static  NotesFragment instance ;

  private NoteContract.userActionListener mActionListener ;

  private NotesAdapter mListAdapter;

  public NotesFragment() {
  }

  public static NotesFragment newInstance(){
    if(instance == null)
      instance = new NotesFragment();
    return instance ;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mListAdapter = new NotesAdapter(mItemListener , new ArrayList<Note>(0));
    mActionListener = new NotePresenter(Injection.provideNotesRepository(), this);
  }

  NotesAdapter.NoteItemListener mItemListener = new NotesAdapter.NoteItemListener() {
    @Override public void onItemClick(Note note) {
        mActionListener.showNoteDetail(note);
    }
  };

  @Override public void onResume() {
    super.onResume();
    mActionListener.loadNote(false);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    // If a note was successfully added, show snackbar
    if (REQUEST_ADD_NOTE == requestCode && Activity.RESULT_OK == resultCode) {
      Snackbar.make(getView(), getString(R.string.successfully_saved_note_message),
          Snackbar.LENGTH_SHORT).show();
    }
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setReenterTransition(true);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_notes, container, false);
    RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.notes_list);
    recyclerView.setAdapter(mListAdapter);

    int numColumns = getContext().getResources().getInteger(R.integer.num_notes_columns);

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

    // Set up floating action button
    FloatingActionButton fab =
        (FloatingActionButton) getActivity().findViewById(R.id.fab_add_notes);

    fab.setImageResource(R.drawable.ic_add);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mActionListener.addNewNote();
      }
    });

    // Pull-to-refresh
    SwipeRefreshLayout swipeRefreshLayout =
        (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
    swipeRefreshLayout.setColorSchemeColors(
        ContextCompat.getColor(getActivity(), R.color.colorPrimary),
        ContextCompat.getColor(getActivity(), R.color.colorAccent),
        ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mActionListener.loadNote(true);
      }
    });
    return root;
  }

  @Override public void showProgress(final boolean show) {
    if(getView() == null)
      return;
    final SwipeRefreshLayout refreshlayout =
        (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

    refreshlayout.post(new Runnable() {
      @Override public void run() {
        refreshlayout.setRefreshing(show);
      }
    });
  }

  @Override public void showNotes(List<Note> notes) {
    mListAdapter.replaceData(notes);
  }

  @Override public void showAddNote() {
      Intent addNoteIntent = new Intent(getContext() , AddNoteActivity.class);
      startActivityForResult(addNoteIntent , REQUEST_ADD_NOTE);
  }
}
