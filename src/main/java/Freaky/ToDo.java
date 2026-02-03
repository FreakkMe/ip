package Freaky;

/**
 * Represents a task with no other special information.
 * A to-do has a description and a completion status.
 */
public class ToDo extends Task {

    /**
     * Creates a to-do task with the specified description.
     * The completion status of a to-do is set default to not completed.
     *
     * @param description Description of the deadline task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a formatted string of the to-do for printing to the user.
     *
     * @return String in the format "[T][ ] 'description'".
     */
    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description;
    }

    /**
     * Returns a string representation of the to-do for saving to a file.
     *
     * @return String in the format "T | 0 | description".
     */
    @Override
    public String toFileString() {
        return "T | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description;
    }

}
