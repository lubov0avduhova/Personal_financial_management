package avduhova.lubov;

import avduhova.lubov.controller.AccountWindowController;
import avduhova.lubov.dto.User;
import avduhova.lubov.viewer.Util;

import java.util.Arrays;
import java.util.Scanner;

import static avduhova.lubov.controller.AccountWindowController.ACCOUNT_WINDOW_CONTROLLER_ACTIONS;
import static avduhova.lubov.viewer.AccountWindow.ACCOUNT_WINDOWS_ACTIONS;
import static avduhova.lubov.viewer.MainWindow.MAIN_WINDOW_ACTIONS;


public class Initialization {
    public final static Scanner scanner = new Scanner(System.in);
    private static final AccountWindowController ACCOUNT_WINDOW_CONTROLLER = new AccountWindowController();

    public static void init() {
        initMainWindow();
        initAccountWindow();
        ACCOUNT_WINDOW_CONTROLLER.initActions();
    }


    private static void initMainWindow() {
        MAIN_WINDOW_ACTIONS.addAll(Arrays.asList(
                Util.chooseAction,
                Util.accessAccount,
                Util.showExit
        ));
    }

    private static void initAccountWindow() {
        ACCOUNT_WINDOWS_ACTIONS.addAll(Arrays.asList(
                Util.chooseAction,
                Util.showAllBudget,
                Util.showByCategory,
                Util.showCurrentBudget,
                Util.addIncome,
                Util.addExpense,
                Util.setLimit,
                Util.showExit
        ));
    }

}
