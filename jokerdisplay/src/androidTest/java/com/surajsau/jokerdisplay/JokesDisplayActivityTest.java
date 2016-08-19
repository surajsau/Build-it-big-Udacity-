package com.surajsau.jokerdisplay;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by surajkumarsau on 18/08/16.
 */

@RunWith(AndroidJUnit4.class)
public class JokesDisplayActivityTest {

    @Rule
    public ActivityTestRule<JokerDisplayActivity> rule = new ActivityTestRule<>(
            JokerDisplayActivity.class, true, false);

    @Test
    public void testIfJokeDisplayedByTextViewIsSameAsStringFromIntent() {
        Intent jokeIntent = new Intent();
        jokeIntent.putExtra(IContants.JOKE_STRING, "Sample Joke");
        rule.launchActivity(jokeIntent);

        onView(withId(R.id.tvJoke)).check(matches(withText("Sample Joke")));
    }
}
