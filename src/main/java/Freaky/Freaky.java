package Freaky;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

/**
 * Represents the Freaky chatbot application.
 * Handles user input, task management, and command execution.
 */
public class Freaky {

    // Storage which handles the tasks stores in hard disk, tasks the tasks that was stored, ui handles the reply message of the bot
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
            return;
        } catch (Exception e) {
            ui.printCorruptedFilesMessage();
        }

        tasks = new TaskList();

    }

    /**
     * Starts the chatbot interaction loop.
     * Continuously reads user input, executes commands and displays responses.
     */
    public void run() {

        // Input of the user
        Scanner scanner = new Scanner(System.in);

        // Initialize variables input to store user's previous input
        String input;

        // Chatbot starts here
        ui.printGreetMessage();

        // Detecting user's input
        while (true) {

            // Stores user's input to input
            input = scanner.nextLine().trim();

            // Checks user's input of different cases: "" (empty)
            if (input.matches(" *")) {
                ui.printRepeatMessage();
                continue;

            // Checks user's input of different cases: "bye"
            } else if (input.trim().equals("bye")) {
                ui.printByeMessage();
                break;

            // Checks user's input of different cases: command case
            } else {
                handleCommand(input);
            }
        }

    }

    /**
     * Handles a single user command.
     *
     * @param input User's input command string.
     */
    private void handleCommand(String input) {

        // Checks user's input of different cases: "list"
        if (input.trim().equals("list")) {
            ui.printTaskList(tasks);
            return;

        // Checks user's input of different cases: "mark"
        } else if (input.startsWith("mark")) {
            handleMark(input, true);
            return;

        // Checks user's input of different cases: "unmark"
        } else if (input.startsWith("unmark")) {
            handleMark(input, false);
            return;

        // Checks user's input of different cases: "delete"
        } else if (input.startsWith("delete")) {
            handleDelete(input);
            return;

        // Checks user's input of different cases: "todo", "deadline" and "event"
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            handleAddTask(input);
            return;

        // Checks user's input of different cases: "check"
        } else if (input.startsWith("check")) {
            handleCheck(input);

        // It's an unknown command
        } else {
            ui.printUnKnownCommandMessage();
        }

    }

    /**
     * Processes a "mark" or "unmark" command for tasks.
     * Marks a task as done if "isMark" is true, otherwise marks it as not done.
     * Performs input validation and prints appropriate UI messages.
     *
     * @param input The full input string from the user, e.g. "mark 2" or "unmark 3".
     * @param isMark True if the command is "mark", false if "unmark".
     */
    private void handleMark(String input, boolean isMark) {

        String[] parts = input.split(" ", 2);

        // Checks if the input after "mark" is empty, returns a message if so
        if (parts.length < 2 || parts[1].matches(" *")) {
            if (isMark) {
                ui.printMarkFormatMessage();
            } else {
                ui.printUnmarkFormatMessage();
            }
            return;
        }

        int taskNumber;

        // Casts the input string after it to an integer
        try {
            taskNumber = Parser.parseInteger(parts[1]) - 1;

        // Checks if the string after "mark " is an integer, returns a message if not
        } catch (NumberFormatException e) {
            if (isMark) {
                ui.printMarkValidNumberMessage();
            } else {
                ui.printUnmarkValidNumberMessage();
            }
            return;
        }

        // Checks if the task number is valid, returns a message if not
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            ui.printListSizeError(tasks.size());
            return;
        }

        Task task = tasks.get(taskNumber);

        // Mark case
        if (isMark) {

            // Checks if the task is already marked as done, returns a message if so
            if (task.getStatusIcon().equals("X")) {
                ui.printAlreadyMarkMessage();

            // Marks the task as done
            } else {
                task.markAsDone();
                storage.save(tasks.getTasks());
                ui.printMarkSuccessMessage(task);
            }

        // Unmark case
        } else {

            // Checks if the task is already marked as undone, returns a message if so
            if (task.getStatusIcon().equals(" ")) {
                ui.printAlreadyUnmarkMessage();

            // Unmarks the task from done
            } else {
                task.markAsUndone();
                storage.save(tasks.getTasks());
                ui.printUnmarkSuccessMessage(task);
            }
        }
    }

    /**
     * Processes a "delete" command for tasks.
     * Removes the task at the specified index after validating input.
     * Prints success or error messages to the UI.
     *
     * @param input The full input string from the user, e.g. "delete 2".
     */
    private void handleDelete(String input) {

        String[] parts = input.split(" ", 2);

        // Checks if the input is valid, returns a message if not
        if (parts.length < 2 || parts[1].matches(" *")) {
            ui.printDeleteFormatMessage();
            return;
        }

        int taskNumber;

        // Casts the input string after it to an integer
        try {
            taskNumber = Parser.parseInteger(parts[1]) - 1;

        // Checks if the string after "delete " is an integer, returns a message if not
        } catch (NumberFormatException e) {
            ui.printDeleteValidNumberMessage();
            return;
        }

        // Checks if the task number is valid, returns a message if not
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            ui.printListSizeError(tasks.size());
            return;
        }

        Task removed = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        storage.save(tasks.getTasks());
        ui.printDeleteSuccessMessage(removed, tasks);

    }

    /**
     * Processes "todo", "deadline", or "event" commands.
     * Adds a new task to the task list after validating the input format and dates.
     * Prints success or error messages to the UI.
     *
     * @param input The full input string from the user, e.g.
     *              "todo read book", "deadline submit report /by 2026-02-01 1800",
     *              or "event team meeting /from 2026-02-01 1500 /to 2026-02-01 1600".
     */
    private void handleAddTask(String input) {

        // Checks if the input after "todo", "deadline" or "event" is valid, returns a message if not
        if (input.trim().equals("todo")) {
            ui.printToDoFormatMessage();
            return;
        } else if (input.startsWith("deadline") && (!input.contains(" /by ") || input.replaceFirst("deadline ", "").replaceFirst("/by", "").matches(" *"))) {
            ui.printDeadlineFormatMessage();
            return;
        } else if (input.startsWith("event") && (!input.contains(" /from ") || !input.contains(" /to ") ||
                input.replaceFirst("event ", "").replaceFirst(" /from ", "").replaceFirst(" /to ", "").matches(" *"))) {
            ui.printEventFormatMessage();
            return;
        }

        Task task;

        // To do case
        if (input.startsWith("todo ")) {
            task = new ToDo(input.split("todo ", 2)[1]);

        // Deadline case
        } else if (input.startsWith("deadline ") && input.contains(" /by ")
                && input.replaceFirst("deadline ", "").replaceFirst("/by", "").matches(" *")
                || input.startsWith("deadline") && !input.contains(" /by ")) {

            String[] parts = input.split("deadline ", 2)[1].split(" /by ", 2);

            LocalDateTime time;

            // Checks if the input date for deadline is valid, returns a message if not
            try {
                time = Parser.parseLocalDateTime(parts[1].trim());
            } catch (DateTimeParseException e) {
                ui.printDeadlineDateTimeErrorMessage();
                return;
            }

            task = new Deadline(parts[0], time);

        // Event case
        } else if (input.startsWith("event ") && input.contains(" /from ") && input.contains(" /to ")
                && input.replaceFirst("event ", "").replaceFirst(" /from ", "").replaceFirst(" /to ", "").matches(" *")
                || input.startsWith("event") && (!input.contains((" /from ")) && !input.contains(" /to "))) {

            String[] parts = input.split("event ", 2)[1].split(" /from ", 2);
            String[] times = parts[1].split(" /to ", 2);

            LocalDateTime startTime;
            LocalDateTime endTime;

            // Checks if the input date for event is valid, returns a message if not
            try {
                startTime = Parser.parseLocalDateTime(times[0].trim());
                endTime = Parser.parseLocalDateTime(times[1].trim());
            } catch (DateTimeParseException e) {
                ui.printEventDateTimeErrorMessage();
                return;
            }

            task = new Event(parts[0], startTime, endTime);

        // Command invalid case
        } else {
            ui.printDeadlineDateTimeErrorMessage();
            return;
        }

        tasks.add(task);
        storage.save(tasks.getTasks());

        // Prints out task info
        ui.printTaskAddedMessage(task, tasks);

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
     */
    private void handleCheck(String input) {

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
            if (tokens[1].trim().equals("deadline")) {
                checkType = CheckType.DEADLINE;
                check = 3;

            // "check event" case
            } else if (tokens[1].trim().equals("event")) {
                checkType = CheckType.EVENT;
                check = 3;

            // "check n" case
            } else {
                try {
                    checkType = CheckType.BOTH;
                    check = Parser.parseInteger(tokens[1].trim());
                } catch (NumberFormatException e) {
                    ui.printCheckFormatMessage();
                    return;
                }
            }

        // In "check deadline/event n" format
        } else if (tokens.length == 3) {

            if (tokens[1].trim().equals("deadline")) {
                checkType = CheckType.DEADLINE;

            } else if (tokens[1].trim().equals("event")) {
                checkType = CheckType.EVENT;

            } else {
                ui.printCheckFormatMessage();
                return;
            }

            // Checks if the input after "check deadline/event ", returns a message if it is invalid
            try {
                check = Parser.parseInteger(tokens[2].trim());
            } catch (NumberFormatException e) {
                ui.printCheckFormatMessage();
                return;
            }

        // Incorrect format
        } else {
            ui.printCheckFormatMessage();
            return;
        }

        // Checks if the number to check is valid (non-positive), no errors even if it exceeds the max number of deadlines/events left
        if (check <= 0) {
            ui.printNegativeValueError();
            return;
        }

        // Extracts deadlines and events that weren't marked as done before
        TaskList deadlineList = Helper.getClosestDeadlines(tasks, check);
        TaskList eventList = Helper.getClosestEvents(tasks, check);

        switch (checkType) {

            // "check" or "check n" case which checks both type of tasks
            case BOTH:
                ui.printCheckDeadlineList(check, deadlineList);
                ui.printCheckEventList(check, eventList);
                break;

            // "check deadline" or "check deadline n" case which checks deadline tasks
            case DEADLINE:
                ui.printCheckDeadlineList(check, deadlineList);
                break;

            // "check event" or "check event n" case which check event tasks
            case EVENT:
                ui.printCheckEventList(check, eventList);
                break;
        }
    }

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Freaky("./data/freaky.txt").run();
    }
}
