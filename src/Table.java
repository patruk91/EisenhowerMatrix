public class Table {
    private TodoMatrix todoMatrix;
    private ColumnsWidth columnsWidth;

    public Table(TodoMatrix todoMatrix) {
        this.todoMatrix = todoMatrix;
        this.columnsWidth = new ColumnsWidth(todoMatrix);
    }

    public void printTable() {
        this.columnsWidth.calculateColumnsWidth();
        String format = getFormatForPrint();

        printHeader(format);
        printPartOfTable(format);
    }

    private void printPartOfTable(String format) {
        TodoQuarter UpLeftQuarter = todoMatrix.getQuarter("IU");
        TodoQuarter UpRightQuarter = todoMatrix.getQuarter("IN");
        TodoQuarter DownLeftQuarter = todoMatrix.getQuarter("NU");
        TodoQuarter DownRightQuarter = todoMatrix.getQuarter("NN");

        higherPartOfTable(format, UpLeftQuarter, UpRightQuarter);
        printFooter();
        lowerPartOfTable(format, DownLeftQuarter, DownRightQuarter);
        printFooter();
    }

    private void higherPartOfTable(String format, TodoQuarter UpLeftQuarter, TodoQuarter UpRightQuarter) {
        int item = 0;
        int left = UpLeftQuarter.getItems().size();
        int right = UpRightQuarter.getItems().size();
        int max = Math.max(left, right);

        for (int i = 0; i < Math.max("IMPORTANT".length(), max); i++) {
            char[] charImportant =  "IMPORTANT".toCharArray();
            if (i < charImportant.length) {
                System.out.print(charImportant[i] + "| ");
            } else {
                System.out.print(" | ");
            }


            if (item < Math.min(left, right)) {
                System.out.printf(format, UpLeftQuarter.getItem(item) , UpRightQuarter.getItem(item));
                item++;
            } else if (left < right && item < right) {
                System.out.printf(format, "" , UpRightQuarter.getItem(item));
                item++;
            } else if (left > right && item < left) {
                System.out.printf(format, UpLeftQuarter.getItem(item) , "");
                item++;
            } else {
                System.out.printf(format, "", "");
            }
        }
    }

    private void lowerPartOfTable(String format, TodoQuarter DownLeftQuarter, TodoQuarter DownRightQuarter) {
        int item = 0;
        int left = DownLeftQuarter.getItems().size();
        int right = DownRightQuarter.getItems().size();
        int max = Math.max(left, right);

        for (int i = 0; i < Math.max("NOT IMPORTANT".length(), max); i++) {
            char[] charImportant =  "NOT IMPORTANT".toCharArray();
            if (i < charImportant.length) {
                System.out.print(charImportant[i] + "| ");
            } else {
                System.out.print(" | ");
            }


            if (item < Math.min(left, right)) {
                System.out.printf(format, DownLeftQuarter.getItem(item) , DownRightQuarter.getItem(item));
                item++;
            } else if (left < right && item < right) {
                System.out.printf(format, "" , DownRightQuarter.getItem(item));
                item++;
            } else if (left > right && item < left) {
                System.out.printf(format, DownLeftQuarter.getItem(item) , "");
                item++;
            } else {
                System.out.printf(format, "", "");
            }
        }
    }

    private void printHeader(String format) {
        String filled = "-";
        filled = filled.repeat(columnsWidth.getFirstWidth() + columnsWidth.getSecondWidth() + 6);
        System.out.print(" | ");
        System.out.printf(format, "URGENT", "NOT URGENT");
        System.out.println(filled);
    }

    private void printFooter() {
        String filled = "-";
        filled = filled.repeat(columnsWidth.getFirstWidth() + columnsWidth.getSecondWidth() + 6);
        System.out.println(filled);
    }

    private String getFormatForPrint() {
        String format = "%-" + columnsWidth.getFirstWidth() + "s| ";
        format += "%-" + columnsWidth.getSecondWidth() + "s| ";
        return format + "%n";
    }
}
