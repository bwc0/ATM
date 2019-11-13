package io.bryantcason.account;

import io.bryantcason.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;
    private Account account2;
    private User user;

    @BeforeEach
    void setUp() {

        account = new Checking(1234, 1000, true);
        account2 = new Savings(1234, 1000, true);

        user = new User("username", "password", "User", "Name");

        user.addAccount(account);
        user.addAccount(account2);
    }

    @Test
    @DisplayName("Account should be deposited")
    void debitSuccessfulTest() {
        boolean actualValue = account.debit(500);
        assertEquals(account.getBalance(), 500);
        assertTrue(actualValue);
    }

    @Test
    @DisplayName("Account should be deposited with overdraft disabled")
    void debitSuccessful_OverdraftDisabled_Test() {
        account.setOverDraftProtection(false);
        boolean actualValue = account.debit(1500);
        assertEquals(account.getBalance(), -515);
        assertTrue(actualValue);
    }

    @Test
    @DisplayName("Account should not be deposited with overdraft enabled")
    void debitFailed_OverdraftEnabled_Test() {
        boolean actualValue = account.debit(1500);
        assertEquals(account.getBalance(), 1000);
        assertFalse(actualValue);
    }

    @Test
    @DisplayName("Account should not be deposited with account being closed")
    void debitFailedAccountClosedTest() {
        account.setBalance(0);
        account.setStatus(AccountStatus.CLOSED);
        boolean actualValue = account.debit(500);
        assertFalse(actualValue);
    }

    @Test
    @DisplayName("Account should not be deposited with account being frozen")
    void debitFailedAccountFrozeTest() {
        account.setStatus(AccountStatus.FROZE);
        boolean actualValue = account.debit(500);
        assertFalse(actualValue);
    }

    @Test
    @DisplayName("Account should be credited")
    void creditSuccessfulTest() {
        boolean actualValue = account.credit(500);
        assertEquals(account.getBalance(), 1500);
        assertTrue(actualValue);
    }

    @Test
    @DisplayName("Account should not be credited with account being closed")
    void creditFailedAccountClosedTest() {
        account.setBalance(0);
        account.setStatus(AccountStatus.CLOSED);
        boolean actualValue = account.credit(500);
        assertFalse(actualValue);
    }

    @Test
    @DisplayName("Money should be transferred from one user account to the same user's second account")
    void transferSuccessfulTransferTest() {
        boolean actualValue = account.transfer(account, account2, 500);
        assertEquals(account.getBalance(), 500);
        assertEquals(account2.getBalance(), 1500);
        assertEquals(account2.getUser().getUsername(), account.getUser().getUsername());
        assertTrue(actualValue);
    }

    @Test
    @DisplayName("Money not should be transferred from one user account to the same user's second account")
    void transferFailureTransferTest() {
        boolean actualValue = account.transfer(account, account2, 1500);
        assertEquals(account.getBalance(), 1000);
        assertEquals(account2.getBalance(), 1000);
        assertEquals(account2.getUser().getUsername(), account.getUser().getUsername());
        assertFalse(actualValue);
    }

    @Test
    @DisplayName("Money should not be transferred from one user account to the second account")
    void transferFailedTransferTest() {
        account2.setUser(new User("username2", "password2", "User2", "Name2"));
        boolean actualValue = account.transfer(account, account2, 500);
        assertFalse(actualValue);
    }
}