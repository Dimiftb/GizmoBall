package Model;

import physics.LineSegment;

public class VerticalLine {
    private int xCoord;
    private int yCoord;
    private LineSegment lineSegment;
    private int width;

    public VerticalLine(int x, int y, int w){
        xCoord = x;
        yCoord = y;
        width = w;
        lineSegment = new LineSegment(x,y, x +w,y);
    }
    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public LineSegment getLineSegment() {
        return lineSegment;
    }

    public int getWidth() {
        return width;
    }
}
