package app.demo.skill.home.com.mvpandtesting.noteDetail;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import app.demo.skill.home.com.mvpandtesting.R;

public class NoteDetailActivity extends AppCompatActivity {

  public static final String EXTRA_NOTE_ID = "NOTE_ID";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_detail);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar acBar = getSupportActionBar();
    acBar.setTitle(R.string.note_detail);
    acBar.setDisplayHomeAsUpEnabled(true);
    acBar.setDisplayShowHomeEnabled(true);

    String noteId = getIntent().getStringExtra(EXTRA_NOTE_ID);

    initFragment(NoteDetailFragment.getInstance(noteId));
  }

  private void initFragment(NoteDetailFragment instance) {
    FragmentManager fragmentManager = getSupportFragmentManager() ;
    fragmentManager.beginTransaction().add(R.id.contentFrame , instance).commit();
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }
}
