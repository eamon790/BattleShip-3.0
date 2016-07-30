package com.mccarron.Game;
import com.mccarron.GameConstants;

import java.util.ArrayList;
public class Ship {
    private ArrayList<String> location;
    private ArrayList<String> cellsHit = new ArrayList<String>();
    private String id;
    public Ship(ArrayList<String> loc, String name)
    {
        location = loc;
        id = name;
    }

    String checkGuess(String guess)
    {
        String result = GameConstants.RESULTS.SHIP_MISSED;

        for(String cell : location)
        {
            System.out.println("Checking guess: " + guess + " with cell: " + cell);
            if(guess.equalsIgnoreCase(cell)) {
                System.out.println("Guess: " + guess + " Equals cell: " + cell);
                result = GameConstants.RESULTS.SHIP_HIT;
                cellsHit.add(cell);
                location.remove(cell);
                System.out.println("Removed cell: " + cell);
                if(location.isEmpty()) {
                    result = GameConstants.RESULTS.SHIP_SUNK;
                    return result;
                }
                System.out.println("breaking for loop and returning: " + result);
                return result;
            }else{
                result = GameConstants.RESULTS.SHIP_MISSED;
            }
            System.out.println("Breaking for loop");
        }

        return result;
    }

    ArrayList<String> getCellsHit(){
        return cellsHit;
    }

    public String getId(){
        return id;
    }
}
