package DesignPaymentSystem.paymentProcessor;

import DesignPaymentSystem.paymentDto.PaymentRequest;
import DesignPaymentSystem.paymentDto.PaymentResponse;
import DesignPaymentSystem.transaction.Status;

public class UPIPaymentProcessor implements IPaymentProcessor {
    @Override
    public PaymentResponse process(PaymentRequest paymentRequest) {
        // Call External Gateway
        return new PaymentResponse(Status.FAILED, paymentRequest.getTransactionId(), null);
    }
}
