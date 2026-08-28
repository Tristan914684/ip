package clsl.task;

/**
 * Represents a task without an associated date.
 */
public class ToDo extends Task {
    /**
     * Creates an incomplete to-do task.
     *
     * @param name description of the task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns this to-do task in its display format.
     *
     * @return the task prefixed with its to-do marker
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns this to-do task in the storage format.
     *
     * @return the task prefixed with its storage type marker
     */
    @Override
    public String getSaveString() {
        return "T | " + super.getSaveString();
    }
}
