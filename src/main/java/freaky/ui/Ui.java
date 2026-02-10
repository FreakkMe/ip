package freaky.ui;

import freaky.task.Task;
import freaky.task.TaskList;

/**
 * Handles all user interactions for the Freaky chatbot.
 * This includes printing messages to the console for greetings, task updates and errors, depending on user prompts.
 */
public class Ui {

    /**
     * Constructs a Ui instance for handling interactions.
     */
    public Ui() { }

    /**
     * Prints a message with a formatted separator bar above and below.
     *
     * @param string The message to print
     */
    public static void print(String string) {
        System.out.println("----------------------------------------------------------------- \n"
                + string + "\n"
                + "----------------------------------------------------------------- \n");
    }

    /**
     * Prints the greeting message when the chatbot starts.
     */
    public void printGreetMessage() {
        print("Hello! I'm Freaky. \n"
            + "What can I do for you?");
    }

    /**
     * Prints the farewell message when the chatbot exits.
     */
    public void printByeMessage() {
        print("You are leaving? T_T Please don't forget Freaky. Freaky is always here to chat with you.");
    }

    /**
     * Prints a message when the user enters an unknown command.
     */
    public void printUnKnownCommandMessage() {
        print("Unknown command T_T");
    }

    /**
     * Prints a message when the error is encountered when loading tasks from hard disk.
     */
    public void printCorruptedFilesMessage() {
        print("Error loading file. Tasks stored on hard disk may be corrupted. T_T");
    }

    /**
     * Prints a message when the user's input is empty.
     */
    public void printRepeatMessage() {
        print("Freaky didn't catch what you say, can you please enter it again?");
    }

    /**
     * Prints a message indicating the format for 'mark' command.
     */
    public void printMarkFormatMessage() {
        print("No way broooo please enter an integer after 'mark' command to mark the corresponding task as done. \n"
                + "Try something like this: 'mark 2'.");
    }

    /**
     * Prints a message indicating the format for 'unmark' command.
     */
    public void printUnmarkFormatMessage() {
        print("No way broooo please enter an integer after 'unmark' command to mark the corresponding task as undone. \n"
                + "Try something like this: 'unmark 2'.");
    }

    /**
     * Prints a message when the 'mark' command is trying to apply on a task number that is greater than the total tasks left.
     */
    public void printMarkValidNumberMessage() {
        print("Broooo the content following by mark should be a valid task number. T_T");
    }

    /**
     * Prints a message when the 'unmark' command is trying to apply on a task number that is greater than the total tasks left.
     */
    public void printUnmarkValidNumberMessage() {
        print("Broooo the content following by mark should be a valid task number. T_T");
    }

    /**
     * Prints an error message when the task number entered by the user is invalid.
     *
     * @param size The number of tasks currently in the list
     */
    public void printListSizeError(int size) {
        print("There is only " + String.valueOf(size) + " tasks in your list, please enter a valid task number.");
    }

    /**
     * Prints a message when user is trying to check a non-positive number of coming deadlines or events.
     */
    public void printNegativeValueError() {
        print("Broooo how is it possible? A non positive number? (◣_◢)");
    }

    /**
     * Prints a message when user is trying to mark a task which is already marked.
     */
    public void printAlreadyMarkMessage() {
        print("The task is already marked as done.");
    }

    /**
     * Prints a message when user is trying to unmark a task which is already not marked.
     */
    public void printAlreadyUnmarkMessage() {
        print("The task is still undone brooooo.");
    }

    /**
     * Prints a message when the 'mark' command is successfully run.
     */
    public void printMarkSuccessMessage(Task task) {
        print("Nice! I've marked this task as done: \n"
                + "  " + task.print());
    }

    /**
     * Prints a message when the 'unmark' command is successfully run.
     */
    public void printUnmarkSuccessMessage(Task task) {
        print("OK, I've marked this task as not done yet: \n"
                + "  " + task.print());
    }

    /**
     * Prints a message indicating the format for 'todo' command.
     */
    public void printToDoFormatMessage() {
        print("No way broooo please enter a task description after 'todo' command to add a todo task to the list. \n"
                + "Try something like this: 'todo praise Freaky'.");
    }

    /**
     * Prints a message indicating the format for 'deadline' command.
     */
    public void printDeadlineFormatMessage() {
        print("No way broooo please enter a task description and a due date separated by '/by' after 'deadline' command to add a deadline task to the list. \n"
                + "Try something like this: 'deadline buy Freaky Premium /by 2026-02-01 0000'.");
    }

    /**
     * Prints a message indicating the format for 'event' command.
     */
    public void printEventFormatMessage() {
        print("No way broooo please enter a task description, a starting time and an ending time separated by '/from' and '/to' after 'event' command to add an event task to the list. \n"
                + "Try something like this: 'event chat with Freaky /from 2026-02-01 0000 /to 3026-02-01 0000'.");
    }

    /**
     * Prints a message indicating the format for 'check' command.
     */
    public void printCheckFormatMessage() {
        print("There is a format error broooo. Please try the following format for check command. \n"
                + "'check':                   default checks both closest deadlines and events \n"
                + "'check n':                 checks both n closest deadlines and events \n"
                + "'check deadline/event':    checks 3 closest deadlines/events \n"
                + "'check deadline/event n':  checks n closest deadlines/events");
    }

    /**
     * Prints a message when the user is trying to create a deadline task with invalid due time.
     */
    public void printDeadlineDateTimeErrorMessage() {
        print("Brooooo Freaky isn't smart enough to understand the date, please enter the date in format yyyy-MM-dd HHmm. \n"
                + "Try something like this: 'deadline buy Freaky Premium /by 2026-02-01 0000'.");
    }

    /**
     * Prints a message when the user is trying to create an event task with invalid start time or end time.
     */
    public void printEventDateTimeErrorMessage() {
        print("Brooooo Freaky isn't smart enough to understand the date, please enter the date in format yyyy-MM-dd HHmm. \n"
                + "Try something like this: 'event chat with Freaky /from 2026-02-01 0000 /to 3026-02-01 0000'.");
    }

    /**
     * Prints a message confirming that a task has been added.
     *
     * @param task The task that was added
     * @param tasks The list containing all tasks
     */
    public void printTaskAddedMessage(Task task, TaskList tasks) {
        print("Got it. I've added this task: \n"
                + "  " + task.print() + "\n"
                + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.");
    }

    /**
     * Prints a message indicating the format for 'delete' command.
     */
    public void printDeleteFormatMessage() {
        print("No way broooo please enter an integer after 'delete' command to delete the corresponding task. \n"
                + "Try something like this: 'delete 2'.");
    }

    /**
     * Prints a message when the 'delete' command is trying to apply on a task number that is greater than the total tasks left.
     */
    public void printDeleteValidNumberMessage() {
        print("Broooo the content following by delete should be a valid task number. T_T");
    }

    /**
     * Prints a message when the 'delete' command is successfully run.
     */
    public void printDeleteSuccessMessage(Task removedTask, TaskList taskList) {
        print("Noted. I've removed this task: \n"
                + "  " + removedTask.print() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints a message indicating the correct format for the "find" command when the user did not provide a keyword.
     */
    public void printFindFormatMessage() {
        print("Broooo you need to provide a keyword to find tasks! \n" +
              "Try something like: 'find book'");
    }

    /**
     * Prints the result of a "find" command by showing all tasks that match the given keyword in their description.
     *
     * If no tasks match, a message indicating no matches is printed.
     *
     * @param matches The TaskList containing all tasks that matched the keyword.
     * @param keyword The keyword used to search the tasks.
     */
    public void printFindSuccessMessage(TaskList matches, String keyword) {
        System.out.println("----------------------------------------------------------------- \n");

        if (matches.size() == 0) {
            System.out.println("Oh no Freaky can't find tasks that matches your keyword: " + keyword);

        } else {
            System.out.println("Here are the matching tasks in your list for keyword: '" + keyword + "'");

            for (int i = 0; i < matches.size(); i++) {
                Task task = matches.get(i);
                System.out.println((i + 1) + "." + task.print());
            }
        }

        System.out.println("----------------------------------------------------------------- \n");
    }

    /**
     * Prints all tasks in the task list, numbered in order.
     *
     * @param taskList The list of tasks to display
     */
    public void printTaskList(TaskList taskList) {
        System.out.println("----------------------------------------------------------------- \n"
                + "Here are the tasks in your list:");
        for (int n = 0; n < taskList.size(); n++) {
            Task task = taskList.get(n);
            System.out.println(String.valueOf(n + 1) + "." + task.print());
        }
        System.out.println("----------------------------------------------------------------- \n");
    }

    /**
     * Prints the upcoming deadlines from the provided list, up to a specified count.
     * If there are fewer deadlines than requested, a message is shown.
     *
     * @param check The number of upcoming deadlines to check
     * @param deadlineList The list of deadlines to display
     */
    public void printCheckDeadlineList(int check, TaskList deadlineList) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Checking the coming " + String.valueOf(check) + " deadlines in your list...");

        if (check > deadlineList.size()) {
            if (deadlineList.size() == 0) {
                System.out.println("Good news! There is no deadlines left in your list. Congrats!");
            } else {
                System.out.println("Good news! There is only " + String.valueOf(deadlineList.size()) + " deadlines left in your list. Congrats!");
            }
        }

        if (deadlineList.size() > 0) {
            System.out.println("Here are the coming " + String.valueOf(deadlineList.size()) + " deadlines in your list:");
        }

        for (int n = 0; n < deadlineList.size(); n++) {
            Task task = deadlineList.get(n);
            System.out.println(String.valueOf(n + 1) + "." + task.print());
        }

        System.out.println("----------------------------------------------------------------- \n");
    }

    /**
     * Prints the upcoming events from the provided list, up to a specified count.
     * If there are fewer events than requested, a message is shown.
     *
     * @param check The number of upcoming events to check
     * @param eventList The list of events to display
     */
    public void printCheckEventList(int check, TaskList eventList) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Checking the coming " + String.valueOf(check) + " events in your list...");

        if (check > eventList.size()) {
            if (eventList.size() == 0) {
                System.out.println("Good news! There is no events left in your list. Congrats!");
            } else {
                System.out.println("Good news! There is only " + String.valueOf(eventList.size()) + " events left in your list. Congrats!");
            }
        }

        if (eventList.size() > 0) {
            System.out.println("Here are the coming " + String.valueOf(eventList.size()) + " events in your list:");
        }

        for (int n = 0; n < eventList.size(); n++) {
            Task task = eventList.get(n);
            System.out.println(String.valueOf(n + 1) + "." + task.print());
        }

        System.out.println("----------------------------------------------------------------- \n");
    }

}