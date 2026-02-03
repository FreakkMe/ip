package Freaky;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class HelperTest {

    @Test
    public void testGetClosestDeadlines() {

        TaskList tasks = new TaskList();
        Deadline deadline1 = new Deadline("deadline 1", LocalDateTime.of(2026, 1, 2, 3, 4));
        Deadline deadline2 = new Deadline("deadline 2", LocalDateTime.of(2026, 2, 3, 4, 5));
        Deadline deadline3 = new Deadline("deadline 3", LocalDateTime.of(2020, 5, 6, 7, 8));
        deadline2.markAsDone();

        tasks.add(deadline1);
        tasks.add(deadline2);
        tasks.add(new ToDo("todo 1"));
        tasks.add(new Event("event 1", LocalDateTime.of(2026, 3, 4, 5, 6), LocalDateTime.of(2026, 4, 5, 6, 7)));
        tasks.add(deadline3);

        TaskList deadlines = Helper.getClosestDeadlines(tasks, 3);

        // Checks if the deadlines mark as done won't be return by getClosestDeadlines
        assertEquals(2, deadlines.size());

        // Checks if the return deadlines are correct and in correct order
        assertEquals(deadline3, deadlines.get(0));
        assertEquals(deadline1, deadlines.get(1));
    }

    @Test
    public void testGetClosestEvents() {

        TaskList tasks = new TaskList();
        Event event1 = new Event("event 1", LocalDateTime.of(2026, 1, 2, 3, 4), LocalDateTime.of(2026, 5, 6, 7, 8));
        Event event2 = new Event("event 2", LocalDateTime.of(2026, 2, 1, 0, 3), LocalDateTime.of(2026, 3, 1, 0, 3));
        Event event3 = new Event("event 3", LocalDateTime.of(2026, 1, 1, 0, 1), LocalDateTime.of(2026, 2, 3, 3, 4));
        event2.markAsDone();

        tasks.add(event1);
        tasks.add(event2);
        tasks.add(new Deadline("deadline 1", LocalDateTime.of(2026, 2, 3, 4, 5)));
        tasks.add(new ToDo("todo task"));
        tasks.add(event3);

        TaskList events = Helper.getClosestEvents(tasks, 3);

        // Checks if the events mark as done won't be return by getClosestEvents
        assertEquals(2, events.size());

        // Checks if the return events are correct and in correct order
        assertEquals(event3, events.get(0));
        assertEquals(event1, events.get(1));
    }
}