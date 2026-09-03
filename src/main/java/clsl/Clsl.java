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
            ui.showLoadingError("Unable to load saved tasks. Starting with an empty task list.");
            loadedTasks = new TaskList();
        } catch (ClslException e) {
            ui.showLoadingError(e.getMessage());
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    /** Runs the application's command-processing loop. */
    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                ParsedCommand parsedCommand = Parser.parse(ui.readCommand());

                if (parsedCommand.getType() == ParsedCommand.Type.BYE) {
                    break;
                }

                switch (parsedCommand.getType()) {
                    case LIST:
                        ui.showTaskList(tasks.asList());
                        break;
                    case MARK: {
                        int taskIndex = parsedCommand.getTaskIndex();
                        tasks.mark(taskIndex);
                        storage.save(tasks.asList());
                        ui.showTaskMarked(tasks.get(taskIndex));
                        break;
                    }
                    case UNMARK: {
                        int taskIndex = parsedCommand.getTaskIndex();
                        tasks.unmark(taskIndex);
                        storage.save(tasks.asList());
                        ui.showTaskUnmarked(tasks.get(taskIndex));
                        break;
                    }
                    case TODO: {
                        ToDo todo = new ToDo(parsedCommand.getDescription());
                        tasks.add(todo);
                        storage.save(tasks.asList());
                        ui.showTaskAdded(todo, tasks.size());
                        break;
                    }
                    case DEADLINE: {
                        Deadline deadline = new Deadline(parsedCommand.getDescription(),
                                parsedCommand.getFirstDate().toString());
                        tasks.add(deadline);
                        storage.save(tasks.asList());
                        ui.showTaskAdded(deadline, tasks.size());
                        break;
                    }
                    case EVENT: {
                        Event event = new Event(parsedCommand.getDescription(),
                                parsedCommand.getFirstDate().toString(),
                                parsedCommand.getSecondDate().toString());
                        tasks.add(event);
                        storage.save(tasks.asList());
                        ui.showTaskAdded(event, tasks.size());
                        break;
                    }
                    case DELETE: {
                        int taskIndex = parsedCommand.getTaskIndex();
                        Task removed = tasks.delete(taskIndex);
                        storage.save(tasks.asList());
                        ui.showTaskDeleted(removed, tasks.size());
                        break;
                    }
                    case ON:
                        ui.showTasksOn(parsedCommand.getFirstDate(),
                                tasks.getTasksOn(parsedCommand.getFirstDate()));
                        break;
                    case FIND:
                        ui.showMatchingTasks(tasks.findTasks(parsedCommand.getDescription()));
                        break;
                    default:
                        throw new ClslException("I don't understand");
                }
            } catch (ClslException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Unable to save task data. Please try again.");
            }
        }
        ui.showGoodbye();
    }

    /** Starts the application with its default task data file. */
    public static void main(String[] args) {
        new Clsl("data/csls.txt").run();
    }
}
