package DesignSplitwise.group;

import DesignSplitwise.Balance;
import DesignSplitwise.debtSimplification.DebtSimplificationStrategy;
import DesignSplitwise.expense.Expense;
import DesignSplitwise.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupManager {
    Map<User, List<Group>> userGroupMap;
    List<Group> allGroups;

    public GroupManager() {
        userGroupMap = new HashMap<>();
        allGroups = new ArrayList<>();
    }

    public List<Group> getUserGroups(User user) {
        return userGroupMap.getOrDefault(user, new ArrayList<>());
    }

    public void addExpenseToGroup(Group group, Expense expense) {
        group.addExpense(expense);
        group.getBalanceSheet().putIfAbsent(expense.getPaidBy(), new HashMap<>());
        expense.getSplits().forEach((user, split) -> {
            group.getBalanceSheet().get(expense.getPaidBy())
                    .computeIfAbsent(user, u -> new Balance(split.getCurrency(), 0)).add(split.getAmount());
            group.getBalanceSheet().computeIfAbsent(user, u -> new HashMap<>())
                    .computeIfAbsent(expense.getPaidBy(), u -> new Balance(split.getCurrency(), 0)).add(split.getAmount());
        });
    }

    public void simplyDebts(Group group, DebtSimplificationStrategy debtSimplificationStrategy) {
        group.setBalanceSheet(debtSimplificationStrategy
                .simplifyDebts(group.getBalanceSheet()));
    }

    public Group createGroup(int id, List<User> members) {
        Group group = new Group(id, members);
        allGroups.add(group);
        members.forEach(user -> userGroupMap.computeIfAbsent(user, u -> new ArrayList<>()).add(group));
        return group;
    }
}
