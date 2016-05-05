package com.duncangb.life;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LifeBoardModelTest {
    public static final int GENERATION_TEST_THRESHOLD = 100;

    public LifeBoardModel simpleBlock;
    public LifeBoardModel simpleBeehive;
    public LifeBoardModel simpleBlinker;
    public LifeBoardModel dieOut;

    @Before
    public void setUp() throws Exception {
        Color white = Color.rgb(255, 255, 255);

        simpleBlock = new LifeBoardModel(5, 5);
        simpleBlock.changeLifeAt(1, 1, new Life(true, white));
        simpleBlock.changeLifeAt(2, 1, new Life(true, white));
        simpleBlock.changeLifeAt(1, 2, new Life(true, white));
        simpleBlock.changeLifeAt(2, 2, new Life(true, white));

        simpleBeehive = new LifeBoardModel(6, 5);
        simpleBeehive.changeLifeAt(1, 2, new Life(true, white));
        simpleBeehive.changeLifeAt(2, 1, new Life(true, white));
        simpleBeehive.changeLifeAt(2, 3, new Life(true, white));
        simpleBeehive.changeLifeAt(3, 1, new Life(true, white));
        simpleBeehive.changeLifeAt(3, 3, new Life(true, white));
        simpleBeehive.changeLifeAt(4, 2, new Life(true, white));

        simpleBlinker = new LifeBoardModel(3, 3);
        simpleBlinker.changeLifeAt(0, 1, new Life(true, white));
        simpleBlinker.changeLifeAt(1, 1, new Life(true, white));
        simpleBlinker.changeLifeAt(2, 1, new Life(true, white));

        dieOut = new LifeBoardModel(1, 1);
        dieOut.changeLifeAt(0, 0, new Life(true, white));
    }

    @Test
    public void testSimpleBlockModel() {
        assertEquals(simpleBlock.getBoardHeight(), 5);
        assertEquals(simpleBlock.getBoardWidth(), 5);

        for (int i = 0; i < GENERATION_TEST_THRESHOLD; i++) {
            assertEquals(simpleBlock.getLivingCells(), 4);
            assertEquals(simpleBlock.getDeadCells(), 21);
            simpleBlock.nextTurn();
        }
    }

    @Test
    public void testSimpleBeehiveModel() {
        assertEquals(simpleBeehive.getBoardHeight(), 5);
        assertEquals(simpleBeehive.getBoardWidth(), 6);

        for (int i = 0; i < 100; i++) {
            assertEquals(simpleBeehive.getLivingCells(), 6);
            assertEquals(simpleBeehive.getDeadCells(), 24);

            assertEquals(simpleBeehive.getLifeAt(1, 2).isAlive(), true);
            assertEquals(simpleBeehive.getLifeAt(2, 1).isAlive(), true);
            assertEquals(simpleBeehive.getLifeAt(2, 3).isAlive(), true);
            assertEquals(simpleBeehive.getLifeAt(3, 1).isAlive(), true);
            assertEquals(simpleBeehive.getLifeAt(3, 3).isAlive(), true);
            assertEquals(simpleBeehive.getLifeAt(4, 2).isAlive(), true);

            simpleBeehive.nextTurn();
        }
    }

    @Test
    public void testSimpleBlinker() {
        assertEquals(simpleBlinker.getBoardHeight(), 3);
        assertEquals(simpleBlinker.getBoardWidth(), 3);

        for (int i = 0; i < GENERATION_TEST_THRESHOLD; i++) {
            assertEquals(simpleBlinker.getLivingCells(), 3);
            assertEquals(simpleBlinker.getDeadCells(), 6);

            if (i % 2 == 0) {
                assertEquals(simpleBlinker.getLifeAt(0, 1).isAlive(), true);
                assertEquals(simpleBlinker.getLifeAt(1, 1).isAlive(), true);
                assertEquals(simpleBlinker.getLifeAt(2, 1).isAlive(), true);
            } else {
                assertEquals(simpleBlinker.getLifeAt(1, 0).isAlive(), true);
                assertEquals(simpleBlinker.getLifeAt(1, 1).isAlive(), true);
                assertEquals(simpleBlinker.getLifeAt(1, 2).isAlive(), true);
            }

            simpleBlinker.nextTurn();
        }
    }

    @Test
    public void testDieOut() {
        dieOut.nextTurn();

        assertEquals(dieOut.getLivingCells(), 0);
        assertEquals(dieOut.getDeadCells(), 1);
    }
}