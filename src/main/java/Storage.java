import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Storage class which handles every operation related to load files and save files
public class Storage {

    private final String filePath;

    // Constructor of the storage, initializes with the file path to the stored date
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Print method to print a string with format bar on top and below
    public static void print(String string) {
        System.out.println("----------------------------------------------------- \n"
                + string + "\n"
                + "----------------------------------------------------- \n");
    }

    // Ensures folder and file exist
    private void ensureFileExists() throws IOException {

        File file = new File(filePath);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

    }

    // Loads tasks from file
    public ArrayList<Task> load() {

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            ensureFileExists();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {

                try {
                    Task task = parseLine(line);
                    tasks.add(task);

                } catch (Exception e) {
                    // Skips corrupted line if discovered
                    print("Warning: Skipping corrupted line: " + line);
                }

            }

            reader.close();

        } catch (IOException e) {
            print("Error loading file: " + e.getMessage());
        }

        return tasks;
    }

    // Saves tasks to file
    public void save(List<Task> tasks) {

        try {
            ensureFileExists();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            print("Error saving file: " + e.getMessage());
        }
    }

    // Converts one line into a Task object
    private Task parseLine(String line) {

        // Format: TYPE | STATUS | DESCRIPTION | OPTIONAL
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {

            case "T":

                ToDo todo = new ToDo(description);
                if (isDone) todo.markAsDone();
                return todo;

            case "D":

                String by = parts[3];
                Deadline deadline = new Deadline(description, by);
                if (isDone) deadline.markAsDone();
                return deadline;

            case "E":

                String fromTo = parts[3];
                String[] times = fromTo.split(" -> ");
                Event event = new Event(description, times[0], times[1]);
                if (isDone) event.markAsDone();
                return event;

            default:

                throw new IllegalArgumentException("Unknown task type");

        }
    }
}