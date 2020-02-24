package com.switchfully.project.rowdyracers.domain;

public class Position {
    private int x;
    private int y;

    public Position(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass())return false;
        Position position = (Position)obj;
        if (position.getY() == this.getY() && position.getX() == this.getX()) return true;
        return false;
    }

    public boolean isAdjacentTo(Position position){
        if (this.x == (position.getX()+1) && this.y == (position.getY())) return true;
        if (this.x == (position.getX() -1) && this.y == (position.getY())) return true;
        if (this.y == (position.getY() +1) && this.x == (position.getX())) return true;
        if (this.y == (position.getY()-1) && this.x == (position.getX())) return true;
        return false;
    }
}
