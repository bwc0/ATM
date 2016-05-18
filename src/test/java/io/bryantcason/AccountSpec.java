package io.bryantcason;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bryantcason on 5/18/16.
 */
public class AccountSpec {

    @Test
    public void withdrawalTest() {
        Account account = new Account("Checking", "123", "Jet Li");
        account.setBalance(1000);
        account.setAccountStatus("open");
        double expectedValue= 600;
        double actualValue = account.withdrawal(400);
        assertEquals("Balance should be 600", expectedValue, actualValue, .0);
    }

    @Test
    public void depositTest(){
        Account account = new Account("Savings", "432", "Jet Li");
        account.setBalance(1000);
        account.setAccountStatus("open");
        double expectedValue = 1400;
        double actualValue = account.deposit(400);
        assertEquals("Balance should be 1400", expectedValue, actualValue, .0);
    }

    @Test
    public void transferTest(){
        Account account = new Account("Checking", "987", "Jet Li");
        Account destinationAccount = new Account("Checking", "098", "Jet Li");
        account.setBalance(1000);
        destinationAccount.setBalance(2000);
        account.transfer(destinationAccount, 300);
        assertEquals(account.getBalance(), 700, .0);
        assertEquals(destinationAccount.getBalance(), 2300, .01);

    }
}
