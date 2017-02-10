/**
 * 
 * Class name : ColorTile.java
 * @author Almas Khan
 * Created on : 30 Nov 2016
 */

/* package name*/

package com.example;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*
* ColorTile class, inherit from JPanel class also implements MouseListerner Interface.
* This class will make make tiles
*/
public class ColorTile  extends JPanel implements MouseListener
{
    private int colorType = 0;
    
    /*
     * Default constructor will set the black border, also add mouse listener 
     * so that mouse actions can be used
     */
    public ColorTile()
    {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(this);
        selectType();
    }
    
    /* This method will check the value of variable 
     * and based upon its value will set the color of the tile
     */
    private void selectType()
    {
        if (colorType == 0) 
        {
            setBackground(Color.lightGray);
        }
        else if (colorType == 1) 
        {
            setBackground(Color.RED);
        }
        else if (colorType == 2) 
        {
            setBackground(Color.blue);
        }
        else if (colorType == 3) 
        {
            setBackground(Color.green);
        }
        else if (colorType == 4) 
        {
            setBackground(Color.yellow);
        }
        else if (colorType == 5) 
        {
            setBackground(Color.pink);
        }
        else if (colorType == 6) 
        {
            setBackground(Color.cyan);
        }
        else if (colorType == 7) 
        {
            setBackground(Color.magenta);
        }
        else if (colorType == 8) 
        {
            setBackground(Color.white);
        }
        else if (colorType == 9) 
        {
            setBackground(Color.black);
        }
    
    }
    
    /**
     *Invoked when the mouse button has been clicked (pressed and released) on a component.
     * @param e receiving mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) 
    {
       setColorType(TileBoard.clickedButton);
       selectType();
    }
    /**
     * Invoked when a mouse button has been pressed on a component.
     * @param e receiving mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) 
    {}
    /**
     * Invoked when a mouse button has been released on a component
     * @param e receiving mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) 
    {}
    /**
     * Invoked when the mouse enters a component.
     * @param e receiving mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) 
    {}
    /**
     * Invoked when the mouse exits a component.
     * @param e receiving mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) 
    {}

    /**
     * Getter for variable colorType
     * @return the colorType
     */
    public int getColorType() {
        return colorType;
    }

    /**
     * Setter for variable colorType
     * @param colorType the colorType to set
     */
    public void setColorType(int colorType) {
        this.colorType = colorType;
        selectType();
    }
 }
