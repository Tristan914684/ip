package clsl.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

/** Tests task-list operations that depend on a task's type and dates. */
public class TaskListTest {
    @Test
    void getTasksOn_tasksWithDifferentDates_returnsOnlyMatchingTasks() {
        ToDo todo = new ToDo("read book");
        Deadline deadline = new Deadline("submit report", "2026-09-02");
        Event event = new Event("conference", "2026-09-01", "2026-09-03");
        TaskList taskList = new TaskList(List.of(todo, deadline, event));

        List<Task> matchingTasks = taskList.getTasksOn(LocalDate.of(2026, 9, 2));

        assertEquals(List.of(deadline, event), matchingTasks);
    }

    @Test
    void getTasksOn_eventBoundaryDates_includesEvent() {
        Event event = new Event("conference", "2026-09-01", "2026-09-03");
        TaskList taskList = new TaskList(List.of(event));

        assertEquals(List.of(event), taskList.getTasksOn(LocalDate.of(2026, 9, 1)));
        assertEquals(List.of(event), taskList.getTasksOn(LocalDate.of(2026, 9, 3)));
    }

    @Test
    void getTasksOn_noTaskMatches_returnsEmptyList() {
        TaskList taskList = new TaskList(List.of(new ToDo("read book")));

        assertEquals(List.of(), taskList.getTasksOn(LocalDate.of(2026, 9, 2)));
    }
}
