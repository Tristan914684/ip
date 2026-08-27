package clsl.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages the tasks in the application.
 */
public class TaskList {
    private final List<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list containing tasks loaded from storage.
     *
     * @param tasks tasks to include in this task list
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /** Adds a task to the end of the list. */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified zero-based index.
     *
     * @param index zero-based index of the task to remove
     * @return the removed task
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /** Marks the task at the specified zero-based index as complete. */
    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    /** Marks the task at the specified zero-based index as incomplete. */
    public void unmark(int index) {
        tasks.get(index).unmarkAsDone();
    }

    /** Returns the task at the specified zero-based index. */
    public Task get(int index) {
        return tasks.get(index);
    }

    /** Returns the number of tasks in the list. */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the tasks that occur on the given date.
     *
     * @param date date to check
     * @return tasks that occur on the given date
     */
    public List<Task> getTasksOn(LocalDate date) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.occursOn(date)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns an immutable view of the tasks for displaying or saving them.
     *
     * @return the tasks in this task list
     */
    public List<Task> asList() {
        return List.copyOf(tasks);
    }
}
