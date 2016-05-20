package io.bryantcason;


public class Account {
    private String accountType, accountNumber, accountStatus, accountName, overDraftPrevention;
    private double balance, interestRate, pin;

    public Ledger ledger;
    public Transaction transaction;

    public Account(String accountType, String accountNumber, String accountName, double pin) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        accountStatus = "open";
        this.pin = pin;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountStatus() {
        return this.accountStatus;
    }

    public Ledger getLedger() {
        return ledger;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public double getBalance() {
        return balance;
    }

    public String getOverDraftPrevention() {
        return overDraftPrevention;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getPIN(){return this.pin;}

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
        }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setOverDraftPrevention(String overDraftPrevention) {
        this.overDraftPrevention = overDraftPrevention;
    }

    public void setPin(double pin){this.pin = pin;}

    public void removeDebit(double amountRemove){
        balance= balance - amountRemove;

    }

    public void addMoney(double amountCredited){
        balance = balance + amountCredited;
    }

}

