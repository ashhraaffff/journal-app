# A4-JournalApp

## About Us

This app was built by:

- **Aditya Ashok Tailor**  
  Student ID: 2022A7PS0389G  
  Email: [f20220389@goa.bits-pilani.ac.in](mailto:f20220389@goa.bits-pilani.ac.in)

- **S Mohammed Ashraf**  
  Student ID: 2022A7PS0645G  
  Email: [f20220645@goa.bits-pilani.ac.in](mailto:f20220645@goa.bits-pilani.ac.in)



## Brief Description

The **Journal App** allows users to **record**, **manage** and **review** journal entries efficiently. Each entry includes the date, start time and end time, enabling users to reflect on their activities over time. The app supports features such as sharing entries, deleting them, and ensuring a smooth user experience through an intuitive interface.  

**Key Features:**

- **Entry Management**: Create, edit, and delete journal entries with ease.
- **Date and Time Picker**: Use built-in date and time pickers for seamless input.
- **Data Persistence**: Journal entries are stored in a Room database for persistent access.
- **Accessibility**: The app is designed with accessibility in mind, ensuring a user-friendly experience for all users.

  
## Overview of Task Completion

1. **Navigation Implementation**:
    - *Nav Graph Actions* : Implemented navigation between fragments using the provided nav graph. This involved setting up safe args to pass data between fragments efficiently.
    - *Shared ViewModel* : Used a shared view model to facilitate data transfer between the `EntryDetailsFragment` and any dialogs that needed to return data to the invoking fragment.

2. **Database Modifications**:
    - *New Schema* : Modified the Room database to include new columns for recording the date, start time, and end time for each journal entry.
    - *CRUD Operations* : Implemented the DELETE operation for journal entries alongside existing INSERT, QUERY, and UPDATE methods. Conducted thorough unit tests on the database to ensure data integrity and proper functionality.
    
3. **UI Updates**:
    - *EntryDetailsFragment* : Added a DELETE button to the menu bar of the `EntryDetailsFragment`. Clicking this button prompts the user with a confirmation dialog before proceeding with the deletion.
    - *SHARE Button* : Implemented a SHARE button in the `EntryDetailsFragment` that constructs a text message (e.g., “Look what I have been up to: <title> on <date>, <start-time> to <end-time>”) and allows users to share it through various apps available on their device.
    - *INFO Button* : Added an INFO button in the `EntryListFragment` that directs users to the webpage of the author of [The Atomic Habits](https://jamesclear.com/atomic-habits) using an Intent to launch the default browser or a web view within the app.

4. **Accessibility Features**:
    - *Talk Back Experience* : Ran the app using Talk Back to evaluate the accessibility of the UI. The experience was positive, as all interactive elements were properly labeled, making navigation intuitive for visually impaired users.
    - *Accessibility Scanner Report* : Conducted a report using the Accessibility Scanner, identifying potential accessibility issues, which were addressed to improve user experience.
    - *Espresso Tests* : Developed Espresso test cases that not only verify the functionality of UI components but also ensure accessibility compliance, such as checking text visibility and navigation.
  
5. **UI Tests for Navigation**:
    - Created comprehensive UI tests to ensure proper navigation flows between fragments, confirming that all transitions operate smoothly and the user experience remains intact.
    
---

## Testing

Once the logic for the app was built and the `EntryDetailsFragment` and `EntryListFragment` were implemented, we wrote various testcases in order to test the bussiness logic of the app and make sure that we had dealt with all the potential bugs.

### Key Testing Highlights:

1. **JUnit Tests**:
    - Added comprehensive **JUnit tests** for testing the logic of the `EntryDetailsFragment`.
    - The tests focused on verifying the Journal Entries, particularly checking whether all the fields were filled and if valid titles and dates were assigned.

2. **Instrumented Tests**:
   - The instrumented tests provide in-depth testing of the app’s core functionalities, particularly the CRUD (Create, Read, Update, Delete) operations and navigational flows. The main instrumented tests include:
     1) *RecyclerView and Entry Display*:
        - Initial View: Confirms that the RecyclerView showing journal entries is visible on app startup.
        - Floating Action Button Navigation: Tests that tapping the plus button navigates to the entry creation screen.
        - Add and Display Entry: Adds a new journal entry and verifies it appears in the RecyclerView with the correct title.
     2) *Journal Entry Details and Editing*:
        - View Journal Entry Details: Opens a journal entry from the list and checks that the details view displays the title.
        - Edit Journal Entry: Modifies the title of an existing entry and ensures the RecyclerView displays the updated title.
     3) *Delete Journal Entry*:
        - Adds an entry, deletes it from the details view with a confirmation dialog, and confirms that the entry is removed from the RecyclerView.
     4) *Back Navigation*:
        - Ensures that pressing the back button from the entry creation or detail view returns the user to the main RecyclerView list.

3. **Espresso Tests**:
     1) *Button Accessibility Checks*:
        - Plus Button:  Ensures the add button is visible, clickable, and accessible with a content description.
        - Info Button: Verifies that the info button in the menu bar is accessible and clickable.
        - Date, Start Time, and End Time Buttons: Checks that each button in the entry details view is displayed and has content descriptions or text for accessibility.
     2) *Navigation Tests*:
        - Navigate to Detail Fragment: Validates that tapping the add entry button navigates correctly to the detail fragment with the save button displayed.
     3) *Content Description Validation*:
        - Checks that text fields like the Title edit text and Save/Delete/Share buttons are displayed and accessible.

4. **Bug Fixes and Verification**:
   - After implementing new functionality, we rigorously tested for potential bugs, ensuring that everything from UI state persistence to UI updates worked as intended.


## Accessibility Enhancement  
- **Accessibility Scanner Report:**
    - We tested the app on Accessiblity Scanner to identify and address potential accessibility issues, ensuring that users with disabilities can navigate and interact with the app effectively.
    - Upon using the Accessibility Scanner we received the following feedbacks for improvements on our UI in order to make it even more accessible to differently abled users:
      
      ![Screenshot 2024-10-30 122117](https://github.com/user-attachments/assets/c2ac112d-70eb-4da2-b8fb-56e3a86c39e8)

      ![Screenshot 2024-10-30 122208](https://github.com/user-attachments/assets/41acf45d-486e-4e2f-9875-81dadbfd3af0)

     - These are some of the images containing the analysis of the UI as done by the Accessibility Scanner. Similarly we had a total of 12 screens analysed.
     - Accordingly, we changed the text from dip to scaled pixels (sp), changed the size of the text and adjusted the contrast ratios on our UI to suit the suggestions provided by the Accessibility Scanner.


## Time Taken

The project took approximately **16** hours to code, test, and refine.

## Pair Programming

We engaged in pair programming throughout the project, allowing us to collaboratively tackle challenges, review code, and ensure thorough documentation.

## Difficulty Level

We would rate the difficulty of this assignment as **7.5** out of 10. Some of the challenges faced included writing the Instrumented Testcases, integrating the various Java class files, making use of LiveData, updating the database and making sure that UI had a good accessibility score.

## Bibliography

1. **ChatGPT and Claude:**  
   Used ChatGPT for understanding how to integrate the various libraries and familiarize ourselves with their syntax.
   
2. **Android Documentation:**  
   Referred to [Android Documentation](https://developer.android.com/docs) for implementing MenuBar action items and Fragments.
   
3. **Swaroop Sir's course site:**
   We referred to [Swaroop Sir's website](https://swaroopjoshi.in/courses/mobile-app-dev/09-persistent-data/) in order to understand how Room Databases and Room Libraries work.
