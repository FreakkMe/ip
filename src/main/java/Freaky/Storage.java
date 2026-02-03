package Freaky;

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

/**
 * Manages saving and loading tasks to/from disk.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object that manages saving and loading tasks to the specified file path.
     *
     * @param filePath The path of the file to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Prints a message to the console surrounded by separator lines for better readability.
     *
     * @param string The message to print.
     */
    public static void print(String string) {
        System.out.println("----------------------------------------------------- \n"
                         + string + "\n"
                         + "----------------------------------------------------- \n");
    }

    /**
     * Ensures that the file at the specified file path exists.
     * If the file or its parent directories do not exist, they will be created.
     *
     * @throws IOException If an I/O error occurs while creating the file or directories.
     */
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

    /**
     * Loads task list from file.
     *
     * @return TaskList loaded from disk.
     */
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

    /**
     * Saves task list to file.
     *
     * @param tasks List of tasks to save.
     */
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

    /**
     * Parses a single line from the storage file and converts it into a Task object.
     * The expected line format is:
     * TYPE | STATUS | DESCRIPTION | OPTIONAL
     *
     * Where:
     *     TYPE: "T" for to-dos, "D" for deadlines, "E" for events
     *     STATUS: "1" if done, "0" if not done
     *     DESCRIPTION: Task's description
     *     OPTIONAL: For Deadline, the due date; for Event, the start and end time in "'start time' -> 'end time'" format
     *
     * @param line The line from the file representing a task.
     * @return The Task object corresponding to the line.
     * @throws IllegalArgumentException If the task type is unknown or if date-time parsing fails.
     */
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