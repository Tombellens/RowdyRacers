package com.switchfully.project.rowdyracers.gui;

import com.switchfully.project.rowdyracers.domain.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;

public class RowdyWindow {
    private int dimension;
    private String player1Name;
    private String player2Name;
    private Grid toDraw;
    private Item[] inventoryPlayer1;
    private Item[] inventoryPlayer2;
    private Player player1;
    private Player player2;
    private int turn;

    private Image logo;
    private Image emptySquare;
    private Image wallSquare;
    private Image grenadeSquare;
    private Image player1Square;
    private Image player2Square;
    private Image redEmptySquare;
    private Image reGrenadeSquare;

    private Image player2past1Square;
    private Image player2past2Square;
    private Image player2past3Square;
    private Image player1past1Square;
    private Image player1past2Square;
    private Image player1past3Square;

    private Image transparentSquare;

    private Image blockedPlayerSquare;
    private Image powerFailureSquare;
    private Image nextToFailureSquare;
    private Image grenadeNextToFailure;
    private Image player1NextToFailure;
    private Image player2NextToFailure;

    private Image teleporterSquare;
    private Image teleporterSquareFailure;
    private Image chargedDiscSquare;
    private Image chargedDiscFailure;
    private Image unchargedDiscSquare;
    private Image unchargedDiscFailure;

    private Image selectedChargedDisc;
    private Image selectedUnchargedDisc;

    private Image player1Turn;
    private Image player2Turn;

    private Square[][] squareToDraw;
    private Game game;

    private JFrame frame;
    private JPanel topPanel;
    private JPanel gridPanel;
    private JPanel statusPanel;
    private Dimension squareDimension;

    private InputListener inputListener;
    private GUIController guiController;



    public RowdyWindow(int dimension, Player player1, Player player2, Grid toDraw,GUIController guiController) {
        this.dimension = dimension;

        this.player1 = player1;
        this.player2 = player2;

        this.player1Name = player1.getPlayerName();
        this.player2Name = player2.getPlayerName();
        this.toDraw = toDraw;

        this.guiController = guiController;
        inputListener = new InputListener(guiController);

        squareToDraw = toDraw.getSquares();

        File logoFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\rowdy_racers_logo.png");
        File emptySquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\empty_square.png");
        File wallSquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\wall_square.png");
        File grenadeSquare = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\grenade_square.png");
        File player2Square = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\player1_square.png");
        File player1Square = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\player2_square.png");
        File redEmptySquare = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\red_empty_square.png");
        File redGrenadeSquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\red_grenade_square.png");

        File player2past3SquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\p2_1_empty_square_.png");
        File player2past2SquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\p2_2_empty_square_.png");
        File player2past1SquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\p2_3_empty_square_.png");

        File player1past1SquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\p1_1_empty_square_.png");
        File player1past2SquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\p1_2_empty_square_.png");
        File player1past3SquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\p1_3_empty_square_.png");

        File blockedPlayerSquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\blocked_square.png");
        File transparentSquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\transparent_square.png");
        File powerFailureSquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\power_failure.png");
        File nextToFailureSquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\next_to_failure.png");
        File grenadeNextToFailureFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\grenade_next_to_failure.png");
        File player2NextToFailureFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\player1_next_to_failure.png");
        File player1NextToFailureFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\player2_nxt_to_failure.png");

        File player2TurnFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\player1_square_turn.png");
        File player1TurnFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\player2_square_turn.png");

        File teleporterSquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\teleporter_square.png");
        File teleporterSquareFailureFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\teleporter_square_failure.png");
        File chargedDiscSquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\charged_disc.png");
        File chargedDiscFailure = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\charged_disc_failure.png");
        File unchargedDiscSquareFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\unchardged_disc.png");
        File unchargedDiscFailureFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\unchardged_disc_failure.png");

        File selectedChargedFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\selected_charged_disc.png");
        File selectedUnchargedFile = new File("C:\\_development\\switchfully\\Projects\\RowdyRacers\\selected_unchardged_disc.png");

        try {
            this.logo = ImageIO.read(logoFile);
            this.emptySquare = ImageIO.read(emptySquareFile);
            this.wallSquare =  ImageIO.read(wallSquareFile);
            this.grenadeSquare = ImageIO.read(grenadeSquare);
            this.player1Square = ImageIO.read(player1Square);
            this.player2Square = ImageIO.read(player2Square);
            this.redEmptySquare = ImageIO.read(redEmptySquare);
            this.reGrenadeSquare= ImageIO.read(redGrenadeSquareFile);

            this.player2past1Square = ImageIO.read(player2past1SquareFile);
            this.player2past2Square = ImageIO.read(player2past2SquareFile);
            this.player2past3Square  = ImageIO.read(player2past3SquareFile);

            this.player1past1Square = ImageIO.read(player1past1SquareFile);
            this.player1past2Square = ImageIO.read(player1past2SquareFile);
            this.player1past3Square = ImageIO.read(player1past3SquareFile);

            this.blockedPlayerSquare = ImageIO.read(blockedPlayerSquareFile);
            this.transparentSquare = ImageIO.read(transparentSquareFile);
            this.powerFailureSquare = ImageIO.read(powerFailureSquareFile);
            this.nextToFailureSquare = ImageIO.read(nextToFailureSquareFile);
            this.grenadeNextToFailure = ImageIO.read(grenadeNextToFailureFile);
            this.player1NextToFailure = ImageIO.read(player1NextToFailureFile);
            this.player2NextToFailure  = ImageIO.read(player2NextToFailureFile);

            this.teleporterSquare = ImageIO.read(teleporterSquareFile);
            this.teleporterSquareFailure = ImageIO.read(teleporterSquareFailureFile);
            this.chargedDiscSquare = ImageIO.read(chargedDiscSquareFile);
            this.chargedDiscFailure  = ImageIO.read(chargedDiscFailure);
            this.unchargedDiscSquare = ImageIO.read(unchargedDiscSquareFile);
            this.unchargedDiscFailure = ImageIO.read(unchargedDiscFailureFile);

            this.selectedChargedDisc = ImageIO.read(selectedChargedFile);
            this.selectedUnchargedDisc = ImageIO.read(selectedUnchargedFile);


            this.player2Turn = ImageIO.read(player2TurnFile);
            this.player1Turn = ImageIO.read(player1TurnFile);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void initialDraw(){
        frame = new JFrame("Rowdy Racers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension frameDimension = new Dimension(800,800);
        frame.setMinimumSize(frameDimension);
        frame.setMaximumSize(frameDimension);

        // make the frame open in the middle of the window
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.getHSBColor(0.32f, 0.15f,0.37f));


        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));


        //The setup of the topPanel
        topPanel = new JPanel();
        Dimension topPanelSize = new Dimension(800,100);
        topPanel.setPreferredSize(topPanelSize);
        topPanel.setBackground(Color.getHSBColor(0.32f,0.0811f,0.55f));
        JLabel logoLabel = new JLabel(new ImageIcon(logo));
        topPanel.add(logoLabel, BorderLayout.CENTER);

        frame.getContentPane().add(topPanel,BorderLayout.NORTH);



        //The setup of the gridPanel
        gridPanel = new JPanel(new GridBagLayout());
        Dimension gridPanelSize = new Dimension(600,600);
        gridPanel.setPreferredSize(gridPanelSize);
        gridPanel.setBackground(Color.getHSBColor(0.32f, 0.0837f,0.6784f));

        int squareWidth = (int) (600/dimension);
        int squareHeight = (int)(600/dimension);

        squareDimension = new Dimension(squareWidth, squareHeight);

        for (int i = 0; i < dimension; i++){
            for (int j=0; j < dimension; j++){

                Image imageToAd = emptySquare;
                if (squareToDraw[i][j] == Square.WALL) imageToAd = wallSquare;
                if (squareToDraw[i][j] == Square.LIGHTGRENADE_PICK_UP) imageToAd = grenadeSquare;
                if (squareToDraw[i][j] == Square.TELEPORTER) imageToAd = teleporterSquare;
                if (squareToDraw[i][j] == Square.CHARGED_DISC) imageToAd = chargedDiscSquare;
                if (squareToDraw[i][j] == Square.UNCHARGED_DISC) imageToAd = unchargedDiscSquare;


                if (j == player1.getPosition().getX()  && i == player1.getPosition().getY()){
                        imageToAd = player1Turn;
                }
                if (j == player2.getPosition().getX()  && i == player2.getPosition().getY()){
                    imageToAd = player2Square;
                }



                JLabel squareLabel = new JLabel(new ImageIcon(imageToAd));
                squareLabel.setPreferredSize(squareDimension);

                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx= j;
                constraints.gridy = i;

                gridPanel.add(squareLabel, constraints);

            }
        }
        frame.getContentPane().add(gridPanel,BorderLayout.CENTER );



        //The setup of the statusPanel
        statusPanel = new JPanel(new GridBagLayout());
        Dimension statusPanelSize = new Dimension(800, 100);
        statusPanel.setPreferredSize(statusPanelSize);
        statusPanel.setBackground(Color.getHSBColor(0.32f,0.0811f,0.55f));


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;

        JLabel playerNameLabel = new JLabel();
        playerNameLabel.setText("Now Playing: " +  player1Name);
        statusPanel.add(playerNameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;

        JLabel playerPhoto = new JLabel(new ImageIcon(player1Square));
        playerPhoto.setPreferredSize(squareDimension);
        statusPanel.add(playerPhoto, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        //constraints.anchor = GridBagConstraints.CENTER;

        JLabel transparentBlock = new JLabel(new ImageIcon(transparentSquare));
        transparentBlock.setPreferredSize(squareDimension);
        statusPanel.add(transparentBlock, constraints);


        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        JLabel inventoryLabel = new JLabel();
        inventoryLabel.setText("Inventory");
        statusPanel.add(inventoryLabel, constraints);

        for (int i = 0; i < 6; i++) {
            constraints.gridx = 2+i;
            constraints.gridy = 1;
            constraints.anchor = GridBagConstraints.EAST;

            Image toAdd = emptySquare;

            if (player1.getInventory()[i] == Item.GRENADE)toAdd = grenadeSquare;
            if (player1.getInventory()[i] == Item.CHARGED_DISC)toAdd = chargedDiscSquare;
            if (player1.getInventory()[i] == Item.UNCHARGED_DISC)toAdd = unchargedDiscSquare;

            if (player1.getInventoryMarker() ==i && player1.getInventory()[i] == Item.GRENADE) toAdd = reGrenadeSquare;
            if (player1.getInventoryMarker() ==i && player1.getInventory()[i] == Item.CHARGED_DISC) toAdd = selectedChargedDisc ; //add right one
            if (player1.getInventoryMarker() ==i && player1.getInventory()[i] == Item.UNCHARGED_DISC) toAdd = selectedUnchargedDisc ; //add right one


            if (player1.getInventoryMarker() ==i && player1.getInventory()[i] == Item.NOTHING) toAdd = redEmptySquare;



            JLabel inventorySquare = new JLabel(new ImageIcon(toAdd));
            inventorySquare.setPreferredSize(squareDimension);
            statusPanel.add(inventorySquare, constraints);
        }

        constraints.gridx = 8;
        constraints.gridy = 1;
        //constraints.anchor = GridBagConstraints.CENTER;

        transparentBlock = new JLabel(new ImageIcon(transparentSquare));
        transparentBlock.setPreferredSize(squareDimension);
        statusPanel.add(transparentBlock, constraints);




        frame.getContentPane().add(statusPanel, BorderLayout.SOUTH);

        frame.addKeyListener(inputListener);
        frame.setVisible(true);
        frame.pack();



    }

    public void reDraw(Game game){

        this.game = game;
        squareToDraw = game.getGrid().getSquares();
        dimension = game.getGrid().getDimension();

        Player currentPlayer = game.getPlayers()[game.getPlayerTurn()];



        //Toppanel redraw
        topPanel.removeAll();
        JLabel logoLabel = new JLabel(new ImageIcon(logo));
        topPanel.add(logoLabel, BorderLayout.CENTER);

        frame.getContentPane().add(topPanel,BorderLayout.NORTH);


        gridPanel.removeAll();
        for (int i = 0; i < dimension; i++){
            for (int j=0; j < dimension; j++){

                Image imageToAd = emptySquare;
                if (squareToDraw[i][j] == Square.WALL) imageToAd = wallSquare;
                if (squareToDraw[i][j] == Square.LIGHTGRENADE_PICK_UP) imageToAd = grenadeSquare;
                if (squareToDraw[i][j] == Square.POWER_FAILURE)imageToAd = powerFailureSquare;
                if (squareToDraw[i][j] == Square.TELEPORTER) imageToAd = teleporterSquare;
                if (squareToDraw[i][j] == Square.CHARGED_DISC) imageToAd = chargedDiscSquare;
                if (squareToDraw[i][j] == Square.UNCHARGED_DISC) imageToAd = unchargedDiscSquare;

                //draw all the pastpositions
                LinkedList<Position> pastPositionsPlayer2 = player2.getPastPositions();

                for (int q = 1; q < 3; q++){
                    Position pastPosition = pastPositionsPlayer2.get(q);
                    if (j == pastPosition.getX() && i == pastPosition.getY()){
                        if (q == 0){
                            imageToAd = player2past1Square;
                        }
                        if (q == 1){
                            imageToAd = player2past2Square;
                        }
                        if (q == 2){
                            imageToAd = player2past3Square;
                        }
                    }
                }

                LinkedList<Position> pastPositionsPlayer1 = player1.getPastPositions();

                for (int q = 1; q < 3; q++){
                    Position pastPosition = pastPositionsPlayer1.get(q);
                    if (j == pastPosition.getX() && i == pastPosition.getY()){
                        if (q == 0){
                            imageToAd = player1past1Square;
                        }
                        if (q == 1){
                            imageToAd = player1past2Square;
                        }
                        if (q == 2){
                            imageToAd = player1past3Square;
                        }
                    }
                }

                if (j == player1.getPosition().getX()  && i == player1.getPosition().getY()){
                    imageToAd = player1Square;
                    if (player1.getBlindTime() != 0){
                        imageToAd = blockedPlayerSquare;
                    }
                }

                if (j == player2.getPosition().getX()  && i == player2.getPosition().getY()){
                    imageToAd = player2Square;
                    if (player2.getBlindTime() != 0){
                        imageToAd = blockedPlayerSquare;
                    }
                }

                Position[] failurePositions = game.getPowerFailures();

                if (j == player1.getPosition().getX()  && i == player1.getPosition().getY()){
                    if (game.getPlayerTurn() == 0){
                        imageToAd = player1Turn;
                    }else{
                        imageToAd = player1Square;
                    }
                }

                if (j == player2.getPosition().getX()  && i == player2.getPosition().getY()){
                    if (game.getPlayerTurn() == 1){
                        imageToAd = player2Turn;
                    }else{
                        imageToAd = player2Square;
                    }
                }

                if (failurePositions != null) {
                    for (int p = 0; p < failurePositions.length; p++) {
                        Position failurePosition = failurePositions[p];
                        Position currentPosition = new Position(j, i);

                        if (currentPosition.isAdjacentTo(failurePosition)) {
                            if (squareToDraw[i][j] == Square.EMPTY) imageToAd = nextToFailureSquare;
                            if (squareToDraw[i][j] == Square.LIGHTGRENADE_PICK_UP) imageToAd = grenadeNextToFailure;
                            if (squareToDraw[i][j] == Square.TELEPORTER) imageToAd = teleporterSquareFailure;
                            if (squareToDraw[i][j] == Square.CHARGED_DISC) imageToAd = chargedDiscFailure;
                            if (squareToDraw[i][j] == Square.UNCHARGED_DISC) imageToAd = unchargedDiscFailure;

                            if (player1.getPosition().equals(currentPosition)) imageToAd = player1NextToFailure;
                            if (player2.getPosition().equals(currentPosition)) imageToAd = player2NextToFailure;

                        }
                    }
                }



                JLabel squareLabel = new JLabel(new ImageIcon(imageToAd));
                squareLabel.setPreferredSize(squareDimension);

                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx= j;
                constraints.gridy = i;

                gridPanel.add(squareLabel, constraints);

            }
        }

        statusPanel.removeAll();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;

        JLabel playerNameLabel = new JLabel();
        playerNameLabel.setText("Now Playing: " + currentPlayer.getPlayerName());
        statusPanel.add(playerNameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;

        Image playerImageToAdd = player1Square;
        if(game.getPlayerTurn() == 1) playerImageToAdd = player2Square;

        JLabel playerPhoto = new JLabel(new ImageIcon(playerImageToAdd));
        playerPhoto.setPreferredSize(squareDimension);
        statusPanel.add(playerPhoto, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        JLabel inventoryLabel = new JLabel();
        inventoryLabel.setText("Inventory");
        statusPanel.add(inventoryLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        //constraints.anchor = GridBagConstraints.CENTER;

        JLabel transparentBlock = new JLabel(new ImageIcon(transparentSquare));
        transparentBlock.setPreferredSize(squareDimension);
        statusPanel.add(transparentBlock, constraints);



        for (int i = 0; i < 6; i++) {
            constraints.gridx = 2+i;
            constraints.gridy = 1;
            constraints.anchor = GridBagConstraints.EAST;

            Image toAdd = emptySquare;

            if (currentPlayer.getInventory()[i] == Item.GRENADE)toAdd = grenadeSquare;
            if (currentPlayer.getInventory()[i] == Item.CHARGED_DISC)toAdd = chargedDiscSquare;
            if (currentPlayer.getInventory()[i] == Item.UNCHARGED_DISC)toAdd = unchargedDiscSquare;

            if (currentPlayer.getInventoryMarker() ==i && currentPlayer.getInventory()[i] == Item.GRENADE) toAdd = reGrenadeSquare;
            if (currentPlayer.getInventoryMarker() ==i && currentPlayer.getInventory()[i] == Item.CHARGED_DISC) toAdd = selectedChargedDisc ; //add right one
            if (currentPlayer.getInventoryMarker() ==i && currentPlayer.getInventory()[i] == Item.UNCHARGED_DISC) toAdd = selectedUnchargedDisc ; //add right one
            JLabel inventorySquare = new JLabel(new ImageIcon(toAdd));
            inventorySquare.setPreferredSize(squareDimension);
            statusPanel.add(inventorySquare, constraints);
        }
        constraints.gridx = 8;
        constraints.gridy = 1;
        //constraints.anchor = GridBagConstraints.CENTER;

        transparentBlock = new JLabel(new ImageIcon(transparentSquare));
        transparentBlock.setPreferredSize(squareDimension);
        statusPanel.add(transparentBlock, constraints);

        constraints.gridx = 9;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        JLabel messageLabel= new JLabel();
        inventoryLabel.setText(currentPlayer.getMessage());
        statusPanel.add(inventoryLabel, constraints);




        frame.getContentPane().removeAll();
        statusPanel.revalidate();
        statusPanel.repaint();

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(gridPanel, BorderLayout.CENTER);
        frame.getContentPane().add(statusPanel, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();



    }
}
