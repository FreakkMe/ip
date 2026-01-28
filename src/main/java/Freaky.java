import java.util.Scanner;

public class Freaky {

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
        String bye = "Bye. Hope to see you again soon!";

        // Chatbot starts here
        print(greet);

        // Initialize variables input to store user's previous input, list to store all user's input and count to store number of user's input
        String input;
        Task[] list = new Task[100];
        int count = 0;

        // Detecting user's input
        while(true) {

            // Stores user's input to input
            input = scanner.nextLine();

            // Checks user's input of different cases: "bye"
            if (input.equals("bye")) {
                break;

            // Checks user's input of different cases: "list"
            } else if (input.equals("list")) {
                System.out.println("----------------------------------------------------- \n"
                                 + "Here are the tasks in your list:");
                for (int n = 0; n < count; n++) {
                    Task task = list[n];
                    System.out.println(String.valueOf(n + 1) + "." + task.print());
                }
                System.out.println("----------------------------------------------------- \n");

            // Checks user's input of different cases: "mark" and "unmark"
            } else if (input.startsWith("mark ") || input.startsWith(("unmark "))) {

                int taskNumber;

                // Splits the input by " " and cast the input string after it to an integer
                try {
                    taskNumber = Integer.parseInt(input.split(" ")[1]);
                } catch (NumberFormatException e) {
                    // Checks if the string after "mark " is an integer, returns a message if not
                    print("Please enter a valid task number.");
                    continue;
                }

                // Checks if the task number is valid, returns a message if not
                if (taskNumber > count) {

                    print("There is only " + String.valueOf(count) + " tasks in your list, please enter a valid task number.");

                } else {

                    // Mark case
                    if (input.startsWith("mark ")) {

                        Task task = list[taskNumber - 1];

                        // Checks if the task is already marked as done, returns a message if so
                        if (task.getStatusIcon().equals("X")) {

                            print("The task is already marked as done.");

                        } else {
                            // Marks the task as done
                            task.markAsDone();
                            print("Nice! I've marked this task as done: \n"
                                + "  " + task.print());

                        }

                    // Unmark case
                    } else {

                        Task task = list[taskNumber - 1];

                        // Checks if the task is already marked as undone, returns a message if so
                        if (task.getStatusIcon().equals(" ")) {

                            print("The task is still undone brooooo.");

                        } else {
                            // Unmarks the task from done
                            task.markAsUndone();
                            print("OK, I've marked this task as not done yet: \n"
                                + "  " + task.print());

                        }
                    }
                }

            // Checks user's input of different cases: "todo", "deadline" and "event"
            } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {

                // To do case
                if (input.startsWith("todo ")) {
                    String task = input.split(" ", 2)[1];
                    list[count] = new ToDo(task);

                // Deadline case
                } else if (input.startsWith("deadline ")) {
                    String[] deadline = input.split(" ", 2)[1].split(" /by ", 2);
                    String task = deadline[0];
                    String time = deadline[1];
                    list[count] = new Deadline(task, time);

                // Event case
                } else {
                    String[] event = input.split(" ", 2)[1].split(" /from ", 2);
                    String task = event[0];
                    String startTime = event[1].split(" /to ", 2)[0];
                    String endTime = event[1].split(" /to ", 2)[1];
                    list[count] = new Event(task, startTime, endTime);
                }

                // Prints out case info
                Task task = list[count];
                count++;
                print("Got it. I've added this task: \n"
                    + "  " + task.print() + "\n"
                    + "Now you have " + String.valueOf(count) + " tasks in the list.");

            } else {

                // It's an unknown command
                print("Unknown command T_T");

            }
        }

        print(bye);

    }
}
