package com.mccarron.Gui;

import com.mccarron.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlphaCoordinates extends JPanel{
    private Font coordinateFont;
    public AlphaCoordinates(Font font){
        super();
        this.setPreferredSize(Dimensions.alphaCoordSize);
        this.setBackground(new Color(179, 202, 255));
        coordinateFont = font;
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(coordinateFont);
        super.paintComponent(g);
        float textIncrement = (float)(Dimensions.windowSize.getHeight() - Dimensions.numberCoordSize.getHeight() - Dimensions.outputPanelSize.getHeight()) / GameConstants.GRID_HEIGHT;
        for(float i = 0; i < Dimensions.windowSize.getHeight(); i += textIncrement) {
            g2D.drawString(String.valueOf(GameConstants.ALPHABET.charAt((int)(i/textIncrement))).toUpperCase(), 0, i + 18f);
        }
    }
}

