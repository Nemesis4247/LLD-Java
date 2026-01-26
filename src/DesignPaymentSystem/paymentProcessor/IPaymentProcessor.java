package DesignPaymentSystem.paymentProcessor;

import DesignPaymentSystem.paymentDto.PaymentRequest;
import DesignPaymentSystem.paymentDto.PaymentResponse;

public interface IPaymentProcessor {
    PaymentResponse process(PaymentRequest paymentRequest);
}
