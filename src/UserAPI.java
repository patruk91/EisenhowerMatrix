public class UserAPI {
    public static void start(String[] args) {
        TodoMatrix todoMatrix = new TodoMatrix();
        Reader reader = new Reader();

        String option = Handler.handleFileOption(reader, todoMatrix);
        System.out.println(todoMatrix); //toString method
        while (!option.equals("q")) {
            Displayer.displayMethods();
            option = Handler.handleMethodOptions(reader, todoMatrix);
        }
    }
}
