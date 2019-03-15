import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Handler {

    public static String handleFileOption(Scanner reader, TodoMatrix todoMatrix) {
        String option;
        do {
            Displayer.displayMenu();
            option = reader.nextLine();
            if (option.equals("1")) {
                return option;
            } else if (option.equals("2")) {
                System.out.print("Please, which file to open (e.g 'data.csv'): ");
                String fileName = reader.nextLine();
                try {
                    todoMatrix.addItemsFromFile(fileName);
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

    public static String handleMethodOptions(Scanner reader, TodoMatrix todoMatrix) {
        String option = reader.nextLine();
        if (option.equals("1")) {
            Displayer.showStatus();
            handleStatusOfShownToDo(reader,todoMatrix);
        } else if (option.equals("2")) {
            System.out.print("Please provide a title: ");
            String title = reader.nextLine();
            System.out.print("Please provide a deadline (format yy-mm-dd): ");
            LocalDate deadline = LocalDate.parse(reader.nextLine());
            todoMatrix.getQuarter("IN").addItem(title, deadline);
        } else {
            String message = option.equals("q") ? "BYE BYE!\n" : "No option available!\n" + "Option: ";
            System.out.print(message);
        }

        return option;
    }

    private static void handleStatusOfShownToDo(Scanner reader, TodoMatrix todoMatrix) {
        String option = reader.nextLine();
        if (option.equals("1")) {
            String status = "IU";
            String message = getCorrectMessage(status, todoMatrix);
            System.out.println(message);

        } else if (option.equals("2")) {
            String status = "IN";
            String message = getCorrectMessage(status, todoMatrix);
            System.out.println(message);

        } else if (option.equals("3")) {
            String status = "NU";
            String message = getCorrectMessage(status, todoMatrix);
            System.out.println(message);

        } else if (option.equals("4")) {
            String status = "NN";
            String message = getCorrectMessage(status, todoMatrix);
            System.out.println(message);
        } else {
            System.out.print("No option available!"); //elseif for not showing option
        }
    }

    private static String getCorrectMessage(String status, TodoMatrix todoMatrix) {
        return todoMatrix.getQuarter(status).getItems().size() == 0
                ? "Matrix is empty!" : todoMatrix.getQuarter(status).toString();
    }





}
