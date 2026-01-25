package DesignSplitwise.expense;

import DesignSplitwise.user.User;

import java.util.List;
import java.util.Optional;

public class ExpenseFactory {
    public Expense createExpense(int expenseId, ExpenseType expenseType,
                                 User paidBy, List<User> members,
                                 String currency, int amount, Optional<Integer> groupIdOptional) {
        if (ExpenseType.EQUAL.equals(expenseType)) {
            return new EqualExpense(expenseId, paidBy, members, currency, amount, groupIdOptional);
        } else {
            throw new RuntimeException("Not Supported");
        }
    }
}
