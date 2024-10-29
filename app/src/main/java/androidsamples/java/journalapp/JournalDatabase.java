package androidsamples.java.journalapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {JournalEntry.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class JournalDatabase extends RoomDatabase {
    private static JournalDatabase instance;
    public abstract JournalEntryDao journalEntryDao();

    public static synchronized JournalDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            JournalDatabase.class, "journal_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
