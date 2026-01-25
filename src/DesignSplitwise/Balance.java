package DesignSplitwise;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Balance {
    String currency;
    int amount;

    public void add(int amount) {
        setAmount(getAmount() + amount);
    }
}
