package DesignPaymentSystem.transaction;

import DesignPaymentSystem.paymentProcessor.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    int transactionId;
    double amount;
    int merchantId;
    Status status;
    PaymentMethod paymentMethod;
}
