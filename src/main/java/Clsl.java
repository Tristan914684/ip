import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Clsl {
    public static void main(String[] args) {
        String banner = "  ____ _     _ \n"
                + " / ___| |___| |\n"
                + "| |   | / __| |\n"
                + "| |___| \\__ \\ |\n"
                + " \\____|_|___/_|\n";
        String greet = "Hello! I'm Clsl.\n"
                + "What can I do for you?\n";
        String end = "Bye. Hope to see you again soon!";

        List<String> list = new ArrayList<>();

        System.out.println(banner);
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ": " + list.get(i));
                }
                System.out.println("");
            }
            else {
                list.add(userInput);
                System.out.println("\nadded: " + userInput);
            }
            userInput = scanner.nextLine();
        }
        System.out.println(end);
    }
}
