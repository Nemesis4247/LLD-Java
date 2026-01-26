package DesignPaymentSystem.fraudDetection;

import DesignPaymentSystem.paymentDto.PaymentRequest;

public interface FraudDetector {
    FraudResult evaluate(PaymentRequest paymentRequest);
}
