public class UserAPI {
    public static void start(String[] args) {
        TodoMatrix todoMatrix = new TodoMatrix();
        Reader reader = new Reader();
        Table table = new Table(todoMatrix);
        String option = Handler.handleFileOption(reader, todoMatrix);
        table.printTable();
        Availabilities availabilities = new Availabilities(todoMatrix);

        while (!option.equals("q")) {
            Displayer.displayMethods();
            option = Handler.handleMethodOptions(reader, todoMatrix, table, availabilities);
        }
    }
}
