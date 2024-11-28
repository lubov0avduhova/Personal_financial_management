package avduhova.lubov;

import avduhova.lubov.dto.Budget;
import avduhova.lubov.dto.Finances;
import avduhova.lubov.dto.User;
import avduhova.lubov.viewer.AccountWindow;
import avduhova.lubov.viewer.MainWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static final MainWindow mainWindow = new MainWindow();
    public static void main(String[] args) {
        Initialization.init();

        // mainWindow.showMainWindow();
        mainWindow.showMainWindow();

//        HashMap<String, List<Finances>> budget = new HashMap<>();
//        List<Finances> list = new ArrayList<>();
//        list.add(new Finances(100, "надо!"));
//
//
//        budget.put("дом", list);
//
//        User user = new User();
//        user.getBudget().put(Budget.INCOME, budget);
//        System.out.println(user);
    }
}
