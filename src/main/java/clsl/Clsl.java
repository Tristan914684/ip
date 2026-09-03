package clsl;

import java.io.IOException;

import clsl.parser.ParsedCommand;
import clsl.parser.Parser;
import clsl.storage.Storage;
import clsl.task.Deadline;
import clsl.task.Event;
import clsl.task.Task;
import clsl.task.TaskList;
import clsl.task.ToDo;
import clsl.ui.Ui;

/**
 * Coordinates user interaction, command processing, task management, and storage.
 */
public class Clsl {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private boolean isExitRequested;

    /**
     * Creates the application and loads its saved tasks.
     *
     * @param filePath Path of the task data file.
     */
    public Clsl(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showMessage(ui.formatLoadingError("Unable to load saved tasks. Starting with an empty task list."));
            loadedTasks = new TaskList();
        } catch (ClslException e) {
            ui.showMessage(ui.formatLoadingError(e.getMessage()));
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    /** Runs the application's command-processing loop. */
    public void run() {
        ui.showMessage(ui.formatWelcome());

        while (!isExitRequested) {
            ui.showMessage(getResponse(ui.readCommand()));
        }
    }

    /**
     * Processes one command and returns the corresponding user-facing response.
     *
     * @param userInput Command entered by the user.
     * @return Response produced after processing the command.
     */
    public String getResponse(String userInput) {
        try {
            ParsedCommand parsedCommand = Parser.parse(userInput);
            switch (parsedCommand.getType()) {
                case LIST:
                    return ui.formatTaskList(tasks.asList());
                case MARK: {
                    int taskIndex = parsedCommand.getTaskIndex();
                    tasks.mark(taskIndex);
                    storage.save(tasks.asList());
                    return ui.formatTaskMarked(tasks.get(taskIndex));
                }
                case UNMARK: {
                    int taskIndex = parsedCommand.getTaskIndex();
                    tasks.unmark(taskIndex);
                    storage.save(tasks.asList());
                    return ui.formatTaskUnmarked(tasks.get(taskIndex));
                }
                case TODO: {
                    ToDo todo = new ToDo(parsedCommand.getDescription());
                    tasks.add(todo);
                    storage.save(tasks.asList());
                    return ui.formatTaskAdded(todo, tasks.size());
                }
                case DEADLINE: {
                    Deadline deadline = new Deadline(parsedCommand.getDescription(),
                            parsedCommand.getFirstDate().toString());
                    tasks.add(deadline);
                    storage.save(tasks.asList());
                    return ui.formatTaskAdded(deadline, tasks.size());
                }
                case EVENT: {
                    Event event = new Event(parsedCommand.getDescription(),
                            parsedCommand.getFirstDate().toString(),
                            parsedCommand.getSecondDate().toString());
                    tasks.add(event);
                    storage.save(tasks.asList());
                    return ui.formatTaskAdded(event, tasks.size());
                }
                case DELETE: {
                    int taskIndex = parsedCommand.getTaskIndex();
                    Task removed = tasks.delete(taskIndex);
                    storage.save(tasks.asList());
                    return ui.formatTaskDeleted(removed, tasks.size());
                }
                case ON:
                    return ui.formatTasksOn(parsedCommand.getFirstDate(),
                            tasks.getTasksOn(parsedCommand.getFirstDate()));
                case FIND:
                    return ui.formatMatchingTasks(tasks.findTasks(parsedCommand.getDescription()));
                case BYE:
                    isExitRequested = true;
                    return ui.formatGoodbye();
                default:
                    throw new ClslException("I don't understand");
            }
        } catch (ClslException e) {
            return ui.formatError(e.getMessage());
        } catch (IOException e) {
            return ui.formatError("Unable to save task data. Please try again.");
        }
    }

    /** Starts the application with its default task data file. */
    public static void main(String[] args) {
        new Clsl("data/csls.txt").run();
    }
}
