package DesignAtm.bank;

import DesignAtm.account.Account;
import DesignAtm.account.Card;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankServiceImpl implements BankService {
    Map<String, Account> accounts;
    Map<String, Card> cards;
    private static final BankServiceImpl instance = new BankServiceImpl();

    private BankServiceImpl() {
        accounts = new ConcurrentHashMap<>();
        cards = new ConcurrentHashMap<>();
    }

    public static BankService getInstance() {
        return instance;
    }

    @Override
    public String validateCard(String cardNo) {
        if (cards.containsKey(cardNo)) {
            return cards.get(cardNo).getCardNo();
        }
        return null;
    }

    @Override
    public boolean validatePIN(String pin, String cardNo) {
        if (cards.containsKey(cardNo)) {
            return cards.get(cardNo).getPin().equals(pin);
        }
        return false;
    }

    @Override
    public int checkBalance(String cardNo) {
        String accountId = cards.get(cardNo).getAccountId();
        return accounts.get(accountId).getBalance();
    }

    @Override
    public boolean withdrawCash(int cash, String cardNo) {
        String accountId = cards.get(cardNo).getAccountId();
        Account account = accounts.get(accountId);
        synchronized (account) {
            return account.withdrawCash(cash);
        }
    }

    @Override
    public boolean depositCash(int cash, String cardNo) {
        String accountId = cards.get(cardNo).getAccountId();
        Account account = accounts.get(accountId);
        synchronized (account) {
            return account.depositCash(cash);
        }
    }

    @Override
    public boolean addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
        for (Card card : account.getCards()) {
            cards.putIfAbsent(card.getCardNo(), card);
        }
        return true;
    }

    @Override
    public boolean addCard(Account account, Card card) {
        return false;
    }
}
