import java.time.LocalDate;

public class TodoItem {

    private String title;
    private LocalDate deadline;
    private boolean isDone = false;

    public TodoItem(String title, LocalDate deadline) {
        this.title = title;
        this.deadline = deadline;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public boolean mark() {
        return this.isDone = true;
    }

    public boolean unmark() {
        return this.isDone = false;
    }

    @Override
    public String toString() {
        return this.deadline + " " + this.title;
    }
}
