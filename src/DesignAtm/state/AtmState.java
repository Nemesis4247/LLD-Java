package DesignAtm.state;

import DesignAtm.account.Card;

public interface AtmState {
    boolean insertCard(String cardNo);

    boolean enterPIN(String pin);

    int checkBalance();

    boolean withdrawCash(int cash);

    boolean depositCash(int cash);

    boolean ejectCard();
}
