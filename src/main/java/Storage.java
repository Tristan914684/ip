import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Handles saving task data to the hard disk.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates storage that saves task to the given relative path.
     */
    public Storage() {
        this.filePath = Path.of("data", "csls.txt");
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
}
