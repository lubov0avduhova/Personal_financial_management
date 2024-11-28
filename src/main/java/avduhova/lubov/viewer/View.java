package avduhova.lubov.viewer;

import avduhova.lubov.dto.Budget;
import avduhova.lubov.dto.Finances;
import avduhova.lubov.dto.User;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class View {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void showByCategory(User user, String category) {

        for (Map.Entry<Budget, Map<String, List<Finances>>> entry : user.getBudget().entrySet()) {
            Budget budgetType = entry.getKey();
            Map<String, List<Finances>> categoryMap = entry.getValue();

            System.out.println("Бюджет по категории: " + budgetType.getDescription());

            // Проходим по категориям внутри каждого бюджета
            for (Map.Entry<String, List<Finances>> categoryEntry : categoryMap.entrySet()) {
                if (categoryEntry.getKey().equals(category)) {
                    for (Finances fin : categoryEntry.getValue()) {
                        System.out.println("  Сумма: " + fin.getAmount() + ", описание: " + fin.getDescription() +
                                ", дата: " + formatter.format(fin.getDate()));
                    }
                }
            }
        }
    }


    public void showAllCategory(User user) {

        for (Budget budgetType : Budget.values()) {
            System.out.println("Бюджет по категории: " + budgetType.getDescription());
            Map<String, List<Finances>> innerMap = user.getBudget().get(budgetType);
            if (innerMap != null) {
                for (Map.Entry<String, List<Finances>> entry : innerMap.entrySet()) {
                    String category = entry.getKey();
                    List<Finances> financesList = entry.getValue();

                    double totalAmount = financesList.stream()
                            .mapToDouble(Finances::getAmount)
                            .sum();

                    System.out.println("Категория: " + category);
                    System.out.println("  Сумма:" + totalAmount);

                    if (budgetType.equals(Budget.EXPENSE)) {
                        double limit = user.getCategoryLimits().getOrDefault(category, 0.0);

                        // Вычитаем лимит из суммы
                        double result = limit - totalAmount;
                        System.out.println("  Лимит для " + category + ": " + limit);
                        System.out.println("  Оставшаяся сумма после вычитания лимита: " + result);

                    }
                }
            }
        }

    }

    public void showTotalAmountOfIncome(User user) {
        double totalIncome = 0.0;
        double totalExpense = 0.0;

        for (Map.Entry<Budget, Map<String, List<Finances>>> entry : user.getBudget().entrySet()) {
            Budget budgetType = entry.getKey();
            Map<String, List<Finances>> categoryMap = entry.getValue();

            if (budgetType == Budget.INCOME) {
                for (List<Finances> financesList : categoryMap.values()) {
                    totalIncome += financesList.stream().mapToDouble(Finances::getAmount).sum();
                }
                System.out.println("Total Income: " + totalIncome);
            }

            if (budgetType == Budget.EXPENSE) {
                for (List<Finances> financesList : categoryMap.values()) {
                    totalExpense += financesList.stream().mapToDouble(Finances::getAmount).sum();
                }
                System.out.println("Total Expense: " + totalExpense);
            }
        }

        double finalBalance = totalIncome - totalExpense;

        System.out.printf("Общая сумма доходов и расходов: %s", finalBalance);
    }


    public void showErrorMessage() {
        System.out.println("Ой, что-то пошло не так! Полундра!");
    }
}
