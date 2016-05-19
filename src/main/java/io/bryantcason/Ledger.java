package io.bryantcason;

import java.util.ArrayList;


public class Ledger {



    private ArrayList<Transaction> ledger = new ArrayList<Transaction>();

    public ArrayList<Transaction> getLedger() {
        return ledger;
    }

    public void addTransaction(Transaction transaction){

        ledger.add(transaction);
    }

    public Transaction createTransctions(double amount, String transactionType, String sourceAccount, String destinationAccount){
        return (new Transaction(amount,transactionType, sourceAccount, destinationAccount));
    }

    public Transaction createTransaction(double amount, String transactionType, String sourceAccount){
        return (new Transaction(amount, transactionType, sourceAccount));
    }

    public void logTransactions(){
        for(Transaction transaction : ledger){
            System.out.println(transaction.getAmount() + "\n " + transaction.getUniqueFinancialTransNum() + "\n " +
                transaction.getFinancialTransNum() + "\n " + transaction.getAmount() + "\n " + transaction.getSourceAccountNumber() +
                    "\n " + transaction.getDestinationAccountNumber() + "\n " + transaction.getTransactionDate() + "\n "
                    + transaction.getTransactionType());
        }
    }
}

