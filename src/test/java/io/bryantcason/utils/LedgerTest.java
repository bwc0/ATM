package io.bryantcason.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LedgerTest {

    Ledger ledger;

    @BeforeEach
    void setUp() {
        ledger = new Ledger();
    }

    @Test
    @DisplayName("Create transaction with source and destination account")
    void createTransactionWithSourceAndDestinationAccountTest() {
        Transaction actualValue = ledger.createTransaction(1000, TransactionType.TRANSFER,
                UUID.randomUUID(), UUID.randomUUID());

        assertEquals(1000, actualValue.getAmount());
        assertEquals(TransactionType.TRANSFER, actualValue.getTransactionType());
        assertNotNull(actualValue.getSourceAccountNumber());
        assertNotNull(actualValue.getDestinationAccountNumber());
    }

    @Test
    @DisplayName("Create transaction with source account")
    void createTransactionWithSourceAccountTest() {
        Transaction actualValue = ledger.createTransaction(1000, TransactionType.DEPOSIT,
                UUID.randomUUID());

        assertEquals(1000, actualValue.getAmount());
        assertEquals(TransactionType.DEPOSIT, actualValue.getTransactionType());
        assertNotNull(actualValue.getSourceAccountNumber());
    }
}