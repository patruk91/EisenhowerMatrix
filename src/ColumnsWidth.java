import java.util.Map;

public class ColumnsWidth {
    private TodoMatrix todoMatrix;
    private int firstWidth;
    private int secondWidth;

    public ColumnsWidth(TodoMatrix todoMatrix) {
        this.todoMatrix = todoMatrix;
        this.firstWidth = 1;
        this.secondWidth = 1;
    }

    public void calculateColumnsWidth() {
        for (Map.Entry<String, TodoQuarter> entry : todoMatrix.getQuarters().entrySet()) {
            String entryKey = entry.getKey();
            TodoQuarter entryValue = entry.getValue();
            boolean leftColumn = entryKey.equals("IU") || entryKey.equals("NU");
            boolean rightColumn = entryKey.equals("IN") || entryKey.equals("NN");

            for (int i = 0; i < entryValue.getItems().size(); i++) {
                int deadlineLength = entryValue.getItem(i).getDeadline().toString().length();
                int titleLength = entryValue.getItem(i).getTitle().length();

                if (this.firstWidth < deadlineLength + titleLength && leftColumn) {
                    this.firstWidth = deadlineLength + titleLength;
                } else if (this.secondWidth < deadlineLength + titleLength && rightColumn) {
                    this.secondWidth = deadlineLength + titleLength;
                }
            }
        }
    }

    public int getFirstWidth() {
        return firstWidth;
    }

    public int getSecondWidth() {
        return secondWidth;
    }

    public int getFirstWidthWithExtraSpaces(int number) {
        return firstWidth + number;
    }

    public int getSecondWidthWithExtraSpaces(int number) {
        return secondWidth + number;
    }
}
