package com.mccarron.Gui;

import com.mccarron.GameConstants;

import javax.swing.*;
import java.awt.*;

public class OutputPanel extends JPanel{
    boolean redraw = false;
    Font panelFont;
    int guesses;
    private String panelText;
    public OutputPanel(Font font){
        super();
        this.setPreferredSize(Dimensions.outputPanelSize);
        this.setBackground(new Color(179, 202,255));
        panelFont = font;
    }

    public void updatePanel(int guessesRemaining, String outputPanelText)
    {
        guesses = guessesRemaining;
        panelText = outputPanelText;
        System.out.println(outputPanelText);
        redraw = true;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Color defaultColor = g2D.getColor();
        g2D.setFont(panelFont);
        if(redraw){

            g2D.drawString(panelText, 300, 25);
            if(guesses > 20){
                g2D.setColor(Color.green);
            }else if(guesses <=20 && guesses > 10){
                g2D.setColor(Color.yellow);
            }else if(guesses <= 10) {
                g2D.setColor(Color.red);
            }

            g2D.drawString(Integer.toString(guesses), 0, panelFont.getSize() + 25);
            g2D.setColor(defaultColor);
            redraw = false;
        }

        g2D.drawString("Guesses Remaining :",0,25);
        if(guesses == 0){
            g2D.setColor(Color.GREEN);
            g2D.drawString("25", 0, panelFont.getSize() + 25);
            g2D.setColor(defaultColor);
        }
        g2D.setColor(new Color(10, 53, 255));
        g2D.drawLine(280,0,280,100);

    }
}
