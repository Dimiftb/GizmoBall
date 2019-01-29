package View;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class GameBoard extends JPanel implements Observer {

    private int board_width;
    private int board_height;
    protected Shape shapeModel;


    public GameBoard(int width, int height) {
        super();
        board_height = height;
        board_width = width;
        setBackground(Color.BLACK);
    }

    private void initBoard(GameView parent) {

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(board_width,board_height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;
        board_width = getSize().width;
        board_height = getSize().height;
        int rows = 20;
        int columns = 20;
        int rowHeight = board_height/rows;
        int rowWidth = board_width/columns;
        g.setColor(Color.CYAN);
       for(int i = 0; i < rows; i++ ) {
            //rows
            g.drawLine(0,i*rowHeight, board_width, i*rowHeight);
            //columns
            g.drawLine(i *rowWidth,0,i*rowWidth,board_height);
        }
            g.setColor(Color.GREEN);
            g.fillRect(120,180,20,20);
            //flipper
            g.setColor(Color.RED);
            g.fillOval(200,180,20,20);
            g.fillRect(210,180,20,20);
            g.fillRect(230,180,20,20);
            g.fillOval(240,180,20,20);
            int x [] = {160,180,180};
            int y [] = {160,160,180};
            g.setColor(Color.MAGENTA);
            g.fillPolygon(x,y,3);








    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
