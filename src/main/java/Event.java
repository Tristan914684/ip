public class Event extends Task {
    protected String start;
    protected String by;

    public Event(String name, String start, String by) {
        super(name);
        this.start = start;
        this.by = by;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + start + " to: " + by + ")";
    }
}