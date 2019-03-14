import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class UserAPI {
    public static void start(String[] args) {
        TodoMatrix todoMatrix = new TodoMatrix();
        Scanner reader = new Scanner(System.in);
        displayMenu();
        handleFileOption(reader, todoMatrix);

        System.out.println(todoMatrix); //toString method

        displayMenuQuarterStatus();
        handleStatusOfToDoItems(reader, todoMatrix);
    }

    private static void handleStatusOfToDoItems(Scanner reader, TodoMatrix todoMatrix) {
        String option = reader.nextLine();
        if (option.equals("1")) {
            System.out.println(todoMatrix.getQuarter("IU"));
            displayMethodsForQuarters();

        } else if (option.equals("2")) {
            System.out.println(todoMatrix.getQuarter("IN"));
            displayMethodsForQuarters();
            handleQuarterOption(reader, todoMatrix);
        } else if (option.equals("3")) {
            System.out.println(todoMatrix.getQuarter("NU"));

        } else if (option.equals("4")) {
            System.out.println(todoMatrix.getQuarter("NN"));
        }
    }

    private static void handleQuarterOption(Scanner reader, TodoMatrix todoMatrix) {

    }


    private static void displayMethodsForQuarters() {
        System.out.println("Please choose a status of matrix: ");
        System.out.println("1. Add item\n"
                            + "2. Mark item\n"
                            + "3. Unmark item\n"
                            + "4. Remove marked item\n"
                            + "5. Archive items - remove all done items\n"
                            + "6. Save to file"
                            + "7. Archive items -> save to file -> quit from application\n"
                            + "8. Show whole Eisenhower matrix\n"
                            + "Press 'q' to exit");
        System.out.print("Option: ");
    }

    private static void displayMenuQuarterStatus() {
        System.out.println("Please choose a status of matrix: ");
        System.out.println("1. urgent & important items\n"
                            + "2. not urgent & important items\n"
                            + "3. urgent & not important items\n"
                            + "4. not urgent & not important items\n"
                            + "Press 'q' to exit");
        System.out.print("Option: ");


    }

    private static void handleFileOption(Scanner reader, TodoMatrix todoMatrix) {
        String option = reader.nextLine();
        while (!option.equals("q")) {
            if (option.equals("1")) {
                //to do: method for create file
                option = "q";
            } else if (option.equals("2")) {
                System.out.print("Please, which file to open (e.g 'data.csv'): ");
                String fileName = reader.nextLine();
                try {
                    todoMatrix.addItemsFromFile(fileName);
                } catch (FileNotFoundException error) {
                    System.out.println("We couldn't read the file. Error: " + error.getMessage());
                } catch (IOException error) {
                    System.out.println("ERROR: " + error.getMessage());
                }
                option = "q";

            } else {
                System.out.println("No option available!");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("What you want to do?");
        System.out.println("1. Create new file for Eisenhower matrix\n"
                            + "2. Read data from file\n"
                            + "Press 'q' to exit");
        System.out.print("Option: ");
        }

}
