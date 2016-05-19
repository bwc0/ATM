package io.bryantcason;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bryantcason on 5/18/16.
 */
public class AccountSpec {

    Account account;
    @Test
    public void removeDebitTest(){
        account = new Account("Checking", "123", "Jet Li");
        account.setBalance(1000);
        account.setAccountStatus("open");
        account.removeDebit(500);
        double expectedValue = 500;
        double actualValue = account.getBalance();
        assertEquals("Should debit sourceAccount $500", expectedValue, actualValue, .0);
    }

    @Test
    public void addMoneyTest(){
        account = new Account("Checking", "321", "Jet Li");
        account.setBalance(1000);
        account.setAccountStatus("open");
        account.addMoney(500);
        double expectValue = 1500;
        double actualValue = account.getBalance();
        assertEquals("Should credit sourceAccount $500", expectValue, actualValue, .0);
    }
}
