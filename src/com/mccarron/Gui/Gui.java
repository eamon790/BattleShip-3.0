package com.mccarron.Gui;
import com.mccarron.Game.Game;
import com.mccarron.GameConstants;
import com.sun.javaws.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

public class Gui {
    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private ArrayList<Cell> grid = new ArrayList<Cell>(GameConstants.GRID_SIZE);
    private GridListener gridListener = new GridListener(grid);
    private Font coordinateFont = new Font(Font.DIALOG,Font.BOLD,21);
    private OutputPanel outputPanel = new OutputPanel(coordinateFont);
    private JFrame window = new JFrame();
    private String gameType;
    public void initGui(){
        window.setSize(Dimensions.windowSize);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.setResizable(false);
        window.setVisible(true);
    }

    public void buildMenuGui(){
        this.clear();

        singlePlayerButton = new JButton("Single Player");
            singlePlayerButton.addActionListener( (e) -> gameType = GameConstants.GameTypes.singlePlayer);
        multiPlayerButton = new JButton("Multi Player");
        java.net.URL imgURL = getClass().getResource("title.png");
        if(imgURL == null) System.out.println("Uh oh");
        ImageIcon titleImage = new ImageIcon(imgURL);
        JLabel title = new JLabel(titleImage);
        JPanel titlePanel = new JPanel();
            titlePanel.add(title);
        JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(singlePlayerButton);
            buttonPanel.add(multiPlayerButton);

        window.setLayout(new BorderLayout());
        window.getContentPane().add(BorderLayout.NORTH, title);
        window.getContentPane().add(buttonPanel);
        window.setVisible(true);

    }
    public void buildGameGui(){
        this.clear();

        System.out.println("Gui cleared");
        NumberCoordinates numCoords = new NumberCoordinates(coordinateFont);
        numCoords.setLayout(new BoxLayout(numCoords,BoxLayout.X_AXIS));
        AlphaCoordinates alphaCoords = new AlphaCoordinates(coordinateFont);
        GridPanel gridPanel = new GridPanel();



        for(int i = 0; i < GameConstants.GRID_SIZE; i++){
            grid.add(new Cell(i));
        }
        for(Cell x : grid){
            gridPanel.add(x);
            x.addActionListener(gridListener);
            x.setText(Integer.toString(x.index));
        }






        window.getContentPane().add(BorderLayout.CENTER, gridPanel);
        window.getContentPane().add(BorderLayout.NORTH, numCoords);
        window.getContentPane().add(BorderLayout.SOUTH, outputPanel);
        window.getContentPane().add(BorderLayout.WEST, alphaCoords);
        window.setVisible(true);

    }

    private void clear(){
        window.getContentPane().removeAll();
        window.revalidate();
        window.repaint();
        window.setVisible(true);
    }
    public void updateGameGui(int numOfGuesses, String resultPanelText){
        outputPanel.updatePanel(numOfGuesses, resultPanelText);
        window.repaint();
    }

    public String getGameType(){ return gameType;}
    public void drawEndGameScreen(boolean lost, String gameOverText, String gameWonText){
        if(lost){
            window.getContentPane().removeAll();
            window.getContentPane().revalidate();

            window.getContentPane().setBackground(new Color(172, 48, 56));
            Graphics windowG = window.getContentPane().getGraphics();
            windowG.setFont(new Font(Font.DIALOG, Font.BOLD, 90));
            windowG.drawString(gameOverText, Dimensions.windowSize.width / 2 - (29 * gameOverText.length()), Dimensions.windowSize.height / 2);


        }else{
           window.getContentPane().removeAll();
           window.getContentPane().removeAll();

           window.getContentPane().setBackground(new Color(94, 172, 108));

            JButton newGame = new JButton("Play Again?");
            JButton endGame = new JButton("exit");
            window.getContentPane().add(newGame, BorderLayout.NORTH);
            window.getContentPane().add(endGame, BorderLayout.SOUTH);
            Graphics windowG = window.getContentPane().getGraphics();
            windowG.setFont(new Font(Font.DIALOG, Font.BOLD, 45));
            windowG.drawString(gameWonText, Dimensions.windowSize.width / 2 - (29 * gameOverText.length() + 30 ), Dimensions.windowSize.height / 2);
        }

    }


}

