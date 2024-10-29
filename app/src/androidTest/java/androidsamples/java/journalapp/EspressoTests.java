package androidsamples.java.journalapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.not;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.accessibility.AccessibilityChecks;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTests {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @BeforeClass
    public static void enableAccessibilityChecks() {
        AccessibilityChecks.enable().setRunChecksFromRootView(true);
    }

    @Test
    public void testPlusButtonAccessibility(){
        onView(withId(R.id.btn_add_entry))
                .check(matches(isDisplayed()))
                .check(matches(isClickable()))
                .check(matches(anyOf(hasContentDescription(), withContentDescription(not("")))));

    }

    @Test
    public void testInfoButtonAccessibility(){
        onView(withId(R.id.action_info))
                .check(matches(isDisplayed()))
                .check(matches(isClickable()))
                .check(matches(anyOf(hasContentDescription(), withContentDescription(not("")))));

    }

    @Test
    public void testNavigationToDetailFragment() {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()));
    }

    @Test
    public void testTitleEditTextAccessibility() {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.edit_title))
                .check(matches(isDisplayed()))
                .check(matches(anyOf(hasContentDescription(), withContentDescription(not("")), withText(not("")))));
    }

    public void testDateButtonAccessibility() {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.btn_start_time))
                .check(matches(isDisplayed()))
                .check(matches(anyOf(hasContentDescription(), withContentDescription(not("")), withText(not("")))));
    }

    public void testStartTimeButtonAccessibility() {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.btn_start_time))
                .check(matches(isDisplayed()))
                .check(matches(anyOf(hasContentDescription(), withContentDescription(not("")), withText(not("")))));
    }

    public void testEndTimeButtonAccessibility() {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.btn_end_time))
                .check(matches(isDisplayed()))
                .check(matches(anyOf(hasContentDescription(), withContentDescription(not("")), withText(not("")))));
    }

    @Test
    public void testSaveButtonAccessibility() {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.btn_save))
                .check(matches(isDisplayed()))
                .check(matches(anyOf(hasContentDescription(), withContentDescription(not("")), withText(not("")))));
    }

    @Test
    public void testDeleteButtonAccessibility() {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.action_delete))
                .check(matches(isDisplayed()))
                .check(matches(anyOf(hasContentDescription(), withContentDescription(not("")), withText(not("")))));
    }

    @Test
    public void testShareButtonAccessibility() {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.action_share))
                .check(matches(isDisplayed()))
                .check(matches(anyOf(hasContentDescription(), withContentDescription(not("")), withText(not("")))));
    }
}
