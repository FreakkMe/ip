import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// The deadline task
public class Deadline extends Task {

    private LocalDateTime time;
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");


    // Constructor of the deadline task, initializes with its description and deadline
    public Deadline(String description, String time) {
        super(description, TaskType.DEADLINE);
        this.time = LocalDateTime.parse(time, INPUT_DATE_FORMAT);
    }

    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description + " (by: " + getTime() + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description + " | " + time.format(INPUT_DATE_FORMAT);
    }

    public String getTime() {
        return time.format(OUTPUT_DATE_FORMAT);
    }
}