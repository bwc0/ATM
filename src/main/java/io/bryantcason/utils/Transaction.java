package io.bryantcason.utils;

import java.util.Date;
import java.util.UUID;

public class Transaction {
    private UUID financialTransNum;
    private UUID uniqueFinancialTransNum;
    private double amount;
    private TransactionType transactionType;
    private UUID sourceAccountNumber;
    private UUID destinationAccountNumber;
    private String transactionDate;

    public Transaction(){}

    public Transaction(double amount, TransactionType transactionType, UUID sourceAccount, UUID destinationAccount) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.sourceAccountNumber = sourceAccount;
        this.destinationAccountNumber = destinationAccount;
        this.uniqueFinancialTransNum = UUID.randomUUID();
        this.financialTransNum = UUID.randomUUID();
        transactionDate = new Date().toString();
    }

    public Transaction(double amount, TransactionType transactionType, UUID sourceAccount) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.sourceAccountNumber = sourceAccount;
        this.uniqueFinancialTransNum = UUID.randomUUID();
        financialTransNum = UUID.randomUUID();
        transactionDate = new Date().toString();
    }

    public UUID getFinancialTransNum() {
        return financialTransNum;
    }

    public void setFinancialTransNum(UUID financialTransNum) {
        this.financialTransNum = financialTransNum;
    }

    public UUID getUniqueFinancialTransNum() {
        return uniqueFinancialTransNum;
    }

    public void setUniqueFinancialTransNum(UUID uniqueFinancialTransNum) {
        this.uniqueFinancialTransNum = uniqueFinancialTransNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public UUID getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(UUID sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public UUID getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(UUID destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}


