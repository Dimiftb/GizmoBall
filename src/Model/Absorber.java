package Model;

import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

public class Absorber implements IGizmo{
    private int xCoord;
    private int yCoord;
    private int width;
    private LineSegment ls;
    private Color colour;
    String id;
    private ArrayList<String> triggers;

    public Absorber(String id,int x1, int y1, int w) {
        colour = Color.red;
        xCoord = x1;
        yCoord = y1;
        width = w;
        colour = Color.RED;
        ls = new LineSegment(xCoord,yCoord,xCoord+ width,yCoord);
        triggers = new ArrayList<>();
        this.id = id;
    }

    public LineSegment getLs() {
        return ls;
    }

    public Color getColour() {
        return colour;
    }

    @Override
    public void setColour(Color color) {
        colour = color;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    @Override
    public void setxCoord(int xCoord) {

    }

    @Override
    public void setyCoord(int yCoord) {

    }

    public int getWidth() {
        return width;
    }

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
}
