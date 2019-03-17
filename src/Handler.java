import java.io.FileNotFoundException;
import java.io.IOException;

public class Handler {

    public static String handleFileOption(Reader reader, TodoMatrix todoMatrix) {
        String option;
        do {
            Displayer.displayMenu();
            option = reader.readString();
            if (option.equals("1")) {
                return option;
            } else if (option.equals("2")) {
                System.out.print("Please, which file to open (e.g 'data.csv'): ");
                String fileName = reader.readString();
                try {
                    todoMatrix.addItemsFromFile(fileName);
                    System.out.println();
                    return option;
                } catch (FileNotFoundException error) {
                    System.out.println("We couldn't read the file. Error: " + error.getMessage());
                } catch (IOException error) {
                    System.out.println("ERROR: " + error.getMessage());
                }
            } else {
                String message = option.equals("q") ? "BYE BYE!\n" : "No option available!\n" + "Option: ";
                System.out.print(message);
            }
        } while (!option.equals("q"));
        return option;
    }

    public static String handleMethodOptions(Reader reader, TodoMatrix todoMatrix,
                                             Table table, Availabilities availabilities) {

        String option = reader.readString();
        if (option.equals("1")) {
            Displayer.showStatus();
            availabilities.shownStatusOfMatrix(reader);
        } else if (option.equals("2")) {
            availabilities.addItemToMatrix(reader);
        } else if (option.equals("3") || option.equals("4") || option.equals("5")) {
            String message = availabilities.markOrUnmarkOrRemoveItem(reader, option);
            System.out.println(message);
        } else if (option.equals("6")) {
            String message = availabilities.archiveItems();
            System.out.println(message);
        } else if(option.equals("7")) {
            String message = availabilities.saveToFile(reader);
            System.out.println(message);
        } else if (option.equals("8")) {
            System.out.println(availabilities.archiveItems());
            System.out.println(availabilities.saveToFile(reader));
            option = "q";
        } else if(option.equals("9")) {
            table.printTable();
        } else {
            String message = option.equals("q") ? "BYE BYE!\n" : "No option available!\n" + "Option: ";
            System.out.print(message);
        }
        return option;
    }
}
