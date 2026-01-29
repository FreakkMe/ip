import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

// The chatbot Freaky
public class Freaky {

    private static final String FILE_PATH = "./data/freaky.txt";

    // Print method to print a string with format bar on top and below
    public static void print(String string) {
        System.out.println("----------------------------------------------------- \n"
                         + string + "\n"
                         + "----------------------------------------------------- \n");
    }

    public static void main(String[] args) {

        // Input of the user
        Scanner scanner = new Scanner(System.in);

        // Greet message
        String greet = "Hello! I'm Freaky. \n"
                     + "What can I do for you?";

        // Bye message
        String bye = "You are leaving? T_T Please don't forget Freaky. Freaky is always here to chat with you.";

        // Check format message
        String checkFormatMessage = "There is a format error broooo. Please try the following format for check command \n"
                                  + "'check':                   default checks both closest deadlines and events \n"
                                  + "'check n':                 checks both n closest deadlines and events \n"
                                  + "'check deadline/event':    checks 3 closest deadlines/events \n"
                                  + "'check deadline/event n':  checks n closest deadlines/events";

        // Chatbot starts here
        print(greet);

        // Initialize variables input to store user's previous input, storage to stores data locally  and list to store all user's input
        String input;
        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> list = new ArrayList<>(storage.load());

        // Detecting user's input
        while(true) {

            // Stores user's input to input
            input = scanner.nextLine();

            // Checks user's input of different cases: "" (empty)
            if (input.matches(" *")) {
                print("Freaky didn't catch what you say, can you please enter it again?");

            // Checks user's input of different cases: "bye"
            } else if (input.trim().equals("bye")) {
                break;

            // Checks user's input of different cases: "list"
            } else if (input.trim().equals("list")) {
                System.out.println("----------------------------------------------------- \n"
                                 + "Here are the tasks in your list:");
                for (int n = 0; n < list.size(); n++) {
                    Task task = list.get(n);
                    System.out.println(String.valueOf(n + 1) + "." + task.print());
                }
                System.out.println("----------------------------------------------------- \n");

            // Checks user's input of different cases: "mark" and "unmark"
            } else if (input.startsWith("mark ") || input.startsWith(("unmark "))) {

                // Checks if the input after "mark" is empty, returns a message if so
                if (input.startsWith("mark ") && input.replaceFirst("mark", "").matches(" *")) {
                    print("No way broooo please enter an integer after 'mark' command to mark the corresponding task as done. \n"
                        + "Try something like this: 'mark 2'.");
                } else if (input.startsWith("unmark ") && input.replaceFirst("unmark", "").matches(" *")) {
                    print("No way broooo please enter an integer after 'unmark' command to mark the corresponding task as undone. \n"
                            + "Try something like this: 'unmark 2'.");
                }

                int taskNumber;

                // Splits the input by " " and cast the input string after it to an integer
                try {
                    taskNumber = Integer.parseInt(input.split(" ")[1]);
                } catch (NumberFormatException e) {
                    // Checks if the string after "mark " is an integer, returns a message if not
                    print("Broooo the content following by mark should be a valid task number. T_T");
                    continue;
                }

                // Checks if the task number is valid, returns a message if not
                if (taskNumber > list.size()) {

                    print("There is only " + String.valueOf(list.size()) + " tasks in your list, please enter a valid task number.");

                } else if (taskNumber <= 0) {
                    print("Broooo how is it possible? A non positive number? (◣_◢)");

                } else {

                    // Mark case
                    if (input.startsWith("mark ")) {

                        Task task = list.get(taskNumber - 1);

                        // Checks if the task is already marked as done, returns a message if so
                        if (task.getStatusIcon().equals("X")) {

                            print("The task is already marked as done.");

                        } else {
                            // Marks the task as done
                            task.markAsDone();
                            storage.save(list);
                            print("Nice! I've marked this task as done: \n"
                                + "  " + task.print());

                        }

                    // Unmark case
                    } else if (input.startsWith("unmark ")) {

                        Task task = list.get(taskNumber - 1);

                        // Checks if the task is already marked as undone, returns a message if so
                        if (task.getStatusIcon().equals(" ")) {

                            print("The task is still undone brooooo.");

                        } else {
                            // Unmarks the task from done
                            task.markAsUndone();
                            storage.save(list);
                            print("OK, I've marked this task as not done yet: \n"
                                + "  " + task.print());

                        }
                    }
                }

            // Checks user's input of different cases: "todo", "deadline" and "event"
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {

                // Checks if the input after "todo", "deadline" or "event" is valid, returns a message if not
                if (input.trim().equals("todo")) {
                    print("No way broooo please enter a task description after 'todo' command to add a todo task to the list. \n"
                        + "Try something like this: 'todo praise Freaky'.");
                    continue;

                } else if (input.startsWith("deadline") && input.contains("/by")
                        && input.replaceFirst("deadline ", "").replaceFirst("/by", "").matches(" *")
                        || input.startsWith("deadline") && !input.contains(" /by ")) {
                    print("No way broooo please enter a task description and a due date separated by '/by' after 'deadline' command to add a deadline task to the list. \n"
                        + "Try something like this: 'deadline buy Freaky Premium /by 2026-02-01 0000'.");
                    continue;

                } else if (input.startsWith("event") && input.contains(" /from ") && input.contains(" /to ")
                        && input.replaceFirst("event ", "").replaceFirst(" /from ", "").replaceFirst(" /to ", "").matches(" *")
                        || input.startsWith("event") && (!input.contains((" /from ")) && !input.contains(" /to "))) {
                    print("No way broooo please enter a task description, a starting time and an ending time separated by '/from' and '/to' after 'event' command to add an event task to the list. \n"
                        + "Try something like this: 'event chat with Freaky /from 2026-02-01 0000 /to 3026-02-01 0000'.");
                    continue;
                }

                // To do case
                if (input.startsWith("todo ")) {

                    String task = input.split("todo ", 2)[1];
                    list.add(new ToDo(task));
                    storage.save(list);

                // Deadline case
                } else if (input.startsWith("deadline ") && input.contains(" /by ")) {

                    // Checks if the input date for deadline is valid, returns a message if not
                    try {
                        String[] deadline = input.split("deadline ", 2)[1].split(" /by ", 2);
                        String task = deadline[0];
                        String time = deadline[1].trim();
                        list.add(new Deadline(task, time));
                        storage.save(list);

                    } catch (DateTimeParseException e) {
                        print("Brooooo Freaky isn't smart enough to understand the date, please enter the date in format yyyy-MM-dd HHmm. \n"
                            + "Try something like this: 'deadline buy Freaky Premium /by 2026-02-01 0000'.");
                        continue;
                    }

                // Event case
                } else if (input.startsWith("event ") && input.contains(" /from ") && input.contains(" /to ")){

                    // Checks if the input date for deadline is valid, returns a message if not
                    try {
                        String[] event = input.split("event ", 2)[1].split(" /from ", 2);
                        String task = event[0];
                        String startTime = event[1].split(" /to ", 2)[0].trim();
                        String endTime = event[1].split(" /to ", 2)[1].trim();
                        list.add(new Event(task, startTime, endTime));
                        storage.save(list);
                    } catch (DateTimeParseException e) {
                        print("Brooooo Freaky isn't smart enough to understand the date, please enter the date in format yyyy-MM-dd HHmm. \n"
                            + "Try something like this: 'event chat with Freaky /from 2026-02-01 0000 /to 3026-02-01 0000'.");
                        continue;
                    }

                }

                // Prints out case info
                Task task = list.get(list.size() - 1);
                print("Got it. I've added this task: \n"
                    + "  " + task.print() + "\n"
                    + "Now you have " + String.valueOf(list.size()) + " tasks in the list.");

            // Checks user's input of different cases: "delete"
            } else if (input.startsWith("delete")) {

                // Checks if the input is valid, returns a message if not
                if (input.startsWith("delete") && input.replaceFirst("delete", "").matches(" *")) {
                    print("No way broooo please enter an integer after 'delete' command to delete the corresponding task. \n"
                            + "Try something like this: 'delete 2'.");
                }

                int taskNumber;

                // Splits the input by " " and cast the input string after it to an integer
                try {
                    taskNumber = Integer.parseInt(input.split(" ")[1]);
                } catch (NumberFormatException e) {
                    // Checks if the string after "delete " is an integer, returns a message if not
                    print("Broooo the content following by delete should be a valid task number. T_T");
                    continue;
                }

                // Checks if the task number is valid, returns a message if not
                if (taskNumber > list.size()) {
                    print("There is only " + String.valueOf(list.size()) + " tasks in your list, please enter a valid task number.");
                    continue;

                } else if (taskNumber <= 0) {
                    print("Broooo how is it possible? A non positive number? (◣_◢)");
                    continue;
                }

                Task removedTask = list.remove(taskNumber - 1);
                storage.save(list);

                print("Noted. I've removed this task: \n"
                    + "  " + removedTask.print() + "\n"
                    + "Now you have " + list.size() + " tasks in the list.");

            // Checks user's input of different cases: "check"
            } else if (input.startsWith("check")) {

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
                        // Checks if the task number is valid, returns a message if not
                        try {
                            checkType = CheckType.BOTH;
                            check = Integer.parseInt(tokens[1].trim());

                        } catch (NumberFormatException e) {
                            print(checkFormatMessage);
                            continue;
                        }
                    }

                // In "check deadline/event n" format
                } else if (tokens.length == 3) {

                    if (tokens[1].trim().equals("deadline")) {
                        checkType = CheckType.DEADLINE;

                    } else if (tokens[1].trim().equals("event")) {
                        checkType = CheckType.EVENT;

                    } else {
                        print(checkFormatMessage);
                        continue;
                    }

                    // Checks if the input after "check deadline/event ", returns a message if it is invalid
                    try {
                        check = Integer.parseInt(tokens[2].trim());
                    } catch (NumberFormatException e) {
                        print(checkFormatMessage);
                        continue;
                    }

                // Incorrect format
                } else {
                    print(checkFormatMessage);
                    continue;
                }

                // Checks if the number to check is valid (non-positive), no errors even if it exceeds the max number of deadlines/events left
                if (check <= 0) {
                    print("Brooooo you serious? You can't check non-positive numbers of tasks");

                } else {

                    // Extracts deadlines and events that weren't marked as done before
                    ArrayList<Task> deadlineList = Helper.getClosestDeadlines(list, check);
                    ArrayList<Task> eventList = Helper.getClosestEvents(list, check);

                    switch (checkType) {

                        // "check" or "check n" case which checks both type of tasks
                        case BOTH:

                            System.out.println("----------------------------------------------------- \n");
                            if (check > deadlineList.size()) {
                                System.out.println("Good news! There is only " + String.valueOf(deadlineList.size()) + " deadlines left in your list. Congrats!");
                            }

                            System.out.println("Here are the coming " + String.valueOf(deadlineList.size())  + " deadlines in your list:");
                            for (int n = 0; n < deadlineList.size(); n++) {
                                Task task = deadlineList.get(n);
                                System.out.println(String.valueOf(n + 1) + "." + task.print());
                            }

                            if (check > eventList.size()) {
                                System.out.println("Good news! There is only " + String.valueOf(eventList.size()) + " events left in your list. Congrats!");
                            }

                            System.out.println("Here are the coming " + String.valueOf(eventList.size()) + " events in your list:");
                            for (int n = 0; n < eventList.size(); n++) {
                                Task task = eventList.get(n);
                                System.out.println(String.valueOf(n + 1) + "." + task.print());
                            }
                            System.out.println("----------------------------------------------------- \n");

                            break;

                        // "check deadline" or "check deadline n" case which checks deadline tasks
                        case DEADLINE:

                            System.out.println("----------------------------------------------------- \n");
                            if (check > deadlineList.size()) {
                                System.out.println("Good news! There is only " + String.valueOf(deadlineList.size()) + " deadlines left in your list. Congrats!");
                            }

                            System.out.println("Here are the coming " + String.valueOf(deadlineList.size()) + " deadlines in your list:");
                            for (int n = 0; n < deadlineList.size(); n++) {
                                Task task = deadlineList.get(n);
                                System.out.println(String.valueOf(n + 1) + "." + task.print());
                            }
                            System.out.println("----------------------------------------------------- \n");

                            break;

                        // "check event" or "check event n" case which check event tasks
                        case EVENT:

                            System.out.println("----------------------------------------------------- \n");
                            if (check > eventList.size()) {
                                System.out.println("Good news! There is only " + String.valueOf(eventList.size()) + " events left in your list. Congrats!");
                            }

                            System.out.println("Here are the coming " + String.valueOf(eventList.size()) + " events in your list:");
                            for (int n = 0; n < eventList.size(); n++) {
                                Task task = eventList.get(n);
                                System.out.println(String.valueOf(n + 1) + "." + task.print());
                            }
                            System.out.println("----------------------------------------------------- \n");

                            break;
                    }
                }

            // It's an unknown command
            } else {

                print("Unknown command T_T");

            }
        }

        print(bye);

    }
}
