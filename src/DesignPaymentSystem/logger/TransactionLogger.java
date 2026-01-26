package DesignPaymentSystem.logger;

import DesignPaymentSystem.logger.channel.ConsoleLogger;
import DesignPaymentSystem.logger.channel.LoggingChannel;
import DesignPaymentSystem.transaction.Transaction;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TransactionLogger {
    List<LoggingChannel> channels;
    static TransactionLogger instance;

    private TransactionLogger() {
        channels = new ArrayList<>();
        channels.add(new ConsoleLogger());
    }

    public static TransactionLogger getInstance() {
        if (instance == null) {
            synchronized (TransactionLogger.class) {
                if (instance == null) {
                    instance = new TransactionLogger();
                }
            }
        }
        return instance;
    }

    public void log(Transaction transaction, String message) {
        channels.forEach(channel -> channel.log(transaction, message));
    }
}
