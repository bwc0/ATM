package io.bryantcason;
import java.util.ArrayList;
import static io.bryantcason.Prompt.*;


public class Bank {

    static ArrayList<Account> allAccounts;
    Ledger ledger;
    Transaction transaction;

    public Bank() {

        this.allAccounts = new ArrayList<Account>();
        ledger = new Ledger();
        transaction = new Transaction();
    }

    public void approved() {
        System.out.println("Approved");
    }

    public void denied() {
        System.out.println("Declined");
    }

    public void withdrawal(double amount, Account sourceAccount) {
        if (sourceAccount.getAccountStatus().equals("open") && sourceAccount.getBalance() > 0){
            sourceAccount.removeDebit(amount);
            approved();
            Transaction withdrawalTransaction = ledger.createTransaction(amount, "Withdrawal", sourceAccount.getAccountNumber());
            ledger.addTransaction(withdrawalTransaction);
        } else {
            denied();
        }
    }

    public void deposit(double amount, Account sourceAccount) {
        if (sourceAccount.getAccountStatus().equals("open")) {
            sourceAccount.addMoney(amount);
            approved();
            Transaction depositTransaction = ledger.createTransaction(amount, "Deposit", sourceAccount.getAccountNumber());
            ledger.addTransaction(depositTransaction);
        } else {
            denied();
        }
    }

    public void closeAccount(Account sourceAccount){
        if(sourceAccount.getBalance() == 0){
            giveMessage("Your account has been closed");
            sourceAccount.setAccountStatus("Closed");
            approved();
        } else{
            giveMessage("Your balance on your account must be 0 to close your account");
            denied();
        }
    }

    public void transfer(Account sourceAccount, Account destinationAccount, double amount) {
        withdrawal(amount, sourceAccount);
        deposit(amount, destinationAccount);
    }

    public Account createAccount() {
        String accountName = askForString("Enter your full name: ");
        String accountType = askForString("Enter the type of account your are creating(lower case): checking, savings, investment");
        String accountNumber = askForString("Enter your desired account number: ");
        double pin = askForInt("Enter a password: (Numbers only!)");
        Account newAccount = (new Account(accountType, accountNumber, accountName, pin));
        allAccounts.add(newAccount);
        return newAccount;
    }

    public Account selectAccount(String number) {
        for (Account account : allAccounts) {
            if (account.getAccountNumber().equals(number)) {
                return account;
            }
        }
         return createAccount();
    }
}
