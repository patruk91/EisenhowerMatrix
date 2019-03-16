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
                this.todoItems.removeIf(todoItem -> todoItem.isDone());
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
            stringBuilder.append(i +1 + ". " + this.todoItems.get(i).toString() + "\n");
        }
        return stringBuilder.toString();
    }
}
