package freaky.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

// Test to Event class
public class EventTest {

    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    @Test
    public void testConstructorAndGetTimeAndPrint() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 2, 3, 4);
        LocalDateTime end = LocalDateTime.of(2026, 2, 3, 4, 5);
        Event event = new Event("team meeting", start, end);

        // Checks description
        assertEquals("team meeting", event.description);

        // Checks icon
        assertEquals("E", event.getTypeIcon());

        //Checks startTime
        assertEquals("Jan 2 2026 03:04", event.getStartTime().format(OUTPUT_DATE_FORMAT));

        // Checks endTime
        assertEquals("Feb 3 2026 04:05", event.getEndTime().format(OUTPUT_DATE_FORMAT));

        //Checks print output
        assertEquals("[E][ ] team meeting (from: Jan 2 2026 03:04 to: Feb 3 2026 04:05)", event.print());
    }

    @Test
    public void testToFileString() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 2, 3, 4);
        LocalDateTime end = LocalDateTime.of(2026, 2, 3, 4, 5);
        Event event = new Event("team meeting", start, end);

        // Checks store format of undone event
        assertEquals("E | 0 | team meeting | 2026-01-02 0304 -> 2026-02-03 0405", event.toFileString());

        // Checks store format of done event
        event.markAsDone();
        assertEquals("E | 1 | team meeting | 2026-01-02 0304 -> 2026-02-03 0405", event.toFileString());
    }

}
