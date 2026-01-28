public class Event extends Task {

    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

}