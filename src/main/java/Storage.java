import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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

            // "todo" case
            case "T":

                ToDo todo = new ToDo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;

            // "deadline" case
            case "D":

                String by = parts[3];

                LocalDateTime time;

                // Checks if the format of the task stored in hard disk is valid, returns a message if not
                try {
                    time = Parser.parseLocalDateTime(parts[3]);
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid deadline datetime in file", e);
                }

                Deadline deadline = new Deadline(description, time);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;

            // "event" case
            case "E":

                String fromTo = parts[3];
                String[] times = fromTo.split(" -> ");

                LocalDateTime startTime;
                LocalDateTime endTime;

                // Checks if the format of the task stored in hard disk is valid, returns a message if not
                try {
                    startTime = Parser.parseLocalDateTime(times[0]);
                    endTime = Parser.parseLocalDateTime(times[1]);
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid event datetime in file", e);
                }

                Event event = new Event(description, startTime, endTime);
                if (isDone) {
                    event.markAsDone();
                }
                return event;

            // Format error case
            default:
                throw new IllegalArgumentException("Unknown task type");
        }
    }
}