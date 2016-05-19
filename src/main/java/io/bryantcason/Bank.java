package io.bryantcason;

import java.util.ArrayList;

/**
 * Created by bryantcason on 5/19/16.
 */
public class Bank {

    static ArrayList<Account> allAccounts;
    Ledger ledger;
    Transaction transaction;

    public Bank(){

        this.allAccounts= new ArrayList<Account>();
        ledger = new Ledger();
        transaction = new Transaction();
    }

    public void approved(){
        System.out.println("Approved");
    }

    public void denied(){
        System.out.println("Declined");
    }

    public void withdrawal(double amount, Account sourceAccount) {
        if(sourceAccount.getAccountStatus().equals("open")){
            sourceAccount.removeDebit(amount);
            approved();
            Transaction withdrawalTransaction = ledger.createTransaction(amount, "Withdrawal", sourceAccount.getAccountNumber());
            ledger.addTransaction(withdrawalTransaction);
        }else {
            denied();
        }
    }

    public void deposit(double amount, Account sourceAccount) {
        if(sourceAccount.getAccountStatus().equals("open")) {
            sourceAccount.addMoney(amount);
            approved();
            Transaction depositTransaction = ledger.createTransaction(amount, "Deposit", sourceAccount.getAccountNumber());
            ledger.addTransaction(depositTransaction);
        }else{
            denied();
        }
    }

    public void transfer(Account sourceAccount, Account destinationAccount, double amount) {
        withdrawal(amount, sourceAccount);
        deposit(amount, destinationAccount);
    }

}
