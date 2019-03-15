import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class UserAPI {
    public static void start(String[] args) {
        TodoMatrix todoMatrix = new TodoMatrix();
        Scanner reader = new Scanner(System.in);

        String option = Handler.handleFileOption(reader, todoMatrix);

        System.out.println(todoMatrix); //toString method
        while (!option.equals("q")) {
            Displayer.displayMethods();
            option = Handler.handleMethodOptions(reader, todoMatrix);
        }

//        System.out.println(todoMatrix.addItem();); //toString method

//        System.out.println(todoMatrix); //toString method

    }
}
