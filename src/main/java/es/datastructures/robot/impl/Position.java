package es.datastructures.robot.impl;

public class Position {
    private final int width;
    private final int height;

    Position(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String toString() {
        return String.format("(%s, %s)", this.width+1, this.height+1);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
