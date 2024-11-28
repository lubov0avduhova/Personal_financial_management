package avduhova.lubov.viewer;

import avduhova.lubov.controller.AccountWindowController;
import avduhova.lubov.controller.MainWindowController;

import java.util.LinkedList;
import java.util.List;

import static avduhova.lubov.Initialization.scanner;

public class MainWindow {
    public static List<String> MAIN_WINDOW_ACTIONS = new LinkedList<>();
    private final MainWindowController controller = new MainWindowController();

    public void showMainWindow() {
        boolean exit = false;

        while (!exit) {

            MAIN_WINDOW_ACTIONS.forEach(System.out::println);

            int input = scanner.nextInt();
            if (input == 0) {
                controller.logOut(AccountWindowController.USER);
                exit = true;
            } else if (input == 1) {
                controller.logIn();
            }

        }
    }
}
