import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class UserAPI {
    public static void start(String[] args) {
        TodoMatrix todoMatrix = new TodoMatrix();
        Scanner reader = new Scanner(System.in);
        handleMenu();
        handleFileOption(reader, todoMatrix);

        System.out.println(todoMatrix); //toString method


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

    private static void handleMenu() {
            System.out.println("What you want to do?");
            System.out.println("1. Create new file for Eisenhower matrix\n" +
                    "2. Read data from file\n" +
                    "Or press 'q' to exit");
        System.out.print("Option: ");
        }

//
//        try {
//            todoMatrix.addItemsFromFile("matrix.csv");
//            todoMatrix.saveItemsToFile("tempfilesave.csv");
//        } catch (FileNotFoundException error) {
//            System.out.println("We couldn't read the file. Error: " + error.getMessage());
//        } catch (IOException error) {
//            System.out.println("ERROR: " + error.getMessage());
//        }
//        todoMatrix.archiveItems();

//        System.out.println(todoMatrix.getQuarters());
//        System.out.println(todoMatrix.getQuarters().get("IU"));
//        System.out.println(todoMatrix.getQuarters().get("IN").getItem(0));
//
//
//        todoMatrix.getQuarters().get("NU").getItem(0);
//
//        todoMatrix.getQuarters().get("NN").getItem(0);

}
