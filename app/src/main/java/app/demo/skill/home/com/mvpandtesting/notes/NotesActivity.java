package app.demo.skill.home.com.mvpandtesting.notes;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import app.demo.skill.home.com.mvpandtesting.R;
import app.demo.skill.home.com.mvpandtesting.stats.StatisticsActivity;
import app.demo.skill.home.com.mvpandtesting.util.EspressoIdlingResource;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class NotesActivity extends AppCompatActivity {

  private DrawerLayout mDrawerlayout;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notes);

    //setUp Toolbar
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar acBar = getSupportActionBar();
    acBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    acBar.setDisplayHomeAsUpEnabled(true);

    //setup drawer layout
    mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mDrawerlayout.setStatusBarBackground(R.color.colorPrimaryDark);
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    if (navigationView != null) setupDrwerContent(navigationView);

    if(savedInstanceState == null)
      initFragment(NotesFragment.newInstance());
  }

  private void initFragment(NotesFragment notesFragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().add(R.id.contentFrame , notesFragment).commit() ;
  }

  private void setupDrwerContent(final NavigationView navigationView) {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
              case R.id.statistics_navigation_menu_item:
                startActivity(new Intent(NotesActivity.this, StatisticsActivity.class));
                break;

              default:
                break;
            }

            item.setCheckable(true);
            mDrawerlayout.closeDrawers();
            return true;
          }
        });
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // Open the navigation drawer when the home icon is selected from the toolbar.
        mDrawerlayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @VisibleForTesting
  public IdlingResource getCountingIdlingResource() {
    return EspressoIdlingResource.getIdlingResource();
  }
}
