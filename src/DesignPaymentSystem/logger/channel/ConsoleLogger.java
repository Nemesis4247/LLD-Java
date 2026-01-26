package DesignPaymentSystem.logger.channel;

import DesignPaymentSystem.transaction.Transaction;

public class ConsoleLogger implements LoggingChannel {
    @Override
    public void log(Transaction transaction, String message) {
        System.out.println("[TranId: " + transaction.getTransactionId() + "] " + message);
    }
}
