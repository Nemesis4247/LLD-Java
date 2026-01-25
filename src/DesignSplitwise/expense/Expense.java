package DesignSplitwise.expense;

import DesignSplitwise.Balance;
import DesignSplitwise.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
public abstract class Expense {
    int id;
    ExpenseType expenseType;
    User paidBy;
    List<User> users;
    String currency;
    int totalAmount;
    Optional<Integer> groupIdOptional;

    public Expense(int id, ExpenseType expenseType, User paidBy, List<User> members, String currency, int amount) {
        this(id, expenseType, paidBy, members, currency, amount, Optional.empty());
    }

    public abstract Map<User, Balance> getSplits();
}
