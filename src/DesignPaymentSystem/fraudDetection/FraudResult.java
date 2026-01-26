package DesignPaymentSystem.fraudDetection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudResult {
    private boolean flagged;
    private double riskScore; // 0.0 to 1.0
    private String reason;
}
