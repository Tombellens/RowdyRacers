package com.switchfully.project.rowdyracers.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartMenu  {
    private int width;
    private int height;
    private JFrame frame;
    BufferedImage image;
    GUIController controller;

    public StartMenu(int width, int height, GUIController controller){
        this.width = width;
        this.height = height;
        this.controller = controller;
        //get the background image
        File imageFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\rowdy_racers_logo.png");
        try {
            image = ImageIO.read(imageFile);
        }catch (IOException e){
            e.printStackTrace();
        }

        setUpScreen(width, height);



    }

    public void setUpScreen(int width, int height){
        frame = new JFrame("Welcome to Rowdy Racers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set dimensions to the given width and height
        Dimension dimension = new Dimension(width, height);
        frame.setMinimumSize(dimension);
        frame.setMaximumSize(dimension);

        // make the frame open in the middle of the window
        frame.setLocationRelativeTo(null);



        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //Add the logo for the frame
        JPanel logoPanel = new JPanel();
        JLabel logoLabel =  new JLabel(new ImageIcon(image));
        logoPanel.add(logoLabel, BorderLayout.CENTER);


        //To get the player names, we need to use a GridBagLayout. More on this later
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        //Set the constraints to the first values of the table
        constraints.gridx = 0;
        constraints.gridy = 0;

        //Initialize the labels
        JLabel player1Label = new JLabel("Player 1:");
        JLabel player2Label = new JLabel("Player 2:");
        JLabel dimensionLabel = new JLabel("Grid: ");

        JTextField player1TextField = new JTextField(20);
        JTextField player2TextField = new JTextField(20);
        JTextField dimensionField = new JTextField(2);

        infoPanel.add(player1Label, constraints);
        constraints.gridx = 1;
        infoPanel.add(player1TextField, constraints);
        constraints.gridx = 0;
        constraints.gridy=1;
        infoPanel.add(player2Label, constraints);
        constraints.gridx=1;
        infoPanel.add(player2TextField,constraints);

        constraints.gridy=3;
        constraints.gridx = 0;

        constraints.anchor = GridBagConstraints.CENTER;
        infoPanel.add(dimensionLabel, constraints);

        constraints.gridx = 1;
        infoPanel.add(dimensionField, constraints);

        JButton goButton = new JButton("Go!");
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setUpOver(player1TextField.getText(), player2TextField.getText(), dimensionField.getText());
                frame.setVisible(false);
            }
        });

        constraints.gridy=5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        infoPanel.add(goButton, constraints);


        mainPanel.add(logoPanel);
        mainPanel.add(infoPanel);
        frame.add(mainPanel);
        frame.pack();

        frame.setVisible(true);

    }



}
