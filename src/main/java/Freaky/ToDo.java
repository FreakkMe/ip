package Freaky;

// The todo task
public class ToDo extends Task {

    // Constructor of the todo task, initializes with its description
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description;
    }

    @Override
    public String toFileString() {
        return "T | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description;
    }
}
