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
     * Returns the greeting message when the chatbot starts.
     *
     * @return Greet message to be displayed.
     */
    public String greetMessage() {
        return "Hello! I'm Freaky. \n"
             + "What can I do for you?";
    }

    /**
     * Returns the farewell message when the chatbot exits.
     *
     * @return Bye message to be displayed.
     */
    public String byeMessage() {
        return "You are leaving? T_T Please don't forget Freaky. Freaky is always here to chat with you.";
    }

    /**
     * Returns a message when the user enters an unknown command.
     *
     * @return Unknown command message to be displayed.
     */
    public String unKnownCommandMessage() {
        return "Unknown command T_T";
    }

    /**
     * Returns a message when the error is encountered when loading tasks from hard disk.
     *
     * @return Corrupted files message to be displayed.
     */
    public String corruptedFilesMessage() {
        return "Error loading file. Tasks stored on hard disk may be corrupted. T_T";
    }

    /**
     * Returns a message when the user's input is empty.
     *
     * @return Repeat message to be displayed.
     */
    public String repeatMessage() {
        return "Freaky didn't catch what you say, can you please enter it again?";
    }

    /**
     * Returns a message indicating the format for 'mark' command.
     *
     * @return Mark format message to be displayed.
     */
    public String markFormatMessage() {
        return "No way broooo please enter an integer after 'mark' command to mark the corresponding task as done. \n"
             + "Try something like this: 'mark 2'.";
    }

    /**
     * Returns a message indicating the format for 'unmark' command.
     *
     * @return Unmark format message to be displayed.
     */
    public String unmarkFormatMessage() {
        return "No way broooo please enter an integer after 'unmark' command to "
               + "mark the corresponding task as undone. \n"
             + "Try something like this: 'unmark 2'.";
    }

    /**
     * Returns a message when the 'mark' command is trying to apply on a task number
     * that is greater than the total tasks left.
     *
     * @return Mark valid number message to be displayed.
     */
    public String markValidNumberMessage() {
        return "Broooo the content following by mark should be a valid task number. T_T";
    }

    /**
     * Returns a message when the 'unmark' command is trying to apply on a task number
     * that is greater than the total tasks left.
     *
     * @return Unmark valid number message to be displayed.
     */
    public String unmarkValidNumberMessage() {
        return "Broooo the content following by mark should be a valid task number. T_T";
    }

    /**
     * Returns an error message when the task number entered by the user is invalid.
     *
     * @param size The number of tasks currently in the list
     * @return List size error message to be displayed.
     */
    public String listSizeError(int size) {
        return "There is only " + String.valueOf(size) + " tasks in your list, please enter a valid task number.";
    }

    /**
     * Returns a message when user is trying to check a non-positive number of coming deadlines or events.
     *
     * @return Negative value error message to be displayed.
     */
    public String negativeValueError() {
        return "Broooo how is it possible? A non positive number? (◣_◢)";
    }

    /**
     * Returns a message when user is trying to mark a task which is already marked.
     *
     * @return Already mark message to be displayed.
     */
    public String alreadyMarkMessage() {
        return "The task is already marked as done.";
    }

    /**
     * Returns a message when user is trying to unmark a task which is already not marked.
     *
     * @return Already unmark message to be displayed.
     */
    public String alreadyUnmarkMessage() {
        return "The task is still undone brooooo.";
    }

    /**
     * Returns a message when the 'mark' command is successfully run.
     *
     * @return Mark success message to be displayed.
     */
    public String markSuccessMessage(Task task) {
        return "Nice! I've marked this task as done: \n"
             + "  " + task.print();
    }

    /**
     * Returns a message when the 'unmark' command is successfully run.
     *
     * @return Unmark success message to be displayed.
     */
    public String unmarkSuccessMessage(Task task) {
        return "OK, I've marked this task as not done yet: \n"
             + "  " + task.print();
    }

    /**
     * Returns a message indicating the format for 'to-do' command.
     *
     * @return To-do format message to be displayed.
     */
    public String toDoFormatMessage() {
        return "No way broooo please enter a task description after 'todo' command to add a todo task to the list. \n"
             + "Try something like this: 'todo praise Freaky'.";
    }

    /**
     * Returns a message indicating the format for 'deadline' command.
     *
     * @return Deadline format message to be displayed.
     */
    public String deadlineFormatMessage() {
        return "No way broooo please enter a task description and a due date separated by '/by' "
               + "after 'deadline' command to add a deadline task to the list. \n"
             + "Try something like this: 'deadline buy Freaky Premium /by 2026-02-01 0000'.";
    }

    /**
     * Returns a message indicating the format for 'event' command.
     *
     * @return Event format message to be displayed.
     */
    public String eventFormatMessage() {
        return "No way broooo please enter a task description, a starting time and an ending time separated by "
               + "'/from' and '/to' after 'event' command to add an event task to the list. \n"
             + "Try something like this: 'event chat with Freaky /from 2026-02-01 0000 /to 3026-02-01 0000'.";
    }

    /**
     * Returns a message indicating the format for 'check' command.
     *
     * @return Check format message to be displayed.
     */
    public String checkFormatMessage() {
        return "There is a format error broooo. Please try the following format for check command. \n"
             + "'check':                   default checks both closest deadlines and events \n"
             + "'check n':                 checks both n closest deadlines and events \n"
             + "'check deadline/event':    checks 3 closest deadlines/events \n"
             + "'check deadline/event n':  checks n closest deadlines/events";
    }

    /**
     * Returns a message when the user is trying to create a deadline task with invalid due time.
     *
     * @return Deadline date time error message to be displayed.
     */
    public String deadlineDateTimeErrorMessage() {
        return "Brooooo Freaky isn't smart enough to understand the date, please enter the date in "
               + "format yyyy-MM-dd HHmm. \n"
             + "Try something like this: 'deadline buy Freaky Premium /by 2026-02-01 0000'.";
    }

    /**
     * Returns a message when the user is trying to create an event task with invalid start time or end time.
     *
     * @return Event date time error message to be displayed.
     */
    public String eventDateTimeErrorMessage() {
        return "Brooooo Freaky isn't smart enough to understand the date, please enter the date in "
               + "format yyyy-MM-dd HHmm. \n"
             + "Try something like this: 'event chat with Freaky /from 2026-02-01 0000 /to 3026-02-01 0000'.";
    }

    /**
     * Returns a message confirming that a task has been added.
     *
     * @param task The task that was added
     * @param tasks The list containing all tasks
     * @return Task added message to be displayed.
     */
    public String taskAddedMessage(Task task, TaskList tasks) {
        return "Got it. I've added this task: \n"
             + "  " + task.print() + "\n"
             + "Now you have " + String.valueOf(tasks.size()) + " tasks in the list.";
    }

    /**
     * Returns a message indicating the format for 'delete' command.
     *
     * @return Delete format message to be displayed.
     */
    public String deleteFormatMessage() {
        return "No way broooo please enter an integer after 'delete' command to delete the corresponding task. \n"
             + "Try something like this: 'delete 2'.";
    }

    /**
     * Returns a message when the 'delete' command is trying to apply on a task number
     * that is greater than the total tasks left.
     *
     * @return Delete valid number message to be displayed.
     */
    public String deleteValidNumberMessage() {
        return "Broooo the content following by delete should be a valid task number. T_T";
    }

    /**
     * Returns a message when the 'delete' command is successfully run.
     *
     * @return Delete success message to be displayed.
     */
    public String deleteSuccessMessage(Task removedTask, TaskList taskList) {
        return "Noted. I've removed this task: \n"
             + "  " + removedTask.print() + "\n"
             + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Returns a message indicating the correct format for the "find" command when the user did not provide a keyword.
     *
     * @return Find format message to be displayed.
     */
    public String findFormatMessage() {
        return "Broooo you need to provide a keyword to find tasks! \n"
             + "Try something like: 'find book'";
    }

    /**
     * Returns the result of a "find" command by showing all tasks that match the given keyword in their description.
     *
     * If no tasks match, a message indicating no matches is printed.
     *
     * @param matches The TaskList containing all tasks that matched the keyword.
     * @param keyword The keyword used to search the tasks.
     * @return Find success message to be displayed.
     */
    public String findSuccessMessage(TaskList matches, String keyword) {

        String msg = "";

        if (matches.size() == 0) {
            msg += "Oh no Freaky can't find tasks that matches your keyword: " + keyword + "\n";

        } else {
            msg += "Here are the matching tasks in your list for keyword: '" + keyword + "' \n";

            for (int i = 0; i < matches.size(); i++) {
                Task task = matches.get(i);
                msg += (i + 1) + "." + task.print() + "\n";
            }
        }

        return msg;
    }

    /**
     * Returns all tasks in the task list, numbered in order.
     *
     * @param taskList The list of tasks to display
     * @return Task list to be displayed.
     */
    public String taskList(TaskList taskList) {

        String msg = "Here are the tasks in your list: \n";

        for (int n = 0; n < taskList.size(); n++) {
            Task task = taskList.get(n);
            msg += String.valueOf(n + 1) + "." + task.print() + "\n";
        }

        return msg;

    }

    /**
     * Returns the upcoming deadlines from the provided list, up to a specified count.
     * If there are fewer deadlines than requested, a message is shown.
     *
     * @param check The number of upcoming deadlines to check
     * @param deadlineList The list of deadlines to display
     * @return Checked deadline list to be displayed.
     */
    public String checkDeadlineList(int check, TaskList deadlineList) {

        String msg = "Checking the coming " + String.valueOf(check) + " deadlines in your list... \n";

        if (check > deadlineList.size()) {
            if (deadlineList.size() == 0) {
                msg += "Good news! There is no deadlines left in your list. Congrats!" + "\n";
            } else {
                msg += "Good news! There is only " + String.valueOf(deadlineList.size()
                       + " deadlines left in your list. Congrats! \n");
            }
        }

        if (deadlineList.size() > 0) {
            msg += "Here are the coming " + String.valueOf(deadlineList.size())
                   + " deadlines in your list: \n";
        }

        for (int n = 0; n < deadlineList.size(); n++) {
            Task task = deadlineList.get(n);
            msg += String.valueOf(n + 1) + "." + task.print() + "\n";
        }

        return msg;

    }

    /**
     * Returns the upcoming events from the provided list, up to a specified count.
     * If there are fewer events than requested, a message is shown.
     *
     * @param check The number of upcoming events to check
     * @param eventList The list of events to display
     * @return Checked event list to be displayed.
     */
    public String checkEventList(int check, TaskList eventList) {

        String msg = "Checking the coming " + String.valueOf(check) + " events in your list... \n";

        if (check > eventList.size()) {
            if (eventList.size() == 0) {
                msg += "Good news! There is no events left in your list. Congrats! \n";
            } else {
                msg += "Good news! There is only " + String.valueOf(eventList.size())
                       + " events left in your list. Congrats! \n";
            }
        }

        if (eventList.size() > 0) {
            msg += "Here are the coming " + String.valueOf(eventList.size()) + " events in your list: \n";
        }

        for (int n = 0; n < eventList.size(); n++) {
            Task task = eventList.get(n);
            msg += String.valueOf(n + 1) + "." + task.print() + "\n";
        }

        return msg;
    }

}
