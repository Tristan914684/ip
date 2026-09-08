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
}
