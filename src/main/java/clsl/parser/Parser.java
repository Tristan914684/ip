package clsl.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import clsl.ClslException;

/**
 * Interprets the parts of commands entered by the user.
 */
public class Parser {
    /** Prevents this utility class from being instantiated. */
    private Parser() {
    }

    /**
     * Parses a full command into the information needed to execute it.
     *
     * @param userInput complete command entered by the user
     * @return a structured representation of the command
     * @throws ClslException if the command is incomplete or invalid
     */
    public static ParsedCommand parse(String userInput) throws ClslException {
        String[] commandParts = userInput.trim().split("\\s+", 2);
        String commandWord = commandParts[0];
        String arguments = commandParts.length == 2 ? commandParts[1].trim() : "";

        switch (commandWord) {
        case "list":
            requireNoArguments(arguments);
            return ParsedCommand.of(ParsedCommand.Type.LIST);
        case "bye":
            requireNoArguments(arguments);
            return ParsedCommand.of(ParsedCommand.Type.BYE);
        case "mark":
            return ParsedCommand.withTaskIndex(ParsedCommand.Type.MARK, parseTaskIndex(arguments));
        case "unmark":
            return ParsedCommand.withTaskIndex(ParsedCommand.Type.UNMARK, parseTaskIndex(arguments));
        case "delete":
            return ParsedCommand.withTaskIndex(ParsedCommand.Type.DELETE, parseTaskIndex(arguments));
        case "todo":
            return ParsedCommand.withDescription(ParsedCommand.Type.TODO,
                    requireDescription(arguments, "todo what exactly?"));
        case "deadline":
            return parseDeadline(arguments);
        case "event":
            return parseEvent(arguments);
        case "on":
            return ParsedCommand.withDescriptionAndDate(ParsedCommand.Type.ON, null,
                    parseDate(arguments, "on what date?"));
        default:
            throw new ClslException("I don't understand");
        }
    }

    /**
     * Parses the description and due date supplied to a deadline command.
     *
     * @param arguments text after the {@code deadline} command word
     * @return the parsed deadline command
     * @throws ClslException if either the description or due date is invalid
     */
    private static ParsedCommand parseDeadline(String arguments) throws ClslException {
        String[] deadlineParts = arguments.split("\\s*/by\\s*", 2);
        String description = requireDescription(deadlineParts[0], "deadline of what?");
        if (deadlineParts.length < 2) {
            throw new ClslException("by when?");
        }

        LocalDate byDate = parseDate(deadlineParts[1], "by when?");
        return ParsedCommand.withDescriptionAndDate(ParsedCommand.Type.DEADLINE, description, byDate);
    }

    /**
     * Parses the description, start date, and end date supplied to an event command.
     *
     * @param arguments text after the {@code event} command word
     * @return the parsed event command
     * @throws ClslException if any event detail is missing or invalid
     */
    private static ParsedCommand parseEvent(String arguments) throws ClslException {
        String[] fromParts = arguments.split("\\s*/from\\s*", 2);
        String description = requireDescription(fromParts[0], "event of what?");
        if (fromParts.length < 2) {
            throw new ClslException("from when?");
        }

        String[] toParts = fromParts[1].split("\\s*/to\\s*", 2);
        if (toParts.length < 2) {
            throw new ClslException("to when?");
        }

        LocalDate startDate = parseDate(toParts[0], "from when?");
        LocalDate endDate = parseDate(toParts[1], "to when?");
        return ParsedCommand.withEventDetails(description, startDate, endDate);
    }

    /**
     * Ensures that a command that accepts no arguments was entered without any.
     *
     * @param arguments text after the command word
     * @throws ClslException if arguments were provided
     */
    private static void requireNoArguments(String arguments) throws ClslException {
        if (!arguments.isEmpty()) {
            throw new ClslException("I don't understand");
        }
    }

    /**
     * Validates and trims a required task description.
     *
     * @param description description text to validate
     * @param errorMessage message to display when the description is blank
     * @return the trimmed description
     * @throws ClslException if the description is blank
     */
    private static String requireDescription(String description, String errorMessage) throws ClslException {
        if (description.isBlank()) {
            throw new ClslException(errorMessage);
        }
        return description.trim();
    }

    /**
     * Converts the user's one-based task number to the application's zero-based index.
     *
     * @param taskNumberText task number entered by the user
     * @return the corresponding zero-based task index
     * @throws ClslException if the task number is absent, non-numeric, or less than one
     */
    private static int parseTaskIndex(String taskNumberText) throws ClslException {
        if (taskNumberText.isBlank()) {
            throw new ClslException("task number missing");
        }

        try {
            int taskNumber = Integer.parseInt(taskNumberText);
            if (taskNumber < 1) {
                throw new ClslException("task number must be at least 1");
            }
            return taskNumber - 1;
        } catch (NumberFormatException e) {
            throw new ClslException("task number must be a whole number");
        }
    }

    /**
     * Parses an ISO-8601 date after ensuring that date text was provided.
     *
     * @param dateText date text to parse
     * @param missingDateMessage message to display when date text is blank
     * @return the parsed date
     * @throws ClslException if the date is blank or not in {@code yyyy-mm-dd} format
     */
    private static LocalDate parseDate(String dateText, String missingDateMessage) throws ClslException {
        if (dateText.isBlank()) {
            throw new ClslException(missingDateMessage);
        }

        try {
            return LocalDate.parse(dateText);
        } catch (DateTimeParseException e) {
            throw new ClslException("Please use yyyy-mm-dd format");
        }
    }
}
