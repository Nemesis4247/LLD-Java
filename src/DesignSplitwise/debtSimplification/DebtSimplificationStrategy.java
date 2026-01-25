package DesignSplitwise.debtSimplification;

import DesignSplitwise.Balance;
import DesignSplitwise.user.User;

import java.util.Map;

public interface DebtSimplificationStrategy {
    Map<User, Map<User, Balance>> simplifyDebts(Map<User, Map<User, Balance>> balanceSheet);
}
