package io.bryantcason;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryantcason on 5/19/16.
 */
public class BankSpec {
    Account sourceAccount;
    Ledger ledger;
    Bank bank;

    @Before
    public void start(){
        ledger = new Ledger();
         bank = new Bank();
    }

    @Test
    public void withdrawalTest() {
        sourceAccount = new Account("Checking", "564", "Jet Li");
        sourceAccount.setBalance(1000);
        bank.withdrawal(500, sourceAccount);
        double expectedValue = 500;
        double actualValue = sourceAccount.getBalance();
        assertEquals("Should withdraw 500 from sourceAccount", expectedValue, actualValue, .0);
    }

    @Test
    public void depositTest(){
        sourceAccount = new Account("Checking", "543", "Jet Li");
        sourceAccount.setBalance(1000);
        bank.deposit(500, sourceAccount);
        double expectedValue = 1500;
        double actualValue = sourceAccount.getBalance();
        assertEquals("Should deposit 500 to sourceAccount", expectedValue, actualValue, .0);
    }

    @Test
    public void transferTest(){
        sourceAccount = new Account("Checking", "543", "Jet Li");
        Account destinationAccount = new Account("Checking", "432", "Jet Li");
        sourceAccount.setBalance(1000);
        destinationAccount.setBalance(1000);
        bank.transfer(sourceAccount, destinationAccount, 500);
        assertEquals(sourceAccount.getBalance(), 500, .0);
        assertEquals(destinationAccount.getBalance(), 1500, .0);

    }

    @Test
    public void ledgerTest(){
        sourceAccount = new Account("Checking", "546", "Jet Li");
        int before = ledger.getLedger().size();
        bank.deposit(500, sourceAccount);
        int after = ledger.getLedger().size();
        assertEquals("Ledger should go up one,", before, after);
    }
}
