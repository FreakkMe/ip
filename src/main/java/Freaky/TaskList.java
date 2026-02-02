package Freaky;

import java.util.ArrayList;
import java.util.List;

// The task list of Freaky
public class TaskList {

    // ArrayList<Task> tasks storing the tasks
    private ArrayList<Task> tasks;

    // Constructor of empty TaskList
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Constructor of saved TaskList (tasks saved on hard disk)
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    // Return the number of tasks in tasks
    public int size() {
        return tasks.size();
    }

    // Returns the task of the given index in tasks
    public Task get(int index) {
        return tasks.get(index);
    }

    // Add a task to tasks
    public void add(Task task) {
        tasks.add(task);
    }

    // Remove a task of the given index from tasks
    public void remove(int index) {
        tasks.remove(index);
    }

    // Returns the tasks
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
