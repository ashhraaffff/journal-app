package androidsamples.java.journalapp;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JournalRepository {
    private JournalEntryDao journalEntryDao;
    private LiveData<List<JournalEntry>> allEntries;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public JournalRepository(Application application) {
        JournalDatabase database = JournalDatabase.getInstance(application);
        journalEntryDao = database.journalEntryDao();
        allEntries = journalEntryDao.getAllEntries();
    }

    public void insert(JournalEntry entry) {
        executorService.execute(() -> journalEntryDao.insert(entry));
    }

    public void update(JournalEntry entry) {
        executorService.execute(() -> journalEntryDao.update(entry));
    }

    public void delete(JournalEntry entry) {
        executorService.execute(() -> journalEntryDao.delete(entry));
    }

    public LiveData<List<JournalEntry>> getAllEntries() {
        return allEntries;
    }

    public LiveData<JournalEntry> getEntryById(int entryId) {
        return journalEntryDao.getEntryById(entryId);
    }

    public void deleteEntryById(int entryId) {
        executorService.execute(() -> journalEntryDao.deleteEntryById(entryId));
    }
}
