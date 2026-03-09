package com.example.map;

public class MarkerStyle {
    // I made these fields 'final' to ensure immutability. 
    // In a Flyweight pattern, shared objects must never change, 
    // otherwise changing one marker's style would accidentally change thousands of others.
    private final String shape;
    private final String color;
    private final int size;
    private final boolean filled;

    public MarkerStyle(String shape, String color, int size, boolean filled) {
        this.shape = shape;
        this.color = color;
        this.size = size;
        this.filled = filled;
    }

    public String getShape() { return shape; }
    public String getColor() { return color; }
    public int getSize() { return size; }
    public boolean isFilled() { return filled; }

    // I removed all setter methods because this object must be read-only to be safely shared.

    @Override
    public String toString() {
        return shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
    }
}

/* Initial code
public class MarkerStyle {
    private String shape;
    private String color;
    private int size;
    private boolean filled;

    public MarkerStyle(String shape, String color, int size, boolean filled) {
        this.shape = shape;
        this.color = color;
        this.size = size;
        this.filled = filled;
    }

    public String getShape() { return shape; }
    public String getColor() { return color; }
    public int getSize() { return size; }
    public boolean isFilled() { return filled; }

    public void setShape(String shape) { this.shape = shape; }
    public void setColor(String color) { this.color = color; }
    public void setSize(int size) { this.size = size; }
    public void setFilled(boolean filled) { this.filled = filled; }

    @Override
    public String toString() {
        return shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
    }
}
*/