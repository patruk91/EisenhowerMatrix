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
            String entryKey = entry.getKey();
            TodoQuarter entryValue = entry.getValue();

            if (entryKey.equals("IU") || entryKey.equals("NU")) {
                columnsWidth[1] = firstColumnWidth(entryValue, columnsWidth[1]);
            } else if (entryKey.equals("IN") || entryKey.equals("NN")) {
                columnsWidth[0] = secondColumnWidth(entryValue, columnsWidth[0]);
            }
        }
        return columnsWidth;
    }

    private int firstColumnWidth(TodoQuarter entryValue, int firstColumnLength) {
        for (int i = 0; i < entryValue.getItems().size(); i++) {
            int deadlineLength = entryValue.getItem(i).getDeadline().toString().length();
            int titleLength = entryValue.getItem(i).getTitle().length();

            if (firstColumnLength < deadlineLength + titleLength) {
                firstColumnLength = deadlineLength + titleLength;
            }
        }
        return  firstColumnLength;
    }

    private int secondColumnWidth(TodoQuarter entryValue, int secondColumnLength) {
        for (int i = 0; i < entryValue.getItems().size(); i++) {
            int deadlineLength = entryValue.getItem(i).getDeadline().toString().length();
            int titleLength = entryValue.getItem(i).getTitle().length();

            if (secondColumnLength < deadlineLength + titleLength) {
                secondColumnLength = deadlineLength + titleLength;
            }
        }
        return secondColumnLength;
    }


    public void createTable(TodoMatrix todoMatrix) {
        String format = "";
        String reverseformat = "";
        int extraSpaces = 10;

        for (int i = 0; i < getColumnsWidth().length; i++) {
            format += "%-" + (getColumnsWidth()[i] + extraSpaces) + "s ";
        }
        format += "%n";

        for (int i = getColumnsWidth().length; i > 0; i--) {
            reverseformat += "%-" + (getColumnsWidth()[i - 1] + extraSpaces) + "s ";
        }
        reverseformat += "%n";

        TodoQuarter IU = todoMatrix.getQuarter("IU");
        TodoQuarter NU = todoMatrix.getQuarter("NU");

        TodoQuarter IN = todoMatrix.getQuarter("IN");
        TodoQuarter NN = todoMatrix.getQuarter("NN");
        System.out.println(format);


        int first = Math.abs(IN.getItems().size() - NN.getItems().size());
        for (int i = 0; i < Math.min(NN.getItems().size(), IN.getItems().size()); i++) {
            System.out.printf(format, IN.getItem(i), NN.getItem(i));
        }

        if (IN.getItems().size() > NN.getItems().size()) {
            for (int j = 1; j <= first; j++) {
                System.out.printf(format, IN.getItem(IN.getItems().size() - j), "");
            }
        } else {
            for (int j = 1; j <= first; j++) {
                System.out.printf(reverseformat, "", NN.getItem(NN.getItems().size() - j), "");
            }
        }

//        System.out.println(format);
//        System.out.println();
//        System.out.println();
//        System.out.printf(format, IU, NU);
//        System.out.printf(format, IN, NN);

    }
}