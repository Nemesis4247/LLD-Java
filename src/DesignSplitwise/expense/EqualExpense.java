package DesignSplitwise.expense;

import DesignSplitwise.Balance;
import DesignSplitwise.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EqualExpense extends Expense {

    public EqualExpense(int id, User paidBy, List<User> users, String currency, int totalAmount, Optional<Integer> groupIdOptional) {
        super(id, ExpenseType.EQUAL, paidBy, users, currency, totalAmount, groupIdOptional);
    }

    @Override
    public Map<User, Balance> getSplits() {
        Map<User, Balance> res = new HashMap<>();
        this.getUsers().forEach(user -> {
            if (!user.equals(getPaidBy())) {
                res.put(user,
                        new Balance(getCurrency(), -1 * getTotalAmount() / (getUsers().size()-1)));
            }
        });
        return res;
    }
}
