public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }

    @Override
    public String print() {
        return "[" + this.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description;
    }
}
