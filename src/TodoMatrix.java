import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TodoMatrix {
    private Map<String, TodoQuarter> todoQuartes;
    private TodoQuarter IU;
    private TodoQuarter IN;
    private TodoQuarter NU;
    private TodoQuarter NN;

    /*
    - 'IU' means that todoQuarter contains important todoItems & urgent
    - 'IN' means that todoQuarter contains important todoItems & not urgent
    - 'NU' means that todoQuarter contains not important todoItems & urgent
    - 'NN' means that todoQuarter contains not important & not urgent todoItems
     */

    public TodoMatrix() {
        this.todoQuartes = new HashMap<>();
        this.todoQuartes.put("IU", this.IU = new TodoQuarter());
        this.todoQuartes.put("IN", this.IN = new TodoQuarter());
        this.todoQuartes.put("NU", this.NU = new TodoQuarter());
        this.todoQuartes.put("NN", this.NN = new TodoQuarter());
    }

    public Map<String, TodoQuarter> getQuarters() {
        return todoQuartes;
    }

    public TodoQuarter getQuarter(String status) {
        return todoQuartes.get(status);
    }

    public void addItem(String title, LocalDate deadline, boolean isImportant) {
        LocalDate actualDate = LocalDate.now();
        long days = ChronoUnit.DAYS.between(actualDate, deadline);

        if (isImportant) {
            isUrgentInAnImportant(days, title, deadline);
        } else {
            isUrgentInAnNotImportant(days, title, deadline);
        }
    }

    private void isUrgentInAnImportant(long days, String title, LocalDate deadline) {
        if (days <= 3) {
            todoQuartes.get("IU").addItem(title, deadline);
        } else {
            todoQuartes.get("IN").addItem(title, deadline);
        }
    }

    private void isUrgentInAnNotImportant(long days, String title, LocalDate deadline) {
        if (days <= 3) {
            todoQuartes.get("NU").addItem(title, deadline);

        } else {
            todoQuartes.get("NN").addItem(title, deadline);
        }
    }
}
