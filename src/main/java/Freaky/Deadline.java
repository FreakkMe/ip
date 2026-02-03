package Freaky;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// The deadline task
public class Deadline extends Task {

    private LocalDateTime time;
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");


    // Constructor of the deadline task, initializes with its description and deadline
    public Deadline(String description, LocalDateTime time) {
        super(description, TaskType.DEADLINE);
        this.time = time;
    }

    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description + " (by: " + time.format(OUTPUT_DATE_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description + " | " + time.format(INPUT_DATE_FORMAT);
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {

        // Checks if they are the same with Task equals
        if (!super.equals(obj)) {
            return false;

        // Checks their due date if it's the same
        } else {
            Deadline other = (Deadline) obj;

            return this.time.equals((other.time));
        }

    }

}