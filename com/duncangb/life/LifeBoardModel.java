package com.duncangb.life;

import javafx.scene.paint.Color;

public class LifeBoardModel {
    public static final Color DEAD_COLOR = Color.color(0.0, 0.0, 0.0);

    private Life[][] this_turn;
    private int board_width;
    private int board_height;
    private int current_generation;

    public LifeBoardModel(int board_width, int board_height) {
        this.board_width  = board_width;
        this.board_height = board_height;
        this.current_generation = 1;

        this_turn = new Life[board_width][board_height];

        for (int x = 0; x < board_width; x++) {
            for (int y = 0; y < board_height; y++) {
                this_turn[x][y] = new Life(false, DEAD_COLOR);
            }
        }
    }

    public int getBoardWidth() {
        return this.board_width;
    }

    public int getBoardHeight() {
        return this.board_height;
    }

    public int getCurrentGeneration() {
        return this.current_generation;
    }

    public void nextTurn() {
        Life[][] New = new Life[getBoardWidth()][getBoardHeight()];

        for (int x = 0; x < getBoardWidth(); x++) {
            for (int y = 0; y < getBoardHeight(); y++) {
                New[x][y] = new Life(false, DEAD_COLOR);
            }
        }

        for (int x = 0; x < getBoardWidth(); x++) {
            for (int y = 0; y < getBoardHeight(); y++) {
                int living_neighbor_count = countLivingNeighbors(x, y);
                Life current_life = this_turn[x][y];

                if (current_life.isAlive()) {
                    if (living_neighbor_count == 2 || living_neighbor_count == 3) {
                        New[x][y].makeLive();
                        New[x][y].setColor(generateChildColor(x, y));
                    } else {
                        New[x][y].kill();
                        New[x][y].setColor(DEAD_COLOR);
                    }
                } else {
                    if (living_neighbor_count == 3) {
                        New[x][y].makeLive();
                        New[x][y].setColor(generateChildColor(x, y));
                    } else {
                        New[x][y].kill();
                        New[x][y].setColor(DEAD_COLOR);
                    }
                }
            }
        }

        this_turn = New;
        current_generation++;
    }

    public void changeLifeAt(int x, int y, Life life) {
        checkXYInput(x, y);

        if (life == null) {
            throw new IllegalArgumentException("Null life value provided.");
        }

        this_turn[x][y] = life;
    }

    public Life getLifeAt(int x, int y) {
        checkXYInput(x, y);

        return this_turn[x][y];
    }

    public int getLivingCells() {
        int living_count = 0;

        for (int x = 0; x < getBoardWidth(); x++) {
            for (int y = 0; y < getBoardHeight(); y++) {
                if (this_turn[x][y].isAlive()) {
                    living_count++;
                }
            }
        }

        return living_count;
    }

    public int getDeadCells() {
        return (getBoardWidth() * getBoardHeight()) - getLivingCells();
    }

    private void checkXYInput(int x, int y) {
        if (x < 0 || x >= getBoardWidth()) {
            throw new ArrayIndexOutOfBoundsException("X value given is out of bounds of the board.");
        }

        if (y < 0 || y >= getBoardHeight()) {
            throw new ArrayIndexOutOfBoundsException("Y value given is out of bounds of the board.");
        }
    }

    private int countLivingNeighbors(int x, int y) {
        int count = 0;

        for (int a = x - 1; a <= x + 1; a++) {
            for (int b = y - 1; b <= y + 1; b++) {
                if (a < 0 || a >= getBoardWidth() || b < 0 || b >= getBoardHeight() || (a == x && b == y)) {
                    continue;
                }

                if (this_turn[a][b].isAlive()) {
                    count++;
                }
            }
        }

        return count;
    }

    private Color generateChildColor(int x, int y) {
        double red_sum   = 0;
        double green_sum = 0;
        double blue_sum  = 0;
        int count = 0;

        for (int a = x - 1; a <= x + 1; a++) {
            for (int b = y - 1; b <= y + 1; b++) {
                if (a < 0 || a >= getBoardWidth() || b < 0 || b >= getBoardHeight() || (a == x && b == y)) {
                    continue;
                }

                if (this_turn[a][b].isAlive()) {
                    red_sum   += this_turn[a][b].getRed();
                    green_sum += this_turn[a][b].getGreen();
                    blue_sum  += this_turn[a][b].getBlue();
                    count++;
                }
            }
        }

        return Color.color(red_sum / count, green_sum / count, blue_sum / count);
    }
}
