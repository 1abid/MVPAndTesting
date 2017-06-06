package app.demo.skill.home.com.mvpandtesting.addNote;

import static com.google.common.base.Preconditions.checkState;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.io.IOException;

import app.demo.skill.home.com.mvpandtesting.Injection;
import app.demo.skill.home.com.mvpandtesting.R;
import app.demo.skill.home.com.mvpandtesting.data.NoteRepositories;
import app.demo.skill.home.com.mvpandtesting.data.NoteServicesApiImpl;
import app.demo.skill.home.com.mvpandtesting.util.EspressoIdlingResource;
import app.demo.skill.home.com.mvpandtesting.util.ImgFileImp;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class AddNoteFragment extends Fragment implements AddNoteContract.view {

  private static final int REQUEST_CODE_IMAGE_CAPTURE = 0x1001;

  private static AddNoteFragment instance ;
  private AddNoteContract.userActionListener mUserActionLisnter ;
  private TextView tilteTV ;
  private TextView descriptionTV ;
  private ImageView thumbIV ;

  public AddNoteFragment() {
  }

  public static AddNoteFragment getInstance(){

    if(instance == null)
      instance = new AddNoteFragment();


    return instance ;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mUserActionLisnter = new AddNotePresenter(Injection.provideNotesRepository(), this , Injection.provideImageFile());

    FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_notes);
    fab.setImageResource(R.drawable.ic_done);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mUserActionLisnter.saveNote(tilteTV.getText().toString() , descriptionTV.getText().toString());
      }
    });
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_addnote , container , false);

    tilteTV = (TextView) view.findViewById(R.id.add_note_title);
    descriptionTV = (TextView) view.findViewById(R.id.add_note_description);
    thumbIV = (ImageView) view.findViewById(R.id.add_note_image_thumbnail);

    setHasOptionsMenu(true);
    setRetainInstance(true);

    return view;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.take_picture:

        try {
          mUserActionLisnter.captureImage();
        } catch (IOException ioe) {
          if (getView() != null) {
            ioe.printStackTrace();
            Snackbar.make(getView(), getString(R.string.take_picture_error),
                Snackbar.LENGTH_LONG).show();
          }
        }
        return true;
    }
    return false;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.fragment_addnote_options_menu_actions, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public void showEmptyNoteError() {
    Snackbar.make(tilteTV, getString(R.string.empty_note_message), Snackbar.LENGTH_LONG).show();
  }

  @Override public void showNoteList() {
    getActivity().setResult(Activity.RESULT_OK);
    getActivity().finish();
  }

  @Override public void openCamera(String saveTo) {
    Intent takeImageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    if(takeImageIntent.resolveActivity(getContext().getPackageManager()) != null){
      takeImageIntent.putExtra(MediaStore.EXTRA_OUTPUT , Uri.parse(saveTo));
      startActivityForResult(takeImageIntent , REQUEST_CODE_IMAGE_CAPTURE);
      return;
    }

    showImageError();
  }

  @Override public void showImagePreview(@NonNull String uri) {
    checkState(!TextUtils.isEmpty(uri), "imageUrl cannot be null or empty!");
    thumbIV.setVisibility(View.VISIBLE);

    EspressoIdlingResource.increment();

    Glide.with(this)
        .load(uri)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .into(new GlideDrawableImageViewTarget(thumbIV){
          @Override
          public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
            super.onResourceReady(resource, animation);
            EspressoIdlingResource.decrement();
          }
        });
  }

  @Override public void showImageError() {
    Snackbar.make(tilteTV, getString(R.string.cannot_connect_to_camera_message),
        Snackbar.LENGTH_SHORT).show();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(REQUEST_CODE_IMAGE_CAPTURE ==  requestCode && resultCode == Activity.RESULT_OK)
      mUserActionLisnter.imageAvailable();
    else
      mUserActionLisnter.imageCapturedFailed();
  }
}
