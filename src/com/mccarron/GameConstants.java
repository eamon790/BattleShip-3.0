package com.mccarron;

import java.awt.*;
public final class GameConstants {
    public static final int GRID_SIZE = 49;
    public static final int GRID_HEIGHT = 7;
    public static final int GRID_WIDTH  = 7;
    private static Dimension CELL_SIZE = new Dimension();
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final int[] SHIP_SIZES = {4,5,3};
    public static final int NUM_OF_SHIPS = 3;


    public static final class RESULTS{
        public static final String SHIP_MISSED = "miss";
        public static final String SHIP_HIT = "hit";
        public static final String SHIP_SUNK = "sunk";
        public static final String ALREADY_GUESSED = "already guessed";
    }

    private static final class SHIP_NAMES{
        public static final String BATTLE_SHIP = "BattleShip";
        public static final String GUN_SHIP = "GunShip";
        public static final String PATROL_BOAT = "PatrolBoat";
        public static final String CARRIER = "Carrier";
        public static final String DEFAULT = "Ship";
    }

    public static final class GameStates {
        public static final String INIT = "init";
        public static final String PLACEMENT = "placement";
        public static String PLAYING = "playing";
    }

    public static final class GameTypes{
        public static final String singlePlayer = "17283917238";
    }
    public static void setCellSize(int w, int h){
        CELL_SIZE.setSize(new Dimension(w,h));
    }
    public static void setCellSize(Dimension d){
        CELL_SIZE = d;
    }
    public static Dimension getCellSize(){
        return CELL_SIZE;
    }
    public static String getShipNameFromSize(int Size)
    {
        switch(Size){
            case 2:
                return SHIP_NAMES.PATROL_BOAT;
            case 3:
                return SHIP_NAMES.GUN_SHIP;
            case 4:
                return SHIP_NAMES.BATTLE_SHIP;
            case 5:
                return SHIP_NAMES.CARRIER;
            default:
                return SHIP_NAMES.DEFAULT;
        }
    }
}
