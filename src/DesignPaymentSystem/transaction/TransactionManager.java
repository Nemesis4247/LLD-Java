package DesignPaymentSystem.transaction;

import DesignPaymentSystem.logger.TransactionLogger;
import DesignPaymentSystem.paymentDto.PaymentRequest;
import DesignPaymentSystem.paymentDto.PaymentResponse;
import DesignPaymentSystem.paymentProcessor.IPaymentProcessor;
import DesignPaymentSystem.paymentProcessor.PaymentMethod;
import DesignPaymentSystem.paymentProcessor.PaymentProcessorFactory;

public class TransactionManager {
    TransactionRepository transactionRepository;
    PaymentProcessorFactory paymentProcessorFactory;
    TransactionLogger LOGGER;
    static TransactionManager instance;

    private TransactionManager() {
        transactionRepository = new TransactionRepository();
        paymentProcessorFactory = new PaymentProcessorFactory();
        LOGGER = TransactionLogger.getInstance();
    }

    public static TransactionManager getInstance() {
        if (instance == null) {
            synchronized (TransactionManager.class) {
                if (instance == null) {
                    instance = new TransactionManager();
                }
            }
        }
        return instance;
    }

    public PaymentResponse handlePayment(PaymentRequest paymentRequest) {
        if (transactionRepository.isProcessed(paymentRequest.getTransactionId())) {
            LOGGER.log(transactionRepository.getTransaction(
                    paymentRequest.getTransactionId()), "Transaction already processed, returning prior response");
            return transactionRepository.getPaymentResponse(paymentRequest.getTransactionId());
        }
        IPaymentProcessor paymentProcessor = paymentProcessorFactory
                .getPaymentProcessor(paymentRequest.getPaymentMethod());
        LOGGER.log(transactionRepository.getTransaction(
                paymentRequest.getTransactionId()), "Initiating transaction processing");
        PaymentResponse paymentResponse = paymentProcessor.process(paymentRequest);
        LOGGER.log(transactionRepository.getTransaction(
                paymentRequest.getTransactionId()), "Transaction processing completed");
        transactionRepository.save(paymentResponse);
        return paymentResponse;
    }

    public Transaction createTransaction(double amount, int merchantId, PaymentMethod paymentMethod) {
        int tranId = (int) (System.currentTimeMillis() % 1000);
        Transaction transaction = new Transaction(tranId, amount, merchantId, Status.PENDING, paymentMethod);
        transactionRepository.save(transaction);
        LOGGER.log(transaction, "Transaction created");
        return transaction;
    }
}
