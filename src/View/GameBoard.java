package View;
import Controller.BuildListener;
import Controller.KeyListener;
import Controller.PointerListener;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class GameBoard extends JPanel implements Observer {
    private static final long serialVersionUID = 1L;
    private int board_width;
    private int board_height;
    private GameBoardModel model;
    private MouseListener ml;
    private String mode;

    public GameBoard(int width, int height, GameBoardModel gm, String m, PointerListener pl) {
        super();
        board_height = height;
        board_width = width;
        setBackground(Color.WHITE);
        setFocusable(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        gm.addObserver(this);
        model = gm;
        ml = pl;
        addMouseListener(ml);
        mode = m;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(board_width, board_height);
    }

    private void bPaintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        board_width = getSize().width;
        board_height = getSize().height;
        int rows = 20;
        int columns = 20;
        int rowHeight = board_height / rows;
        int rowWidth = board_width / columns;
        g2.setColor(Color.black);
        for (int i = 0; i < rows; i++) {
            //rows
            g2.drawLine(0, i * rowHeight, board_width, i * rowHeight);
            //columns
            g2.drawLine(i * rowWidth, 0, i * rowWidth, board_height);
        }
       rPaintComponent(g);
    }

    private void rPaintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : model.getBalls()) {
            if (b != null) {
                g2.setColor(b.getColour());
                int x = (int) (b.getxCoord() - b.getRadius());
                int y = (int) (b.getyCoord() - b.getRadius());
                int width = (int) (2 * b.getRadius());
                g2.fillOval(x, y, width, width);
            }
        }
        Absorber a = model.getAbsorber();
        if (a != null) {
            g2.setColor(a.getColour());
            g2.fillRect(a.getxCoord(), a.getyCoord(), a.getWidth(), 20);
        }
        for(IGizmo ig: model.getGizmos()) {
            if( ig instanceof  Square) {
                g2.setColor(ig.getColour());
                g2.fillRect(ig.getxCoord(), ig.getyCoord(), ig.getWidth(), ig.getWidth());
            }
            else if( ig instanceof CircleGizmo) {
                CircleGizmo c = (CircleGizmo) ig;
                g2.setColor(c.getColour());
                int width = (int) c.getCircle().getRadius() * 2;
                int x = (int) (c.getCircle().getCenter().x() - c.getRadius());
                int y = (int) (c.getCircle().getCenter().y() - c.getRadius());
                g2.fillOval(x, y, width, width);
            }
            else if(ig instanceof Triangle) {
                Triangle tr = (Triangle) ig;
                int[] xPoints = {(int) tr.getTopLeft().getCenter().x(), (int) tr.getTopRight().getCenter().x(), (int) tr.getBottomLeft().getCenter().x()};
                int[] yPoints = {(int) tr.getTopLeft().getCenter().y(), (int) tr.getTopRight().getCenter().y(), (int) tr.getBottomLeft().getCenter().y()};
                g2.setColor(tr.getColour());
                g2.fillPolygon(xPoints, yPoints, 3);
            }
            else if(ig instanceof Flipper) {
                Flipper fl = (Flipper) ig;
                int[] xPoints = {(int)fl.getLine1().p1().x(), (int) fl.getLine1().p2().x(),(int) fl.getLine2().p2().x(),(int) fl.getLine2().p1().x()};
                int[] yPoints = {(int)fl.getLine1().p1().y(),(int) fl.getLine1().p2().y(), (int) fl.getLine2().p2().y(),(int) fl.getLine2().p1().y()};
                int[] xPointss = {(int)fl.getLine1().p1().x(),(int)fl.getLine2().p1().x(),(int)fl.getLine2().p2().x(),(int)fl.getLine1().p2().x()};
                int[] yPointss = {(int)fl.getLine1().p1().y(),(int)fl.getLine2().p1().y(),(int)fl.getLine2().p2().y(),(int)fl.getLine1().p2().y()};
                g2.setColor(fl.getColour());g2.fillArc((int)fl.getSemiCircle1().getCenter().x()-fl.getRadius(),(int)fl.getSemiCircle1().getCenter().y()-fl.getRadius(),2*fl.getRadius(),2*fl.getRadius(),0,360);
                g2.fillArc((int)fl.getSemiCircle2().getCenter().x()-fl.getRadius(),(int)fl.getSemiCircle2().getCenter().y()-fl.getRadius(),2*fl.getRadius(),2*fl.getRadius(),0,360);

                //g2.setColor(Color.PINK);

                g2.fillPolygon(xPoints,yPoints,4);


            }
        }

        // Draw all of the flippers
       /* for (Flipper f : model.getFlippers()) {
            g2.setColor(f.getColour());
            RoundRectangle2D.Double rectangle = new RoundRectangle2D.Double(f.getX(), f.getY(), 70, 10, 10, 10);
            AffineTransform transform = new AffineTransform();

            if (f.getActive()) {
                f.setAngle(f.getAngle() + 10);
                if (f.getAngle() > 90) {
                    f.setAngle(90);
                }
            }
            else {
                f.setAngle(f.getAngle() - 10);
                if (f.getAngle() < 0) {
                    f.setAngle(0);
                }
            }

            transform.rotate(Math.toRadians(f.getAngle()), f.getX() + 5, f.getY() + 5);
            if(f.getSide()){
                try {
                    transform.invert();
                }
                catch (NoninvertibleTransformException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Shape newRectangle = transform.createTransformedShape(rectangle);
            g2.fill(newRectangle);
        }*/


    }


    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
        switch (mode) {
            case "Build":
                bPaintComponent(g);
                break;
            case "Run":
                rPaintComponent(g);
                break;
            default:
                System.out.println("Error painting the GameBoard");
                break;
        }

    }

    protected void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        repaint();
    }
}
