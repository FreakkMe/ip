public class Task {

    // String description stores description of the task, boolean isDone stores the status of the task
    protected String description;
    protected boolean isDone;

    // Constuctor of task, default to be marked as undone
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Returns the status of the task
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    // Marks the task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Marks the task as undone
    public void markAsUndone() {
        this.isDone = false;
    }

}