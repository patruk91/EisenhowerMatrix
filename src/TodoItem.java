import java.time.LocalDate;

public class TodoItem {

    private String title;
    private LocalDate deadline;
    private boolean isDone = false;

    public boolean isDone() {
        return isDone;
    }

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

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String mark = (this.isDone) ? "x" : " " ;
        String dayMonth = this.deadline.getDayOfMonth() + "-" + this.deadline.getMonthValue();
        return  String.format("[%s] %s %s", mark, dayMonth, this.title);
    }
}
