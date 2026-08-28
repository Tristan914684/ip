package clsl.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected boolean isDone;
    protected String name;
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Creates an incomplete task with the given description.
     *
     * @param name description of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns the icon used to display this task's completion status.
     *
     * @return {@code [X]} when complete, otherwise {@code [ ]}
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /** Marks this task as complete. */
    public void markAsDone() {
        this.isDone = true;
    }
    /** Marks this task as incomplete. */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns this task's description.
     *
     * @return the task description
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a display representation of this task.
     *
     * @return the completion icon followed by the description
     */
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
