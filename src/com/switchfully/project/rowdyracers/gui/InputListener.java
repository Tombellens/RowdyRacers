package com.switchfully.project.rowdyracers.gui;

import com.switchfully.project.rowdyracers.domain.Position;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener implements KeyListener {
    private GUIController guiController;

    public InputListener(GUIController guiController) {
        this.guiController = guiController;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key " +  e.getKeyCode() + " is pressed and released" );

        //Moves in the inventory
        if (e.getKeyCode() == 110) guiController.moveInventoryUp();
        if (e.getKeyCode() ==96)guiController.moveInventoryDown();

        //Moves on the board
        if (e.getKeyCode() == 39) guiController.movePlayer(new Position(1,0)); //Right
        if (e.getKeyCode() == 38) guiController.movePlayer(new Position(0,1));  //Up
        if (e.getKeyCode() == 37) guiController.movePlayer(new Position(-1,0)); //Left
        if (e.getKeyCode() == 40) guiController.movePlayer(new Position(0,-1)); //Down

        //Put down a grenade

        if (e.getKeyCode() == 80) guiController.putDown();
        //Quit turn
        if (e.getKeyCode() == 81) guiController.playerQuit();

    }
}
