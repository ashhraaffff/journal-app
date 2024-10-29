package androidsamples.java.journalapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class EntryListFragment extends Fragment {
  private RecyclerView mRecyclerView;
  private JournalEntryAdapter mAdapter;
  private JournalViewModel mJournalViewModel;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.entry_list_menu, menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.action_info) {
      String url = "https://jamesclear.com/atomic-habits";
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse(url));
      startActivity(intent);

      return true;
    }
    return super.onOptionsItemSelected(item);
  }


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_entry_list, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mRecyclerView = view.findViewById(R.id.recyclerView);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    mAdapter = new JournalEntryAdapter();
    mRecyclerView.setAdapter(mAdapter);

    mJournalViewModel = new ViewModelProvider(this).get(JournalViewModel.class);
    mJournalViewModel.getAllEntries().observe(getViewLifecycleOwner(), this::updateEntries);

    FloatingActionButton addButton = view.findViewById(R.id.btn_add_entry);
    addButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.entryDetailsFragment));

    mAdapter.setOnItemClickListener(entry -> {
      int entryId = entry;
      EntryListFragmentDirections.ActionEntryListFragmentToEntryDetailsFragment action =
              EntryListFragmentDirections.actionEntryListFragmentToEntryDetailsFragment(entryId);
      Navigation.findNavController(view).navigate(action);
    });




  }

  private void updateEntries(List<JournalEntry> entries) {
    mAdapter.setEntries(entries);  // Update adapter with the fetched entries
  }
}