package io.bryantcason;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryantcason on 5/22/16.
 */
public class LedgerSpec {

    @Test
    public void logTransactionTest() {
        Transaction logTransaction = new Transaction();
        Ledger newLedger = new Ledger();
        logTransaction.setTransactionType("Transfer");
        logTransaction.setSourceAccountNumber("123");
        logTransaction.setDestinationAccountNumber("321");
        logTransaction.setTransactionDate("02/02/2013");
        newLedger.logTransactions();
        assertNotNull("Should not return null", newLedger);
    }
    @Test
    public void ledgerTest(){
        Account sourceAccount = new Account("Checking", "546", "Jet Li", 5);
        Ledger ledger = new Ledger();
        Bank bank = new Bank();
        int before = ledger.getLedger().size();
        bank.deposit(500, sourceAccount);
        int after = ledger.getLedger().size();
        assertEquals("Ledger should go up one,", before, after);
    }
}
