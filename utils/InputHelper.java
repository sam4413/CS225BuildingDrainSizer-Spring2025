package utils;

import java.util.Scanner;

public class InputHelper {
    //im in the middle of debugging a scanner bug, so this will look a bit messy for now. 
    // in next demo on thursday this will be fixed. Check github for updates.
    public static int processIntChoice() {
        int choice = -1;
        try {
            Scanner input = new Scanner(System.in);
            Log.out("Please enter an option: ");
            String inChoice = input.nextLine();
            choice = Integer.parseInt(inChoice);
            input.close();
            return choice;
        } catch (Exception err) {
            /*Log.error(
                    "An error has occured: " + err.getMessage() + "\nStack Trace: " + ExceptionHelper.stackToString(err));
            return -1;*/
            return 8;
        }

    }

    public static String processStringChoice() {
        try {
            Scanner input = new Scanner(System.in);
            Log.out("Please enter an option: ");
            String inChoice = input.nextLine();
            input.close();
            return inChoice;
        } catch (Exception err) {
            Log.error(
                    "An error has occured: " + err.getMessage() + "\nStack Trace: " + ExceptionHelper.stackToString(err));
            return null;
        }

    }
}
