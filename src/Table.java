import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Table {
    private TodoMatrix todoMatrix;

    public Table(TodoMatrix todoMatrix) {
        this.todoMatrix = todoMatrix;
    }

    public Integer[] getColumnsWidth() {
        Integer[] columnsWidth = {0, 0};
        for (Map.Entry<String, TodoQuarter> entry : todoMatrix.getQuarters().entrySet()) {
            if (entry.getKey().equals("IU") || entry.getKey().equals("NU")) {
                for (int i = 0; i < entry.getValue().getItems().size(); i++) {
                    TodoItem todoItemAtIndex = entry.getValue().getItem(i);
                    if (columnsWidth[1] < todoItemAtIndex.getDeadline().toString().length()
                            + todoItemAtIndex.getTitle().length()) {
                        columnsWidth[1] = todoItemAtIndex.getDeadline().toString().length()
                                + todoItemAtIndex.getTitle().length();
                    }
                }
            } else if (entry.getKey().equals("IN") || entry.getKey().equals("NN")) {
                for (int i = 0; i < entry.getValue().getItems().size(); i++) {
                    TodoItem todoItemAtIndex = entry.getValue().getItem(i);
                    if (columnsWidth[0] < todoItemAtIndex.getDeadline().toString().length()
                            + todoItemAtIndex.getTitle().length()) {
                        columnsWidth[0] = todoItemAtIndex.getDeadline().toString().length()
                                + todoItemAtIndex.getTitle().length();
                    }
                }
            }
        }
        return columnsWidth;
    }

    public void createTable(TodoMatrix todoMatrix) {
        String format = "";
        int extraSpaces = 10;

        for (int column : getColumnsWidth()) {
            format += "%-" + (column + extraSpaces) + "s ";
        }
        format += "%n";

        TodoQuarter IU =todoMatrix.getQuarter("IU");
        TodoQuarter NU= todoMatrix.getQuarter("NU");

        TodoQuarter IN = todoMatrix.getQuarter("IN");
        TodoQuarter NN = todoMatrix.getQuarter("NN");
        System.out.println(format);
        for (int i = 0; i < NN.getItems().size(); i++) {
            System.out.printf(format, IN.getItem(i), NN.getItem(i));
        }

//        System.out.println(format);
//        System.out.println();
//        System.out.println();
//        System.out.printf(format, IU, NU);
//        System.out.printf(format, IN, NN);
    }


}