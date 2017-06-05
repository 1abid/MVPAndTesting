package app.demo.skill.home.com.mvpandtesting.data;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

import android.support.v4.util.ArrayMap;

/**
 * This is the endpoint for your data source. Typically, it would be a SQLite db and/or a server
 * API. In this example, we fake this by creating the data on the fly.
 */
public class NoteServiceApiEndPoint {

  private final static ArrayMap<String, Note> DATA;

  static {
    DATA = new ArrayMap<>(2);
    addNote("Oh yes!", "I demand trial by Unit testing", null);
    addNote("Espresso", "UI Testing for Android", null);
  }

  private static void addNote(String title , String description , String imgUrl){
    Note newNote = new Note(title , description , imgUrl);
    DATA.put(newNote.getmID() , newNote);
  }


  public static ArrayMap<String , Note> loadPersistedNotes(){
    return DATA ;
  }
}
