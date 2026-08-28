package clsl.task;

import java.time.LocalDate;
/**
 * Represents a task occurring from a start date through an end date.
 */
public class Event extends Task {
    protected LocalDate start;
    protected String formattedStart;
    protected LocalDate by;
    protected String formattedBy;

    /**
     * Creates an incomplete event task.
     *
     * @param name description of the task
     * @param start start date in {@code yyyy-mm-dd} format
     * @param by end date in {@code yyyy-mm-dd} format
     */
    public Event(String name, String start, String by) {
        super(name);
        this.start = LocalDate.parse(start);
        this.formattedStart = this.start.format(OUTPUT_FORMAT);
        this.by = LocalDate.parse(by);
        this.formattedBy = this.by.format(OUTPUT_FORMAT);
    }

    /**
     * Returns this event in its display format.
     *
     * @return the task prefixed with its event marker and date range
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedBy + ")";
    }

    /**
     * Returns this event in the storage format.
     *
     * @return the task prefixed with its storage type marker and date range
     */
    @Override
    public String getSaveString() {
        return "E | " + super.getSaveString() + " | " + start + " | " + by;
    }

    /**
     * Reports whether this event occurs on the given date, inclusively.
     *
     * @param date date to check
     * @return {@code true} if the date is within the event's range
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return !date.isBefore(start) && !date.isAfter(by);
    }
}
