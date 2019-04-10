package Model;

import physics.Circle;

import java.awt.*;
import java.util.ArrayList;

public class CircleGizmo implements IGizmo {
    private int xCoord;
    private int yCoord;
    private int radius;
    private Color colour;
    private Circle circle;
    private String id;
    private ArrayList<String> triggers;

    public CircleGizmo(String idName,int x, int y) {
        colour = Color.GRAY;
        xCoord = x;
        yCoord = y;
        radius = 10;
        circle = new Circle(xCoord+ 10,yCoord + 10,10);
       id = idName;
       triggers = new ArrayList<>();

    }

    public Circle getCircle(){return circle;}

    public int getxCoord() {
        return xCoord;
    }


    public int getyCoord() {
        return yCoord;
    }

    public int getWidth(){return radius*2;}

    public String getId(){return id;}

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

    public double getRadius() {
        return radius;
    }

    public Color getColour() {
        return colour;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
        circle = new Circle(xCoord + 10, yCoord + 10, radius);

    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
        circle = new Circle(xCoord + 10, yCoord + 10, radius);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }
}
