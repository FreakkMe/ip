package Freaky;

// The ui for Freaky
public class Ui {

    // Constructor of the Ui
    public Ui() { }

    // Print method to print a string with format bar on top and below
    public static void print(String string) {
        System.out.println("----------------------------------------------------------------- \n"
                + string + "\n"
                + "----------------------------------------------------------------- \n");
    }

    public void printGreetMessage() {
        print("Hello! I'm Freaky. \n"
            + "What can I do for you?");
    }

    public void printByeMessage() {
        print("You are leaving? T_T Please don't forget Freaky. Freaky is always here to chat with you.");
    }

    public void printUnKnownCommandMessage() {
        print("Unknown command T_T");
    }

    public void printCorruptedFilesMessage() {
        print("Error loading file. Tasks stored on hard disk may be corrupted. T_T");
    }

    public void printRepeatMessage() {
        print("Freaky didn't catch what you say, can you please enter it again?");
    }

    public void printMarkFormatMessage() {
        print("No way broooo please enter an integer after 'mark' command to mark the corresponding task as done. \n"
                + "Try something like this: 'mark 2'.");
    }

    public void printUnmarkFormatMessage() {
        print("No way broooo please enter an integer after 'unmark' command to mark the corresponding task as undone. \n"
                + "Try something like this: 'unmark 2'.");
    }

    public void printMarkValidNumberMessage() {
        print("Broooo the content following by mark should be a valid task number. T_T");
    }

    public void printListSizeError(int size) {
        print("There is only " + String.valueOf(size) + " tasks in your list, please enter a valid task number.");
    }

    public void printNegativeValueError() {
        print("Broooo how is it possible? A non positive number? (◣_◢)");
    }

    public void printAlreadyMarkMessage() {
        print("The task is already marked as done.");
    }

    public void printAlreadyUnmarkMessage() {
        print("The task is still undone brooooo.");
    }

    public void printMarkSuccessMessage(Task task) {
        print("Nice! I've marked this task as done: \n"
                + "  " + task.print());
    }

    public void printUnmarkSuccessMessage(Task task) {
        print("OK, I've marked this task as not done yet: \n"
                + "  " + task.print());
    }

    public void printToDoFormatMessage() {
        print("No way broooo please enter a task description after 'todo' command to add a todo task to the list. \n"
                + "Try something like this: 'todo praise Freaky'.");
    }

    public void printDeadlineFormatMessage() {
        print("No way broooo please enter a task description and a due date separated by '/by' after 'deadline' command to add a deadline task to the list. \n"
                + "Try something like this: 'deadline buy Freaky Premium /by 2026-02-01 0000'.");
    }

    public void printEventFormatMessage() {
        print("No way broooo please enter a task description, a starting time and an ending time separated by '/from' and '/to' after 'event' command to add an event task to the list. \n"
                + "Try something like this: 'event chat with Freaky /from 2026-02-01 0000 /to 3026-02-01 0000'.");
    }

    public void printDeadlineDateTimeErrorMessage() {
        print("Brooooo Freaky isn't smart enough to understand the date, please enter the date in format yyyy-MM-dd HHmm. \n"
                + "Try something like this: 'deadline buy Freaky Premium /by 2026-02-01 0000'.");
    }

    public void printEventDateTimeErrorMessage() {
        print("Brooooo Freaky isn't smart enough to understand the date, please enter the date in format yyyy-MM-dd HHmm. \n"
                + "Try something like this: 'event chat with Freaky /from 2026-02-01 0000 /to 3026-02-01 0000'.");
    }

    public void printTaskAddedMessage(Task task, TaskList taskList) {
        print("Got it. I've added this task: \n"
                + "  " + task.print() + "\n"
                + "Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
    }

    public void printDeleteFormatMessage() {
        print("No way broooo please enter an integer after 'delete' command to delete the corresponding task. \n"
                + "Try something like this: 'delete 2'.");
    }

    public void printDeleteValidNumberMessage() {
        print("Broooo the content following by delete should be a valid task number. T_T");
    }

    public void printDeleteSuccessMessage(Task removedTask, TaskList taskList) {
        print("Noted. I've removed this task: \n"
                + "  " + removedTask.print() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printCheckFormatMessage() {
        print("There is a format error broooo. Please try the following format for check command. \n"
            + "'check':                   default checks both closest deadlines and events \n"
            + "'check n':                 checks both n closest deadlines and events \n"
            + "'check deadline/event':    checks 3 closest deadlines/events \n"
            + "'check deadline/event n':  checks n closest deadlines/events");
    }

    public void printFindFormatMessage() {
        print("Broooo you need to provide a keyword to find tasks! \n" +
              "Try something like: 'find book'");
    }

    public void printFindSuccessMessage(TaskList matches, String keyword) {
        System.out.println("----------------------------------------------------------------- \n");

        if (matches.size() == 0) {
            System.out.println("No tasks matched your keyword: " + keyword);

        } else {
            System.out.println("Here are the matching tasks in your list for keyword: '" + keyword + "'");

            for (int i = 0; i < matches.size(); i++) {
                Task task = matches.get(i);
                System.out.println((i + 1) + "." + task.print());
            }
        }

        System.out.println("----------------------------------------------------------------- \n");
    }

    public void printCheckDeadlineList(int check, TaskList deadlineList) {
        System.out.println("----------------------------------------------------------------- \n");
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

    public void printCheckEventList(int check, TaskList eventList) {
        System.out.println("----------------------------------------------------- \n");
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

        System.out.println("----------------------------------------------------- \n");
    }

    public void printTaskList(TaskList taskList) {
        System.out.println("----------------------------------------------------------------- \n"
                + "Here are the tasks in your list:");
        for (int n = 0; n < taskList.size(); n++) {
            Task task = taskList.get(n);
            System.out.println(String.valueOf(n + 1) + "." + task.print());
        }
        System.out.println("----------------------------------------------------------------- \n");
    }

}