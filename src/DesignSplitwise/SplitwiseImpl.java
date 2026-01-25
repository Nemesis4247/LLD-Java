package DesignSplitwise;

import DesignSplitwise.debtSimplification.DebtSimplificationStrategy;
import DesignSplitwise.expense.Expense;
import DesignSplitwise.expense.ExpenseFactory;
import DesignSplitwise.expense.ExpenseType;
import DesignSplitwise.group.Group;
import DesignSplitwise.group.GroupManager;
import DesignSplitwise.user.User;
import DesignSplitwise.user.UserManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SplitwiseImpl implements ISplitwise {
    UserManager userManager;
    GroupManager groupManager;
    ExpenseFactory expenseFactory;

    public SplitwiseImpl() {
        userManager = new UserManager();
        groupManager = new GroupManager();
        expenseFactory = new ExpenseFactory();
    }

    public User createUser(String id, String name) {
        return userManager.createUser(id, name);
    }

    public Group createGroup(int groupId, List<User> members) {
        return groupManager.createGroup(groupId, members);
    }

    public Expense createGroupExpense(int expenseId, ExpenseType expenseType,
                                      User paidBy, List<User> members,
                                      String currency, int amount, Group group) {
        Expense expense = expenseFactory.createExpense(expenseId, expenseType, paidBy, members, currency, amount, Optional.of(group.getId()));
        groupManager.addExpenseToGroup(group, expense);
        return expense;
    }

    public void simplifyGroupBalances(Group group, DebtSimplificationStrategy debtSimplificationStrategy) {
        groupManager.simplyDebts(group, debtSimplificationStrategy);
    }

    public Map<User, Balance> getUserNetBalances(User user) {
        Map<User, Balance> res = userManager.getBalanceSheet().getOrDefault(user, new HashMap<>());
        for (Group userGroup : groupManager.getUserGroups(user)) {
            mergeBalances(res, userGroup.getBalanceSheet().getOrDefault(user, new HashMap<>()));
        }
        return res;
    }

    void mergeBalances(Map<User, Balance> balance1, Map<User, Balance> balance2) {
        balance1.forEach((u, s) -> {
            balance1.computeIfAbsent(u, k -> new Balance(s.getCurrency(), 0)).add(s.getAmount());
        });
    }

    public Expense createDirectExpense(int expenseId, ExpenseType expenseType,
                                      User paidBy, List<User> members,
                                      String currency, int amount) {
        Expense expense = expenseFactory.createExpense(expenseId, expenseType, paidBy, members, currency, amount, Optional.empty());
        userManager.addExpense(expense);
        return expense;
    }

    @Override
    public boolean deleteExpense(Expense expense) {
        return false;
    }
}
