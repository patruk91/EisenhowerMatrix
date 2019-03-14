import java.time.LocalDate;
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
            if (todoItems.get(i).isDone()) {
                todoItems.remove(i);
//                this.todoItems.removeIf(todoItem -> todoItem.isDone());
            }
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
        String result = "";
        for (int i = 0; i < this.todoItems.size(); i++) {
            String mark = (this.todoItems.get(i).isDone()) ? "x" : " " ;
            String dayMonth = this.todoItems.get(i).getDeadline().getDayOfMonth() + "-"
                    + this.todoItems.get(i).getDeadline().getMonthValue();
            result += String.format("%d. [%s] %s %s\n", i + 1, mark, dayMonth, this.todoItems.get(i).getTitle());
        }
        return result;
    }
}
