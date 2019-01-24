package View;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameBoard extends JPanel {

    private final int BOARD_WIDTH = 20;
    private final int BOARD_HEIGHT = 20;


    private HashMap<Integer, Integer > shapes;

    public GameBoard(GameView parent) {


    }

    private void initBoard(GameView parent) {

    }

    private void drawSquare(int coord_x, int coord_y, Shape.GeometricShape geo_shape, Color color) {

    }

    private Shape.GeometricShape getShapeAtLocation(int coord_x, int coord_y) {
        return null;
    }

    private void drawComponent(Graphics g) {

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponent(g);
    }

}
