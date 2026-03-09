package DesignAtm.state;

import DesignAtm.AtmSystem;
import DesignAtm.account.Card;

public class ReadyAtmState implements AtmState {
    AtmSystem atmSystem;

    public ReadyAtmState(AtmSystem atmSystem) {
        this.atmSystem = atmSystem;
    }

    @Override
    public boolean insertCard(String cardNo) {
        cardNo = this.atmSystem.validateCard(cardNo);
        if (cardNo == null) {
            System.out.println("Invalid Card, ejecting");
            ejectCard();
            return false;
        }
        this.atmSystem.setCurrentCardNo(cardNo);
        System.out.println("Card Validated, please enter pin");
        this.atmSystem.setState(new CardInsertedAtmState(this.atmSystem));
        return true;
    }

    @Override
    public boolean enterPIN(String pin) {
        System.out.println("Enter Card first");
        return false;
    }

    @Override
    public int checkBalance() {
        System.out.println("Enter Card first");
        return 0;
    }

    @Override
    public boolean withdrawCash(int cash) {
        System.out.println("Enter Card first");
        return false;
    }

    @Override
    public boolean depositCash(int cash) {
        System.out.println("Enter Card first");
        return false;
    }

    @Override
    public boolean ejectCard() {
        System.out.println("Enter Card first");
        return false;
    }
}
