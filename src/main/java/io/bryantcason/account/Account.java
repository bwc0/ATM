package io.bryantcason.account;


import io.bryantcason.Prompt;
import io.bryantcason.user.User;

import java.util.UUID;

public class Account {

    private AccountType type;
    private UUID accountNum;
    private AccountStatus status;
    private int pin;
    private double balance, interestRate;
    private boolean overDraftProtection;
    private User user;

    public Account(int pin) {
        this.pin = pin;
    }

    public Account(AccountType type, int pin) {
        this.type = type;
        this.pin = pin;
        this.accountNum = UUID.randomUUID();
        this.status = AccountStatus.OPEN;
        this.balance = 0;
    }

    public Account(AccountType type, int pin, double balance, boolean overDraftProtection) {
        this.type = type;
        this.pin = pin;
        this.accountNum = UUID.randomUUID();
        this.status = AccountStatus.OPEN;
        this.balance = balance;
        this.overDraftProtection = overDraftProtection;
    }

    public Account(AccountType type, int pin, double balance, boolean overDraftProtection, User user) {
        this.type = type;
        this.pin = pin;
        this.accountNum = UUID.randomUUID();
        this.status = AccountStatus.OPEN;
        this.balance = balance;
        this.overDraftProtection = overDraftProtection;
        this.user = user;
    }

    public boolean debit(double amount) {
        if (isClosedOrFrozen(this)) return false;

        if (balance < amount) {

            if (overDraftProtection) {
                denied();
                Prompt.giveMessage("Insufficient funds.");

                return false;
            }

            balance = balance - amount - 15;
            Prompt.giveMessage("Account overdraft. You have been charged an additional $15.00. Balance: "
                    + balance);

            approved();
            return true;
        }

        approved();
        balance -= amount;
        return true;
    }

    public boolean credit(double amount) {
        if (isClosedOrFrozen(this)) return false;

        approved();
        balance += amount;
        return true;
    }

    public boolean transfer(Account source, Account destination, double amount) {
        if (source.getUser() != destination.getUser()) {
            denied();
            return false;
        }
        if (isClosedOrFrozen(source)) return false;
        if (isClosedOrFrozen(destination)) return false;

        source.debit(amount);
        destination.credit(amount);
        return true;
    }

    private boolean isClosedOrFrozen(Account source) {
        if (source.status == AccountStatus.CLOSED || source.status == AccountStatus.FROZE) {
            denied();
            Prompt.giveMessage("Sorry! Account: " + source.accountNum + " is " + status);
            return true;
        }

        return false;
    }

    private void approved() {
        Prompt.giveMessage("Approved");
    }

    private void denied() {
        Prompt.giveMessage("Denied");
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public UUID getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(UUID accountNum) {
        this.accountNum = accountNum;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isOverDraftProtection() {
        return overDraftProtection;
    }

    public void setOverDraftProtection(boolean overDraftProtection) {
        this.overDraftProtection = overDraftProtection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

