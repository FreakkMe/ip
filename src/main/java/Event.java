import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// The event task
public class Event extends Task {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    // The constructor of the event task, initializes with its description, start time and end time
    public Event(String description, String startTime, String endTime) {
        super(description, TaskType.EVENT);
        this.startTime = LocalDateTime.parse(startTime, INPUT_DATE_FORMAT);
        this.endTime = LocalDateTime.parse(endTime, INPUT_DATE_FORMAT);
    }

    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description + " (from: " + getStartTime() + " to: " + getEndTime() + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description + " | " + startTime.format(INPUT_DATE_FORMAT) + " -> " + endTime.format(INPUT_DATE_FORMAT);
    }

    public String getStartTime() {
        return this.startTime.format(OUTPUT_DATE_FORMAT);
    }

    public String getEndTime() {
        return this.endTime.format(OUTPUT_DATE_FORMAT);
    }

}