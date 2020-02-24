package com.switchfully.project.rowdyracers.domain;

import java.util.LinkedList;

public class Player {
    private String playerName;
    private Position position;
    private Item[] inventory;
    private int blindTime;
    private int inventoryMarker;
    private int inventoryCounter;
    private int grenadeCounter;

    private String message;

    private Position beginPosition;

    private boolean hasMadeMove;
    private boolean hasDroppedItem;

    private LinkedList<Position> pastPositions;
    private LinkedList<Position> grenadePositions;



    public Player(String playerName, Position position) {
        this.playerName = playerName;
        this.position = position;
        this.beginPosition = position;
        this.blindTime = 0;

        inventoryMarker = 0;
        inventoryCounter = 0;
        grenadeCounter = 0;

        initializeInventory();

        pastPositions = new LinkedList<Position>();
        initializePastPositions();

        grenadePositions = new LinkedList<Position>();
        initializeGrenadePositions();

        this.hasMadeMove = false;
        this.hasDroppedItem = false;

    }

    private void initializeInventory(){
        inventory = new Item[6];

        for (int i = 0; i < inventory.length; i++){
            inventory[i] = Item.NOTHING;
        }
    }

    private void initializePastPositions(){
        Position nullPosition = new Position (-1, -1);

        for (int i = 0; i < 3; i++) {
            pastPositions.add(nullPosition);
        }


    }

    private void initializeGrenadePositions(){
        Position nullPosition = new Position(-1,-1);

        for (int i = 0; i < 6; i++){
            grenadePositions.add(nullPosition);
        }
    }

    public void putDownGrenade(Position position){
        grenadePositions.add(grenadeCounter, position);
        grenadeCounter++;
    }

    public boolean grenadeIsOwn(Position position){
        return grenadePositions.contains(position);
    }

    public void addToInventory(Item item){
        inventory[inventoryCounter] = item;
        inventoryCounter++;
    }

    public void addPastPosition(Position position){
        pastPositions.add(position);
        pastPositions.removeFirst();
    }

    public LinkedList<Position> getPastPositions(){
        return pastPositions;
    }


    public String getPlayerName() {
        return playerName;
    }

    public Position getPosition() {
        return position;
    }

    public Item[] getInventory() {
        return inventory;
    }

    public int getInventoryMarker() {
        return inventoryMarker;
    }

    public void setInventoryMarker(int inventoryMarker) {
        this.inventoryMarker = inventoryMarker;
    }

    public boolean isHasMadeMove() {
        return hasMadeMove;
    }

    public void setHasMadeMove(boolean hasMadeMove) {
        this.hasMadeMove = hasMadeMove;
    }

    public boolean isHasDroppedItem() {
        return hasDroppedItem;
    }

    public void setHasDroppedItem(boolean hasDroppedItem) {
        this.hasDroppedItem = hasDroppedItem;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean hasMadeMove(){
        return this.hasMadeMove;
    }

    public int getInventoryCounter() {
        return inventoryCounter;
    }

    public void setInventoryCounter(int inventoryCounter) {
        this.inventoryCounter = inventoryCounter;
    }

    public int getBlindTime() {
        return blindTime;
    }

    public void setBlindTime(int blindTime) {
        this.blindTime = blindTime;
    }

    public Position getBeginPosition() {
        return beginPosition;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
