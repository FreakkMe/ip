public class Deadline extends Task {

    private String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String print() {
        return "[" + this.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.time + ")";
    }

    public String getTime() {
        return this.time;
    }
}