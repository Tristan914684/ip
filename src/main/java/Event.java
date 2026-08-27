import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected String formattedStart;
    protected LocalDate by;
    protected String formattedBy;

    public Event(String name, String start, String by) {
        super(name);
        this.start = LocalDate.parse(start);
        this.formattedStart = this.start.format(OUTPUT_FORMAT);
        this.by = LocalDate.parse(by);
        this.formattedBy = this.by.format(OUTPUT_FORMAT);
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedBy + ")";
    }
    @Override
    public String getSaveString() {
        return "E | " + super.getSaveString() + " | " + start + " | " + by;
    }
}