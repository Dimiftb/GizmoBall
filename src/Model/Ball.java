package Model;

import physics.Circle;
import physics.Vect;

import java.awt.*;

import static java.lang.Math.abs;

public class Ball {
    private double xCoord;
    private double yCoord;
    private double radius;
    private Vect velocity;
    private Color colour;
    private Boolean stopped;
    private Vect centerPoint;
    private double friction;
    private String id;
    private double gravity;


    public Ball(String id, double x, double y, double xv, double yv) {
        xCoord = x; // Centre coordinates
        yCoord = y;
        colour = Color.BLUE;
        velocity = new Vect(xv, yv);
        radius = 10;
        stopped = false;
        this.id = id;
    }

    public Circle makeCircle() {
        Circle c = new Circle(xCoord, yCoord, radius);
        centerPoint = c.getCenter();
        return c;
    }

    public void applyGravity() {
        double time = 0.05;
        gravity = gravity * 0.05 * 30;
        this.setVelocity(this.getVelocity().plus(new Vect(0, gravity)));
    }

    public void applyFriction() {
        double mu1 = 0.0;
        double mu2 = 0.0;
        if (friction == 0) {
            mu1 = 0.0;
        } else if (friction == 25) {
            mu1 = 0.025;
            mu2 = 0.025;
        } else if (friction == 50) {
            mu1 = 0.05;
            mu2 = 0.05;
        } else if (friction > 0 && friction < 25) {
            mu1 = 0.0125;
            mu2 = 0.0125;
        } else if (friction > 25 && friction < 50) {
            mu1 = 0.0375;
            mu2 = 0.0375;
        }

        double delta_t = 0.05;
        Vect newVelocty = this.getVelocity().times(1 - (mu1 * delta_t) - (mu2 * abs(this.getVelocity().length()) * delta_t / 30));
        this.setVelocity(newVelocty);
        friction = Math.round(newVelocty.length() * 100) / 100.0;
    }


    public double getxCoord() {
        return xCoord;
    }

    public double getFriction() {
        return friction;
    }

    public double getyCoord() {
        return yCoord;
    }

    public double getRadius() {
        return radius;
    }

    public Vect getVelocity() {
        return velocity;
    }

    public Color getColour() {
        return colour;
    }

    public Boolean isStopped() {
        return stopped;
    }

    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setVelocity(Vect velocity) {
        this.velocity = velocity;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public void setStopped(Boolean stopped) {
        this.stopped = stopped;
    }

    public Vect getCenterPoint() {
        return centerPoint;
    }

    public String getId() {
        return id;
    }

    public void setFriction(double friction) {
        this.friction = friction;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
}
