package DesignPaymentSystem;

import DesignPaymentSystem.fraudDetection.FraudDetector;
import DesignPaymentSystem.fraudDetection.FraudResult;
import DesignPaymentSystem.fraudDetection.SimpleFraudDetector;
import DesignPaymentSystem.logger.TransactionLogger;
import DesignPaymentSystem.paymentDto.PaymentRequest;
import DesignPaymentSystem.paymentDto.PaymentResponse;
import DesignPaymentSystem.paymentProcessor.PaymentMethod;
import DesignPaymentSystem.transaction.Status;
import DesignPaymentSystem.transaction.Transaction;
import DesignPaymentSystem.transaction.TransactionManager;

import java.util.Collections;

public class PaymentService {
    TransactionManager transactionManager;
    TransactionLogger LOGGER;
    FraudDetector fraudDetector;
    static PaymentService instance;

    private PaymentService() {
        transactionManager = TransactionManager.getInstance();
        LOGGER = TransactionLogger.getInstance();
        fraudDetector = new SimpleFraudDetector();
    }

    public static PaymentService getInstance() {
        if (instance == null) {
            synchronized (PaymentService.class) {
                if (instance == null) {
                    instance = new PaymentService();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        PaymentService paymentService = PaymentService.getInstance();
        PaymentResponse paymentResponse1 = paymentService.makePayment(100.0, 100, PaymentMethod.CREDIT_CARD);
        assert paymentResponse1.getPaymentStatus().equals(Status.COMPLETED);
        PaymentResponse paymentResponse2 = paymentService.makePayment(10001.0, 101, PaymentMethod.CREDIT_CARD);
        assert paymentResponse2.getPaymentStatus().equals(Status.REJECTED);
        PaymentResponse paymentResponse3 = paymentService.makePayment(101.0, 101, PaymentMethod.UPI);
        assert paymentResponse3.getPaymentStatus().equals(Status.FAILED);
    }

    public PaymentResponse makePayment(double amount, int merchantId, PaymentMethod paymentMethod) {
        Transaction transaction = transactionManager.createTransaction(amount, merchantId, paymentMethod);
        LOGGER.log(transaction, "Creating payment request");
        PaymentRequest paymentRequest = new PaymentRequest(transaction.getTransactionId(), paymentMethod, amount, Collections.emptyMap());
        LOGGER.log(transaction, "Payment request created");
        LOGGER.log(transaction, "Running Fraud Checks");
        FraudResult fraudResult = fraudDetector.evaluate(paymentRequest);
        if (fraudResult.isFlagged()) {
            LOGGER.log(transaction, "Flagged! aborting transaction");
            return new PaymentResponse(Status.REJECTED, transaction.getTransactionId(), null);
        }
        LOGGER.log(transaction, "Passed Fraud Checks, submitting Payment request");
        PaymentResponse paymentResponse = transactionManager.handlePayment(paymentRequest);
        if (paymentResponse.getPaymentStatus().equals(Status.COMPLETED)) {
            LOGGER.log(transaction, "transaction completed successfully");
        } else {
            LOGGER.log(transaction, "transaction failed, something went wrong");
        }
        return paymentResponse;
    }
}
