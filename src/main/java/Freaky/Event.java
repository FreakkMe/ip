package Freaky;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// The event task
public class Event extends Task {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    // The constructor of the event task, initializes with its description, start time and end time
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description + " (from: " + this.startTime.format(OUTPUT_DATE_FORMAT) + " to: " + this.endTime.format(OUTPUT_DATE_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description + " | " + startTime.format(INPUT_DATE_FORMAT) + " -> " + endTime.format(INPUT_DATE_FORMAT);
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    @Override
    public boolean equals(Object obj) {

        // Checks if they are the same with Task equals
        if (!super.equals(obj)) {
            return false;

            // Checks their startTime and endTime if it's the same
        } else {
            Event other = (Event) obj;

            return this.startTime.equals((other.startTime)) && this.endTime.equals(other.endTime);
        }

    }

}