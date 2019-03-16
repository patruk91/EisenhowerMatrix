import java.util.Arrays;

public class UserAPI {
    public static void start(String[] args) {
        TodoMatrix todoMatrix = new TodoMatrix();
        Reader reader = new Reader();
        Table table = new Table(todoMatrix);
//        System.out.println(Arrays.toString(table.getColumnsWidth()));

        String option = Handler.handleFileOption(reader, todoMatrix);
        System.out.println(todoMatrix); //toString method
//        System.out.println(Arrays.toString(table.getColumnsWidth()));
        table.HigherPartOfTable(todoMatrix);
        table.LowerPartOfTable(todoMatrix);

        while (!option.equals("q")) {
            Displayer.displayMethods();
            option = Handler.handleMethodOptions(reader, todoMatrix);
        }
    }
}
