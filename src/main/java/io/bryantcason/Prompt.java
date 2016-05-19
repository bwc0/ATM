package io.bryantcason;

import java.util.Scanner;


public class Prompt {

    public static int askForInt(String message){
        Scanner input = new Scanner(System.in);
        giveMessage(message);
        return input.nextInt();
    }

    public static double askForDouble(String message){
        Scanner input = new Scanner(System.in);
        giveMessage(message);
        return input.nextDouble();
    }

    public static String askForString(String message){
        Scanner input = new Scanner(System.in);
        giveMessage(message);
        return input.nextLine();
    }

    public static void giveMessage(String message){
        System.out.println(message);
    }

}
