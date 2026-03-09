package DesignAtm.bank;

import DesignAtm.account.Account;
import DesignAtm.account.Card;

public interface BankService {

    String validateCard(String cardNo);

    boolean validatePIN(String pin, String card);

    int checkBalance(String cardNo);

    boolean withdrawCash(int cash, String cardNo);

    boolean depositCash(int cash, String cardNo);

    boolean addAccount(Account account);
    boolean addCard(Account account, Card card);
}
