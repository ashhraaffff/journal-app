package androidsamples.java.journalapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TimePickerFragment extends DialogFragment {
  private static final String ARG_TIME = "time";

  public interface OnTimeSelectedListener {
    void onTimeSelected(int hourOfDay, int minute);
  }
  private OnTimeSelectedListener mListener;
  @NonNull
  public static TimePickerFragment newInstance(Date time) {
    // TODO implement the method
    Bundle args = new Bundle();
    args.putSerializable(ARG_TIME, time);

    TimePickerFragment fragment = new TimePickerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO implement the method
    return ;
  }

  public void setOnTimeSelectedListener(OnTimeSelectedListener listener) {
    mListener = listener;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    // TODO implement the method
    Calendar calendar = Calendar.getInstance();
    if (getArguments() != null) {
      calendar = (Calendar) getArguments().getSerializable(ARG_TIME);
    }
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    return new TimePickerDialog(requireContext(), this::onTimeSet, hour, minute, DateFormat.is24HourFormat(requireContext()));
  }

  private void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    if (mListener != null) {
      mListener.onTimeSelected(hourOfDay, minute);
    }
  }
}
