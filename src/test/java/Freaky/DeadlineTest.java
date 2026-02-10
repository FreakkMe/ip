package freaky;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Test to Deadline class
public class DeadlineTest {

    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    @Test
    public void testConstructorAndGetTimeAndPrint() {
        LocalDateTime time = LocalDateTime.of(2026, 1, 2, 3, 4);
        Deadline deadline = new Deadline("submit report", time);

        // Checks description
        assertEquals("submit report", deadline.description);

        // Checks icon
        assertEquals("D", deadline.getTypeIcon());

        //Checks time
        assertEquals("Jan 2 2026 03:04", deadline.getTime().format(OUTPUT_DATE_FORMAT));

        // Checks print output
        assertEquals("[D][ ] submit report (by: Jan 2 2026 03:04)", deadline.print());
    }

    @Test
    public void testPrintDone() {
        LocalDateTime time = LocalDateTime.of(2026, 1, 2, 3, 4);
        Deadline deadline = new Deadline("submit report", time);
        deadline.markAsDone();

        // Checks mark as done function
        assertEquals("[D][X] submit report (by: Jan 2 2026 03:04)", deadline.print());
    }

    @Test
    public void testToFileString() {
        LocalDateTime time = LocalDateTime.of(2026, 1, 2, 3, 4);
        Deadline deadline = new Deadline("submit report", time);

        // Checks store format of undone deadline
        assertEquals("D | 0 | submit report | 2026-01-02 0304", deadline.toFileString());

        // Checks store format of done deadline
        deadline.markAsDone();
        assertEquals("D | 1 | submit report | 2026-01-02 0304", deadline.toFileString());
    }
}