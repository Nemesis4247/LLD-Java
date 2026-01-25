package DesignSplitwise.user;

import DesignSplitwise.Balance;
import DesignSplitwise.expense.Expense;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserManager {
    Map<String, User> userMap;
    Map<User, Map<User, Balance>> balanceSheet;

    public UserManager() {
        userMap = new HashMap<>();
        balanceSheet = new HashMap<>();
    }


    public User getUser(String userId) {
        return userMap.get(userId);
    }

    public User createUser(String id, String name) {
        User user = new User(id, name);
        userMap.putIfAbsent(id, user);
        return user;
    }

    public Map<User, Balance> getUserBalance(User userId) {
        return balanceSheet.get(userId);
    }

    public void addExpense(Expense expense) {
        balanceSheet.putIfAbsent(expense.getPaidBy(), new HashMap<>());
        expense.getSplits().forEach((user, split) -> {
            getBalanceSheet().get(expense.getPaidBy())
                    .computeIfAbsent(user, u -> new Balance(split.getCurrency(), 0)).add(split.getAmount());
        });
    }

}
