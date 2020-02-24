package com.switchfully.project.rowdyracers.domain;

import com.switchfully.project.rowdyracers.gui.GUIController;

public class ModelController {
    private GUIController guiController;
    private Game game;

    public ModelController() {
        guiController = new GUIController(this);
    }

    public void startUpProcedure(){
        guiController.setUpStartWindow(500,350);
    }

    public void startUpGame(String playerName1, String playerName2, int dimension){
        Position positionPlayer1 = new Position(0, dimension-1);
        Player player1 = new Player(playerName1, positionPlayer1);

        Position positionPlayer2 = new Position(dimension-1, 0);
        Player player2 = new Player(playerName2, positionPlayer2);


        game = new Game(dimension, player1, player2);

        guiController.startMainGUI(dimension, game.getGrid(),game.getPlayer1(), game.getPlayer2());

        //This code should be deleted, just here to check the redraw-function
       // guiController.reDrawGUI(game.getGrid(), player1, player2, 0);
    }

    public void moveInventoryUp(){


        Player playerToChange = game.getPlayers()[game.getPlayerTurn()];
        int inventoryMarker = playerToChange.getInventoryMarker();

        if (inventoryMarker != 5) {
            playerToChange.setInventoryMarker(inventoryMarker + 1);
        }else{
            playerToChange.setInventoryMarker(0);
        }

        guiController.reDrawGUI(game);

    }

    public void moveInventoryDown(){

        Player playerToChange = game.getPlayers()[game.getPlayerTurn()];
        int inventoryMarker = playerToChange.getInventoryMarker();

        if (inventoryMarker != 0) {
            playerToChange.setInventoryMarker(inventoryMarker - 1);
        }else{
            playerToChange.setInventoryMarker(5);
        }

        guiController.reDrawGUI(game);
    }

    public void movePlayer(Position position){
        Player playerToChange = game.getPlayers()[game.getPlayerTurn()];
        Square[][] squares = game.getGrid().getSquares();
        int max = game.getGrid().getDimension();

        //Check if the user is blinded
        if (playerToChange.getBlindTime() > 0)return;

        //Check if the user has already made a move
        if (playerToChange.hasMadeMove()) return;

        int newX = playerToChange.getPosition().getX() + position.getX();
        int newY = playerToChange.getPosition().getY() - position.getY();

        //Check if the user wants to leave the board
        if ((newX < 0 || newX > max-1) || (newY < 0 || newY > max-1)) return;

        //Check if the user wants to run into a wall
        if (squares[newY][newX] == Square.WALL)return;

        //check if the user wants to run into another player
        Player otherPlayer = game.getOtherPlayer();
        Position otherPlayerPosition = otherPlayer.getPosition();
        if ((newX == otherPlayerPosition.getX()) && (newY == otherPlayerPosition.getY())) return;

        Position newPosition = new Position(newX, newY);

        //Check if the user will run into the trail of the other player
        if (newPosition.equals(otherPlayer.getPastPositions().getLast())) return;

        //Check if the user will step on a teleporter
        if (squares[newY][newX] == Square.TELEPORTER){
            int teleporterTiles = game.getTelePorters().length;

            for (int i = 0; i < teleporterTiles; i++){
                if (game.getTelePorters()[i].getCurrentPosition().equals(newPosition)){
                    System.out.println("Teleporter found");
                    newPosition = game.getTelePorters()[i].getDestinationTeleporter().getCurrentPosition();
                    break;
                }
            }


        }

        //Check if the user can pick up a grenade
        if (squares[newY][newX] == Square.LIGHTGRENADE_PICK_UP){
            playerToChange.addToInventory(Item.GRENADE);
            playerToChange.setMessage("You picked up a grenade");
            squares[newY][newX] = Square.EMPTY;
        }

        //Check if the user can pick up a charged disc
        if (squares[newY][newX] == Square.CHARGED_DISC) {
            playerToChange.addToInventory(Item.CHARGED_DISC);
        }

        if (squares[newY][newX] == Square.UNCHARGED_DISC) {
            playerToChange.addToInventory(Item.UNCHARGED_DISC);
        }



        //Check if the user stepped on a grenade
        if (squares[newY][newX] == Square.LIGHTGRENADE_TO_EXPLODE){
            if (!(playerToChange.grenadeIsOwn(newPosition))){
                playerToChange.setBlindTime(3);
                playerToChange.setMessage("You are blinded by a grenade");
                squares[newY][newX] = Square.EMPTY;

            }
        }

        //check if the user stepped on a powerfield
        if (squares[newY][newX] == Square.POWER_FAILURE){
            playerToChange.setBlindTime(playerToChange.getBlindTime()+1);
            playerToChange.setMessage("You stept in the field of a power failure");
        }

        if (game.getPowerFailures() != null) {
            for (int i = 0; i < game.getPowerFailures().length; i++) {
                if (newPosition.isAdjacentTo(game.getPowerFailures()[i])) {
                    playerToChange.setBlindTime(playerToChange.getBlindTime() + 1);
                    playerToChange.setMessage("You stept in the field of a power failure");
                }
            }
        }

        //Check if the user is  done

        if (newPosition.equals(game.getEndPosition(playerToChange.getBeginPosition()))){
           endGame(playerToChange);
        }



        playerToChange.addPastPosition(playerToChange.getPosition());
        playerToChange.setPosition(newPosition);
        playerToChange.setHasMadeMove(true);

        if (playerToChange.hasMadeMove() && playerToChange.isHasDroppedItem()){
            nextTurn();
            return;
        }

        guiController.reDrawGUI(game);

    }

    public void nextTurn(){
        Player playerToChange = game.getPlayers()[game.getPlayerTurn()];

        playerToChange.setHasMadeMove(false);
        playerToChange.setHasDroppedItem(false);
        playerToChange.setMessage("");

        int playerTurn = game.getPlayerTurn();
        int playerBlind = playerToChange.getBlindTime();

        if(playerBlind > 1){
            playerToChange.setBlindTime(playerBlind-1);
            playerToChange.setMessage("You are still stunned");
        }

        if (playerBlind == 1){
            playerToChange.setBlindTime(0);
        }

        if (playerTurn == 0){
            game.setPlayerTurn(1);
            System.out.println("Blind time player 2" + game.getPlayer2().getBlindTime());

        }

        if (playerTurn == 1){
            game.setPlayerTurn(0);
            System.out.println("Blind time player 1" + game.getPlayer1().getBlindTime());
        }

        guiController.reDrawGUI(game);

    }

    public void putDown(){
        Player playerToPut = game.getPlayers()[game.getPlayerTurn()];
        Position positionToPut = playerToPut.getPosition();
        Square[][] squares = game.getGrid().getSquares();

        int inventoryMarker = playerToPut.getInventoryMarker();
        Item[] inventory = playerToPut.getInventory();

        if (inventory[inventoryMarker] == Item.GRENADE){
            putDownGrenade();
            return;
        }

        if (inventory[inventoryMarker] == Item.NOTHING){
            return;
        }

    }

    public void putDownGrenade(){
        Player playerToPut = game.getPlayers()[game.getPlayerTurn()];
        Position positionToPut = playerToPut.getPosition();
        Square[][] squares = game.getGrid().getSquares();

        squares[positionToPut.getY()][positionToPut.getX()] = Square.LIGHTGRENADE_TO_EXPLODE;
        playerToPut.putDownGrenade(positionToPut);

        int inventoryMarker = playerToPut.getInventoryMarker();
        Item[] inventory = playerToPut.getInventory();

        inventory[inventoryMarker] = Item.NOTHING;
        playerToPut.setInventoryCounter(playerToPut.getInventoryCounter()-1);

        playerToPut.setHasDroppedItem(true);
        playerToPut.setMessage("You put down a grenade");
        game.getGrid().printGridState();

        if (playerToPut.hasMadeMove() && playerToPut.isHasDroppedItem()){
            nextTurn();
            return;
        }
        guiController.reDrawGUI(game);

    }

    public void endGame(Player player){
        System.out.println(player.getPlayerName() + " has won");
        System.exit(0);
    }

}
