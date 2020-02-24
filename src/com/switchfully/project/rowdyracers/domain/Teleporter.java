package com.switchfully.project.rowdyracers.domain;

public class Teleporter {
    private Position currentPosition;
    private Teleporter destinationTeleporter;

    public Teleporter(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setDestinationTeleporter(Teleporter destinationTeleporter) {
        this.destinationTeleporter = destinationTeleporter;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Teleporter getDestinationTeleporter() {
        return destinationTeleporter;
    }
}
