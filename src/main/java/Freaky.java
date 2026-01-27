import java.util.Scanner;

public class Freaky {

    public static void print(String string) {
        System.out.println("----------------------------------------------------- \n"
                         + string
                         + "----------------------------------------------------- \n");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String greet = "Hello! I'm Freaky. \n"
                     + "What can I do for you? \n";

        String bye = "Bye. Hope to see you again soon! \n";

        print(greet);

        String input;
        String list = "";
        int count = 0;

        while(true) {

            input = scanner.nextLine();

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
