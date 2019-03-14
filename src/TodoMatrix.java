import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.*;

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

    public void addItemsFromFile(String fileName) throws IOException {
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\\|");

                String title = record[0];
                LocalDate deadline = getDateFromFile(record[1]);
                String isImportant= isImportant(record);

                if (!isImportant.equals("")) {
                    addItem(title,  deadline,  true);
                } else {
                    addItem(title,  deadline,  false);
                }
            }
        }
    }

    private LocalDate getDateFromFile(String dateDayMonth) {
        String[] dayMonth = dateDayMonth.split("-");
        String currentDate = String.format("%s-%s-%s", Year.now().getValue(), dayMonth[1], dayMonth[0]);
        return LocalDate.parse(currentDate);
    }

    private String isImportant(String[] record) {
        String isImportant;
        if (record.length > 2) {
            isImportant = record[2];
        } else {
            isImportant = "";
        }
        return isImportant;
    }
}
