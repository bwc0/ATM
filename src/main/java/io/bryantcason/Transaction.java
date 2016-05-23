package io.bryantcason;

import java.util.Date;

public class Transaction {
    private static int financialTransNum;
    private int uniqueFinancialTransNum;
    private double amount;
    private String transactionType;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private String transactionDate;

    public int getUniqueFinancialTransNum() {
        return uniqueFinancialTransNum;
    }

    public int getFinancialTransNum() {
        return financialTransNum;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionType(String transactionType){
        this.transactionType = transactionType;
    }

    public void setSourceAccountNumber(String sourceAccountNumber){
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber){
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public void setTransactionDate(String transactionDate){
        this.transactionDate = transactionDate;
    }

    public Transaction(){}

    public Transaction(double amount, String transactionType, String sourceAccount, String destinationAccount) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.sourceAccountNumber = sourceAccount;
        this.destinationAccountNumber = destinationAccount;
        this.uniqueFinancialTransNum = financialTransNum + 1;
        financialTransNum++;
        transactionDate = new Date().toString();
    }

    public Transaction(double amount, String transactionType, String sourceAccount) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.sourceAccountNumber = sourceAccount;
        this.destinationAccountNumber = "cash";
        this.uniqueFinancialTransNum = financialTransNum + 1;
        financialTransNum++;
        transactionDate = new Date().toString();
    }


}


