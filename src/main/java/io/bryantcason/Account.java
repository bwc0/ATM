package io.bryantcason;

/**
 * Created by bryantcason on 5/18/16.
 */
public class Account {
    private String accountType, accountNumber, accountStatus, accountName, overDraftPrevention;
    private double balance, interestRate;

    public Account(String accountType, String accountNumber, String accountName) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
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

    public double getBalance() {
        return balance;
    }

    public String getOverDraftPrevention() {
        return overDraftPrevention;
    }

    public double getInterestRate() {
        return interestRate;
    }

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

    public void approved(){
        System.out.println("Approved");
    }

    public void denied(){
        System.out.println("Declined");
    }

    public double withdrawal(double amount) {
        if(accountStatus.equals("open")){
            balance = balance - amount;
            approved();
        }else{
            denied();
        }
        return balance;
    }

    public double deposit(double amount) {
        if(accountStatus.equals("open")) {
            balance = balance + amount;
            approved();
        }else{
            denied();
        }
        return balance;
    }

    public void transfer(Account destinationAccount, double transferAmount) {
        balance = balance - transferAmount;
        destinationAccount.setBalance(destinationAccount.getBalance() + transferAmount);
    }

}

