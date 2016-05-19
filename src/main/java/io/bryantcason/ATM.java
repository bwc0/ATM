package io.bryantcason;


import static io.bryantcason.Bank.allAccounts;
import static io.bryantcason.Prompt.*;


public class ATM {

    public void engine() {
        Account currentAccount = null;
        Bank bank = new Bank();
        int usersInt;
        String userString;
        boolean atmRunning  = true;

        while(atmRunning){
            welcomeUser();

            int userChoice = askForInt("Select an option: \n 1. Create Account \n 2. Withdrawal \n 3. Deposit \n 4. Transfer \n 5. Check Balance \n 6. Ledger \n 7. Select Account \n 8. Exit ");

            switch(userChoice){
                case 1:
                    currentAccount = bank.createAccount();
                    break;
                case 2:
                    bank.withdrawal(askForDouble("Enter amount to withdrawal"), currentAccount);
                    break;
                case 3:
                    bank.deposit(askForDouble("Enter amount to deposit"), currentAccount);
                    break;
                case 5:
                    giveMessage("Your balance is " + currentAccount.getBalance());
                    break;
                case 6:
                    bank.ledger.logTransactions();
                    break;
                case 4:
                    usersInt = askForInt("What is the index of your account?");
                    bank.transfer(currentAccount, allAccounts.get(usersInt), askForDouble("Enter amount to transfer: "));
                    break;
                case 7:
                    bank.findAccount(askForString("Select Account: "));
                    break;
                case 8:
                    giveMessage("Exit");
                    atmRunning = false;

            }
        }

    }


    public void welcomeUser(){
        giveMessage("Welcome to the ATM!");
    }



}
