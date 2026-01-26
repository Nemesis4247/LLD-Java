package DesignPaymentSystem.paymentProcessor;

public class PaymentProcessorFactory {
    public IPaymentProcessor getPaymentProcessor(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case CREDIT_CARD -> new CreditCardPaymentProcessor();
            case UPI -> new UPIPaymentProcessor();
            default -> throw new UnsupportedOperationException("Method not supported");
        };
    }
}
