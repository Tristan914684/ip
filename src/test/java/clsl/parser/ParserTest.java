package clsl.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import clsl.ClslException;
import org.junit.jupiter.api.Test;

/** Tests the conversion of user input into parsed commands. */
public class ParserTest {
    @Test
    void parse_todoCommand_returnsTodoDetails() throws Exception {
        ParsedCommand command = Parser.parse("todo read book");

        assertEquals(ParsedCommand.Type.TODO, command.getType());
        assertEquals("read book", command.getDescription());
    }

    @Test
    void parse_deadlineCommand_returnsDescriptionAndDate() throws Exception {
        ParsedCommand command = Parser.parse("deadline submit report /by 2026-09-01");

        assertEquals(ParsedCommand.Type.DEADLINE, command.getType());
        assertEquals("submit report", command.getDescription());
        assertEquals("2026-09-01", command.getFirstDate().toString());
    }

    @Test
    void parse_eventCommand_returnsDescriptionStartAndEndDates() throws Exception {
        ParsedCommand command = Parser.parse("event conference /from 2026-09-01 /to 2026-09-03");

        assertEquals(ParsedCommand.Type.EVENT, command.getType());
        assertEquals("conference", command.getDescription());
        assertEquals("2026-09-01", command.getFirstDate().toString());
        assertEquals("2026-09-03", command.getSecondDate().toString());
    }

    @Test
    void parse_findCommand_returnsKeyword() throws Exception {
        ParsedCommand command = Parser.parse("find book");

        assertEquals(ParsedCommand.Type.FIND, command.getType());
        assertEquals("book", command.getDescription());
    }

    @Test
    void parse_markWithInvalidTaskNumber_throwsHelpfulException() {
        ClslException exception = assertThrows(ClslException.class, () -> Parser.parse("mark zero"));

        assertEquals("task number must be a whole number", exception.getMessage());
    }

    @Test
    void parse_todoWithoutDescription_throwsHelpfulException() {
        ClslException exception = assertThrows(ClslException.class, () -> Parser.parse("todo"));

        assertEquals("todo what exactly?", exception.getMessage());
    }

    @Test
    void parse_findWithoutKeyword_throwsHelpfulException() {
        ClslException exception = assertThrows(ClslException.class, () -> Parser.parse("find"));

        assertEquals("find what?", exception.getMessage());
    }
}
