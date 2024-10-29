package androidsamples.java.journalapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface JournalEntryDao {
    @Insert
    void insert(JournalEntry entry);

    @Update
    void update(JournalEntry entry);

    @Delete
    void delete(JournalEntry entry);

    @Query("SELECT * FROM JournalEntry ORDER BY date DESC")
    LiveData<List<JournalEntry>> getAllEntries();

    @Query("SELECT * FROM JournalEntry WHERE id = :entryId")
    LiveData<JournalEntry> getEntryById(int entryId);

    @Query("DELETE FROM JournalEntry WHERE id = :entryId")
    void deleteEntryById(int entryId);
}
