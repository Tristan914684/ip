import java.io.IOException;

public class Clsl {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList list;
        try {
            list = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError("Unable to load saved tasks. Starting with an empty task list.");
            list = new TaskList();
        } catch (ClslException e) {
            ui.showLoadingError(e.getMessage());
            list = new TaskList();
        }

        ui.showWelcome();

        while (true) {
            try {
                String userInput = ui.readCommand();
                ParsedCommand parsedCommand = Parser.parse(userInput);

                if (parsedCommand.getType() == ParsedCommand.Type.BYE) {
                    break;
                }

                switch (parsedCommand.getType()) {
                case LIST:
                    ui.showTaskList(list.asList());
                    break;
                case MARK:
                    int taskNumber = parsedCommand.getTaskIndex();
                    list.mark(taskNumber);
                    storage.save(list.asList());
                    ui.showTaskMarked(list.get(taskNumber));
                    break;
                case UNMARK:
                    taskNumber = parsedCommand.getTaskIndex();
                    list.unmark(taskNumber);
                    storage.save(list.asList());
                    ui.showTaskUnmarked(list.get(taskNumber));
                    break;
                case TODO:
                    String description = parsedCommand.getDescription();
                    ToDo t = new ToDo(description);
                    list.add(t);
                    storage.save(list.asList());
                    ui.showTaskAdded(t, list.size());
                    break;
                case DEADLINE:
                    Deadline d = new Deadline(parsedCommand.getDescription(),
                            parsedCommand.getFirstDate().toString());
                    list.add(d);
                    storage.save(list.asList());
                    ui.showTaskAdded(d, list.size());
                    break;
                case EVENT:
                    Event e = new Event(parsedCommand.getDescription(),
                            parsedCommand.getFirstDate().toString(),
                            parsedCommand.getSecondDate().toString());
                    list.add(e);
                    storage.save(list.asList());
                    ui.showTaskAdded(e, list.size());
                    break;
                case DELETE:
                    taskNumber = parsedCommand.getTaskIndex();
                    Task removed = list.delete(taskNumber);
                    storage.save(list.asList());
                    ui.showTaskDeleted(removed, list.size());
                    break;
                case ON:
                    ui.showTasksOn(parsedCommand.getFirstDate(), list.asList());
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
}
