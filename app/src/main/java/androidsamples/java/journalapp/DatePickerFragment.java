package androidsamples.java.journalapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {

  private static final String ARG_DATE = "date";

  public interface DatePickerListener {
    void onDateSelected(Date date);
  }
  @NonNull
  public static DatePickerFragment newInstance(Date date) {
    // TODO implement the method
    DatePickerFragment fragment = new DatePickerFragment();
    Bundle args = new Bundle();
    args.putLong(ARG_DATE, date != null ? date.getTime() : System.currentTimeMillis());
    fragment.setArguments(args);
    return fragment;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    // TODO implement the method
    long dateInMillis = getArguments() != null ? getArguments().getLong(ARG_DATE) : System.currentTimeMillis();
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(dateInMillis);

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    return new DatePickerDialog(requireContext(), (datePicker, selectedYear, selectedMonth, selectedDay) -> {
      Calendar selectedCalendar = Calendar.getInstance();
      selectedCalendar.set(Calendar.YEAR, selectedYear);
      selectedCalendar.set(Calendar.MONTH, selectedMonth);
      selectedCalendar.set(Calendar.DAY_OF_MONTH, selectedDay);
      Date selectedDate = selectedCalendar.getTime();
      if (getParentFragment() instanceof DatePickerListener) {
        ((DatePickerListener) getParentFragment()).onDateSelected(selectedDate);
      } else if (getActivity() instanceof DatePickerListener) {
        ((DatePickerListener) getActivity()).onDateSelected(selectedDate);
      }
    }, year, month, day);
  }
}
