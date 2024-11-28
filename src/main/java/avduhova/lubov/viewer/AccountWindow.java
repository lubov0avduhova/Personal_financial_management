package avduhova.lubov.viewer;

import avduhova.lubov.dto.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static avduhova.lubov.Initialization.scanner;
import static avduhova.lubov.controller.AccountWindowController.ACCOUNT_WINDOW_CONTROLLER_ACTIONS;

public class AccountWindow {
    public static final List<String> ACCOUNT_WINDOWS_ACTIONS = new LinkedList<>();

    public void showAccountWindow() {
        System.out.println("Вы вошли в аккаунт");
        boolean logout = false;
        do {

            ACCOUNT_WINDOWS_ACTIONS.forEach(System.out::println);
            scanner.nextLine();
            int choice = scanner.nextInt();
            if (choice == 0) {
                logout = true;
            } else {

                ACCOUNT_WINDOW_CONTROLLER_ACTIONS.entrySet().stream().filter(entry -> Integer.parseInt(entry.getKey()) == choice)
                        .findFirst().ifPresentOrElse(category -> category.getValue().run(), () -> {
                            System.out.println("Нет такой категории");
                        });
            }

        } while (!logout);

    }

}
