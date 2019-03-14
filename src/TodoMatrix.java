import java.io.*;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.*;

public class TodoMatrix {
    private Map<String, TodoQuarter> todoQuarters;
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
        this.todoQuarters = new HashMap<>();
        this.todoQuarters.put("IU", this.IU = new TodoQuarter());
        this.todoQuarters.put("IN", this.IN = new TodoQuarter());
        this.todoQuarters.put("NU", this.NU = new TodoQuarter());
        this.todoQuarters.put("NN", this.NN = new TodoQuarter());
    }

    public Map<String, TodoQuarter> getQuarters() {
        return todoQuarters;
    }

    public TodoQuarter getQuarter(String status) {
        return todoQuarters.get(status);
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
            todoQuarters.get("IU").addItem(title, deadline);
        } else {
            todoQuarters.get("IN").addItem(title, deadline);
        }
    }

    private void isUrgentInAnNotImportant(long days, String title, LocalDate deadline) {
        if (days <= 3) {
            todoQuarters.get("NU").addItem(title, deadline);

        } else {
            todoQuarters.get("NN").addItem(title, deadline);
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
            isImportant = "important";
        } else {
            isImportant = "";
        }
        return isImportant;
    }

    public void saveItemsToFile(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, TodoQuarter> entry : this.todoQuarters.entrySet()) {
                int i = 0;
                while (i < entry.getValue().getItems().size()) {
                    TodoItem item = entry.getValue().getItem(i);
                    String getCorrectDateFormat = item.getDeadline().getDayOfMonth()
                            + "-" + item.getDeadline().getMonthValue();

                    if (entry.getKey().equals("IU") || entry.getKey().equals("IN")) {
                        bufferedWriter.write(String.format("%s|%s|important\n", item.getTitle(), getCorrectDateFormat));
                        i++;
                    } else {
                        bufferedWriter.write(String.format("%s|%s|\n", item.getTitle(), getCorrectDateFormat));
                        i++;
                    }
                }
            }
        }
    }

    public void archiveItems() {
        for (Map.Entry<String, TodoQuarter> entry : this.todoQuarters.entrySet()) {
            entry.getValue().archiveItems();
        }
    }

    @Override
    public String toString() {
        String a = "";
        return a;
    }

}
