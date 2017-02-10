/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Almas Khan
 */
public class TileBoard extends JFrame
{
    private JButton btnGenerate,btnSave, btnLoad, emptyButton, redButton,blueButton, greenButton,
            yellowButton, pinkButton, cyanButton, magentaButton, whiteButton, 
            wallButton;
    private JLabel rowLabel;
    private JTextField rowText, colText;
    public static int clickedButton = 0;
    public static final int EMPTY_BUTTON = 0, RED_BUTTON = 1, BLUE_BUTTON = 2, 
            GREEN_BUTTON = 3, YELLOW_BUTTON = 4, PINK_BOX = 5, 
            CYAN_BUTTON = 6, MAGENTA_BUTTON = 7, WHITE_BUTTON = 8,
            WALL_BUTTON = 9;
    static int rowValue, colValue;
    private JPanel topPanel, leftPanel, bottomPanel;
    
    public TileBoard()
    {
        //create the components
        emptyButton = new JButton("Empty Slot");
        redButton = new JButton("Red Box");
        blueButton = new JButton("Blue Box");
        greenButton = new JButton("Green Box");
        yellowButton = new JButton("Yellow Box");
        pinkButton = new JButton("Pink Door");
        cyanButton = new JButton("Cyan Door");
        magentaButton = new JButton("Magenta Door");
        whiteButton = new JButton("White Door");
        wallButton = new JButton("Wall");
        
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(10,1));
        leftPanel.add(emptyButton);
        leftPanel.add(redButton);
        leftPanel.add(blueButton);
        leftPanel.add(greenButton);
        leftPanel.add(yellowButton);
        leftPanel.add(pinkButton);
        leftPanel.add(cyanButton);
        leftPanel.add(magentaButton);
        leftPanel.add(whiteButton);
        leftPanel.add(wallButton);
        
        ButtonHandler myButtonHandler = new ButtonHandler();
    
        emptyButton.addActionListener(myButtonHandler);
        redButton.addActionListener(myButtonHandler);
        blueButton.addActionListener(myButtonHandler);
        greenButton.addActionListener(myButtonHandler);
        yellowButton.addActionListener(myButtonHandler);
        pinkButton.addActionListener(myButtonHandler);
        cyanButton.addActionListener(myButtonHandler);
        magentaButton.addActionListener(myButtonHandler);
        whiteButton.addActionListener(myButtonHandler);
        wallButton.addActionListener(myButtonHandler);
      
        bottomPanel = new JPanel();
        //create the container
        topPanel = new JPanel();
        //assign layout manager
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        rowLabel = new JLabel("Rows: ");
        rowText = new JTextField("5", 5);
        colText= new JTextField("5", 5);
        btnGenerate = new JButton("Generate");
        btnLoad = new JButton("Load");
        btnSave = new JButton("Save");
        
        btnGenerate.addActionListener(myButtonHandler);
        btnSave.addActionListener(myButtonHandler);
        btnLoad.addActionListener(myButtonHandler);
        topPanel.add(rowLabel);
        topPanel.add(rowText);
        topPanel.add(colText);
        topPanel.add(btnGenerate);
        topPanel.add(btnLoad);
        topPanel.add(btnSave);
        //add components to the container
        
        //assign layout manager to the Form/Frame
        this.setLayout(new BorderLayout());
        //add subcontainers to the Frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST );
        this.add(bottomPanel, BorderLayout.CENTER);
       
        
        this.pack();
        this.setVisible(true);
        this.isResizable();
        topPanel.setBorder(BorderFactory.createEtchedBorder(0));
        leftPanel.setBorder(BorderFactory.createEtchedBorder(0));
        
    }
    
      
    
    
    

    private class ButtonHandler implements ActionListener
    {
        JButton btn = null;
    
        private ButtonHandler() {}
        
        private void generateGrid(int rows, int cols)
        {
            if (bottomPanel.countComponents()!=0) 
            {
                bottomPanel.removeAll();
            }
            bottomPanel = new JPanel();
            bottomPanel.setLayout(new GridLayout(rows, cols));
            TileBoard.this.add(TileBoard.this.bottomPanel);
            for (int i = 0; i < rows; i++) 
            {
                for (int j = 0; j < cols; j++)
                {
                    ColorTile t = new ColorTile();
                    
                    bottomPanel.add(t);
                }
            }
            bottomPanel.revalidate();
        }
    
      
    
        private void doSave() throws IOException
        {
            JFileChooser dlgSave = new JFileChooser();
            int result = dlgSave.showSaveDialog(TileBoard.this);
            if (result == 0)
            {
                FileWriter writer = new FileWriter(dlgSave.getSelectedFile().getPath());
                ColorTile tile = null;
                writer.write(String.valueOf(TileBoard.rowValue) + "\n");
                writer.write(String.valueOf(TileBoard.colValue) + "\n");
                for (Component component : TileBoard.this.bottomPanel.getComponents()) 
                {
                        tile = (ColorTile)component;
                        writer.write(String.valueOf(tile.getColorType()) + "\n");
                }
                writer.close();
        
                JOptionPane.showMessageDialog(null, "File saved successfully");
            }
        }
     
        private void doLoad() throws IOException
        {
            JFileChooser dlgLoad = new JFileChooser();
            int result = dlgLoad.showOpenDialog(TileBoard.this);
            if (result == 0)
            {
                FileReader reader = new FileReader(dlgLoad.getSelectedFile().getPath());
                BufferedReader bufferReader = new BufferedReader(reader);
        
                ColorTile tile = null;
        
                rowValue = Integer.parseInt(bufferReader.readLine());
        
                colValue = Integer.parseInt(bufferReader.readLine());
                rowText.setText(""+rowValue);
                colText.setText(""+colValue);
                
                if (bottomPanel.countComponents()!=0) 
                {
                    bottomPanel.removeAll();
                }
                bottomPanel = new JPanel();
                bottomPanel.setLayout(new GridLayout(TileBoard.rowValue, TileBoard.colValue));
                TileBoard.this.add(TileBoard.this.bottomPanel);
                 for (int i = 0; i < rowValue; i++) 
                 {
                    for (int j = 0; j < colValue; j++)
                    {
                        String r = bufferReader.readLine();
                        ColorTile t = new ColorTile();
                        t.setColorType(Integer.parseInt(r));
                        bottomPanel.add(t);
                    }
                }  
                bufferReader.close();
               
                bottomPanel.revalidate();
            }
        }
        public void actionPerformed(ActionEvent e)
        {
            JButton btn = (JButton)e.getSource();
            if (btn == btnGenerate)
            {
                rowValue = Integer.parseInt(rowText.getText());
                colValue = Integer.parseInt(colText.getText());
                generateGrid(rowValue, colValue);
            }
            else if (btn == btnSave)
            {
                try
                {
                    doSave();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error in file save.");
                }
            }
            else if (btn == btnLoad)
            {
                try
                {
                    doLoad();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error in file load.");
                }
            }
            else if (btn == emptyButton) 
            {
                clickedButton = 0;
            }
            else if (btn == redButton) 
            {
                clickedButton = 1;
            }
            else if (btn == blueButton) 
            {
                clickedButton = 2;
            }
            else if (btn == greenButton) 
            {
                clickedButton = 3;
            }
            else if (btn == yellowButton) 
            {
                clickedButton = 4;
            }
            else if (btn == pinkButton) 
            {
                clickedButton = 5;
            }
            else if (btn == cyanButton) 
            {
                clickedButton = 6;
            }
            else if (btn == magentaButton) 
            {
                clickedButton = 7;
            }
            else if (btn == whiteButton) 
            {
                clickedButton = 8;
            }
            else if (btn == wallButton) 
            {
                clickedButton = 9;
            }
        }
    }
    
}
