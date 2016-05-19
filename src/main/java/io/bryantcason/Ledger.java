package io.bryantcason;

import java.util.ArrayList;

/**
 * Created by bryantcason on 5/19/16.
 */
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
        for(int i = 0; i < ledger.size() - 1; i++) {
            System.out.print(ledger.get(i));
        }
    }
}

