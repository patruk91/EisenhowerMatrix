import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

    public static String handleMethodOptions(Reader reader, TodoMatrix todoMatrix) {
        String option = reader.readString();
        if (option.equals("1")) {
            Displayer.showStatus();
            handleStatusOfShownToDo(reader,todoMatrix);
        } else if (option.equals("2")) {
            addItemToMatrix(reader, todoMatrix);
        } else if (option.equals("3") || option.equals("4") || option.equals("5")) {
            String message = markOrUnmarkOrRemoveItem(todoMatrix, reader, option);
            System.out.println(message);
        } else if (option.equals("6")) {
            String message = archiveItems(todoMatrix);
            System.out.println(message);
        } else if(option.equals("7")) {
            String message = saveToFile(todoMatrix, reader);
            System.out.println(message);
        } else if (option.equals("8")) {
            System.out.println(archiveItems(todoMatrix));
            System.out.println(saveToFile(todoMatrix, reader));
            option = "q";
        } else if(option.equals("9")) {
            Table table = new Table(todoMatrix);
            table.HigherPartOfTable(todoMatrix);
            table.LowerPartOfTable(todoMatrix);
        }

        else {
            String message = option.equals("q") ? "BYE BYE!\n" : "No option available!\n" + "Option: ";
            System.out.print(message);
        }
        return option;
    }

    private static String saveToFile(TodoMatrix todoMatrix, Reader reader) {
        System.out.print("File name: ");
        String fileName = reader.readString();
        try {
            todoMatrix.saveItemsToFile(fileName);
        } catch (IOException error) {
            System.out.println("ERROR: " + error.getMessage());
            System.out.println("ERROR: We cannot save file.");
        }
        return "File saved!";
    }

    private static String archiveItems(TodoMatrix todoMatrix) {
            todoMatrix.archiveItems();
            return "Items archived!";
    }

    private static String markOrUnmarkOrRemoveItem(TodoMatrix todoMatrix, Reader reader, String option) {
        System.out.println("Please provide a title: ");
        String title = reader.readString();
        String[] itemStatus = {"IU", "IN", "NU", "NN"};
        for (int i = 0; i < itemStatus.length; i++) {
            TodoQuarter itemQuarter = todoMatrix.getQuarter(itemStatus[i]);
            List<TodoItem> todoItemList = itemQuarter.getItems();

            for (int itemIndex = 0; itemIndex < todoItemList.size(); itemIndex++) {
                if (title.equals(todoItemList.get(itemIndex).getTitle())) {
                    if (option.equals("3")) {
                        todoItemList.get(itemIndex).mark();
                        return "Successful mark!";
                    } else if (option.equals("4")){
                        todoItemList.get(itemIndex).unmark();
                        return "Successful unmark!";
                    } else {
                        todoMatrix.getQuarter(itemStatus[i]).removeItem(itemIndex);
                        return "Item removed!";
                    }
                }
            }
        }
        return "Sorry TODO item don't exist!";
    }

    private static void addItemToMatrix(Reader reader, TodoMatrix todoMatrix) {
        String title = handleTitle(reader);

        String date = handleDate(reader);
        LocalDate deadline = LocalDate.parse(date);

        System.out.print("Is this important (y/n)?: ");
        String checkIfImportant = reader.readString();

        boolean isImportant = checkIfImportant.equals("y");
        todoMatrix.addItem(title, deadline, isImportant);
    }

    private static String handleTitle(Reader reader) {
        String title = "";
        while (title.isEmpty()) {
            System.out.print("Please provide a title: ");
            title = reader.readString();
        }
        return title;
    }

    private static String handleDate(Reader reader) {
        String userInput = "";

        while (!(reader.checkIfDateHaveCorrectFormat(userInput) && reader.checkDataLength(userInput))) {
            System.out.print("Please provide a deadline (format mm-dd): ");
            userInput = reader.readString();
            if (reader.checkIfDateHaveCorrectFormat(userInput)) {
                reader.checkDataLength(userInput);

            }
        }

        return Year.now().getValue() + "-" + userInput;
    }

    private static void handleStatusOfShownToDo(Reader reader, TodoMatrix todoMatrix) {
        String option = reader.readString();
        String status;
        String message;
        if (option.equals("1")) {
            status = "IU";
            message = getCorrectMessage(status, todoMatrix);
            System.out.println(message);

        } else if (option.equals("2")) {
            status = "IN";
            message = getCorrectMessage(status, todoMatrix);
            System.out.println(message);

        } else if (option.equals("3")) {
            status = "NU";
            message = getCorrectMessage(status, todoMatrix);
            System.out.println(message);

        } else if (option.equals("4")) {
            status = "NN";
            message = getCorrectMessage(status, todoMatrix);
            System.out.println(message);
        } else {
            System.out.println("No option available!");
        }
    }

    private static String getCorrectMessage(String status, TodoMatrix todoMatrix) {
        return todoMatrix.getQuarter(status).getItems().size() == 0
                ? "Matrix is empty!" : todoMatrix.getQuarter(status).toString();
    }





}
