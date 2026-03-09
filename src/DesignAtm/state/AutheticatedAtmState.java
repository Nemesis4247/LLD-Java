package DesignAtm.state;

import DesignAtm.AtmSystem;

public class AutheticatedAtmState implements AtmState {
    AtmSystem atmSystem;

    public AutheticatedAtmState(AtmSystem atmSystem) {
        this.atmSystem = atmSystem;
    }

    @Override
    public boolean insertCard(String cardNo) {
        System.out.println("Card already inserted");
        return false;
    }

    @Override
    public boolean enterPIN(String pin) {
        System.out.println("PIN already validated");
        return false;
    }

    @Override
    public int checkBalance() {
        int balance = this.atmSystem.checkBalance(atmSystem.getCurrentCardNo());
        System.out.println("Account balance: " + balance);
        return balance;
    }

    @Override
    public boolean withdrawCash(int cash) {
        boolean res = this.atmSystem.withdrawCash(cash, atmSystem.getCurrentCardNo());
        if (res) {
            System.out.println("Operation successful");
            checkBalance();
        } else {
            System.out.println("Operation failed");
            checkBalance();
        }
        return res;
    }

    @Override
    public boolean depositCash(int cash) {
        boolean res = this.atmSystem.depositCash(cash, atmSystem.getCurrentCardNo());
        if (res) {
            System.out.println("Operation successful");
            checkBalance();
        } else {
            System.out.println("Operation failed");
            checkBalance();
        }
        return res;
    }

    @Override
    public boolean ejectCard() {
        this.atmSystem.setCurrentCardNo(null);
        System.out.println("Card ejected");
        return true;
    }
}
