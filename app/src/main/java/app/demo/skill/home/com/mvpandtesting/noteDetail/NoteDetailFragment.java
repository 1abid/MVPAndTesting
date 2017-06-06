package app.demo.skill.home.com.mvpandtesting.noteDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.demo.skill.home.com.mvpandtesting.Injection;
import app.demo.skill.home.com.mvpandtesting.R;
import app.demo.skill.home.com.mvpandtesting.data.InmemoryNotesRepository;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepositories;
import app.demo.skill.home.com.mvpandtesting.data.NoteServicesApiImpl;
import app.demo.skill.home.com.mvpandtesting.util.EspressoIdlingResource;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 6/6/17.
 * mail : la4508@gmail.com
 */

public class NoteDetailFragment extends Fragment implements NoteDetailContract.view{


  private String noteId ;

  private NoteDetailContract.userActionListener mUserActionListener ;

  private TextView mTitle , mDescription ;

  private ImageView mImageView ;

  public NoteDetailFragment() {
  }

  public static NoteDetailFragment getInstance(@NonNull String noteId){
    checkNotNull(noteId);
    Bundle b = new Bundle();
    b.putString(NoteDetailActivity.EXTRA_NOTE_ID , noteId);
    NoteDetailFragment instance = new NoteDetailFragment();
    instance.setArguments(b);

    return instance ;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_addnote , container , false) ;

    mTitle = (TextView) view.findViewById(R.id.note_detail_title);
    mDescription = (TextView) view.findViewById(R.id.note_detail_description);
    mImageView = (ImageView) view.findViewById(R.id.note_detail_image);

    return view;
  }

  @Override public void onResume() {
    super.onResume();
    noteId = getArguments().getString(NoteDetailActivity.EXTRA_NOTE_ID);
    mUserActionListener.openNote(noteId);
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mUserActionListener = new NoteDetailPresenter(NoteRepositories.getInMemoryRepoInstance(new NoteServicesApiImpl()), this);
  }

  @Override public void setProgressIndicator(boolean active) {
    if(active){
      mTitle.setText("");
      mDescription.setText(getString(R.string.loading));
    }
  }

  @Override public void showMissingNote() {
    Snackbar.make(mTitle , getString(R.string.no_data) , Snackbar.LENGTH_SHORT).show();
  }

  @Override public void hideTitle() {
    mTitle.setVisibility(View.INVISIBLE);
  }

  @Override public void showTitle(String title) {
    mTitle.setVisibility(View.VISIBLE);
    mTitle.setText(title);
  }

  @Override public void showImage(String imageUrl) {

    EspressoIdlingResource.increment();

    mImageView.setVisibility(View.VISIBLE);

    Glide.with(this)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .into(new GlideDrawableImageViewTarget(mImageView){
          @Override public void onResourceReady(GlideDrawable resource,
              GlideAnimation<? super GlideDrawable> animation) {
            super.onResourceReady(resource, animation);
            EspressoIdlingResource.decrement();
          }
        });
  }

  @Override public void hideImage() {
    mImageView.setImageDrawable(null);
    mImageView.setVisibility(View.INVISIBLE);
  }

  @Override public void hideDescription() {
    mDescription.setVisibility(View.INVISIBLE);
  }

  @Override public void showDescription(String description) {
    mDescription.setVisibility(View.VISIBLE);
    mDescription.setText(description);
  }
}
