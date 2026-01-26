package DesignPaymentSystem.paymentDto;

import DesignPaymentSystem.paymentProcessor.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class PaymentRequest {
    int transactionId;
    PaymentMethod paymentMethod;
    double amount;
    Map<String, String> paymentDetails;
}
