package io.bryantcason.utils;

import java.util.ArrayList;
import java.util.UUID;


public class Ledger {

    private ArrayList<Transaction> ledger = new ArrayList<>();

    public ArrayList<Transaction> getLedger() {
        return ledger;
    }

    public void addTransaction(Transaction transaction) {

        ledger.add(transaction);
    }

    public Transaction createTransaction(double amount, TransactionType transactionType, UUID sourceAccountNum, UUID destinationAccountNum) {
        return new Transaction(amount, transactionType, sourceAccountNum, destinationAccountNum);
    }

    public Transaction createTransaction(double amount, TransactionType transactionType, UUID sourceAccountNum) {
        return new Transaction(amount, transactionType, sourceAccountNum);
    }

    public void logTransactions() {
        for (Transaction transaction : ledger) {
            System.out.println("Transaction Type: " + transaction.getTransactionType() + "\n" + "Amount: " + transaction.getAmount() + "\n"
                    + "UFTN: " + transaction.getUniqueFinancialTransNum() + "\n" + "FTN: " + transaction.getFinancialTransNum() + "\n" +
                    "Destination Account Number: " + transaction.getDestinationAccountNumber() + "\n" + "Source Account Number: " +
                    transaction.getSourceAccountNumber() + "\n" + "Date: " + transaction.getTransactionDate() + "\n");

        }
    }
}


