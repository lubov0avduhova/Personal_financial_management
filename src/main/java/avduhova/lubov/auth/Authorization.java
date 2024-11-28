package avduhova.lubov.auth;

import avduhova.lubov.dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Authorization {
    private List<User> users  = new ArrayList<>();;

    // Метод для авторизации или регистрации пользователя
    public User authenticateOrRegister(User inputUser) {
        User user = findUserByLogin(inputUser.getLogin());

        if (user != null) {
            // Если логин есть, проверяем пароль
            if (user.getPassword().equals(inputUser.getPassword())) {
                System.out.println("Авторизация успешна");
            } else {
                // Если пароль не совпадает
                System.out.println("Неверный логин или пароль");
            }
        } else {
            System.out.println("\nПользователь не найден в памяти программы. Регистрируем нового пользователя.");

            if (isLoginUnique(inputUser.getLogin())) {
                user = new User(inputUser.getLogin(), inputUser.getPassword());
                users.add(user);
                System.out.println("Новый пользователь зарегистрирован: " + inputUser.getLogin());
            } else {
                System.out.println("Пользователь с таким логином уже существует.");
            }
        }
        return user;
    }

    private User findUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }


    private boolean isLoginUnique(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }
}
