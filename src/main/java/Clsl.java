import java.util.Scanner;

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
        System.out.println(banner);
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while(!userInput.equals("bye")) {
            System.out.println("\n" + userInput);
            userInput = scanner.nextLine();
        }
        System.out.println(end);
    }
}
