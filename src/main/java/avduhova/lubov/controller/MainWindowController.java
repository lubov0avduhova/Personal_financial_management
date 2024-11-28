package avduhova.lubov.controller;

import avduhova.lubov.auth.Authorization;
import avduhova.lubov.dto.User;
import avduhova.lubov.saver.Saver;
import avduhova.lubov.viewer.AccountWindow;

import java.util.Optional;

import static avduhova.lubov.Initialization.scanner;
import static avduhova.lubov.viewer.Util.inputLogin;
import static avduhova.lubov.viewer.Util.inputPassword;
import static avduhova.lubov.viewer.Util.loggedIn;

public class MainWindowController {

    private final Authorization auth = new Authorization();
    private final Saver saver = new Saver();
    private final AccountWindow accountWindow = new AccountWindow();

    public void logIn() {
        System.out.println(inputLogin);
        String login = scanner.next();
        System.out.println(inputPassword);
        String password = scanner.next();

        System.out.println(loggedIn);
        Optional<User> user = saver.loadFromFile(String.valueOf(login));
        User userAccount = auth.authenticateOrRegister(user.orElse(
                new User(login, password)
        ));


        if (user.isPresent()) {
            AccountWindowController.USER = user.get();
            accountWindow.showAccountWindow();

        } else {
            AccountWindowController.USER = userAccount;
            accountWindow.showAccountWindow();
        }
    }

    public void logOut(User user) {
        System.out.println("я в сохранении");
        saver.saveToFile(user);
    }
}
