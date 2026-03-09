package DesignAtm.account;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class Card {
    String cardNo;
    String userName;
    String accountId;
    String pin;
}
