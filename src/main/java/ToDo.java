public class ToDo extends Task {

    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String print() {
        return "[" + super.getTypeIcon() + "][" + super.getStatusIcon() + "] " + super.description;
    }
}
