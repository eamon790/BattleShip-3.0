package com.mccarron.Gui;
import com.mccarron.GameConstants;

import javax.swing.*;
import java.awt.*;
/**
 * Created by Eamon on 6/26/2015.
 */
public class GridPanel extends JPanel{
    public GridPanel()
    {
        super(new GridLayout(GameConstants.GRID_WIDTH, GameConstants.GRID_HEIGHT));

    }


}
