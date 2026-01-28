

public abstract class Task {

    // String description stores description of the task, boolean isDone stores the status of the task
    protected String description;
    private Status status;
    private TaskType type;

    // Enum of status
    public enum Status {
        DONE,
        NOT_DONE
    }

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    // Constructor of task, default to be marked as undone
    public Task(String description, TaskType type) {
        this.description = description;
        this.status = Status.NOT_DONE;
        this.type = type;
    }

    // Returns the status of the task
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

    public String getTypeIcon() {

        if (this.type.equals(TaskType.TODO)) {
            return "T";

        } else if (this.type.equals(TaskType.DEADLINE)) {
            return "D";

        } else if (this.type.equals(TaskType.EVENT)) {
            return "E";

        } else {
            return "UNKNOWN";
        }
    }

    public abstract String print();

}