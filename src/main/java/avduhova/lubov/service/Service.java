package avduhova.lubov.service;

import avduhova.lubov.dto.Budget;
import avduhova.lubov.dto.Finances;
import avduhova.lubov.dto.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Service {
    private Finances finances;

//    public boolean createCategory(String category) {
//        return user.getFinances().putIfAbsent(category, factory.add(category, new Income())) != null;
//    }

//
//    public void addIncome(User user, String category, Income income) {
//
//    }

    public boolean addFinanceIncome(User user, String category, double sum, String description) {
        // Создаем новый объект Income (сумма, дата и описание)
        finances = new Finances(sum, description);

        return addFinance(Budget.INCOME, user, category);

    }


    public boolean addFinanceExpenses(User user, String category, double sum, String description) {
        // Создаем новый объект Income (сумма, дата и описание)
        finances = new Finances(sum, description);

        return addFinance(Budget.EXPENSE, user, category);
    }

    private boolean addFinance(Budget budget, User user, String category) {
        // Получаем или создаем карту для данного бюджета
        Map<String, List<Finances>> budgetMap = user.getBudget().computeIfAbsent(budget, k -> new HashMap<>());

        // Получаем или создаем список для конкретной категории
        List<Finances> financesList = budgetMap.computeIfAbsent(category, k -> new ArrayList<>());

        // Добавляем новую запись в список
        financesList.add(finances);

        // Проверяем, существует ли категория в бюджете
        if (!budgetMap.containsKey(category)) {
            budgetMap.put(category, financesList);
        }

        // Обновляем карту в основном бюджете
        user.getBudget().put(budget, budgetMap);
        return true;
    }

    public void setCategoryLimit(User user, String category, double limit) {
        user.getCategoryLimits().put(category, limit);
    }
}
