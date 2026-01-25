package DesignSplitwise.expense;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
public class ExpenseManager {
    Map<Integer, Expense> expenseMap;

    public void addExpense() {

    }

    public Expense getExpense(int id) {
        return expenseMap.get(id);
    }
}
