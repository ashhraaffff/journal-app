package androidsamples.java.journalapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JournalEntryAdapter extends RecyclerView.Adapter<JournalEntryAdapter.EntryHolder> {
    private List<JournalEntry> entries = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public EntryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_entry, parent, false);
        return new EntryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryHolder holder, int position) {
        JournalEntry entry = entries.get(position);
        holder.textViewTitle.setText(entry.getTitle());
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy");
        holder.textViewDate.setText(dateFormat.format(entry.getDate()));
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        holder.textViewStartTime.setText(timeFormat.format(entry.getStartTime()));
        holder.textViewEndTime.setText(timeFormat.format(entry.getEndTime()));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void setEntries(List<JournalEntry> entries) {
        this.entries = entries;
        notifyDataSetChanged();
    }

    class EntryHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewStartTime;
        private TextView textViewEndTime;

        public EntryHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.txt_item_title);
            textViewDate = itemView.findViewById(R.id.txt_item_date);
            textViewStartTime = itemView.findViewById(R.id.txt_item_start_time);
            textViewEndTime = itemView.findViewById(R.id.txt_item_end_time);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(entries.get(position).getId());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int entryId);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

