package com.mccarron.Game;

import com.mccarron.GameConstants;
import com.mccarron.Gui.Gui;
import java.util.ArrayList;

public class Game {
    private ArrayList<Ship> ships = new ArrayList<Ship>();
    private ArrayList<String> guesses = new ArrayList<String>();
    private ArrayList<String> cellsHit = new ArrayList<String>();
    private static Gui gui = new Gui();
    private String lastShipSunk;
    private static String gameState;
    private boolean gameOver = false;
    private GameHelper gameHelper = new GameHelper();
    private int guessesRemaining = 25;

    public static void main(String args[])
    {
        Game game = new Game();

        gameState = GameConstants.GameStates.INIT;

        game.runGame(game.init());
    }



    private String init() //create 3 ships and place them on grid
    {
        assert gameState.equals(GameConstants.GameStates.INIT);
        for(int x = 0; x < GameConstants.NUM_OF_SHIPS; x++){
            ships.add(new Ship(gameHelper.createLocation(GameConstants.SHIP_SIZES[x]), GameConstants.getShipNameFromSize(GameConstants.SHIP_SIZES[x])));
        }

        gui.initGui();
        gui.buildMenuGui();
        while(gui.getGameType() == null)
           try{ Thread.sleep(1); }catch (InterruptedException IE) {IE.printStackTrace();}
        return gui.getGameType();
    }



    private String checkGuess(String guess){
        String result = GameConstants.RESULTS.SHIP_MISSED;
        for(Ship s : ships){
            result = s.checkGuess(guess);

            if(result.equals(GameConstants.RESULTS.SHIP_HIT))
                break;

            if(result.equals(GameConstants.RESULTS.SHIP_SUNK)) {
                for(String cell : s.getCellsHit()) {
                    cellsHit.add(cell);
                    lastShipSunk = s.getId();
                }
                ships.remove(s);
                break;
            }
            for(String lastGuess : guesses){
                if(lastGuess.equalsIgnoreCase(guess)){
                    result = GameConstants.RESULTS.ALREADY_GUESSED;
                }

            }
        }
        guesses.add(guess);
        if(!result.equals(GameConstants.RESULTS.ALREADY_GUESSED))
            guessesRemaining--;
        return result;
    }

    private void runGame(String gameType)
    {
        gameState = GameConstants.GameStates.PLAYING;
        gui.buildGameGui();
        while(!gameOver) {
            String result = "test";

            if (GuessHandler.wasCellGuessed()) {
                result = checkGuess(GuessHandler.getGuess());
                GuessHandler.onGuessChecked(result, cellsHit);
                gui.updateGameGui(guessesRemaining, result);
            }

            if(ships.isEmpty()){
                endGame(false, gui);
                gameOver = true;
                break;
            }
            if(guessesRemaining<=0){
                endGame(true, gui);
                gameOver = true;
                break;
            }
        }

    }


    private void endGame(boolean lost, Gui gui){
        gui.drawEndGameScreen(lost, "GAME OVER", "YOU SUNK ALL THE SHIPS!");
    }



}
