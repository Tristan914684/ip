package clsl.task;

import java.time.LocalDate;
/**
 * Represents a task that must be completed by a particular date.
 */
public class Deadline extends Task {
    protected LocalDate by;
    protected String formattedBy;
    /**
     * Creates an incomplete deadline task.
     *
     * @param name description of the task
     * @param by due date in {@code yyyy-mm-dd} format
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
        this.formattedBy = this.by.format(OUTPUT_FORMAT);
    }

    /**
     * Returns this deadline in its display format.
     *
     * @return the task prefixed with its deadline marker and due date
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }

    /**
     * Returns this deadline in the storage format.
     *
     * @return the task prefixed with its storage type marker and due date
     */
    @Override
    public String getSaveString() {
        return "D | " + super.getSaveString() + " | " + by;
    }

    /**
     * Reports whether this deadline is due on the given date.
     *
     * @param date date to compare with the deadline
     * @return {@code true} if the dates are equal
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return by.equals(date);
    }
}
