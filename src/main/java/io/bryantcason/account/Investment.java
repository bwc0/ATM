package io.bryantcason.account;

import io.bryantcason.account.Account;

/**
 * Created by bryantcason on 5/19/16.
 */
public class Investment extends Account {

    public Investment(int pin) {
        super(AccountType.Investment, pin);
    }
}
