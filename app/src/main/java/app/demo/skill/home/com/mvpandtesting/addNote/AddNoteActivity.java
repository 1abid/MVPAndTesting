package app.demo.skill.home.com.mvpandtesting.addNote;

import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import app.demo.skill.home.com.mvpandtesting.R;
import app.demo.skill.home.com.mvpandtesting.util.EspressoIdlingResource;

public class AddNoteActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_addnote);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar acBar = getSupportActionBar() ;
    acBar.setTitle(R.string.add_note);
    acBar.setDisplayHomeAsUpEnabled(true);
    acBar.setDisplayShowHomeEnabled(true);

    if(savedInstanceState == null)
      initFragment(AddNoteFragment.getInstance());
  }

  private void initFragment(AddNoteFragment instance) {
    FragmentManager fragamentManager = getSupportFragmentManager();
    fragamentManager.beginTransaction().add(R.id.contentFrame , instance).commit();
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true ;
  }

  @VisibleForTesting
  public IdlingResource getCountingIdlingResource() {
    return EspressoIdlingResource.getIdlingResource();
  }

}
