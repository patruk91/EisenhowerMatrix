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
        String status = "important";

        printHeader(EXTRA_SPACES, format);
        printTheSameNumberOfRows(leftQuarter, rightQuarter, format, reverseFormat, status);
//        printOthersRows(leftQuarter, rightQuarter, format, reverseFormat, status);
    }

    private void printHeader(int EXTRA_SPACES, String format) {
        int size = getColumnsWidth()[1]/2 + EXTRA_SPACES;
        String filled = "-";
        filled = filled.repeat(getColumnsWidth()[0] + getColumnsWidth()[1] + EXTRA_SPACES *2 + 2);
        format = "%-" + (getColumnsWidth()[1] + 3) + "s| ";
        format += "%-" + (getColumnsWidth()[0]) + "s| ";
        System.out.printf(format, " | URGENT", "NOT URGENT");
        System.out.println("\n" + filled);
    }

    private void printFooter(int EXTRA_SPACES) {
        String filled = "-";
        filled = filled.repeat(getColumnsWidth()[0] + getColumnsWidth()[1] + EXTRA_SPACES*2);
        System.out.println(filled);
    }

    public void LowerPartOfTable(TodoMatrix todoMatrix) {
        final int EXTRA_SPACES = 3;
        String format = getFormatForPrint(EXTRA_SPACES);
        String reverseFormat = getReverseFormatForPrint(EXTRA_SPACES);

        TodoQuarter leftQuarter = todoMatrix.getQuarter("NU");
        TodoQuarter rightQuarter = todoMatrix.getQuarter("NN");
        String status = "not important";

        printFooter(EXTRA_SPACES);
        printTheSameNumberOfRows(leftQuarter, rightQuarter, format, reverseFormat, status);
        printFooter(EXTRA_SPACES);

    }

    private void printTheSameNumberOfRows(TodoQuarter leftQuarter,
                                          TodoQuarter rightQuarter, String format, String reverseFormat, String status) {
        int j = 0;
        char[] charImportant = new char[status.toCharArray().length];
        int minSizeOfItems = Math.min(rightQuarter.getItems().size(), leftQuarter.getItems().size());
        for (int i = 0; i < minSizeOfItems; i++) {
            if (status.equals("important")) {
                charImportant =  "IMPORTANT".toCharArray();
                System.out.print(charImportant[i] + "| ");
                j++;
            } else if (status.equals("not important")) {
                charImportant =  "NOT IMPORTANT".toCharArray();
                System.out.print(charImportant[i] + "| ");
                j++;
            }

            System.out.printf(format, leftQuarter.getItem(i), rightQuarter.getItem(i));
        }
        printOthersRows(leftQuarter, rightQuarter, format, reverseFormat, charImportant, j);
    }

    private void printOthersRows(TodoQuarter leftQuarter, TodoQuarter rightQuarter,
                                 String format, String reverseFormat, char[] charImp, int j) {
        int sizeOfLeftItem = leftQuarter.getItems().size();
        int sizeOfRightItem = rightQuarter.getItems().size();

        int sizeDifference = (Math.abs(sizeOfLeftItem - sizeOfRightItem));
        if (sizeOfLeftItem > sizeOfRightItem) {
            for (int i = 1; i <= sizeDifference; i++) {
                String condition = (charImp.length > j) ? charImp[j] + "| " : " |";
                System.out.printf(format, condition + leftQuarter.getItem(sizeOfLeftItem - i), "");
                j++;
            }
            while (j < charImp.length) {
                System.out.print(charImp[j] + "| ");
                format = "%-" + (getColumnsWidth()[0]) + "s| ";
                format += "%-" + (getColumnsWidth()[1] + (Math.abs(getColumnsWidth()[0] - getColumnsWidth()[1])) + 4) + "s| ";
                format += "%n";
                System.out.printf(format, "", "");
                j++;
            }
        } else {
            for (int i = 1; i <= sizeDifference; i++) {
                String condition = (charImp.length > j) ? charImp[j] + "| " : " |";
                System.out.printf(reverseFormat, condition, rightQuarter.getItem(sizeOfRightItem - i), "");
                j++;
            }
            while (j < charImp.length) {
                System.out.print(charImp[j] + "| ");
                reverseFormat = "%-" + (getColumnsWidth()[1]) + "s| ";
                reverseFormat += "%-" + (getColumnsWidth()[0]) + "s| ";
                reverseFormat += "%n";
                System.out.printf(reverseFormat, "", "");
                j++;
            }
        }
    }

    private String getFormatForPrint(int extraSpaces) {
        String format = "";
        format += "%-" + (getColumnsWidth()[0]) + "s| ";
        format += "%-" + (getColumnsWidth()[1] + (Math.abs(getColumnsWidth()[0] - getColumnsWidth()[1])) + 9) + "s| ";

        return format + "%n";
    }

    private String getReverseFormatForPrint(int extraSpaces) {
        String reverseFormat = "";
        reverseFormat += "%-" + (getColumnsWidth()[1] + 3) + "s| ";
        reverseFormat += "%-" + (getColumnsWidth()[0]+ (Math.abs(getColumnsWidth()[0] - getColumnsWidth()[1]))) + "s| ";



        return reverseFormat + "%n";
    }
}