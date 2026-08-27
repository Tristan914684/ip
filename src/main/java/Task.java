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

    /**
     * Returns whether this task occurs on the given date
     * The base implementation returns false since a plain task has no date.
     *
     * @param date The date to check against
     * @return true if the task occurs on the given date, false otherwise
     */
    public boolean occursOn(LocalDate date) {
        return false;
    }
}