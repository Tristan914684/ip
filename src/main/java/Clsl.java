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
            try {
                if (userInput.equals("list")) {
                    System.out.println("\nHere are your task in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1)
                                + "."
                                + list.get(i).toString());
                    }
                    System.out.println("");
                } else if (userInput.startsWith("mark")) {
                    String[] parts = userInput.split(" ");
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    list.get(taskNumber).markAsDone();
                    System.out.println("\nNice! I've marked this task as Done:"
                            + "\n"
                            + list.get(taskNumber).toString()
                            + "\n");
                } else if (userInput.startsWith("unmark")) {
                    String[] parts = userInput.split(" ");
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    list.get(taskNumber).unmarkAsDone();
                    System.out.println("\nOK, I've marked this task as not done yet:"
                            + "\n"
                            + list.get(taskNumber).toString()
                            + "\n");
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(4).trim();
                    if (description.isEmpty()) {
                        throw new ClslException("todo what exactly?");
                    }
                    ToDo t = new ToDo(description);
                    list.add(t);
                    System.out.println("Got it. I've added this task:"
                            + "\n  "
                            + t.toString()
                            + "\n"
                            + "Now you have "
                            + list.size()
                            + " task in the list.\n");
                } else if (userInput.startsWith("deadline")) {
                    String description = userInput.substring(8).trim();
                    if (description.isEmpty()) {
                        throw new ClslException("deadline of what?");
                    }
                    if (!description.contains("/by")) {
                        throw new ClslException("by when?");
                    }
                    String[] parts = description.split("/by", 2);
                    String name = parts[0].trim();
                    if (name.isEmpty()) {
                        throw new ClslException("deadline of what?");
                    }
                    String by = parts[1].trim();
                    if (by.isEmpty()) {
                        throw new ClslException("by when?");
                    }
                    Deadline d = new Deadline(name, by);
                    list.add(d);
                    System.out.println("Got it. I've added this task:"
                            + "\n  "
                            + d.toString()
                            + "\n"
                            + "Now you have "
                            + list.size()
                            + " task in the list.\n");
                } else if (userInput.startsWith("event")) {
                    String description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new ClslException("event of what?");
                    }
                    if (!description.contains("/from")) {
                        throw new ClslException("from when?");
                    }
                    if (!description.contains("/to")) {
                        throw new ClslException("to when?");
                    }
                    String[] parts1 = description.split("/from", 2);
                    String name = parts1[0].trim();
                    if (name.isEmpty()) {
                        throw new ClslException("event of what?");
                    }
                    if (!parts1[1].contains("/to")) {
                        throw new ClslException("to when?");
                    }
                    String[] parts2 = parts1[1].split("/to", 2);
                    String from = parts2[0].trim();
                    String to = parts2[1].trim();
                    if (from.isEmpty()) {
                        throw new ClslException("from when?");
                    }
                    if (to.isEmpty()) {
                        throw new ClslException("to when?");
                    }
                    Event e = new Event(name, from, to);
                    list.add(e);
                    System.out.println("Got it. I've added this task:"
                            + "\n  "
                            + e.toString()
                            + "\n"
                            + "Now you have "
                            + list.size()
                            + " task in the list.\n");
                } else {
                    throw new ClslException("I don't understand");
                }
            }
            catch (ClslException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            }
            userInput = scanner.nextLine();
        }
        System.out.println(end);
    }
}