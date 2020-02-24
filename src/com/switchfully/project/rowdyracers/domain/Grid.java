package com.switchfully.project.rowdyracers.domain;

import java.util.Random;

public class Grid {
    private int dimension;
    private Square[][] squares;

    public Grid(int dimension) {
        this.dimension = dimension;
        squares = new Square[dimension][dimension];
    }

    public void initializeGrid(){
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                squares[i][j] = Square.EMPTY;
            }
        }
    }

    public void initializeWalls(){
        int wallTiles = 0;
        int maxHorizontalLength = dimension/2;
        int maxVerticalLength = dimension/2;


        //Make a wallFactor between 0.15 and 0.20
        double wallFactor = (double)((new Random().nextInt(5))+15)/100;
        System.out.println(wallFactor);



        //Max. 20% of the total squares can be covered by a wall. As long as the number of Walltiles stays under 20%, new walls will be made
        while (wallTiles <= (wallFactor*(dimension * dimension))){
            //Here a new wall gets made

            int wallDirectionId = new Random().nextInt(4);
            Direction direction = Direction.values()[wallDirectionId];



            if (direction == Direction.NORTH){
                //Determine the max length of a wall. It should always be bigger then 2, and should be smaller then 50% of the ver. length
                int tilesToAdd = new Random().nextInt(((maxVerticalLength-2) +1))+2;

                //Check if the wallTiles don't exceed the 20% rule
                if ((wallTiles + tilesToAdd) >= (wallFactor*(dimension * dimension)) ){
                    break;
                }else{
                    int x = new Random().nextInt((((dimension-2)-1)+1))+1;
                    int y = new Random().nextInt((((dimension-2-tilesToAdd)-1)+1))+1;

                    for (int i = 0; i < tilesToAdd; i++){
                        squares[y+i][x] = Square.WALL;
                    }

                    wallTiles = wallTiles + tilesToAdd;

                }

            }

            if(direction == Direction.SOUTH){
                int tilesToAdd = new Random().nextInt(((maxVerticalLength-2) +1))+2;

                if ((wallTiles + tilesToAdd) >= (wallFactor*(dimension * dimension)) ){
                    break;
                }else{
                    int x = new Random().nextInt((((dimension-2)-1)+1))+1;
                    int y = new Random().nextInt((((dimension-1)-(tilesToAdd+1)+1)))+(tilesToAdd+1);
                    for (int i = tilesToAdd; i > 0; i--){
                        squares[y-i][x] = Square.WALL;
                    }
                    wallTiles = wallTiles + tilesToAdd;
                }

            }

            if (direction == Direction.EAST){
                int tilesToAdd = new Random().nextInt(((maxHorizontalLength-2) +1))+2;

                if ((wallTiles + tilesToAdd) >= (0.2*(dimension * dimension)) ){
                    break;
                }else{
                    int x = new Random().nextInt(((dimension-1-tilesToAdd) - 1)+ 1) + 1;
                    int y = new Random().nextInt((((dimension-2)-1)+1))+1;
                    for (int i = 0; i < tilesToAdd; i++){
                        squares[y][x+i] = Square.WALL;
                    }
                    wallTiles = wallTiles + tilesToAdd;

                }

            }

            if (direction == Direction.WEST){
                int tilesToAdd = new Random().nextInt(((maxHorizontalLength-2) +1))+2;

                if ((wallTiles + tilesToAdd) >= (0.2*(dimension * dimension)) ){
                    break;
                }else{
                    int x = new Random().nextInt((((dimension-1) - (tilesToAdd+1))+ 1)) + (tilesToAdd+1);
                    int y = new Random().nextInt((((dimension-2)-1)+1))+1;
                    for (int i = tilesToAdd; i > 0; i--){
                        squares[y][x-i] = Square.WALL;
                    }
                    wallTiles = wallTiles + tilesToAdd;

                }

            }






        }
    }

    public void initializeGrenades(){
        int grenades = new Random().nextInt(((int)(0.05*(dimension*dimension)) + 2) - ((int)(0.05*(dimension*dimension)) - 2) + 1)+ ((int)(0.05*(dimension*dimension)) - 2);
        int grenadesPutDown = 0;

        //Initialize the grenade in the start-field of player1
        int grenade1X = new Random().nextInt(2);
        int grenade1Y = new Random().nextInt((dimension-1) - (dimension - 3) + 1) + (dimension -3);

        squares[grenade1Y][grenade1X] = Square.LIGHTGRENADE_PICK_UP;
        grenadesPutDown++;

        ////Initialize the grenade in the start-field of player2
        int grenade2Y = new Random().nextInt(2);
        int grenade2X = new Random().nextInt((dimension-1) - (dimension - 3) + 1) + (dimension -3);

        squares[grenade2Y][grenade2X] = Square.LIGHTGRENADE_PICK_UP;
        grenadesPutDown++;


        //Initialize the other grenades
        while(grenadesPutDown < grenades){
            int grenadeX = new Random().nextInt((dimension-2)-1) + 1;
            int grenadeY = new Random().nextInt((dimension-2)-1) + 1;

            if (squares[grenadeY][grenadeX] == Square.EMPTY){
                squares[grenadeY][grenadeX] = Square.LIGHTGRENADE_PICK_UP;
                grenadesPutDown++;
            }

        }

    }

    public Position[] initializePowerFailures(Position[] oldPowerFailures){

        //Remove the former power failures
        if (oldPowerFailures != null) {
            for (int i = 0; i < oldPowerFailures.length; i++){
                int oldX = oldPowerFailures[i].getX();
                int oldY = oldPowerFailures[i].getY();

                squares[oldY][oldX] = Square.EMPTY;

            }
        }

        int powerFailures = new Random().nextInt(((int)(0.05*(dimension*dimension)) + 2) - ((int)(0.05*(dimension*dimension)) - 2) + 1)+ ((int)(0.05*(dimension*dimension)) - 2);
        System.out.println("Power failures" + powerFailures);
        int failuresPutDown = 0;
        Position[] newPowerFailures = new Position[powerFailures];

        while(failuresPutDown < powerFailures){
            int powerFailureX = new Random().nextInt((dimension-2)-1) + 1;
            int powerFailureY = new Random().nextInt((dimension-2)-1) + 1;

            if (squares[powerFailureY][powerFailureX] == Square.EMPTY){
                squares[powerFailureY][powerFailureX] = Square.POWER_FAILURE;
                newPowerFailures[failuresPutDown] = new Position(powerFailureX, powerFailureY);
                failuresPutDown++;
            }

        }

        return newPowerFailures;
    }

    public void  printGridState(){
        for (int i = 0; i < dimension; i++){
            System.out.print('\n');
            for (int j = 0; j < dimension; j++){
                System.out.print("[" + squares[i][j].getStateId() + "]");
            }
        }
        System.out.print('\n');
        System.out.println("=====================================");
    }

    public Square[][] getSquares() {
        return squares;
    }

    public int getDimension() {
        return dimension;
    }

    public Teleporter[] initializeTeleporters(){
        int maxTeleporters = (int) (0.03 * (dimension * dimension));
        Teleporter[] teleporters = new Teleporter[maxTeleporters];
        int addedTeleporters = 0;



        while (addedTeleporters < maxTeleporters){
            int teleportX = new Random().nextInt((dimension-2)-1) + 1;
            int teleportY = new Random().nextInt((dimension-2)-1) + 1;

            if (squares[teleportY][teleportX] == Square.EMPTY){
                squares[teleportY][teleportX]  = Square.TELEPORTER;
                Position positionOfTeleporter = new Position(teleportX, teleportY);
                Teleporter telePorterToAdd = new Teleporter(positionOfTeleporter);
                teleporters[addedTeleporters] = telePorterToAdd;
                System.out.println("Added teleporter");
                addedTeleporters++;
            }
        }

        //set the destination teleporters for each teleporter
        for (int i = 0; i < teleporters.length; i++){
            if (i == teleporters.length -1) teleporters[i].setDestinationTeleporter(teleporters[0]);
            else{
                teleporters[i].setDestinationTeleporter(teleporters[i+1]);
            }
        }
        return teleporters;
    }

    public void initializeDiscs (){

        //Initialize a charged disc
        boolean chargedDiscIsPut = false;

        while (!chargedDiscIsPut) {
            int chargedDiscPosition = new Random().nextInt((dimension - 2)) + 1;

            if(squares[chargedDiscPosition][chargedDiscPosition] == Square.EMPTY){
                squares[chargedDiscPosition][chargedDiscPosition] = Square.CHARGED_DISC;
                chargedDiscIsPut = true;
            }
        }

        //Initialize the uncharged discs
        int unchargedDiscs = new Random().nextInt(((int)(0.02*(dimension*dimension)) + 2) - (2) + 1)+ (2);
        int discsPutDown = 0;

        while(discsPutDown < unchargedDiscs){
            int discX = new Random().nextInt((dimension-2)-1) + 1;
            int discY = new Random().nextInt((dimension-2)-1) + 1;

            if (squares[discY][discX] == Square.EMPTY){
                squares[discY][discX] = Square.UNCHARGED_DISC;
                discsPutDown++;
            }

        }

    }
}
