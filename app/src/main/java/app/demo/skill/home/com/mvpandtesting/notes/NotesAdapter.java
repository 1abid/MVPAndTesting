package app.demo.skill.home.com.mvpandtesting.notes;

import android.gesture.Prediction;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import app.demo.skill.home.com.mvpandtesting.R;
import app.demo.skill.home.com.mvpandtesting.data.Note;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

  interface NoteItemListener {
    void onItemClick(Note note);
  }

  private NoteItemListener itemListener ;
  private List<Note> mListNotes;



  public NotesAdapter(NoteItemListener itemListener, List<Note> mListNotes) {
    this.itemListener = itemListener;
    this.mListNotes = mListNotes;
  }


  @Override public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View itemView = inflater.inflate(R.layout.item_note , null);
    NotesViewHolder viewHolder = new NotesViewHolder(itemView , itemListener);
    return viewHolder;
  }


  @Override public void onBindViewHolder(NotesViewHolder holder, int position) {
    Note note = mListNotes.get(position);

    holder.title.setText(note.getmNoteTitle());
    holder.description.setText(note.getmNoteDetail());
  }

  public void replaceData(List<Note> notes){
    setList(notes);
    notifyDataSetChanged();
  }

  private void setList(List<Note> notes) {
    checkNotNull(notes);
    mListNotes = notes;
  }

  private Note getItem(int position){
    checkArgument(position != -1);
    return mListNotes.get(position);
  }

  @Override public int getItemCount() {
    return null == mListNotes ? 0 : mListNotes.size();
  }

  public class NotesViewHolder extends ViewHolder implements OnClickListener{

    public TextView title;

    public TextView description;
    private NoteItemListener mItemListener;


    public NotesViewHolder( View itemView , NoteItemListener clickListener) {
      super(itemView);
      mItemListener = clickListener ;

      title = (TextView) itemView.findViewById(R.id.note_detail_title);
      description = (TextView) itemView.findViewById(R.id.note_detail_description);

      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      int position = getAdapterPosition();
      mItemListener.onItemClick(getItem(position));
    }
  }
}
