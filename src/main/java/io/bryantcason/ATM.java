package io.bryantcason;


import io.bryantcason.account.Account;
import io.bryantcason.user.User;
import io.bryantcason.utils.Prompt;

import java.util.InputMismatchException;

import static io.bryantcason.utils.Prompt.*;


public class ATM {

    private static Prompt prompt = new Prompt();
    private static User user;
    private static Bank bank = new Bank(prompt);
    private static Account account;

    public void engine() {

        int choice = 0;
        boolean running = true;

        while (running) {

            choice = getChoiceForUserNotSignedIn(choice);

            while(choice != 0 && running) {

                switch (choice) {
                    case 1:
                        newUser();
                        break;
                    case 2:
                        existingUser();
                        break;
                }

                while (choice != -1 && bank.getUsers().size() != 0) {
                    choice = getChoiceForSignedInUser(choice);

                    switch (choice) {
                        case 1:
                            newAccount();
                            break;

                        case 2:
                            loginAccount();
                            break;

                        case 3:
                            if (checkIfAnAccountExist(account)) break;

                            getAccountInformation(account);
                            break;

                        case 4:
                            if (checkIfAnAccountExist(account)) break;

                            giveMessage("Account Number: " + account.getAccountNum() +
                                "\nBalance: " + account.getBalance());
                            break;

                        case 5:
                            if (checkIfAnAccountExist(account)) break;
                            bank.withdrawal(account);
                            break;

                        case 6:
                            if (checkIfAnAccountExist(account)) break;
                            bank.deposit(account);
                            break;

                        case 7:
                            transfer();
                            break;

                        case 8:
                            listAccounts();
                            break;

                        case 9:
                            closeAccount();
                            break;

                        case 86:
                            choice = -1;
                            user = null;
                            account = null;
                            break;
                    }
                }
                break;
            }

        }
    }

    private static int getChoiceForUserNotSignedIn(int choice) {
        try {
            choice = prompt.askForInt("1. New User\n" +
                    "2. Existing User\n" +
                    "Enter number: ");
        }
        catch (InputMismatchException ime) {
            getExceptionMessage(ime.getMessage());
        }
        return choice;
    }

    private static int getChoiceForSignedInUser(int choice) {
        try
        {
            choice = prompt.askForInt("\n1. Create Account" +
                    "\n2. Login to Account" +
                    "\n3. Current Account Information" +
                    "\n4. Balance " +
                    "\n5. Withdrawal" +
                    "\n6. Deposit" +
                    "\n7. Transfer" +
                    "\n8. List Accounts" +
                    "\n9. Close Account" +
                    "\n10. Profile" +
                    "\n86. Sign-out" +
                    "\nEnter number: ");
        }
        catch (InputMismatchException ime)
        {
            getExceptionMessage(ime.getMessage());
        }

        return choice;
    }

    private static void newUser() {
        String username = prompt.askForString("Enter username: ");
        String password = prompt.askForString("Enter password: ");
        String firstName = prompt.askForString("Enter first name: ");
        String lastName = prompt.askForString("Enter last name: ");

        user = bank.createUser(username, password, firstName, lastName);
        giveMessage("Welcome, " + username + "\n");
    }

    private static void existingUser() {
        String username = prompt.askForString("Enter username: ");
        String password = prompt.askForString("Enter password: ");

        user = bank.selectUser(new User(username, password));
        giveMessage("Welcome " + user.getUsername() + "\n");
    }

    private static void newAccount() {
        try {
            int accountType = prompt.askForInt("\nSelect an account type: \n" +
                    "1. Checking\n" +
                    "2. Savings\n" +
                    "3. Investment");
            int pin = prompt.askForInt("Enter pin for account: ");
            int balance = prompt.askForInt("Enter a starting balance: ");
            String overdraft = prompt.askForString("Would you like overdraft protection: y/n ");

            account = bank.createAccount(user, accountType, pin, balance, overdraft);

            giveMessage("Account created");
        } catch (NumberFormatException nfe) {
            getExceptionMessage(nfe.getMessage());
        }
    }

    private static void loginAccount() {
        try {
            int pin = prompt.askForInt("\nEnter pin: ");
            account = bank.selectAccount(user, pin);

            if  (checkIfAnAccountExist(account)) {
                giveMessage("Please try again.");
            } else {
                giveMessage("Current Account:");
                getAccountInformation(account);
            }
        } catch (NumberFormatException nfe) {
            getExceptionMessage(nfe.getMessage());
        }
    }

    private static void transfer() {
        try {
            listAccounts();

            int pin = prompt.askForInt("\nWhich account are you transferring from (enter pin): ");
            Account sourceAccount = bank.selectAccount(user, pin);
            pin = prompt.askForInt("Which account are you transferring to (enter pin): ");
            Account destinationAccount = bank.selectAccount(user, pin);

            if  (checkIfAnAccountExist(sourceAccount) && checkIfAnAccountExist(destinationAccount)) {
                giveMessage("Please try again. Something went wrong");
            } else {
                bank.transfer(sourceAccount, destinationAccount);
            }

        } catch(NumberFormatException nfe) {
            getExceptionMessage(nfe.getMessage());
        }
    }

    private static void listAccounts() {
        for (Account temp : user.getAccounts()) {
            getAccountInformation(temp);
        }
    }

    private static void closeAccount() {
        try {
            int pin = prompt.askForInt("\nEnter pin for account to close: ");
            bank.closeAccount(bank.selectAccount(user, pin));
        } catch (NumberFormatException nfe) {
            getExceptionMessage(nfe.getMessage());
        }
    }

    private static void getAccountInformation(Account temp) {
        giveMessage("\nType: " + temp.getType() +
                "\nAccount Number: " + temp.getAccountNum() +
                "\nStatus: " + temp.getStatus() +
                "\nBalance " + temp.getBalance() +
                "\nOverdraft Protection: " + temp.isOverDraftProtection());
    }

    private static boolean checkIfAnAccountExist(Account account) {
        if (account == null) {
            giveMessage("You must create or login to an account");
            return true;
        }
        return false;
    }

    private static void getExceptionMessage(String message) {
        Prompt.giveMessage("Must enter a number: " + message);
    }
}
