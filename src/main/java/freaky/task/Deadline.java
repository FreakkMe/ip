package freaky.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * A deadline has a description, a completion status and a due time.
 */
public class Deadline extends Task {

    private final LocalDateTime time;
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");


    /**
     * Creates a deadline task with the specified description and due time.
     * The completion status of a deadline is set default to not completed.
     *
     * @param description Description of the deadline task.
     * @param time Due date and time of the task.
     */
    public Deadline(String description, LocalDateTime time) {
        super(description, TaskType.DEADLINE);
        this.time = time;
    }

    /**
     * Returns a formatted string of the deadline for printing to the user.
     *
     * @return String in the format "[D][ ] 'description' (by: 'time')".
     */
    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + time.format(OUTPUT_DATE_FORMAT) + ")";
    }

    /**
     * Returns a string representation of the deadline for saving to a file.
     *
     * @return String in the format "D | 0 | description | yyyy-MM-dd HHmm".
     */
    @Override
    public String toFileString() {
        return "D | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.getDescription() + " | " + time.format(INPUT_DATE_FORMAT);
    }

    /**
     * Returns the due time of the deadline.
     *
     * @return LocalDateTime representing the deadline's due time.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Returns true if this deadline is equal to the specified object.
     * Two deadlines are considered equal if they are both of type Deadline, have the same description, status, type and due time.
     *
     * @param obj the object to compare with this deadline.
     * @return true if the given object is a Deadline with the same description, status, type, and due time; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        // Checks if they are the same with Task equals
        if (!super.equals(obj)) {
            return false;

        // Checks their due date if it's the same
        } else {
            Deadline otherTask = (Deadline) obj;

            return this.time.equals((otherTask.time));
        }

    }

}