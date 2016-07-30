package com.mccarron.Gui;

import com.mccarron.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * Created by Eamon on 6/27/2015.
 */
public class NumberCoordinates extends JPanel{
    private Font coordinateFont;
    public NumberCoordinates(Font font){
        super();
        this.setPreferredSize(Dimensions.numberCoordSize);
        this.setBackground(new Color(179, 202, 255));
        coordinateFont = font;
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(coordinateFont);
        super.paintComponent(g);
        float textIncrement = (float)(Dimensions.windowSize.getWidth() - Dimensions.alphaCoordSize.getWidth()) / GameConstants.GRID_WIDTH;
        for(float i  = 0; i < Dimensions.windowSize.getWidth(); i+= textIncrement){
            g2D.drawString(Integer.toString(Math.round(i/textIncrement)),(int)(i + Dimensions.alphaCoordSize.width + 5),Dimensions.numberCoordSize.height - 1);

        }
    }

}
