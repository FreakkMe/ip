// The deadline task
public class Deadline extends Task {

    private String time;

    // Constructor of the deadline task, initializes with its description and deadline
    public Deadline(String description, String time) {
        super(description, TaskType.DEADLINE);
        this.time = time;
    }

    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.time + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description + " | " + this.time;
    }

    public String getTime() {
        return this.time;
    }
}