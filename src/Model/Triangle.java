package Model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

public class Triangle implements IGizmo {
    private int xCoord;
    private int yCoord;
    private int width;
    private LineSegment top;
    private LineSegment leftSide;
    private LineSegment hypotenuse;
    private Circle topLeft;
    private Circle topRight;
    private Circle bottomLeft;
    private ArrayList<LineSegment> lines;
    private ArrayList<Circle> circles;
    private String id;
    private ArrayList<String> triggers;
    private Color colour;

    public Triangle(String idName, int x, int y) { // it should take a name as a parameter Triangle(int x, int y, String id)
        colour = Color.magenta;
        xCoord = x;
        yCoord = y;
        width = 20;
        id = idName;
        lines = new ArrayList<>();
        circles = new ArrayList<>();
        triggers = new ArrayList<>();
        top = new LineSegment(x, y, x + width, y);
        leftSide = new LineSegment(x, y, x, y + width);
        hypotenuse = new LineSegment(x, y + width, x + width, y);
        topLeft = new Circle(x, y, 0);
        topRight = new Circle(x + width, y, 0);
        bottomLeft = new Circle(x, y + width, 0);
        addAllLinesAndCircles();

    }

    public void rotateTriangle() {
        this.setTop(new LineSegment(this.xCoord, this.yCoord, this.getxCoord() + width, this.getyCoord()));
        this.setLeftSide(new LineSegment(this.getxCoord() + width, this.getyCoord(), this.getxCoord() + width, this.getyCoord() + width));
        this.setHypotenuse(new LineSegment(this.xCoord, this.yCoord, this.getxCoord() + width, this.getyCoord() + width));
        this.setBottomLeft(new Circle(this.getxCoord(), this.getyCoord(), 0));
        this.setTopLeft(new Circle(this.getxCoord() + width, this.getyCoord(), 0));
        this.setTopRight(new Circle(this.getxCoord() + width, this.getyCoord() + width, 0));
        lines.clear();
        circles.clear();
        addAllLinesAndCircles();

    }

    public void rotateTriangle2() {
        this.setTop(new LineSegment(this.xCoord + width, this.yCoord + width, this.getxCoord(), this.getyCoord() + width));
        this.setLeftSide(new LineSegment(this.xCoord + width, this.yCoord + width, this.getxCoord() + width, this.getyCoord()));
        this.setHypotenuse(new LineSegment(this.getxCoord() + width, this.getyCoord(), this.getxCoord(), this.getyCoord() + width));
        this.setBottomLeft(new Circle(this.getxCoord() + width, this.getyCoord(), 0));
        this.setTopLeft(new Circle(this.getxCoord() + width, this.getyCoord() + width, 0));
        this.setTopRight(new Circle(this.getxCoord(), this.getyCoord() + width, 0));
        lines.clear();
        circles.clear();
        addAllLinesAndCircles();
    }

    public void rotateTriangle3() {
        this.setTop(new LineSegment(this.getxCoord(), this.getyCoord() + width, this.getxCoord(), this.getyCoord()));
        this.setLeftSide(new LineSegment(this.getxCoord(), this.getyCoord() + width, this.getxCoord() + width, this.getyCoord() + width));
        this.setHypotenuse(new LineSegment(this.getxCoord() + width, this.getyCoord() + width, this.getxCoord(), this.getyCoord()));
        this.setBottomLeft(new Circle(this.getxCoord() + width, this.getyCoord() + width, 0));
        this.setTopLeft(new Circle(this.getxCoord(), this.getyCoord() + width, 0));
        this.setTopRight(new Circle(this.getxCoord(), this.getyCoord(), 0));
        lines.clear();
        circles.clear();
        addAllLinesAndCircles();

    }

    public void rotateTriangle4() {
        this.setTop(new LineSegment(this.getxCoord(), this.getyCoord(), this.getxCoord() + width, this.getyCoord()));
        this.setLeftSide(new LineSegment(this.getxCoord(), this.getyCoord(), this.getxCoord(), this.getyCoord() + width));
        this.setHypotenuse(new LineSegment(this.getxCoord(), this.getyCoord() + width, this.getxCoord() + width, this.getyCoord()));
        this.setTopLeft(new Circle(this.getxCoord(), this.getyCoord(), 0));
        this.setTopRight(new Circle(this.getxCoord() + width, this.getyCoord(), 0));
        this.setBottomLeft(new Circle(this.getxCoord(), this.getyCoord() + width, 0));
        lines.clear();
        circles.clear();
        addAllLinesAndCircles();
    }

    public void addAllLinesAndCircles() {
        lines.add(top);
        lines.add(leftSide);
        lines.add(hypotenuse);
        circles.add(topLeft);
        circles.add(topRight);
        circles.add(bottomLeft);
    }

    public int getxCoord() {
        return xCoord;
    }


    public int getyCoord() {
        return yCoord;
    }

    @Override
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
        this.setTop(new LineSegment(xCoord, yCoord, xCoord + width, yCoord));
        this.setLeftSide( new LineSegment(xCoord, yCoord, xCoord, yCoord + width));
        this.setHypotenuse(new LineSegment(xCoord, yCoord + width, xCoord + width, yCoord));
        this.setTopLeft(new Circle(xCoord, yCoord, 0));
        this.setTopRight(new Circle(xCoord + width, yCoord, 0));
        this.setBottomLeft(new Circle(xCoord, yCoord + width, 0));
    }

    @Override
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
        int width = this.getWidth();
        if (this.getHypotenuse().p1().x() == this.getxCoord()
                && this.getHypotenuse().p1().y() == (this.getyCoord() + width)) {
            this.rotateTriangle();
        } else if (this.getHypotenuse().p1().x() == this.getxCoord()
                && this.getHypotenuse().p1().y() == this.getyCoord()) {
            this.rotateTriangle2();
        } else if (this.getHypotenuse().p1().x() == (this.getxCoord() + width)
                && this.getHypotenuse().p1().y() == this.getyCoord()) {
            this.rotateTriangle3();
        } else {
            this.rotateTriangle4();
        }
    }


    public int getWidth() {
        return width;
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

    public String getId() {
        return id;
    }

    @Override
    public ArrayList<String> getTriggers() {
        return triggers;
    }


    public LineSegment getTop() {
        return top;
    }

    public LineSegment getLeftSide() {
        return leftSide;
    }

    public LineSegment getHypotenuse() {
        return hypotenuse;
    }

    public Circle getTopLeft() {
        return topLeft;
    }

    public Circle getTopRight() {
        return topRight;
    }

    public Circle getBottomLeft() {
        return bottomLeft;
    }

    public ArrayList<LineSegment> getLines() {
        return lines;
    }

    public ArrayList<Circle> getCircles() {
        return circles;
    }

    public void setTop(LineSegment top) {
        this.top = top;
    }

    public void setLeftSide(LineSegment leftSide) {
        this.leftSide = leftSide;
    }

    public void setHypotenuse(LineSegment hypotenuse) {
        this.hypotenuse = hypotenuse;
    }

    public void setTopLeft(Circle topLeft) {
        this.topLeft = topLeft;
    }

    public void setTopRight(Circle topRight) {
        this.topRight = topRight;
    }

    public void setBottomLeft(Circle bottomLeft) {
        this.bottomLeft = bottomLeft;
    }
}