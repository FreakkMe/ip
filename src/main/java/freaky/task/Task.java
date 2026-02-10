package freaky.task;

/**
 * Represents a task, which can be one of type to-do, deadline or event.
 * A task has a description and a completion status.
 */
public abstract class Task {

    // String description stores description of the task, boolean isDone stores the status of the task
    private final String description;
    private Status status;
    private final TaskType type;

    // Enumerations of status
    public enum Status {
        DONE,
        NOT_DONE
    }

    // Enumerations of task types
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Creates a task with the specified description.
     * The completion status of a task is set default to not completed.
     *
     * @param description Description of the task.
     * @param type Type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.status = Status.NOT_DONE;
        this.type = type;
    }

    // Gets the description of the task
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the icon representing the completion status of the task.
     *
     * @return "X" if the task is done, otherwise " "
     */
    public String getStatusIcon() {
        return (status.equals(Status.DONE) ? "X" : " "); // mark done task with X
    }

    // Marks the task as done
    public void markAsDone() {
        this.status = Status.DONE;
    }

    // Marks the task as undone
    public void markAsUndone() {
        this.status = Status.NOT_DONE;
    }

    /**
     * Returns the icon representing the type of the task.
     *
     * @return "T" for to-do, "D" for deadline, "E" for event, "UNKNOWN" if type is unknown (should not happen)
     */
    public String getTypeIcon() {

        switch (this.type) {

        case TODO: return "T";

        case DEADLINE: return "D";

        case EVENT: return "E";

        default: return "UNKNOWN";

        }
    }

    /**
     * Returns a string representation of the task for display.
     * Subclasses must implement this method to show task details.
     *
     * @return Formatted string for display
     */
    public abstract String print();

    /**
     * Returns a string representation of the task for saving to a file.
     * Subclasses must implement this method to provide a storage-friendly format.
     *
     * @return Formatted string for file storage
     */
    public abstract String toFileString();

    /**
     * Compares this task with another object for equality.
     * Two tasks are equal if they are of the same type, have the same description, status and type.
     *
     * @param obj the object to compare with
     * @return true if the tasks are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        // They are the same reference
        if (this == obj) {
            return true;

        // Either obj is null or they are not of the same type
        } else if (obj == null || this.getClass() != obj.getClass()) {
            return false;

        // Compares their type, status and description
        } else {
            Task otherTask = (Task) obj;

            return this.description.equals(otherTask.description) && this.status.equals(otherTask.status) && this.type.equals(otherTask.type);
        }

    }

}