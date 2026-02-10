package freaky.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

// Test to ToDo class
public class ToDoTest {

    @Test
    public void testConstructorAndPrint() {
        ToDo todo = new ToDo("finish homework");

        // Checks description
        assertEquals("finish homework", todo.description);

        // Checks type icon
        assertEquals("T", todo.getTypeIcon());

        // Checks status icon for not done
        assertEquals(" ", todo.getStatusIcon());

        // Checks print output
        assertEquals("[T][ ] finish homework", todo.print());
    }

    @Test
    public void testMarkAsDone() {
        ToDo todo = new ToDo("finish homework");
        todo.markAsDone();

        // Checks status icon (should be "X")
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void testToFileString() {
        ToDo todo = new ToDo("finish homework");

        // Checks store format of undone todo
        assertEquals("T | 0 | finish homework", todo.toFileString());

        // Checks store format of done todo
        todo.markAsDone();
        assertEquals("T | 1 | finish homework", todo.toFileString());
    }

}
