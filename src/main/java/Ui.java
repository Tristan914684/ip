import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Handles all console input and output for the Clsl application.
 */
public class Ui {
    private static final String BANNER =
              "  ____ _     _ \n"
            + " / ___| |___| |\n"
            + "| |   | / __| |\n"
            + "| |___| \\__ \\ |\n"
            + " \\____|_|___/_|\n";
    private static final String GREETING = "Hello! I'm Clsl.\n"
            + "What can I do for you?\n";
    private final Scanner scanner;

    /** Creates a UI that reads commands from standard input. */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /** Displays the application's welcome message. */
    public void showWelcome() {
        System.out.println(BANNER);
        System.out.println(GREETING);
    }

    /** Reads one complete command entered by the user. */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Displays the application's goodbye message. */
    public void showGoodbye() {
        System.out.println("\nBye. Hope to see you again soon!");
    }

    /** Displays a message when previously saved tasks cannot be loaded. */
    public void showLoadingError(String message) {
        System.out.println("\n" + message + "\n");
    }

    /** Displays an error message for an invalid command or failed operation. */
    public void showError(String message) {
        System.out.println("\n" + message + "\n");
    }

    /** Displays all tasks currently in the list. */
    public void showTaskList(List<Task> tasks) {
        System.out.println("\nHere are your tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println();
    }

    /** Displays confirmation that a task has been marked as complete. */
    public void showTaskMarked(Task task) {
        System.out.println("\nNice! I've marked this task as Done:\n" + task + "\n");
    }

    /** Displays confirmation that a task has been marked as incomplete. */
    public void showTaskUnmarked(Task task) {
        System.out.println("\nOK, I've marked this task as not done yet:\n" + task + "\n");
    }

    /** Displays confirmation that a task was added. */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:\n  " + task
                + "\nNow you have " + taskCount + " tasks in the list.\n");
    }

    /** Displays confirmation that a task was removed. */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("\nNoted. I've removed this task:\n  " + task
                + "\nNow you have " + taskCount + " tasks in the list.\n");
    }

    /** Displays tasks that occur on the specified date. */
    public void showTasksOn(LocalDate date, List<Task> tasks) {
        System.out.println("\nHere are the task occuring on " + date + ":");
        int count = 0;
        for (Task task : tasks) {
            if (task.occursOn(date)) {
                count++;
                System.out.println(count + "." + task);
            }
        }
        if (count == 0) {
            System.out.println("none");
        }
        System.out.println(" ");
    }
}
