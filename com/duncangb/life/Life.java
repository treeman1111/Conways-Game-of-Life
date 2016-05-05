package com.duncangb.life;

import javafx.scene.paint.Color;

public class Life {
    private Color color;
    private boolean alive;

    public Life(boolean alive, Color color) {
        this.alive = alive;
        this.color = color;
    }

    public double getRed() {
        return this.color.getRed();
    }

    public double getGreen() {
        return this.color.getGreen();
    }

    public double getBlue() {
        return this.color.getBlue();
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color c) {
        if (c == null) {
            throw new IllegalArgumentException("Provide a non-null argument, please.");
        }

        this.color = c;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void makeLive() {
        this.alive = true;
    }

    public void kill() {
        this.alive = false;
    }

    @Override
    public String toString() {
        return String.format("%b, %s", this.alive, this.color);
    }
}
