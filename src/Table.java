import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Table {
    private TodoMatrix todoMatrix;

    public Table(TodoMatrix todoMatrix) {
        this.todoMatrix = todoMatrix;
    }

    private Integer[] getColumnsWidth() {
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

    public void HigherPartOfTable(TodoMatrix todoMatrix) {
        final int EXTRA_SPACES = 2;
        String format = getFormatForPrint(EXTRA_SPACES);
        String reverseFormat = getReverseFormatForPrint(EXTRA_SPACES);

        TodoQuarter leftQuarter = todoMatrix.getQuarter("IU");
        TodoQuarter rightQuarter = todoMatrix.getQuarter("IN");

        printTheSameNumberOfRows(leftQuarter, rightQuarter, format);
        printOthersRows(leftQuarter, rightQuarter, format, reverseFormat);

    }

    public void LowerPartOfTable(TodoMatrix todoMatrix) {
        final int EXTRA_SPACES = 2;
        String format = getFormatForPrint(EXTRA_SPACES);
        String reverseFormat = getReverseFormatForPrint(EXTRA_SPACES);

        TodoQuarter leftQuarter = todoMatrix.getQuarter("NU");
        TodoQuarter rightQuarter = todoMatrix.getQuarter("NN");

        printTheSameNumberOfRows(leftQuarter, rightQuarter, format);
        printOthersRows(leftQuarter, rightQuarter, format, reverseFormat);

    }

    private void printTheSameNumberOfRows(TodoQuarter leftQuarter, TodoQuarter rightQuarter, String format) {
        int minSizeOfItems = Math.min(rightQuarter.getItems().size(), leftQuarter.getItems().size());
        for (int i = 0; i < minSizeOfItems; i++) {
            System.out.printf(format, leftQuarter.getItem(i), rightQuarter.getItem(i));
        }
    }

    private void printOthersRows(TodoQuarter leftQuarter, TodoQuarter rightQuarter,
                                 String format, String reverseFormat) {
        int sizeOfLeftItem = leftQuarter.getItems().size();
        int sizeOfRightItem = rightQuarter.getItems().size();

        int sizeDifference = Math.abs(sizeOfLeftItem - sizeOfRightItem);
        if (sizeOfLeftItem > sizeOfRightItem) {
            for (int i = 1; i <= sizeDifference; i++) {
                System.out.printf(format, leftQuarter.getItem(sizeOfLeftItem - i), "");
            }
        } else {
            for (int i = 1; i <= sizeDifference; i++) {
                System.out.printf(reverseFormat, "", rightQuarter.getItem(sizeOfRightItem - i), "");
            }
        }
    }

    private String getFormatForPrint(int extraSpaces) {
        String format = "";
        for (int i = 0; i < getColumnsWidth().length; i++) {
            format += "%-" + (getColumnsWidth()[i] + extraSpaces) + "s ";
        }
        return format + "%n";
    }

    private String getReverseFormatForPrint(int extraSpaces) {
        String reverseFormat = "";
        for (int i = getColumnsWidth().length; i > 0; i--) {
            reverseFormat += "%-" + (getColumnsWidth()[i - 1] + extraSpaces) + "s ";
        }
        return reverseFormat + "%n";
    }

//        System.out.println(format);
//        System.out.println();
//        System.out.println();
//        System.out.printf(format, IU, NU);
//        System.out.printf(format, IN, NN);

}