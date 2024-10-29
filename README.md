# A3-JournalApp

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


3. **Bug Fixes and Verification**:
   - After implementing new functionality, we rigorously tested for potential bugs, ensuring that everything from UI state persistence to UI updates worked as intended.


## Accessibility Enhancement  
- **Accessibility Scanner Report:** 

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
   Referred to [Android Documentation](https://developer.android.com/docs) for implementing SharedPreferences and Fragments.
   
3. **Swaroop Sir's course site:**
   We referred to [Swaroop Sir's website](https://swaroopjoshi.in/courses/mobile-app-dev/09-persistent-data/) in order to understand how Room Databases and Room Libraries work.
