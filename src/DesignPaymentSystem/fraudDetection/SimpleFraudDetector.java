package DesignPaymentSystem.fraudDetection;

import DesignPaymentSystem.paymentDto.PaymentRequest;

public class SimpleFraudDetector implements FraudDetector {
    @Override
    public FraudResult evaluate(PaymentRequest paymentRequest) {
        FraudResult result = new FraudResult();
        if (paymentRequest.getAmount() > 10000) {
            result.setFlagged(true);
            result.setRiskScore(0.9);
            result.setReason("High-value transaction");
        }
        // Add more rules: velocity checks, blacklisted cards, geo mismatch
        return result;
    }
}
