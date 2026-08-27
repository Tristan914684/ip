import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected boolean isDone;
    protected String name;
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void markAsDone() {
        this.isDone = true;
    }
    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + name;
    }

    /**
     * Returns this task's completion status and description in the save-file format.
     *
     * @return Task completion status and description.
     */
    public String getSaveString() {
        String status = isDone ? "1" : "0";
        return status + " | " + name;
    }
}