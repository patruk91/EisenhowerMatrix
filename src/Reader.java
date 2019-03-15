import java.util.Scanner;

public class Reader {
    private final Scanner reader = new Scanner(System.in);

    public String readString() {
        return reader.nextLine();
    }

    public int readInteger() {
        return Integer.parseInt(reader.nextLine());
    }

    public int readInteger(String number) {
        return Integer.parseInt(reader.nextLine());
    }

    public boolean isNumeric(String number) {
        return number != null && number.matches("[-+]?\\d*\\.?\\d+");
    }

    public boolean checkIfDateHaveCorrectFormat(String date) {
        String[] splitDate = date.split("-");
        for (int i = 0; i < splitDate.length; i++) {
            if (!isNumeric(splitDate[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDataLength(String date) {
        String[] splitDate = date.split("-");
        if (splitDate.length == 2) {
            return checkIfDaysMonthsAreCorrect(splitDate);
        } else {
            return false;
        }
    }

    private boolean checkIfDaysMonthsAreCorrect(String [] splitDate) {
        int day = Integer.parseInt(splitDate[1]);
        int month = Integer.parseInt(splitDate[0]);
        return (day <= 31 && day > 0 && month <= 12 && month > 0);
    }

    private boolean isEmptyInput(String string) {
        return string.isEmpty();
    }
}