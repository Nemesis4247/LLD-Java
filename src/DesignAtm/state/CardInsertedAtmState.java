package DesignAtm.state;

import DesignAtm.AtmSystem;

public class CardInsertedAtmState implements AtmState {

    AtmSystem atmSystem;

    public CardInsertedAtmState(AtmSystem atmSystem) {
        this.atmSystem = atmSystem;
    }


    @Override
    public boolean insertCard(String cardNo) {
        System.out.println("Card already inserted");
        return false;
    }

    @Override
    public boolean enterPIN(String pin) {
        boolean res = this.atmSystem.validatePIN(pin, this.atmSystem.getCurrentCardNo());
        if (res) {
            System.out.println("PIN validated, User authenticated");
            atmSystem.setState(new AutheticatedAtmState(atmSystem));
        } else {
            System.out.println("PIN invalid, please retry");
        }
        return res;
    }

    @Override
    public int checkBalance() {
        System.out.println("Enter PIN first");
        return 0;
    }

    @Override
    public boolean withdrawCash(int cash) {
        System.out.println("Enter PIN first");
        return false;
    }

    @Override
    public boolean depositCash(int cash) {
        System.out.println("Enter PIN first");
        return false;
    }

    @Override
    public boolean ejectCard() {
        this.atmSystem.setCurrentCardNo(null);
        System.out.println("Card ejected");
        return true;
    }
}
