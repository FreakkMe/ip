package freaky;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

// Test to TaskList class
public class TaskListTest {

    @Test
    public void testAddAndGetSize() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("read book");
        tasks.add(todo);

        // Checks the size of tasks after adding one task
        assertEquals(1, tasks.size());

        //Checks if the task is added correctly
        assertEquals(todo, tasks.get(0));
    }

    @Test
    public void testRemove() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("read book");
        tasks.add(todo);
        tasks.remove(0);

        // Checks if the remove command functions properly
        assertEquals(0, tasks.size());
    }
}