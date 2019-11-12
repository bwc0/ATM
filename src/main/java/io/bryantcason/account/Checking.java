package io.bryantcason.account;

import io.bryantcason.user.User;

/**
 * Created by bryantcason on 5/19/16.
 */
public class Checking extends Account {

    public Checking(int pin, double balance, boolean overDraftProject) {
        super(AccountType.Checking, pin, balance, overDraftProject);
    }

    public Checking(int pin, double balance, boolean overDraftProject, User user) {
        super(AccountType.Checking, pin, balance, overDraftProject, user);
    }
}
