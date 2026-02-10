package freaky.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific start time and end time.
 * An event has a description, a completion status, a start time and an end time.
 */
public class Event extends Task {

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Creates an event task with the specified description, start time and end time.
     * The completion status of an event is set default to not completed.
     *
     * @param description Description of the event task.
     * @param startTime Start date and time of the task.
     * @param endTime End date and time of the task.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a formatted string of the event for printing to the user.
     *
     * @return String in the format "[E][ ] 'description' (from: 'start time' to: 'end time')".
     */
    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.getDescription() + " (from: " + this.startTime.format(OUTPUT_DATE_FORMAT) + " to: " + this.endTime.format(OUTPUT_DATE_FORMAT) + ")";
    }

    /**
     * Returns a string representation of the event for saving to a file.
     *
     * @return String in the format "E | 0 | description | yyyy-MM-dd HHmm -> yyyy-MM-dd HHmm".
     */
    @Override
    public String toFileString() {
        return "E | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.getDescription() + " | " + startTime.format(INPUT_DATE_FORMAT) + " -> " + endTime.format(INPUT_DATE_FORMAT);
    }

    /**
     * Returns the start time of the event.
     *
     * @return LocalDateTime representing the event's start time.
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return LocalDateTime representing the event's end time.
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns true if this event is equal to the specified object.
     * Two events are considered equal if they are both of type Event, have the same description, status, type, start time and end time.
     *
     * @param obj the object to compare with this event.
     * @return true if the given object is a Event with the same description, status, type, start time and end time; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        // Checks if they are the same with Task equals
        if (!super.equals(obj)) {
            return false;

            // Checks their startTime and endTime if it's the same
        } else {
            Event otherTask = (Event) obj;

            return this.startTime.equals((otherTask.startTime)) && this.endTime.equals(otherTask.endTime);
        }

    }

}