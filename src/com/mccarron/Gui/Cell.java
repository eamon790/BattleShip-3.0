package com.mccarron.Gui;
import com.mccarron.GameConstants;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton{
    int index;
    private boolean hit;
    private boolean sunk;
    private boolean missed;
    public Cell(int i){
        super("");
        index = i;
    }

    public void setHit(){
        if(!this.missed && !this.sunk)
        hit = true;
    }

    public boolean isHit(){
        return hit;
    }

    public void setSunk(){
        sunk = true;
        hit = false;
    }

    public boolean isSunk(){
        return sunk;
    }

    public void setMissed(){
        if(!this.missed && !this.sunk)
        missed = true;
    }

    public boolean isMissed(){
        return missed;
    }
}
