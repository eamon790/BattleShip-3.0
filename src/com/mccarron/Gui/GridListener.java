package com.mccarron.Gui;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import com.mccarron.Game.GuessHandler;
import com.mccarron.GameConstants;


public class GridListener implements ActionListener{
    private Cell lastCellClicked;
    private ArrayList<Cell> grid;
    public GridListener(ArrayList<Cell> cells){
        grid = cells;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked");
        Cell cellClicked = getSource(e);
        //if(Game.get)
        GuessHandler.setGuess(cellClicked.index, this);
        GuessHandler.onCellGuessed();

    }
    public void updateButton(ArrayList<String> cellsHit){
        System.out.println("grid listener checking if guess was checked");
        if(GuessHandler.wasGuessChecked()){
            System.out.println("Guess check confirmed");
            if (GuessHandler.getResult().equals(GameConstants.RESULTS.SHIP_HIT)) {
                lastCellClicked.setHit();
            }else if(GuessHandler.getResult().equals(GameConstants.RESULTS.SHIP_MISSED)){
                lastCellClicked.setMissed();
            }else if(GuessHandler.getResult().equals(GameConstants.RESULTS.SHIP_SUNK)){
                int[] cellsSunk = convertCellsToInt(cellsHit);
                for(int i : cellsSunk){
                    for(Cell cell : grid){
                        if(cell.index == i){
                            cell.setSunk();
                        }
                    }
                }
            }

            for(Cell cell : grid){
                if(cell.isSunk()){
                    cell.setBackground(Color.red);
                }
                if(cell.isHit()){
                    cell.setBackground(Color.yellow);
                }
                if(cell.isMissed()){
                    cell.setBackground(Color.blue);
                }
            }
            GuessHandler.clearGuess();
        }

    }

    private Cell getSource(ActionEvent e){
        Cell source = null;
        try{
            source = (Cell)e.getSource();
        }catch(ClassCastException exception){
            exception.printStackTrace();
            System.out.println("Attempted to cast Button to Cell");
            source = new Cell(-1);
        }
        lastCellClicked = source;
        return source;
    }

    private int[] convertCellsToInt(ArrayList<String> cells){
        int[] values = new int[cells.size()];
        int i = 0;
        for(String cell : cells){
           int value1 = (GameConstants.ALPHABET.indexOf(cell.charAt(0)) * GameConstants.GRID_WIDTH);
           int value2 = (Integer.parseInt(String.valueOf(cell.charAt(1))));
           values[i] = value1 + value2;
           System.out.println("Cell: " + cell + " = " + value1 + " + " + value2);
           System.out.println(values[i]);
           i++;
       }
       return values;
    }



}
