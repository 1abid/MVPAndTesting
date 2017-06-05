package app.demo.skill.home.com.mvpandtesting.util;

import java.io.IOException;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public interface ImgFile {

  void create(String name , String extension) throws IOException;

  void delete() ;

  boolean exists() ;

  String getPath() ;

}
