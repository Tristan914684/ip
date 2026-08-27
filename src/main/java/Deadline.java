import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    protected LocalDate by;
    protected String formattedBy;
    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
        this.formattedBy = this.by.format(OUTPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
    @Override
    public String getSaveString() {
        return "D | " + super.getSaveString() + " | " + by;
    }
}