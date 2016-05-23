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

    public Transaction createTransaction(double amount, String transactionType, String sourceAccount, String destinationAccount){
        return (new Transaction(amount,transactionType, sourceAccount, destinationAccount));
    }

    public Transaction createTransaction(double amount, String transactionType, String sourceAccount){
        return (new Transaction(amount, transactionType, sourceAccount));
    }

    public void logTransactions(){
        for(Transaction transaction : ledger){
            System.out.println(" Amount: " + transaction.getAmount() + "\n UFTN: " + transaction.getUniqueFinancialTransNum() + "\n FTN: " +
                transaction.getFinancialTransNum() + "\n Destination Account Number: " + transaction.getDestinationAccountNumber() + "\n Source Account Number: " + transaction.getSourceAccountNumber() +
                    "\n Date: " + transaction.getTransactionDate() + "\n Transaction Type: "
                    + transaction.getTransactionType() + "\n");
        }
    }
}

