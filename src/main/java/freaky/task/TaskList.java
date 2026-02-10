package freaky.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    // ArrayList<Task> tasks storing the tasks
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * The internal list of tasks is initialized as an empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList initialized with the given list of tasks.
     * A new internal ArrayList is created to avoid external modification of the original list.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the number of tasks in this TaskList.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns task at a given index.
     *
     * @param index Index to get.
     * @return Task at index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at a given index.
     *
     * @param index Index of task to remove.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the internal list of tasks.
     * Note: The returned list is a clone of the internal list, so modifications to it will not affect the TaskList.
     *
     * @return The ArrayList of tasks in this TaskList.
     */
    public ArrayList<Task> getTasks() {
        return (ArrayList<Task>) tasks.clone();
    }

}
