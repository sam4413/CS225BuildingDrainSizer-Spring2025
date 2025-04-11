package utils;

import java.util.Scanner;

public class InputHelper {
    //im in the middle of debugging a scanner bug, so this will look a bit messy for now. 
    // in next demo on thursday this will be fixed. Check github for updates.
    public static int processIntChoice(Scanner input, String prompt) {
        int choice = -1;
        try {
            Log.out(prompt);
            String inChoice = input.nextLine();
            choice = Integer.parseInt(inChoice);
            return choice;
        } catch (Exception err) {
            Log.error(
                    "An error has occured: " + err.getMessage() + "\nStack Trace: " + ExceptionHelper.stackToString(err));
            return -1;
        }

    }

    public static String processStringChoice(Scanner input, String prompt) {
        try {
            Log.out(prompt);
            String inChoice = input.nextLine();
            return inChoice;
        } catch (Exception err) {
            Log.error(
                    "An error has occured: " + err.getMessage() + "\nStack Trace: " + ExceptionHelper.stackToString(err));
            return null;
        }

    }

    public static void processPause(Scanner input, String prompt) {
        try {
            Log.out(prompt);
            input.nextLine();
            return;
        } catch (Exception err) {
            Log.error(
                    "An error has occured: " + err.getMessage() + "\nStack Trace: " + ExceptionHelper.stackToString(err));
            return;
        }
    }
}
