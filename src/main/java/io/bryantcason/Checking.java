package io.bryantcason;

/**
 * Created by bryantcason on 5/19/16.
 */
public class Checking extends Account {

    public Checking(String accountName, String accountNumber, String accountType, double pin){
        super(accountType, accountNumber, accountName, pin);
    }
}
