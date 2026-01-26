package DesignPaymentSystem.paymentProcessor;

import DesignPaymentSystem.paymentDto.PaymentRequest;
import DesignPaymentSystem.paymentDto.PaymentResponse;
import DesignPaymentSystem.transaction.Status;

public class CreditCardPaymentProcessor implements IPaymentProcessor {
    @Override
    public PaymentResponse process(PaymentRequest paymentRequest) {
        // Call External Gateway
        return new PaymentResponse(Status.COMPLETED, paymentRequest.getTransactionId(),
                String.valueOf(System.currentTimeMillis()));
    }
}
