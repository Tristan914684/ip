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

        List<Task> list = new ArrayList<>();

        System.out.println(banner);
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("\nHere are your task in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1)
                            + "."
                            + list.get(i).toString());
                }
                System.out.println("");
            }
            else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" ");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                list.get(taskNumber).markAsDone();
                System.out.println("\nNice! I've marked this task as Done:"
                        + "\n"
                        + list.get(taskNumber).toString()
                        + "\n");
            }
            else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" ");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                list.get(taskNumber).unmarkAsDone();
                System.out.println("\nOK, I've marked this task as not done yet:"
                        + "\n"
                        + list.get(taskNumber).toString()
                        + "\n");
            }
            else {
                list.add(new Task(userInput));
                System.out.println("\nadded: "
                        + userInput
                        + "\n");
            }
            userInput = scanner.nextLine();
        }
        System.out.println(end);
    }
}