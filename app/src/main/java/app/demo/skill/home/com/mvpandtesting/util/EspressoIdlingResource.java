package app.demo.skill.home.com.mvpandtesting.util;

import android.support.test.espresso.IdlingResource;

/**
 * Created by VutkaBilai on 6/5/17.
 * mail : la4508@gmail.com
 */

public class EspressoIdlingResource {

  private static final String RESOURCE = "GLOBAL";

  private static final SimpleCountingIdelingResources mCountingIdlingResource =
      new SimpleCountingIdelingResources(RESOURCE);

  public static void increment() {
    mCountingIdlingResource.increment();
  }

  public static void decrement() {
    mCountingIdlingResource.decrement();
  }

  public static IdlingResource getIdlingResource() {
    return mCountingIdlingResource;
  }
}
