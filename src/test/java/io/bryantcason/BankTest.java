package io.bryantcason;

import io.bryantcason.account.*;
import io.bryantcason.user.User;
import io.bryantcason.utils.Prompt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by bryantcason on 5/19/16.
 */
class BankTest {
    private Account sourceAccount;
    private Account destinationAccount;
    private Bank bank;
    private User user;

    private Prompt prompt;

    @BeforeEach
    void start(){
         prompt = mock(Prompt.class);

         user = new User("UserName12", "password12", "Userer", "Nameme");
         bank = new Bank(prompt);
         bank.getUsers().add(user);
         sourceAccount = new Checking(3230, 1000, true);
         destinationAccount = new Savings(3333, 0, true);
    }

    @Test
    @DisplayName("Should withdrawal amount from account")
    void withdrawalSuccessfulTest() {
        when(prompt.askForDouble(anyString())).thenReturn(500.0);
        bank.withdrawal(sourceAccount);

        double actualValue = sourceAccount.getBalance();
        assertEquals(500, actualValue);
    }

    @Test
    @DisplayName("Should not withdrawal amount with Overdraft enabled from account")
    void withdrawalFailedTestOverdraftEnabledTest() {
        when(prompt.askForDouble(anyString())).thenReturn(1500.0);
        bank.withdrawal(sourceAccount);

        double actualValue = sourceAccount.getBalance();
        assertEquals(1000, actualValue);
    }

    @Test
    @DisplayName("Should withdrawal amount with Overdraft disabled from account")
    void withdrawalFailedTestOverdraftDisabledTest() {
        when(prompt.askForDouble(anyString())).thenReturn(1500.0);
        sourceAccount.setOverDraftProtection(false);
        bank.withdrawal(sourceAccount);

        double actualValue = sourceAccount.getBalance();
        assertEquals(-515, actualValue);
    }

    @Test
    @DisplayName("Should not withdrawal amount from closed account")
    void withdrawalFailedTestAccountClosedTest() {
        when(prompt.askForDouble(anyString())).thenReturn(750.0);
        sourceAccount.setStatus(AccountStatus.CLOSED);
        bank.withdrawal(sourceAccount);

        double actualValue = sourceAccount.getBalance();
        assertEquals(1000, actualValue);
    }

    @Test
    @DisplayName("Should deposit amount in the account")
    void depositSuccessfulTest(){
        when(prompt.askForDouble(anyString())).thenReturn(1000.0);
        bank.deposit(sourceAccount);

        double actualValue = sourceAccount.getBalance();
        assertEquals(2000, actualValue);
    }

    @Test
    @DisplayName("Should not deposit amount in closed account")
    void depositFailureAccountClosedTest(){
        when(prompt.askForDouble(anyString())).thenReturn(1000.0);

        sourceAccount.setStatus(AccountStatus.CLOSED);
        bank.deposit(sourceAccount);

        double actualValue = sourceAccount.getBalance();
        assertEquals(1000, actualValue);
    }

    @Test
    @DisplayName("Should transfer amount from source to destination")
    void transferSuccessfulTest(){
        when(prompt.askForDouble(anyString())).thenReturn(500.0);
        bank.transfer(sourceAccount, destinationAccount);

        assertEquals(sourceAccount.getBalance(), 500);
        assertEquals(destinationAccount.getBalance(), 500);
    }

    @Test
    @DisplayName("Should not transfer amount from source to destination overdraft enabled")
    void transferFailureOverdraftEnabledTest(){
        when(prompt.askForDouble(anyString())).thenReturn(1500.0);
        bank.transfer(sourceAccount, destinationAccount);

        assertEquals(sourceAccount.getBalance(), 1000);
        assertEquals(destinationAccount.getBalance(), 0);
    }

    @Test
    @DisplayName("Should not transfer amount from source to destination overdraft disabled")
    void transferFailureOverdraftDisabledTest(){
        when(prompt.askForDouble(anyString())).thenReturn(1500.0);
        sourceAccount.setOverDraftProtection(false);
        bank.transfer(sourceAccount, destinationAccount);

        assertEquals(sourceAccount.getBalance(), -515);
        assertEquals(destinationAccount.getBalance(), 1500.0);
    }

    @Test
    @DisplayName("Should create new user and add to Bank user arraylist")
    void createUserTest() {
        User actualValue = bank.createUser("UserName", "password", "User", "Name");
        assertNotNull(actualValue);
        assertEquals("UserName", actualValue.getUsername());
        assertEquals("password", actualValue.getPassword());
        assertEquals("User", actualValue.getFirstName());
        assertEquals("Name", actualValue.getLastName());
    }

    @Test
    @DisplayName("Should select user and return user")
    void selectUserSuccessfulTest() {
        User actualValue = bank.selectUser(user);

        assertNotNull(actualValue);
        assertEquals("UserName12", actualValue.getUsername());
        assertEquals("password12", actualValue.getPassword());
        assertEquals("Userer", actualValue.getFirstName());
        assertEquals("Nameme", actualValue.getLastName());
    }

    @Test
    @DisplayName("Should not select unknown user")
    void selectUserFailureTest() {
        User actualValue = bank.selectUser(new User("UnknownUser", "Password",
                "Unknown", "User"));
        assertNull(actualValue);
    }

    @Test
    @DisplayName("Should create account, overdraft enabled by yes and return account")
    void createAccountOverdraftEnabledByYesTest() {
        Account actualValue = bank
                .createAccount(user, 1, 3333, 2500, "yes");
        assertEquals(user, actualValue.getUser());
        assertEquals(AccountType.Checking, actualValue.getType());
        assertEquals(3333, actualValue.getPin());
        assertEquals(2500, actualValue.getBalance());
        assertTrue(actualValue.isOverDraftProtection());
    }

    @Test
    @DisplayName("Should create account, overdraft enabled by y and return account")
    void createAccountEnabledbyYTest() {
        Account actualValue = bank
                .createAccount(user, 1, 3333, 2500, "y");
        assertEquals(user, actualValue.getUser());
        assertEquals(AccountType.Checking, actualValue.getType());
        assertEquals(3333, actualValue.getPin());
        assertEquals(2500, actualValue.getBalance());
        assertTrue(actualValue.isOverDraftProtection());
    }

    @Test
    @DisplayName("Should create account, overdraft disabled and return account")
    void createAccountDisabledTest() {
        Account actualValue = bank
                .createAccount(user, 1, 3333, 2500, "no");
        assertEquals(user, actualValue.getUser());
        assertEquals(AccountType.Checking, actualValue.getType());
        assertEquals(3333, actualValue.getPin());
        assertEquals(2500, actualValue.getBalance());
        assertFalse(actualValue.isOverDraftProtection());
    }

    @Test
    @DisplayName("Should select and return account")
    void selectAccountSuccessTest() {
        user.addAccount(sourceAccount);
        Account actualValue = bank.selectAccount(user, sourceAccount.getPin());

        assertEquals(user, actualValue.getUser());
        assertEquals(sourceAccount.getType(), actualValue.getType());
        assertEquals(sourceAccount.getPin(), actualValue.getPin());
        assertEquals(sourceAccount.getBalance(), actualValue.getBalance());
        assertTrue(actualValue.isOverDraftProtection());
    }

    @Test
    @DisplayName("Should select and return account")
    void selectAccountFailedTest() {
        User actualValue = bank.selectUser(new User());
        assertNull(actualValue);
    }
}


