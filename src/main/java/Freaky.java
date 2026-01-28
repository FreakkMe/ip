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

            // Checks user's input of different cases: "bye", "list" and others
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("----------------------------------------------------- \n"
                                 + "Here are the tasks in your list:");
                for (int n = 0; n < count; n++) {
                    System.out.println(String.valueOf(n + 1) + ".[" + list[n].getStatusIcon() + "] " + list[n].description);
                }
                System.out.println("-----------------------------------------------------");
            } else if (input.startsWith("mark ")) {

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
                    Task task = list[taskNumber - 1];
                    // Checks if the task is already marked as done, returns a message if so
                    if (task.getStatusIcon().equals("X")) {
                        print("The task is already marked as done.");
                    } else {
                        task.markAsDone();
                        print("Nice! I've marked this task as done: \n"
                                + "  [" + task.getStatusIcon() + "] " + task.description);
                    }
                }

            } else if (input.startsWith("unmark ")) {

                int taskNumber;
                // Splits the input by " " and cast the input string after it to an integer
                taskNumber = Integer.parseInt(input.split(" ")[1]);
                try {
                    taskNumber = Integer.parseInt(input.split(" ")[1]);
                } catch (NumberFormatException e) {
                    // Checks if the string after "unmark " is an integer, returns a message if not
                    print("Please enter a valid task number.");
                    continue;
                }

                // Checks if the task number is valid, returns a message if not
                if (taskNumber > count) {
                    print("There is only " + String.valueOf(count) + " tasks in your list, please enter a valid task number.");
                } else {
                    Task task = list[taskNumber - 1];
                    // Checks if the task is already marked as undone, returns a message if so
                    if (task.getStatusIcon().equals(" ")) {
                        print("The task is still undone brooooo.");
                    } else {
                        task.markAsUndone();
                        print("OK, I've marked this task as not done yet: \n"
                                + " [" + task.getStatusIcon() + "] " + task.description);
                    }
                }

            } else {
                list[count] = new Task(input);
                count++;
                print("Task added: " + input);
            }
        }

        print(bye);

    }
}
