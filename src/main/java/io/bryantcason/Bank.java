package io.bryantcason;
import io.bryantcason.account.Account;
import io.bryantcason.account.AccountStatus;
import io.bryantcason.user.User;
import io.bryantcason.utils.Ledger;
import io.bryantcason.utils.Prompt;
import io.bryantcason.utils.Transaction;
import io.bryantcason.utils.TransactionType;

import java.util.ArrayList;
import java.util.List;

import static io.bryantcason.utils.Prompt.*;


public class Bank {

    private List<User> users;
    private Ledger ledger;
    private Prompt prompt;

    public Bank(Prompt prompt) {
       users = new ArrayList<>();
       ledger = new Ledger();
       this.prompt = prompt;
    }

    public void approved() {
        System.out.println("Approved");
    }

    public void denied() {
        System.out.println("Declined");
    }

    public void withdrawal(Account sourceAccount) {
        double withdrawalAmount = prompt.askForDouble("Enter amount to withdrawal: ");

        if (!sourceAccount.debit(withdrawalAmount)) {
            denied();
        } else {
            approved();
            Transaction withdrawalTransaction = addTransactionToLedger(sourceAccount, withdrawalAmount,
                    TransactionType.WITHDRAWAL);
            receipt(withdrawalTransaction, sourceAccount);
        }
    }

    public void deposit(Account sourceAccount) {
        double depositAmount = prompt.askForDouble("Enter amount to deposit: ");

        if (!sourceAccount.credit(depositAmount)) {
            denied();
        } else {
            approved();
            Transaction depositTransaction = addTransactionToLedger(sourceAccount, depositAmount, TransactionType.DEPOSIT);
            receipt(depositTransaction, sourceAccount);
        }
    }

    public void transfer(Account sourceAccount, Account destinationAccount) {
        double transferAmount = prompt.askForDouble("Enter amount to transfer: ");

        if (!sourceAccount.transfer(sourceAccount, destinationAccount, transferAmount)) {
            denied();
        } else {
            approved();
            Transaction transferTransaction = ledger.createTransaction(transferAmount, TransactionType.TRANSFER,
                    sourceAccount.getAccountNum(), destinationAccount.getAccountNum());
            ledger.addTransaction(transferTransaction);
            receipt(transferTransaction, sourceAccount, destinationAccount);
        }
    }

    public void closeAccount(Account sourceAccount){
        if(sourceAccount.getBalance() == 0) {
            giveMessage("Your account has been closed");
            sourceAccount.setStatus(AccountStatus.CLOSED);
            approved();
        } else{
            giveMessage("Your balance on your account must be open and balance must be 0 to close.");
            denied();
        }
    }

    public Account createAccount(User user, int accountTypeSelection,
                                 int pin, double balance, String overdraftSelection) {

        return user.openAccount(accountTypeSelection, balance, pin,
                overdraftSelection.equals("y") || overdraftSelection.equals("yes"));
    }

    public Account selectAccount(User user, int pin) {

        for (Account account : user.getAccounts()) {
            if (account.getPin() == pin) {
                return account;
            } else {
                giveMessage("Account pin is incorrect.");
            }
        }
         return null;
    }

     public User createUser(String username, String password, String firstName, String lastName) {
        User user = new User(username,password, firstName, lastName);
        users.add(user);
        return user;
    }

    public User selectUser(User temp) {

        for (User user : users) {
            if (user.getUsername().equals(temp.getUsername())
                    && user.getPassword().equals(temp.getPassword())) {
                return user;
            }
        }

        return null;
    }

    private Transaction addTransactionToLedger(Account sourceAccount, double amount, TransactionType type) {
        Transaction depositTransaction = ledger.createTransaction(amount, type, sourceAccount.getAccountNum());
        ledger.addTransaction(depositTransaction);
        return depositTransaction;
    }

    private void receipt(Transaction withdrawalTransaction, Account source) {
        giveMessage("Transaction:\n Amount:" + withdrawalTransaction.getAmount() + "\n Type: "
                + withdrawalTransaction.getTransactionType() + "\n Transaction Number: " +
                withdrawalTransaction.getFinancialTransNum() + "\n Source a/n: " +
                withdrawalTransaction.getSourceAccountNumber() + "\n Balance: " + source.getBalance());
    }

    private void receipt(Transaction withdrawalTransaction, Account source, Account destination) {
        giveMessage("Transaction:\n Amount:" + withdrawalTransaction.getAmount() + "\n Type: "
                + withdrawalTransaction.getTransactionType() + "\n Transaction Number: " +
                withdrawalTransaction.getFinancialTransNum() + "\n Source a/n: " +
                withdrawalTransaction.getSourceAccountNumber() + "\n Destination a/n: " +
                withdrawalTransaction.getDestinationAccountNumber() + "\n Source Account Balance: " +
                source.getBalance() + "\n Destination Account Balance: " + destination.getBalance());
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }

    public Prompt getPrompt() {
        return prompt;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }
}
