package com.mccarron.Game;
import com.mccarron.GameConstants;
import com.mccarron.Gui.GridListener;
import java.util.ArrayList;

public class GuessHandler {
    private static String guess;
    private static String result;
    private static boolean cellGuessed = false;
    private static boolean guessChecked = false;
    private static GridListener instance;
    public static void setGuess(int index, GridListener gl){
        String alpha = String.valueOf(GameConstants.ALPHABET.charAt((int)(index / GameConstants.GRID_HEIGHT)));
        int num = index % 7;
        guess = alpha.concat(Integer.toString(num));
        instance = gl;
    }


    public static String getGuess(){
        System.out.println(guess + "was read from Guess handler");
        return guess;
    }

    public static boolean wasCellGuessed(){
        return cellGuessed;
    }

    public static void onCellGuessed(){
        cellGuessed = true;
    }

    public static void onGuessChecked(String result, ArrayList<String> cellsHit)
    {
        guessChecked = true;
        cellGuessed = false;
        setResult(result);
        System.out.println("Guess checked by handler: " + GuessHandler.result + ". waiting for listener to update button");
        instance.updateButton(cellsHit);
    }

    private static void setResult(String r){
        result = r;
    }

    public static String getResult(){
        return result;
    }

    public static boolean wasGuessChecked(){
        return guessChecked;
    }

    public static void clearGuess(){
        guess = "";
        guessChecked = false;
        cellGuessed = false;
    }
}
