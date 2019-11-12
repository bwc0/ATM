package io.bryantcason.account;

import io.bryantcason.account.Account;
import io.bryantcason.user.User;

/**
 * Created by bryantcason on 5/19/16.
 */
public class Savings extends Account {

    public Savings(int pin, double balance, boolean overDraftProject) {
        super(AccountType.Savings, pin, balance, overDraftProject);
    }

    public Savings(int pin, double balance, boolean overDraftProject, User user) {
        super(AccountType.Savings, pin, balance, overDraftProject, user);
    }
}
