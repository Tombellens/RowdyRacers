package com.switchfully.project.rowdyracers.domain;

public enum Square {
    EMPTY('E'), WALL('W'), LIGHTGRENADE_PICK_UP('P'), LIGHTGRENADE_TO_EXPLODE('T'),
    LIGHTGRENADE_EXPLODED('E'), POWER_FAILURE('F'), TELEPORTER('R'), CHARGED_DISC('C'), UNCHARGED_DISC('U');

    private char stateId;

    Square(char stateId) {
        this.stateId = stateId;
    }

    public char getStateId() {
        return stateId;
    }
}
