import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
        StringBuilder stringBuilder = new StringBuilder();
        String mark = (this.isDone) ? "x" : " " ;
        String dayMonth = this.deadline.getDayOfMonth() + "-" + this.deadline.getMonthValue();

        LocalDate actualDate = LocalDate.now();
        long days = ChronoUnit.DAYS.between(actualDate, getDeadline());

        if (days == 0 && !isDone) {
            stringBuilder.append(String.format(Colors.ANSI_RED + "[%s] %s %s" + Colors.ANSI_RESET, mark, dayMonth, this.title));
        } else if (days <= 3  && !isDone) {
            stringBuilder.append(String.format(Colors.ANSI_YELLOW + "[%s] %s %s" + Colors.ANSI_RESET, mark, dayMonth, this.title));
        } else if (days > 3  && !isDone) {
            stringBuilder.append(String.format(Colors.ANSI_GREEN + "[%s] %s %s" + Colors.ANSI_RESET, mark, dayMonth, this.title));
        } else {
            stringBuilder.append(String.format("[%s] %s %s", mark, dayMonth, this.title));
        }
        return stringBuilder.toString();
//        return  String.format("[%s] %s %s", mark, dayMonth, this.title);

    }
}
