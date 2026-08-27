package clsl.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import clsl.ClslException;
import clsl.task.Deadline;
import clsl.task.Event;
import clsl.task.Task;
import clsl.task.ToDo;

/**
 * Handles saving task data to the hard disk.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates storage that saves tasks to the given file path.
     *
     * @param filePath path of the task data file
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Saves all tasks to the data file.
     *
     * @param tasks tasks currently in the task list.
     * @throws IOException if the file cannot be written.
     */
    public void save(List<Task> tasks) throws IOException {
        Files.createDirectories(filePath.getParent());

        StringBuilder contents = new StringBuilder();
        for (Task task : tasks) {
            contents.append(task.getSaveString()).append(System.lineSeparator());
        }

        Files.writeString(filePath, contents.toString());
    }

    /**
     * Loads tasks from the data file.
     *
     * @return Tasks recreated from the data file.
     * @throws IOException If the data file cannot be read.
     * @throws ClslException If the data file contains an unknown task type.
     */
    public List<Task> load() throws IOException, ClslException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        List<String> lines = Files.readAllLines(filePath);
        List<Task> tasks = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            Task task;

            if (parts[0].equals("T")) {
                task = new ToDo(parts[2]);
            } else if (parts[0].equals("D")) {
                task = new Deadline(parts[2], parts[3]);
            } else if (parts[0].equals("E")) {
                task = new Event(parts[2], parts[3], parts[4]);
            } else {
                throw new ClslException("Unknown task type in file input");
            }

            if (parts[1].equals("1")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;
    }
}
