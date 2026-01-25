package DesignSplitwise;

import DesignSplitwise.debtSimplification.DebtSimplificationStrategy;
import DesignSplitwise.expense.Expense;
import DesignSplitwise.expense.ExpenseType;
import DesignSplitwise.group.Group;
import DesignSplitwise.user.User;

import java.util.List;
import java.util.Map;

public interface ISplitwise {
    User createUser(String id, String name);

    Group createGroup(int groupId, List<User> members);

    Expense createGroupExpense(int expenseId, ExpenseType expenseType,
                                      User paidBy, List<User> members,
                                      String currency, int amount, Group group);

    void simplifyGroupBalances(Group group, DebtSimplificationStrategy debtSimplificationStrategy);

    Map<User, Balance> getUserNetBalances(User user);

    Expense createDirectExpense(int expenseId, ExpenseType expenseType,
                                      User paidBy, List<User> members,
                                      String currency, int amount);

    boolean deleteExpense(Expense expense);
}
