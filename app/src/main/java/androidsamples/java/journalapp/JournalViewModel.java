package androidsamples.java.journalapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class JournalViewModel extends AndroidViewModel {
    private JournalRepository repository;
    private LiveData<List<JournalEntry>> allEntries;

    public JournalViewModel(@NonNull Application application) {
        super(application);
        repository = new JournalRepository(application);
        allEntries = repository.getAllEntries();
    }

    public void insert(JournalEntry entry) {
        repository.insert(entry);
    }

    public void update(JournalEntry entry) {
        repository.update(entry);
    }

    public void delete(JournalEntry entry) {
        repository.delete(entry);
    }

    public LiveData<List<JournalEntry>> getAllEntries() {
        return allEntries;
    }

    public LiveData<JournalEntry> getEntryById(int entryId) {
        return repository.getEntryById(entryId);
    }

    public void deleteEntryById(int entryId) {
        repository.deleteEntryById(entryId);
    }
}
