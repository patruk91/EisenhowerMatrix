import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public class Availabilities {
    private TodoMatrix todoMatrix;

    public Availabilities(TodoMatrix todoMatrix) {
        this.todoMatrix = todoMatrix;
    }

    public void shownStatusOfMatrix(Reader reader) {
        String option = reader.readString();
        String status;
        String message;
        if (option.equals("1")) {
            status = "IU";
            message = getCorrectMessage(status);
            System.out.println(message);

        } else if (option.equals("2")) {
            status = "IN";
            message = getCorrectMessage(status);
            System.out.println(message);

        } else if (option.equals("3")) {
            status = "NU";
            message = getCorrectMessage(status);
            System.out.println(message);

        } else if (option.equals("4")) {
            status = "NN";
            message = getCorrectMessage(status);
            System.out.println(message);
        } else {
            System.out.println("No option available!");
        }
    }

    private String getCorrectMessage(String status) {
        return this.todoMatrix.getQuarter(status).getItems().size() == 0
                ? "Matrix is empty!" : this.todoMatrix.getQuarter(status).toString();
    }

    public void addItemToMatrix(Reader reader) {
        String title = isTitleCorrect(reader);

        String date = isDateCorrect(reader);
        LocalDate deadline = LocalDate.parse(date);

        System.out.print("Is this important (y/n)?: ");
        String checkIfImportant = reader.readString();

        boolean isImportant = checkIfImportant.equals("y");
        this.todoMatrix.addItem(title, deadline, isImportant);
        System.out.println("Item added!");
    }

    private String isTitleCorrect(Reader reader) {
        String title = "";
        while (title.isEmpty()) {
            System.out.print("Please provide a title: ");
            title = reader.readString();
        }
        return title;
    }

    private String isDateCorrect(Reader reader) {
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

    public String markOrUnmarkOrRemoveItem(Reader reader, String option) {
        System.out.println("Please provide a title: ");
        String title = reader.readString();
        String[] itemStatus = {"IU", "IN", "NU", "NN"};
        for (int i = 0; i < itemStatus.length; i++) {
            TodoQuarter itemQuarter = this.todoMatrix.getQuarter(itemStatus[i]);
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

    public String archiveItems() {
        this.todoMatrix.archiveItems();
        return "Items archived!";
    }

    public String saveToFile(Reader reader) {
        System.out.print("File name: ");
        String fileName = reader.readString();
        try {
            this.todoMatrix.saveItemsToFile(fileName);
        } catch (IOException error) {
            System.out.println("ERROR: " + error.getMessage());
            System.out.println("ERROR: We cannot save file.");
        }
        return "File saved!";
    }








}
