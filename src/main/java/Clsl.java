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
            else if (userInput.startsWith("todo")) {
                ToDo t = new ToDo(userInput.substring(5));
                list.add(t);
                System.out.println("Got it. I've added this task:"
                        + "\n  "
                        + t.toString()
                        + "\n"
                        + "Now you have "
                        + list.size()
                        + " task in the list.\n");
            }
            else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.substring(9).split("/by");
                Deadline d = new Deadline(parts[0].trim(), parts[1].trim());
                list.add(d);
                System.out.println("Got it. I've added this task:"
                        + "\n  "
                        + d.toString()
                        + "\n"
                        + "Now you have "
                        + list.size()
                        + " task in the list.\n");
            }
            else if (userInput.startsWith("event")) {
                String[] parts1 = userInput.substring(6).split("/from");
                String name = parts1[0].trim();
                String[] parts2 = parts1[1].split("/to");
                String from = parts2[0].trim();
                String to = parts2[1].trim();
                Event e = new Event(name, from, to);
                list.add(e);
                System.out.println("Got it. I've added this task:"
                        + "\n  "
                        + e.toString()
                        + "\n"
                        + "Now you have "
                        + list.size()
                        + " task in the list.\n");
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