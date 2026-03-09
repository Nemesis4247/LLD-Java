package DesignAtm.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Account {
    String accountId;
    List<Card> cards;
    String userId;
    int balance;

    public boolean depositCash(int cash) {
        if (cash < 0) return false;
        this.balance += cash;
        return true;
    }

    public boolean withdrawCash(int cash) {
        if (cash < this.balance) return false;
        this.balance -= cash;
        return true;
    }
}
