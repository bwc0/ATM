package io.bryantcason.utils;

import java.util.Scanner;


public class Prompt {

    private Scanner scanner;

    public Prompt() {
        this.scanner = new Scanner(System.in);
    }

    public int askForInt(String message){
        giveMessage(message);
        return scanner.nextInt();
    }

    public double askForDouble(String message){
        giveMessage(message);
        return scanner.nextDouble();
    }

    public String askForString(String message){
        scanner = new Scanner(System.in);
        giveMessage(message);
        return scanner.nextLine();
    }

    public static void giveMessage(String message){
        System.out.println(message);
    }

    public Scanner getScanner() {
        return scanner;
    }
}
