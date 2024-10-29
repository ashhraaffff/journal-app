package androidsamples.java.journalapp;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class JournalAppInstrumentedTests {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testInitialRecyclerViewIsDisplayed() {
        // Verify RecyclerView of journal entries is visible on startup
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }

    @Test
    public void testFloatingActionButtonNavigatesToAddEntry() {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.edit_title)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddNewJournalEntryAndDisplayInRecyclerView() {
        onView(withId(R.id.btn_add_entry)).perform(click());

        onView(withId(R.id.edit_title)).perform(typeText("Test Journal Entry"), closeSoftKeyboard());

        onView(withId(R.id.btn_save)).perform(click());

        onView(withId(R.id.recyclerView))
                .check(matches(isDisplayed()))
                .check(matches(ViewMatchers.hasDescendant(withText("Test Journal Entry"))));
    }


    @Test
    public void testViewJournalEntryDetails() {
        onView(withId(R.id.recyclerView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.edit_title)).check(matches(isDisplayed()));
    }

    @Test
    public void testEditJournalEntryUpdatesEntry() {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.edit_title))
                .perform(clearText())
                .perform(typeText("Updated Journal Entry"), closeSoftKeyboard());

        onView(withId(R.id.btn_save)).perform(click());

        onView(withId(R.id.recyclerView))
                .check(matches(ViewMatchers.hasDescendant(withText("Updated Journal Entry"))));
    }

    @Test
    public void testDeleteJournalEntry() {
        // First, add a Journal Entry
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.edit_title)).perform(typeText("Test Journal Entry to be deleted"), closeSoftKeyboard());
        onView(withId(R.id.btn_save)).perform(click());

        // Click on the entry to open details
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Click delete action
        onView(withId(R.id.action_delete)).perform(click());

        // Wait for the dialog to appear
        onView(withText("Are you sure you want to delete this journal entry?"))
                .check(matches(isDisplayed()));

        // Confirm deletion
        onView(withText("Yes")).perform(click());

        // Check that the entry is no longer displayed
        onView(withId(R.id.recyclerView))
                .check(matches(not(ViewMatchers.hasDescendant(withText("Test Journal Entry to be deleted")))));
    }

    @Test
    public void testNavigationBackToMainFragment() {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.edit_title)).check(matches(isDisplayed()));
        pressBack();

        // Verify we are back on the main fragment
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }
}
