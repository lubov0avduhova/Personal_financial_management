package avduhova.lubov.controller;

import avduhova.lubov.dto.Budget;
import avduhova.lubov.dto.User;
import avduhova.lubov.service.Service;
import avduhova.lubov.viewer.Util;
import avduhova.lubov.viewer.View;
import lombok.Setter;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import static avduhova.lubov.Initialization.scanner;


public class AccountWindowController {
    private final Service service = new Service();
    private final View view = new View();
    public static User USER = new User();


    public static Map<String, Runnable> ACCOUNT_WINDOW_CONTROLLER_ACTIONS = new HashMap<>();

    private void addBudget(Budget budget) {
        try {

            System.out.println(Util.chooseCategory);
            String category = scanner.next();

            System.out.println(Util.chooseAmount);
            double amount = scanner.nextInt();

            System.out.println(Util.chooseDescription);
            String description = scanner.next();

            boolean result = false;
            if (category != null && amount != 0 && description != null) {

                if (Budget.INCOME.equals(budget)) {
                    service.addFinanceIncome(USER, category, amount, description);
                } else {
                    service.addFinanceExpenses(USER, category, amount, description);
                }
            }
        } catch (InputMismatchException e) {
            view.showErrorMessage();
        }
    }


    private void showAllCategories() {
        view.showAllCategory(USER);
    }

    private void showByCategory() {
        try {
            System.out.println(Util.chooseCategory);
            String category = scanner.next();
            view.showByCategory(USER, category);
        } catch (InputMismatchException e) {
            view.showErrorMessage();
        }
    }

    private void showTotalAmountOfIncome() {
        view.showTotalAmountOfIncome(USER);
    }

    private void setLimit() {
        try {
            System.out.println(Util.chooseCategory);
            String category = scanner.next();

            System.out.println(Util.chooseLimit);
            double limit = scanner.nextDouble();

            service.setCategoryLimit(USER, category, limit);
        } catch (InputMismatchException e) {
            view.showErrorMessage();
        }
    }


    public void initActions() {
        ACCOUNT_WINDOW_CONTROLLER_ACTIONS.put("1", this::showTotalAmountOfIncome);
        ACCOUNT_WINDOW_CONTROLLER_ACTIONS.put("2", this::showByCategory);
        ACCOUNT_WINDOW_CONTROLLER_ACTIONS.put("3", this::showAllCategories);
        ACCOUNT_WINDOW_CONTROLLER_ACTIONS.put("4", () -> addBudget(Budget.INCOME));
        ACCOUNT_WINDOW_CONTROLLER_ACTIONS.put("5", () -> addBudget(Budget.EXPENSE));
        ACCOUNT_WINDOW_CONTROLLER_ACTIONS.put("6", this::setLimit);
    }
}
