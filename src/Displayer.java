public class Displayer {

    public static void displayMenu() {
        System.out.println("What you want to do?");
        System.out.println("1. Start new Eisenhower matrix\n"
                            + "2. Read existing Eisenhower matrix\n"
                            + "Press 'q' to exit");
        System.out.print("Option: ");
    }

    public static void showStatus() {
        System.out.println("Please pick option for show status: ");
        System.out.println("1. Important items & urgent\n"
                + "2. Important items & not urgent\n"
                + "3. Not important items & urgent\n"
                + "4. Not important items & not urgent");
        System.out.print("Option: ");
    }

    public static void displayMethods() {
        System.out.println("Please choose option to edit your field: ");
        System.out.println("1. Show status\n"
                            + "2. Add item\n"
                            + "2. Mark item\n"
                            + "3. Unmark item\n"
                            + "4. Remove marked item\n"
                            + "5. Archive items - remove all done items\n"
                            + "6. Save to file\n"
                            + "7. Archive items -> save to file -> quit from application\n"
                            + "8. Show whole Eisenhower matrix\n"
                            + "Press 'q' to exit");
        System.out.print("Option: ");
    }
}
