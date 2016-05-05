package com.duncangb.life;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class LifeController implements Initializable {
    @FXML private Canvas board;
    @FXML private Label information;
    private LifeBoardModel model;
    private GraphicsContext gfx;
    private Color color1 = Color.rgb(0, 255, 0);
    private Color color2 = Color.rgb(255, 255, 0);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new LifeBoardModel(125, 125);

        for (int x = 0; x < model.getBoardWidth(); x++) {
            for (int y = 0; y < model.getBoardHeight(); y++) {
                if (Math.random() > 0.50) {
                    if (Math.random() > 0.50) {
                        model.changeLifeAt(x, y, new Life(true, color1));
                    } else {
                        model.changeLifeAt(x, y, new Life(true, color2));
                    }
                } else {
                    model.changeLifeAt(x, y, new Life(false, Color.rgb(0, 0, 0)));
                }
            }
        }

        gfx = board.getGraphicsContext2D();
        drawBoard();
    }

    @FXML
    public void handleNextGeneration(Event event) {
        model.nextTurn();
        drawBoard();
    }

    @FXML
    public void handleCanvasMousePress(Event event) {

    }

    private void drawBoard() {
        int cell_w = (int) board.getWidth() / model.getBoardWidth();
        int cell_h = (int) board.getHeight() / model.getBoardHeight();

        information.setText(String.format("Generation: %d, Population: %d", model.getCurrentGeneration(),
                model.getLivingCells()));

        for (int x = 0; x < model.getBoardWidth(); x++) {
            for (int y = 0; y < model.getBoardHeight(); y++) {
                gfx.setFill(model.getLifeAt(x, y).getColor());
                gfx.fillRect(x * cell_w, y * cell_h, cell_w, cell_h);
            }
        }
    }
}
