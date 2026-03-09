package DesignAtm;

import DesignAtm.account.Account;
import DesignAtm.account.Card;
import DesignAtm.bank.BankService;
import DesignAtm.bank.BankServiceImpl;
import DesignAtm.state.AtmState;
import DesignAtm.state.ReadyAtmState;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public class AtmSystem{
    BankService bankService;
    AtmState currentState;
    @Setter
    @Getter
    String currentCardNo;

    public AtmSystem(BankService bankService) {
        this.bankService = bankService;
        currentState = new ReadyAtmState(this);
    }

    public void setState(AtmState state) {
        this.currentState = state;
    }


    public boolean insertCard(String cardNo) {
        return this.currentState.insertCard(cardNo);
    }

    public boolean enterPIN(String pin) {
        return this.currentState.enterPIN(pin);
    }

    public int checkBalance() {
        return this.currentState.checkBalance();
    }

    public boolean withdrawCash(int cash) {
        return this.currentState.withdrawCash(cash);
    }

    public boolean depositCash(int cash) {
        return this.currentState.depositCash(cash);
    }

    public boolean ejectCard() {
        return this.currentState.ejectCard();
    }

    public String validateCard(String cardNo) {
        return this.bankService.validateCard(cardNo);
    }

    public boolean validatePIN(String pin, String card) {
        return this.bankService.validatePIN(pin, card);
    }

    public int checkBalance(String cardNo) {
        return this.bankService.checkBalance(cardNo);
    }

    public boolean withdrawCash(int cash, String cardNo) {
        return this.bankService.withdrawCash(cash, cardNo);
    }

    public boolean depositCash(int cash, String cardNo) {
        return this.bankService.depositCash(cash, cardNo);
    }

    public static void main(String[] args) {
        BankService bankService = BankServiceImpl.getInstance();
        bankService.addAccount(new Account("account007",
                Arrays.asList(new Card("card007", "ishan", "account007", "1234")),
                "ishan", 0));
        AtmSystem atmSystem = new AtmSystem(bankService);
        atmSystem.insertCard("card007");
        atmSystem.enterPIN("1234");
        atmSystem.depositCash(100);
        atmSystem.ejectCard();
    }
}
