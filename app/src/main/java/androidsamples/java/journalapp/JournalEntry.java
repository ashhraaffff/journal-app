package androidsamples.java.journalapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class JournalEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private Date date;
    private Date startTime;
    private Date endTime;

    public JournalEntry(String title, Date date, Date startTime, Date endTime) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public int getId() {
        return (int) id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public Date getDate() {
        return date;
    }
    public Date getStartTime() {
        return startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
}
