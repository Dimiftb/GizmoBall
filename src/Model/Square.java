package Model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

public class Square implements IGizmo {
    private int xCoord;
    private int yCoord;
    private int width;
    private LineSegment s1;
    private LineSegment s2;
    private LineSegment s3;
    private LineSegment s4;
    private Circle c1;
    private Circle c2;
    private Circle c3;
    private Circle c4;
    private ArrayList<LineSegment> lines;
    private ArrayList<Circle> circles;
    private String id;
    private ArrayList<String> triggers;
    private Color colour;

    public Square(String idName, int x, int y) {
        colour = Color.PINK;
        lines = new ArrayList<>();
        circles = new ArrayList<>();
        triggers = new ArrayList<>();
        xCoord = x;
        yCoord = y;
        width = 20;
        id = idName;
        s1 = new LineSegment(x, y, x + width, y);
        c1 = new Circle(x, y, 0);
        s2 = new LineSegment(x, y, x, y + width);
        c2 = new Circle(x + width, y, 00);
        s3 = new LineSegment(x + width, y, x + width, y + width);
        c3 = new Circle(x, y + width, 00);
        s4 = new LineSegment(x, y + width, x + width, y + width);
        c4 = new Circle(x + width, y + width, 0);
        lines.add(s1);
        lines.add(s2);
        lines.add(s3);
        lines.add(s4);
        circles.add(c1);
        circles.add(c2);
        circles.add(c3);
        circles.add(c4);

    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public String getId() {
        return id;
    }

    @Override
    public ArrayList<String> getTriggers() {
        return triggers;
    }
    @Override
    public void addTrigger(String id) {
        triggers.add(id);
    }
    @Override
    public void removeTrigger(String id) {
        triggers.removeIf(tid -> tid.equals(id));
    }

    @Override
    public Color getColour() {
        return colour;
    }

    @Override
    public void setColour(Color color) {
        colour = color;
    }

    public LineSegment getS1() {
        return s1;
    }

    public LineSegment getS2() {
        return s2;
    }

    public LineSegment getS3() {
        return s3;
    }

    public LineSegment getS4() {
        return s4;
    }

    public Circle getC1() {
        return c1;
    }

    public Circle getC2() {
        return c2;
    }

    public Circle getC3() {
        return c3;
    }

    public Circle getC4() {
        return c4;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<LineSegment> getLines() {
        return lines;
    }

    public ArrayList<Circle> getCircles() {
        return circles;
    }

    @Override
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    @Override
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setS1(LineSegment s1) {
        this.s1 = s1;
    }

    public void setS2(LineSegment s2) {
        this.s2 = s2;
    }

    public void setS3(LineSegment s3) {
        this.s3 = s3;
    }

    public void setS4(LineSegment s4) {
        this.s4 = s4;
    }

    public void setC1(Circle c1) {
        this.c1 = c1;
    }

    public void setC2(Circle c2) {
        this.c2 = c2;
    }

    public void setC3(Circle c3) {
        this.c3 = c3;
    }

    public void setC4(Circle c4) {
        this.c4 = c4;
    }
}