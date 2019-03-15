import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TodoQuarter {
    private List<TodoItem> todoItems;

    public TodoQuarter() {
        this.todoItems = new ArrayList<>();
    }

    public void addItem(String title, LocalDate deadline) {
        TodoItem todoItem = new TodoItem(title, deadline);
        this.todoItems.add(todoItem);
        this.todoItems.sort(Comparator.comparing(TodoItem::getDeadline));
    }

    public void removeItem(int index) {
        this.todoItems.remove(index);
    }

    public void archiveItems() {
        for (int i = 0; i < this.todoItems.size(); i++) {
//            if (todoItems.get(i).isDone()) {
//                todoItems.remove(i);
                this.todoItems.removeIf(todoItem -> todoItem.isDone());
//            }
        }
    }

    public TodoItem getItem(int index) {
        return this.todoItems.get(index);
    }

    public List<TodoItem> getItems() {
        return this.todoItems;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.todoItems.size(); i++) {
            String mark = (this.todoItems.get(i).isDone()) ? "x" : " " ;
            int deadlineDay = this.todoItems.get(i).getDeadline().getDayOfMonth();
            int deadlineMonth = this.todoItems.get(i).getDeadline().getMonthValue();

            String day = deadlineDay < 10 ? "0" + deadlineDay : deadlineDay + "";
            String month = deadlineMonth < 10 ? "0" + deadlineMonth : deadlineMonth + "";
            String dayMonth = day + "-" + month;


            LocalDate actualDate = LocalDate.now();
            long days = ChronoUnit.DAYS.between(actualDate, this.todoItems.get(i).getDeadline());
            boolean checkIfIsDone = this.todoItems.get(i).isDone();
            String title = this.todoItems.get(i).getTitle();

            if (days == 0 && !checkIfIsDone) {
                stringBuilder.append(String.format(Colors.ANSI_RED + "%d. [%s] %s %s\n" + Colors.ANSI_RESET,
                        i + 1, mark, dayMonth, title));
            } else if (days <= 3  && !checkIfIsDone) {
                stringBuilder.append(String.format(Colors.ANSI_YELLOW + "%d. [%s] %s %s\n" + Colors.ANSI_RESET,
                        i + 1, mark, dayMonth, title));
            } else if (days > 3  && !checkIfIsDone) {
                stringBuilder.append(String.format(Colors.ANSI_GREEN + "%d. [%s] %s %s\n" + Colors.ANSI_RESET,
                        i + 1, mark, dayMonth, title));
            } else {
                stringBuilder.append(String.format("%d. [%s] %s %s\n",
                        i + 1, mark, dayMonth, title));
            }
        }

        return stringBuilder.toString();
    }

//    private StringBuilder colorString() {
//
//    }
}
