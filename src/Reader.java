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
}