package androidsamples.java.journalapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntryDetailsFragment # newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntryDetailsFragment extends Fragment {
  private EditText mTitle;
  private TextView mSelectedDate;
  private TextView mStartTime;
  private TextView mEndTime;
  private JournalViewModel mJournalViewModel;

  private Calendar mSelectedDateTime;
  private Calendar mStartTimeCalendar;
  private Calendar mEndTimeCalendar;

  private int entryID = -1;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true); // Enable options menu
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.entry_detail_menu, menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_share:
        shareEntry();
        return true;
      case R.id.action_delete:
        deleteEntry();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void shareEntry() {
    String title = mTitle.getText().toString();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    String shareMessage = String.format(
            "Look what I have been up to: \nTitle: %s\nDate: %s\nFrom: %s To: %s",
            title,
            dateFormat.format(mSelectedDateTime.getTime()),
            timeFormat.format(mStartTimeCalendar.getTime()),
            timeFormat.format(mEndTimeCalendar.getTime())
    );

    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
    sendIntent.setType("text/plain");

    Intent shareIntent = Intent.createChooser(sendIntent, "Share via");
    startActivity(shareIntent);
  }

  private void deleteEntry() {
    if (entryID != -1) {
      new AlertDialog.Builder(requireContext())
              .setTitle("Delete Entry")
              .setMessage("Are you sure you want to delete this journal entry?")
              .setPositiveButton("Yes", (dialog, which) -> {
                mJournalViewModel.deleteEntryById(entryID);
                Toast.makeText(requireContext(), "Journal entry deleted", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
              })
              .setNegativeButton("No", (dialog, which) -> {
                dialog.dismiss();
              })
              .create()
              .show();
    }
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_entry_details, container, false);
    mJournalViewModel = new ViewModelProvider(requireActivity()).get(JournalViewModel.class);

    mTitle = view.findViewById(R.id.edit_title);
    mSelectedDate = view.findViewById(R.id.tv_selected_date);
    mStartTime = view.findViewById(R.id.btn_start_time);
    mEndTime = view.findViewById(R.id.btn_end_time);

    Button btnSelectDate = view.findViewById(R.id.btn_entry_date);
    Button btnSelectStartTime = view.findViewById(R.id.btn_start_time);
    Button btnSelectEndTime = view.findViewById(R.id.btn_end_time);
    Button btnSave = view.findViewById(R.id.btn_save);

    mSelectedDateTime = Calendar.getInstance();
    mStartTimeCalendar = Calendar.getInstance();
    mEndTimeCalendar = Calendar.getInstance();

    if (getArguments() != null) {
      entryID = getArguments().getInt("entryId", -1);
    }

    if (entryID != -1) {
      mJournalViewModel.getEntryById(entryID).observe(getViewLifecycleOwner(), entry -> {
        if (entry != null) {
          mTitle.setText(entry.getTitle());
          mSelectedDateTime.setTime(entry.getDate());
          mStartTimeCalendar.setTime(entry.getStartTime());
          mEndTimeCalendar.setTime(entry.getEndTime());

          SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
          mSelectedDate.setText(dateFormat.format(entry.getDate()));

          SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
          mStartTime.setText(timeFormat.format(entry.getStartTime()));
          mEndTime.setText(timeFormat.format(entry.getEndTime()));
        }
      });
    }

    btnSelectDate.setOnClickListener(v -> showDatePickerDialog());
    btnSelectStartTime.setOnClickListener(v -> showTimePickerDialog(true));
    btnSelectEndTime.setOnClickListener(v -> showTimePickerDialog(false));
    btnSave.setOnClickListener(v -> saveEntry());
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }


  private boolean isValidTitle(String title) {
    return title != null && !title.trim().isEmpty();
  }

  private boolean isValidTimeRange(Date startTime, Date endTime) {
    return !startTime.after(endTime);
  }

  private boolean isValidJournalDate(Date journalDate) {
    Calendar now = Calendar.getInstance();
    Calendar journalCal = Calendar.getInstance();
    journalCal.setTime(journalDate);
    now.set(Calendar.HOUR_OF_DAY, 0);
    now.set(Calendar.MINUTE, 0);
    now.set(Calendar.SECOND, 0);
    now.set(Calendar.MILLISECOND, 0);
    journalCal.set(Calendar.HOUR_OF_DAY, 0);
    journalCal.set(Calendar.MINUTE, 0);
    journalCal.set(Calendar.SECOND, 0);
    journalCal.set(Calendar.MILLISECOND, 0);

    return !journalCal.after(now);
  }

  private void showDatePickerDialog() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(
            requireContext(),
            (view, year, month, dayOfMonth) -> {
              mSelectedDateTime.set(year, month, dayOfMonth);
              SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
              mSelectedDate.setText(dateFormat.format(mSelectedDateTime.getTime()));
            },
            mSelectedDateTime.get(Calendar.YEAR),
            mSelectedDateTime.get(Calendar.MONTH),
            mSelectedDateTime.get(Calendar.DAY_OF_MONTH)
    );
    datePickerDialog.show();
  }

  private void showTimePickerDialog(boolean isStartTime) {
    TimePickerDialog.OnTimeSetListener listener = (view, hourOfDay, minute) -> {
      if (isStartTime) {
        mStartTimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mStartTimeCalendar.set(Calendar.MINUTE, minute);
        mStartTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
      } else {
        mEndTimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mEndTimeCalendar.set(Calendar.MINUTE, minute);
        mEndTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
      }
    };
    TimePickerDialog timePickerDialog = new TimePickerDialog(
            requireContext(),
            listener,
            isStartTime ? mStartTimeCalendar.get(Calendar.HOUR_OF_DAY) : mEndTimeCalendar.get(Calendar.HOUR_OF_DAY),
            isStartTime ? mStartTimeCalendar.get(Calendar.MINUTE) : mEndTimeCalendar.get(Calendar.MINUTE),
            true
    );
    timePickerDialog.show();
  }

  private void saveEntry() {
    String title = mTitle.getText().toString();
    Date date = mSelectedDateTime.getTime();
    Date startTime = mStartTimeCalendar.getTime();
    Date endTime = mEndTimeCalendar.getTime();
    if (!isValidTitle(title)) {
      Toast.makeText(requireContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show();
      return;
    }
    if (!isValidJournalDate(date)) {
      Toast.makeText(requireContext(), "Cannot create journal entries for future dates", Toast.LENGTH_SHORT).show();
      return;
    }
    Calendar now = Calendar.getInstance();
    boolean isToday = mSelectedDateTime.get(Calendar.YEAR) == now.get(Calendar.YEAR) &&
            mSelectedDateTime.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH) &&
            mSelectedDateTime.get(Calendar.MONTH) == now.get(Calendar.MONTH);

    if (isToday) {
      if (startTime.after(now.getTime())) {
        Toast.makeText(requireContext(), "Start time cannot be in the future", Toast.LENGTH_SHORT).show();
        return;
      }
      if (endTime.after(now.getTime())) {
        Toast.makeText(requireContext(), "End time cannot be in the future", Toast.LENGTH_SHORT).show();
        return;
      }
    }
    if (!isValidTimeRange(startTime, endTime)) {
      Toast.makeText(requireContext(), "End time must be after or equal to start time", Toast.LENGTH_SHORT).show();
      return;
    }
    JournalEntry entry = new JournalEntry(title, date, startTime, endTime);
    if (entryID != -1) {
      entry.setId(entryID);
      mJournalViewModel.update(entry);
      Toast.makeText(requireContext(), "Journal entry updated", Toast.LENGTH_SHORT).show();
    }
    else {
      mJournalViewModel.insert(entry);
      Toast.makeText(requireContext(), "Journal entry saved", Toast.LENGTH_SHORT).show();
    }
    requireActivity().onBackPressed();
  }
}