// The event task
public class Event extends Task {

    private String startTime;
    private String endTime;

    // The constructor of the event task, initializes with its description, start time and end time
    public Event(String description, String startTime, String endTime) {
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description + " | " + this.startTime + " -> " + this.endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

}