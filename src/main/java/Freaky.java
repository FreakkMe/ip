import java.util.Scanner;

public class Freaky {

    // Print method to print a string with format bar on top and below
    public static void print(String string) {
        System.out.println("----------------------------------------------------- \n"
                         + string
                         + "----------------------------------------------------- \n");
    }

    public static void main(String[] args) {

        // Input of the useer
        Scanner scanner = new Scanner(System.in);

        // Greet message
        String greet = "Hello! I'm Freaky. \n"
                     + "What can I do for you? \n";

        // Bye message
        String bye = "Bye. Hope to see you again soon! \n";

        // Chatbot starts here
        print(greet);

        // Initialize variables input to store user's previous input, list to store all user's input and count to store number of user's input
        String input;
        String list = "";
        int count = 0;

        // Detecting user's input
        while(true) {

            // Stores user's input to input
            input = scanner.nextLine();

            // Checks user's input of different cases: "bye", "list" and others
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                print(list);
            } else {
                count++;
                list = list + String.valueOf(count) + ". " + input + "\n";
                print("added: " + input + "\n");
            }
        }

        print(bye);

    }
}
