package com.mccarron.Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class GameHelper {
    private int shipCount = 0;
    private int gridLength = 7;
    private int gridSize = 49;
    private final String ALPHABET = "abcdefghijklmnop";
    private boolean[] grid = new boolean[gridSize]; // an array of booleans representing if the spot in the grid is taken
    ArrayList<String> createLocation(int shipSize){
        //Random rand = new Random(System.nanoTime());
        ArrayList<String> sLocation = new ArrayList<String>();
        String[] alphaCoords = new String[shipSize];
        String rowLetter = null;
        int[] coords = new int[shipSize]; //numerical coordinates on a 7*7 (0-48) grid of ship being currently placed
        int attempts = 0;
        int location = 0;
        int increment;
        boolean success = false;

        shipCount++;
        increment = 1;
        if((shipCount % 2) == 0)  //for every 2nd (odd) ship
        {
            increment = gridLength;  // place ship vertically (adding 7 will move ship up a row in the grid)
        }

        while(!success & attempts++ < 200){
            location = (int)(Math.random() * gridSize);
            //System.out.println("Trying location: " + location);
            int currentPosition = 0; //integer to track what part of the ship is being placed
            success = true;
            while(success && currentPosition < shipSize) {
                if(!grid[location]) // tests if location is not taken
                {
                    coords[currentPosition++] = location; //sets the location that is not taken to the part of the ship being placed
                                                          //then increments the part of the ship being placed
                    location += increment; //increments the location (+7 if being vertically placed, +1 if horizontally placed
                    if(location >= gridSize) success = false; //if the ship doesn't fit onto grid horizontally, break out of loop
                    if(currentPosition > 0 && (location % gridLength) == 0) success = false; //^ (vertical test)
                }else{
                    System.out.println("used location: " + location); // location
                    success = false;                                  // already used
                }
            }
        }

        int i = 0; //a variable used to track which part of ship is being placed onto grid
        int row = 0;
        int column = 0;

        while(i < shipSize)
        {
            grid[coords[i]] = true; //set location on grid to true if that part of the ship is placed there
            row = (int)(coords[i] / gridLength);
            column = coords[i] % gridLength;
            rowLetter = getAlphaCoordFromRow(row);

            sLocation.add(rowLetter.concat(Integer.toString(column)));

            i++;
        }
        System.out.println("Coords:" + sLocation);
        return sLocation;
    }


    private String getAlphaCoordFromRow(int coords)
    {
        return String.valueOf(ALPHABET.charAt(coords));
    }

    public String getGridDimensions()
    {
        return gridLength + "x" + gridLength;
    }
}
