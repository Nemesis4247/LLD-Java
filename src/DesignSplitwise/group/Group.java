package DesignSplitwise.group;

import DesignSplitwise.Balance;
import DesignSplitwise.expense.Expense;
import DesignSplitwise.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Group {
    int id;
    List<User> members;
    Map<User, Map<User, Balance>> balanceSheet;
    List<Expense> groupExpenses;

    public Group(int id, List<User> members) {
        this.id = id;
        this.members = members;
        balanceSheet = new HashMap<>();
        groupExpenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        groupExpenses.add(expense);
    }
}
