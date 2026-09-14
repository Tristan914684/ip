package clsl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/** Tests end-to-end command responses for the application. */
public class ClslTest {
    @TempDir
    Path temporaryDirectory;

    @Test
    void getResponse_addingDuplicateTask_returnsAlreadyAddedError() {
        Clsl clsl = new Clsl(temporaryDirectory.resolve("tasks.txt").toString());
        clsl.getResponse("todo Buy milk");

        assertEquals("\nalready added\n", clsl.getResponse("todo buy   milk"));
        assertEquals("\nHere are your tasks in your list:\n1.[T][ ] Buy milk\n",
                clsl.getResponse("list"));
    }

    @Test
    void getResponse_usingTaskNumberBeyondList_returnsRangeError() {
        Clsl clsl = new Clsl(temporaryDirectory.resolve("tasks.txt").toString());
        clsl.getResponse("todo Buy milk");

        assertEquals("\ntask number must be at least 1\n", clsl.getResponse("mark 0"));
        assertEquals("\ntask number must be at least 1\n", clsl.getResponse("mark -1"));
        assertEquals("\ntask number is out of range\n", clsl.getResponse("mark 2"));
        assertEquals("\ntask number is out of range\n", clsl.getResponse("unmark 2"));
        assertEquals("\ntask number is out of range\n", clsl.getResponse("delete 2"));
        assertEquals("\nHere are your tasks in your list:\n1.[T][ ] Buy milk\n",
                clsl.getResponse("list"));
    }
}
