public class Deadline extends Task {

    private String time;

    public Deadline(String description, String time) {
        super(description, TaskType.DEADLINE);
        this.time = time;
    }

    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.time + ")";
    }

    public String getTime() {
        return this.time;
    }
}