package io.bryantcason.utils;

import java.io.PrintStream;
import java.util.Scanner;


public class Prompt {

    private final Scanner scanner;
    private final PrintStream out;

    public Prompt(Scanner scanner, PrintStream out) {
        this.scanner = new Scanner(System.in);
        this.out = out;
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
        giveMessage(message);
        return scanner.nextLine();
    }

    public static void giveMessage(String message){
        System.out.println(message);
    }

}
