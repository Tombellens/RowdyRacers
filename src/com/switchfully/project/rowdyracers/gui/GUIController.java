package com.switchfully.project.rowdyracers.gui;

import com.switchfully.project.rowdyracers.domain.*;

public class GUIController {
    private ModelController modelController;
    private RowdyWindow window;

    public GUIController(ModelController modelController) {
        this.modelController = modelController;
    }

    public void setUpStartWindow(int width, int height){

        // The GUI Controller can set up the start menu. Dimensions need to be passed through for the dimension
        StartMenu startMenu = new StartMenu(width, height, this);

    }

    public void setUpOver(String userName1, String userName2, String dimension){
        String player1Name = "Player 1";
        String player2Name = "Player 2";
        int gridDimension = 10;

        if (userName1 != "" && userName1 != null){
            player1Name = userName1;
        }

        if (userName2 != ""){
            player2Name = userName2;
        }

        try{
            gridDimension = Integer.parseInt(dimension);
        }catch (Exception e){

        }

        modelController.startUpGame(player1Name, player2Name,gridDimension);
    }

    public void startMainGUI(int dimension, Grid grid, Player player1, Player player2){
        window = new RowdyWindow(dimension, player1, player2, grid,this);
        window.initialDraw();
    }

    public void reDrawGUI(Game game){
        window.reDraw(game);
    }

    public void moveInventoryUp(){
        modelController.moveInventoryUp();
    }




    public void moveInventoryDown(){
        modelController.moveInventoryDown();
    }

    public void movePlayer(Position position){
        modelController.movePlayer(position);
    }

    public void playerQuit(){
        modelController.nextTurn();
    }

    public void putDown(){
        modelController.putDown();

    }



}
