package DesignPaymentSystem.logger.channel;

import DesignPaymentSystem.transaction.Transaction;

public interface LoggingChannel {
    void log(Transaction transaction, String message);
}
