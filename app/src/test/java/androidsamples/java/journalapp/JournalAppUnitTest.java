  package androidsamples.java.journalapp;

  import org.junit.Before;
  import org.junit.Test;

  import static org.junit.Assert.*;
  import static org.mockito.Mockito.mock;

  import java.util.Calendar;
  import java.util.Date;

  /**
   * Example local unit test, which will execute on the development machine (host).
   *
   * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
   */
  public class JournalAppUnitTest {
    private JournalViewModel mJournalViewModel;
    @Before
    public void setUp() {
      mJournalViewModel = mock(JournalViewModel.class);
    }

    @Test
    public void testTitle_emptyTitle_returnsFalse() {
      String title = "";
      assertFalse("Title should not be empty", isValidTitle(title));
    }

    @Test
    public void testTitle_whitespaceOnly_returnsFalse() {
      String title = "      ";
      assertFalse("Title should not be whitespace only", isValidTitle(title));
    }

    @Test
    public void testDate_inFuture_returnsFalse() {
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.DAY_OF_YEAR, 1);
      Date date = cal.getTime();

      assertFalse("Date should not be in the future", isValidJournalDate(date));
    }

    @Test
    public void testStartAndEndTimes_endBeforeStart_returnsFalse() {
      Calendar startCal = Calendar.getInstance();
      Calendar endCal = Calendar.getInstance();
      startCal.set(Calendar.HOUR_OF_DAY, 12);
      endCal.set(Calendar.HOUR_OF_DAY, 10);

      assertFalse("End time should not be before start time", isValidTimeRange(startCal.getTime(), endCal.getTime()));
    }

    @Test
    public void testStartAndEndTimes_sameStartAndEndTime_returnsTrue() {
      Calendar cal = Calendar.getInstance();
      Date sameTime = cal.getTime();

      assertTrue("Start and end time being the same should be valid", isValidTimeRange(sameTime, sameTime));
    }

    private boolean isValidTitle(String title) {
      return title != null && !title.trim().isEmpty();
    }

    private boolean isValidJournalDate(Date journalDate) {
      Calendar now = Calendar.getInstance();
      Calendar journalCal = Calendar.getInstance();
      journalCal.setTime(journalDate);

      now.set(Calendar.HOUR_OF_DAY, 0);
      now.set(Calendar.MINUTE, 0);
      now.set(Calendar.SECOND, 0);
      now.set(Calendar.MILLISECOND, 0);

      return !journalCal.after(now);
    }

    private boolean isValidTimeRange(Date startTime, Date endTime) {
      return !startTime.after(endTime);
    }
  }