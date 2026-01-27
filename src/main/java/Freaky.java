import java.util.Scanner;

public class Freaky {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String greet = "----------------------------------------------------- \n"
                     + "Hello! I'm Freaky. \n"
                     + "What can I do for you? \n"
                     + "----------------------------------------------------- \n";

        String bye = "----------------------------------------------------- \n"
                   + "Bye. Hope to see you again soon! \n"
                   + "----------------------------------------------------- \n";

        System.out.println(greet);

        String input;
        String list = "";
        int count = 0;

        while(true) {

            input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("----------------------------------------------------- \n"
                                 + list
                                 + "----------------------------------------------------- \n");
            } else {
                count++;
                list = list + String.valueOf(count) + ". " + input + "\n";
                System.out.println("----------------------------------------------------- \n"
                                 + "added: " + input + "\n"
                                 + "----------------------------------------------------- \n");
            }
        }

        System.out.println(bye);

    }
}
