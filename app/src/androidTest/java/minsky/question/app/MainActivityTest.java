package minsky.question.app;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static minsky.question.app.di.UnresponsiveNetworkModule.EMPTY_PAGE;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp()
    {
        mainActivityTestRule.launchActivity(new Intent());
    }

    @Test
    public void afterStartEmptyPageMessageIsDisplayed() throws Exception
    {
        onView(withText(EMPTY_PAGE)).check(matches(isDisplayed())); // mocked network responses always with this text.
    }
}
