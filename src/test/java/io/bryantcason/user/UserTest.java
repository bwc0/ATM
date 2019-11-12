package io.bryantcason.user;

import io.bryantcason.account.Account;
import io.bryantcason.account.AccountStatus;
import io.bryantcason.account.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Account account;
    private String username = "Username";
    private String password = "password";
    private String firstName = "User";
    private String lastName = "Name";
    private double balance = 1000;
    private int accountPin = 52353;
    private boolean overDraftProtection = true;

    @BeforeEach
    public void setUp() {
        user = new User(username, password, firstName, lastName);
        account = new Account(AccountType.Checking, accountPin, balance, overDraftProtection);
    }

    @Test
    @DisplayName("Add account to user test should return User with 1 account")
    void addAccount_UserShouldHaveOneAccountTest() {
        User actualValue = user.addAccount(account);
        assertEquals(1, actualValue.getAccounts().size());
        assertEquals(account.getUser().getUsername(), user.getUsername());
    }

    @Test
    @DisplayName("Create Checking account")
    void openAccountShouldCreateCheckingAccountByDefaultTest() {
        Account actualValue = user.openAccount(1, 5000, 3231, true);
        assertNotNull(actualValue);
        assertEquals(5000, actualValue.getBalance());
        assertEquals(3231, actualValue.getPin());
        assertEquals(AccountType.Checking, actualValue.getType());
        assertTrue(actualValue.isOverDraftProtection());
    }

    @Test
    @DisplayName("Create Savings account")
    void openAccountShouldCreateSavingsAccountTest() {
        Account actualValue = user.openAccount(2, 5000, 3231, true);
        assertNotNull(actualValue);
        assertEquals(5000, actualValue.getBalance());
        assertEquals(3231, actualValue.getPin());
        assertEquals(AccountType.Savings, actualValue.getType());
        assertTrue(actualValue.isOverDraftProtection());
    }

    @Test
    @DisplayName("Create Savings account")
    void openAccountShouldCreateInvestmentAccountTest() {
        Account actualValue = user.openAccount(3, 5000, 3231, true);
        assertNotNull(actualValue);
        assertEquals(0, actualValue.getBalance());
        assertEquals(3231, actualValue.getPin());
        assertEquals(AccountType.Investment, actualValue.getType());
    }

    @Test
    @DisplayName("Close account Success test")
    void closeAccountSuccessTest() {
        account.setBalance(0);
        boolean actualValue = user.closeAccount(account);
        assertEquals(AccountStatus.CLOSED, account.getStatus());
        assertTrue(actualValue);
    }

    @Test
    @DisplayName("Close account failed test")
    void closeAccountFailedTest() {
        boolean actualValue = user.closeAccount(account);
        assertFalse(actualValue);
    }
}