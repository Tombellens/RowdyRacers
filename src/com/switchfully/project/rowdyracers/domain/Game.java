package com.switchfully.project.rowdyracers.domain;

public class Game {
    private Grid grid;
    private ModelController modelController;
    private Position[] powerFailures;
    private Teleporter[] telePorters;

    private Player player1;
    private Player player2;
    private Player[] players;
    private int playerTurn;
    private int totalTurns;


    public Game( int dimension, Player player1, Player player2){
        this.grid = new Grid(dimension);
        this.player1 = player1;
        this.player2 = player2;
        players = new Player[]{player1, player2};
        playerTurn =  0;
        totalTurns = 0;

        grid.initializeGrid();
        grid.printGridState();

        grid.initializeWalls();
        grid.initializeGrenades();
        initializeTeleporters();
        grid.initializeDiscs();
        grid.printGridState();
    }

    public Grid getGrid() {
        return grid;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
        totalTurns++;
        newPowerFailures();
        checkNewPowerFailures();
        grid.printGridState();
    }

    public Player getOtherPlayer(){
        if (playerTurn == 0) return players[1];
        if (playerTurn == 1) return players[0];

        return null;
    }

    public Position getEndPosition(Position beginPosition){
        int newX = 0;
        if (beginPosition.getX() == 0) newX = grid.getDimension()-1;

        int newY = 0;
        if(beginPosition.getY() == 0) newY = grid.getDimension()-1;

        return new Position(newX, newY);

    }

    public void newPowerFailures(){
        Position[] newPowerFailures = grid.initializePowerFailures(powerFailures);
        powerFailures = newPowerFailures;
    }

    public int getTotalTurns() {
        return totalTurns;
    }

    public Position[] getPowerFailures() {
        return powerFailures;
    }

    public void checkNewPowerFailures(){
        for (int i = 0; i < powerFailures.length; i++){
            Position thisPowerFailure = powerFailures[i];
            if (player1.getPosition().equals(thisPowerFailure)) {
                player1.setHasMadeMove(true);
                //player1.setMessage("You are located on a powerfield!");
            }
            if (player2.getPosition().equals(thisPowerFailure)) {
                player2.setHasMadeMove(true);
                //player2.setMessage("You are located on a powerfield!");
            }
            if (thisPowerFailure.isAdjacentTo(player1.getPosition())){
                player1.setHasMadeMove(true);
                //player1.setMessage("You are located around a powerfield!");
            }
            if (thisPowerFailure.isAdjacentTo(player2.getPosition())){
                player2.setHasMadeMove(true);
                //player2.setMessage("You are located around a powerfield!");
            }
        }
    }

    private void initializeTeleporters(){
        telePorters = grid.initializeTeleporters();
    }

    public Teleporter[] getTelePorters() {
        return telePorters;
    }
}
