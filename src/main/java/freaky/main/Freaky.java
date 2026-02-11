package freaky.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import freaky.parser.Parser;
import freaky.storage.Storage;
import freaky.task.Deadline;
import freaky.task.Event;
import freaky.task.Task;
import freaky.task.TaskList;
import freaky.task.ToDo;
import freaky.ui.Ui;

/**
 * Represents the Freaky chatbot application.
 * Handles user input, task management, and command execution.
 */
public class Freaky {

    // Storage which handles the tasks stores in hard disk, tasks the tasks that was stored,
    // ui handles the reply message of the bot
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Freaky chatbot with the specified file path for storage.
     * Attempts to load saved tasks from disk; if failed, starts with an empty task list.
     *
     * @param filePath File path to store/load tasks.
     */
    public Freaky(String filePath) {

        // Initializes Freaky with an ui and a storage
        ui = new Ui();
        storage = new Storage(filePath);

        // Loads the tasks saved on hard disk
        try {
            tasks = new TaskList(storage.load());

        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Handles a single user command and returns the response message.
     *
     * @param input User's input command string.
     * @return Response message to be displayed.
     */
    public String handleCommand(String input) {

        // Checks user's input of different cases: "" (empty)
        if (input.trim().isEmpty()) {
            return ui.repeatMessage();

        // Checks user's input of different cases: "bye"
        } else if (input.trim().equalsIgnoreCase("bye")) {
            return ui.byeMessage();

        // Checks user's input of different cases: "list"
        } else if (input.trim().equals("list")) {
            return ui.taskList(tasks);

        // Checks user's input of different cases: "mark"
        } else if (input.startsWith("mark")) {
            return handleMark(input, true);

        // Checks user's input of different cases: "unmark"
        } else if (input.startsWith("unmark")) {
            return handleMark(input, false);

        // Checks user's input of different cases: "delete"
        } else if (input.startsWith("delete")) {
            return handleDelete(input);

        // Checks user's input of different cases: "to-do", "deadline" and "event"
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return handleAddTask(input);

        // Checks user's input of different cases: "check"
        } else if (input.startsWith("check")) {
            return handleCheck(input);

        // Checks user's input of different cases: "find"
        } else if (input.startsWith("find")) {
            return handleFind(input);

        // It's an unknown command
        } else {
            return ui.unKnownCommandMessage();
        }
    }

    /**
     * Processes a "mark" or "unmark" command for tasks.
     * Marks a task as done if "isMark" is true, otherwise marks it as not done.
     * Performs input validation and prints appropriate UI messages.
     *
     * @param input The full input string from the user, e.g. "mark 2" or "unmark 3".
     * @param isMark True if the command is "mark", false if "unmark".
     * @return String of the mark command replied by Freaky.
     */
    private String handleMark(String input, boolean isMark) {

        String[] parts = input.split(" ", 2);

        // Checks if the input after "mark" is empty, returns a message if so
        if (parts.length < 2 || parts[1].matches(" *")) {
            return isMark ? ui.markFormatMessage() : ui.unmarkFormatMessage();
        }

        int taskNumber;

        // Casts the input string after it to an integer
        try {
            taskNumber = Parser.parseInteger(parts[1]) - 1;

        // Checks if the string after "mark " is an integer, returns a message if not
        } catch (NumberFormatException e) {
            return isMark ? ui.markValidNumberMessage() : ui.unmarkValidNumberMessage();
        }

        // Checks if the task number is valid, returns a message if not
        if (taskNumber < 0) {
            return ui.negativeValueError();
        } else if (taskNumber >= tasks.size()) {
            return ui.listSizeError(tasks.size());
        }

        Task task = tasks.get(taskNumber);

        // Mark case
        if (isMark) {

            // Checks if the task is already marked as done, returns a message if so
            if (task.getStatusIcon().equals("X")) {
                return ui.alreadyMarkMessage();

            // Marks the task as done
            } else {
                task.markAsDone();
                storage.save(tasks.getTasks());
                return ui.markSuccessMessage(task);
            }

        // Unmark case
        } else {

            // Checks if the task is already marked as undone, returns a message if so
            if (task.getStatusIcon().equals(" ")) {
                return ui.alreadyUnmarkMessage();

            // Unmarks the task from done
            } else {
                task.markAsUndone();
                storage.save(tasks.getTasks());
                return ui.unmarkSuccessMessage(task);
            }
        }
    }

    /**
     * Processes a "delete" command for tasks.
     * Removes the task at the specified index after validating input.
     * Prints success or error messages to the UI.
     *
     * @param input The full input string from the user, e.g. "delete 2".
     * @return String of the delete command replied by Freaky.
     */
    private String handleDelete(String input) {

        String[] parts = input.split(" ", 2);

        // Checks if the input is valid, returns a message if not
        if (parts.length < 2 || parts[1].matches(" *")) {
            return ui.deleteFormatMessage();
        }

        int taskNumber;

        // Casts the input string after it to an integer
        try {
            taskNumber = Parser.parseInteger(parts[1]) - 1;

        // Checks if the string after "delete " is an integer, returns a message if not
        } catch (NumberFormatException e) {
            return ui.deleteValidNumberMessage();
        }

        // Checks if the task number is valid, returns a message if not
        if (taskNumber < 0) {
            return ui.negativeValueError();
        } else if (taskNumber >= tasks.size()) {
            return ui.listSizeError(tasks.size());
        }

        Task removed = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        storage.save(tasks.getTasks());

        return ui.deleteSuccessMessage(removed, tasks);
    }

    /**
     * Processes "todo", "deadline", or "event" commands.
     * Adds a new task to the task list after validating the input format and dates.
     * Prints success or error messages to the UI.
     *
     * @param input The full input string from the user, e.g.
     *              "todo read book", "deadline submit report /by 2026-02-01 1800",
     *              or "event team meeting /from 2026-02-01 1500 /to 2026-02-01 1600".
     * @return String of the to-do/deadline/event command replied by Freaky.
     */
    private String handleAddTask(String input) {

        boolean hasBy = input.contains(" /by ");
        boolean hasFrom = input.contains(" /from ");
        boolean hasTo = input.contains(" /to ");

        // Checks if the input after "to-do", "deadline" or "event" is valid, returns a message if not
        if (input.trim().equals("todo")) {
            return ui.toDoFormatMessage();
        } else if (input.startsWith("deadline") && !hasBy
                || input.replaceFirst("deadline ", "").replaceFirst(" /by ", "").matches(" *")) {
            return ui.deadlineFormatMessage();
        } else if (input.startsWith("event") && (!hasFrom || !hasTo)
                || input.replaceFirst("event ", "").replaceFirst(" /from ", "")
                .replaceFirst(" /to ", "").matches(" *")) {
            return ui.eventFormatMessage();
        }

        Task task;

        // To-do case
        if (input.startsWith("todo ")) {
            task = new ToDo(input.split("todo ", 2)[1]);

        // Deadline case
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.split("deadline ", 2)[1].split(" /by ", 2);

            LocalDateTime time;

            // Checks if the input date for deadline is valid, returns a message if not
            try {
                time = Parser.parseLocalDateTime(parts[1].trim());
            } catch (DateTimeParseException e) {
                return ui.deadlineDateTimeErrorMessage();
            }

            task = new Deadline(parts[0], time);

        // Event case
        } else if (input.startsWith("event ")) {
            String[] parts = input.split("event ", 2)[1].split(" /from ", 2);
            String[] times = parts[1].split(" /to ", 2);

            LocalDateTime startTime;
            LocalDateTime endTime;

            // Checks if the input date for event is valid, returns a message if not
            try {
                startTime = Parser.parseLocalDateTime(times[0].trim());
                endTime = Parser.parseLocalDateTime(times[1].trim());
            } catch (DateTimeParseException e) {
                return ui.eventDateTimeErrorMessage();
            }

            task = new Event(parts[0], startTime, endTime);

        // Command invalid case
        } else {
            return ui.eventDateTimeErrorMessage();
        }

        tasks.add(task);
        storage.save(tasks.getTasks());

        // Returns task info
        return ui.taskAddedMessage(task, tasks);
    }

    /**
     * Processes the "check" command to display upcoming deadlines and/or events.
     * Supports various formats:
     * - "check"                                : shows 1 closest deadline and 1 closest event
     * - "check n"                              : shows n closest deadlines and events
     * - "check deadline" or "check event"      : shows 3 closest of deadlines/events
     * - "check deadline n" or "check event n"  : shows n closest of deadlines/events
     *
     * Prints the tasks to the UI and handles invalid input gracefully.
     *
     * @param input The full input string from the user, e.g. "check", "check 2",
     *              "check deadline", or "check event 5".
     * @return String of the check command replied by Freaky.
     */
    private String handleCheck(String input) {

        // Default checks both task, one per task
        enum CheckType { BOTH, DEADLINE, EVENT }
        CheckType checkType;
        int check;

        // Split input into tokens to check the match type
        String[] tokens = input.trim().split(" ");

        // The default "check" case
        if (tokens.length == 1 && input.equals("check")) {
            checkType = CheckType.BOTH;
            check = 1;

        // Can be in "check n" format or "check deadline/event" format
        } else if (tokens.length == 2) {

            // "check deadline" case
            if (tokens[1].equals("deadline")) {
                checkType = CheckType.DEADLINE;
                check = 3;

            // "check event" case
            } else if (tokens[1].equals("event")) {
                checkType = CheckType.EVENT;
                check = 3;

            // "check n" case
            } else {
                try {
                    checkType = CheckType.BOTH;
                    check = Parser.parseInteger(tokens[1]);
                } catch (NumberFormatException e) {
                    return ui.checkFormatMessage();
                }
            }

        // In "check deadline/event n" format
        } else if (tokens.length == 3) {

            if (tokens[1].equals("deadline")) {
                checkType = CheckType.DEADLINE;

            } else if (tokens[1].equals("event")) {
                checkType = CheckType.EVENT;

            } else {
                return ui.checkFormatMessage();
            }

            // Checks if the input after "check deadline/event ", returns a message if it is invalid
            try {
                check = Parser.parseInteger(tokens[2]);
            } catch (NumberFormatException e) {
                return ui.checkFormatMessage();
            }

        // Incorrect format
        } else {
            return ui.checkFormatMessage();
        }

        // Checks if the number to check is valid (non-positive), no errors even if it
        // exceeds the max number of deadlines/events left
        if (check <= 0) {
            return ui.negativeValueError();
        }

        // Extracts deadlines and events that weren't marked as done before
        TaskList deadlineList = Helper.getClosestDeadlines(tasks, check);
        TaskList eventList = Helper.getClosestEvents(tasks, check);

        StringBuilder response = new StringBuilder();

        switch (checkType) {

        // "check" or "check n" case which checks both type of tasks
        case BOTH:
            response.append(ui.checkDeadlineList(check, deadlineList))
                    .append("\n")
                    .append(ui.checkEventList(check, eventList));
            break;

        // "check deadline" or "check deadline n" case which checks deadline tasks
        case DEADLINE:
            response.append(ui.checkDeadlineList(check, deadlineList));
            break;

        // "check event" or "check event n" case which check event tasks
        case EVENT:
            response.append(ui.checkEventList(check, eventList));
            break;

        // This line shouldn't be reached
        default:
            return ui.checkFormatMessage();
        }

        return response.toString().trim();
    }

    /**
     * Processes the "find" command by searching for tasks that contain a given keyword in their description.
     * If no keyword is provided, prints a message indicating the correct format of the command.
     *
     * The search is case-insensitive and matches any part of the task description.
     *
     * @param input The full user input string starting with "find" followed by the keyword.
     *              Example: "find book".
     * @return String of the find command replied by Freaky.
     */
    private String handleFind(String input) {
        String[] parts = input.split(" ", 2);

        // Returns a message indicating the format of 'find' command if no keyword is provided
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            return ui.findFormatMessage();
        }

        String keyword = parts[1].trim();
        TaskList matches = new TaskList();

        // Search through all tasks
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }

        return ui.findSuccessMessage(matches, keyword);
    }

    //    /**
    //     * Main entry point of the application.
    //     *
    //     * @param args Command line arguments (not used).
    //     */
    //    public static void main(String[] args) {
    //        new Freaky("./data/freaky.txt").run();
    //            }

}
