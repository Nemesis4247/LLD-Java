package DesignSplitwise.debtSimplification;

import DesignSplitwise.Balance;
import DesignSplitwise.user.User;

import java.util.Map;

public class HeapDebtSimplificationStrategy implements DebtSimplificationStrategy {
    @Override
    public Map<User, Map<User, Balance>> simplifyDebts(Map<User, Map<User, Balance>> balanceSheet) {
        return balanceSheet;
    }
}
