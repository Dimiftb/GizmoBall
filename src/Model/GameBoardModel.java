package Model;


import View.GameView;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import javax.sound.sampled.Line;
import java.awt.*;
import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameBoardModel extends Observable {

    private Wall wallObject;
    private ArrayList<IGizmo> gizmos;
    private ArrayList<Ball> balls;
    public Absorber absorber;
    private ArrayList<String> connections;
    private ArrayList<KeyDetails> keyDetails;
    private KeyDetails kd;
    private ArrayList<IGizmo> rotatedGizmos;
    private boolean bool = false;
    Random rand = new Random();
    private float r = rand.nextFloat();
    private float g = rand.nextFloat();
    private float b1 = rand.nextFloat();

    public GameBoardModel() {
        balls = new ArrayList<>();
        connections = new ArrayList<>();
        keyDetails = new ArrayList<>();
        gizmos = new ArrayList<>();
        rotatedGizmos = new ArrayList<>();
        wallObject = new Wall(0, 0, 400, 400);
    }

    public ArrayList<KeyDetails> getKeyDetails() {
        return keyDetails;
    }

    public void activateTriggers(IGizmo iGizmo) {
        for (String id : iGizmo.getTriggers()) {
            IGizmo ig = getGizmobyID(id);
            ig.setColour(new Color(r,g,b1));
            if (ig instanceof Flipper) {
                Flipper f = (Flipper) ig;
                f.setActive(true);
                f.setTriggered(true);

            }
        }
    }


    private CollisionDetails timeUntilCollision(Ball b) {

        Circle ballCircle = b.makeCircle();
        Vect ballVelocity = b.getVelocity();
        ArrayList<LineSegment> wallSegments = wallObject.getLineSegments();
        double time = 0.0;
        double timeTick = Double.MAX_VALUE;
        Vect newVelocity = new Vect(0, 0);


        //absorber collision
        if (absorber != null) {
            LineSegment lineSegment = absorber.getLs();
            time = Geometry.timeUntilWallCollision(lineSegment, ballCircle, ballVelocity);
            if (time < timeTick) {
                timeTick = time;
                System.out.println(time);
                if (time <= 0.05) {
                    absorber.setColour(new Color(r, g, b1));
                    activateTriggers(absorber);
                    b.setxCoord(350.0D);
                    b.setyCoord(390.0D);
                    b.setStopped(true);

                }
            }
        }

        //wall collision
        for (LineSegment wall : wallSegments) {
            time = Geometry.timeUntilWallCollision(wall, ballCircle, ballVelocity);
            if (time < timeTick) {
                timeTick = time;
                newVelocity = Geometry.reflectWall(wall, b.getVelocity(), 1.0);

            }
        }
        for (IGizmo ig : gizmos) {
            if (ig instanceof Square) {
                Square sg = (Square) ig;
                for (LineSegment ls : sg.getLines()) {
                    time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
                    if (time < timeTick) {
                        timeTick = time;
                        if (time <= 0.05) {
                            activateTriggers(ig);
                            sg.setColour(new Color(r, g, b1));
                        }
                        newVelocity = Geometry.reflectWall(ls, b.getVelocity(), 1.0);
                    }
                }

                for (Circle c : sg.getCircles()) {
                    time = Geometry.timeUntilCircleCollision(c, ballCircle, ballVelocity);
                    if (time < timeTick) {
                        timeTick = time;
                        newVelocity = Geometry.reflectCircle(c.getCenter(), b.getCenterPoint(), b.getVelocity(), 1.0);
                        if (time <= 0.05) {
                            activateTriggers(ig);
                            sg.setColour(new Color(r, g, b1));
                        }

                    }
                }

            } else if (ig instanceof Triangle) {
                Triangle t = (Triangle) ig;
                for (LineSegment ls : t.getLines()) {
                    time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
                    if (time < timeTick) {
                        timeTick = time;
                        newVelocity = Geometry.reflectWall(ls, b.getVelocity(), 1.0);
                        if (time <= 0.05) {
                            activateTriggers(ig);
                            t.setColour(new Color(r, g, b1));
                        }
                    }
                }
                for (Circle c : t.getCircles()) {
                    time = Geometry.timeUntilCircleCollision(c, ballCircle, ballVelocity);
                    if (time < timeTick) {
                        if (time <= 0.05) {
                            activateTriggers(ig);
                            t.setColour(new Color(r, g, b1));
                        }
                        timeTick = time;
                        newVelocity = Geometry.reflectCircle(c.getCenter(), b.getCenterPoint(), b.getVelocity(), 1.0);
                    }
                }
            } else if (ig instanceof CircleGizmo) {
                CircleGizmo circleGizmo = (CircleGizmo) ig;
                time = Geometry.timeUntilCircleCollision(circleGizmo.getCircle(), ballCircle, ballVelocity);
                if (time <= timeTick) {
                    System.out.println(time);
                    timeTick = time;
                    newVelocity = Geometry.reflectCircle(circleGizmo.getCircle().getCenter(), b.getCenterPoint(), b.getVelocity(), 1.0);
                    if (time <= 0.05) {
                        activateTriggers(ig);
                        circleGizmo.setColour(new Color(r, g, b1));
                    }
                }
            } else if (ig instanceof Flipper) {
                Flipper flipper = (Flipper) ig;
                time = Geometry.timeUntilWallCollision(flipper.getLine1(), ballCircle, ballVelocity);
                if (time < timeTick) {
                    timeTick = time;
                    newVelocity = Geometry.reflectWall(flipper.getLine1(), b.getVelocity(), 1.0);
                    if (time <= 0.05) {
                        activateTriggers(ig);
                        flipper.setColour(new Color(r, g, b1));
                    }
                }
                time = Geometry.timeUntilWallCollision(flipper.getLine2(), ballCircle, ballVelocity);
                if (time < timeTick) {
                    timeTick = time;
                    newVelocity = Geometry.reflectWall(flipper.getLine2(), b.getVelocity(), 1.0);
                    if (time == 0.05) {
                        activateTriggers(ig);
                        flipper.setColour(new Color(r, g, b1));
                    }
                }
                time = Geometry.timeUntilCircleCollision(flipper.getSemiCircle1(), ballCircle, ballVelocity);
                if (time <= timeTick) {
                    timeTick = time;
                    newVelocity = Geometry.reflectCircle(flipper.getSemiCircle1().getCenter(), b.getCenterPoint(), b.getVelocity(), 1.0);
                    if (time <= 0.05) {
                        activateTriggers(ig);
                        flipper.setColour(new Color(r, g, b1));
                    }
                }
                time = Geometry.timeUntilCircleCollision(flipper.getSemiCircle2(), ballCircle, ballVelocity);
                if (time <= timeTick) {
                    timeTick = time;
                    newVelocity = Geometry.reflectCircle(flipper.getSemiCircle2().getCenter(), b.getCenterPoint(), b.getVelocity(), 1.0);
                    //flipperCollision=true;
                    if (time <= 0.05) {
                        activateTriggers(ig);
                        flipper.setColour(new Color(r, g, b1));
                    }
                }
            }
        }

           /* if(line.isATrigger())
                gizmo = line.getGizmo().trigger();   //returns the gizmo that will be activated
                by the trigger
            else
                gizmo = null;
            */

        return new CollisionDetails(timeTick, newVelocity);
    }


    public void moveBall() {
        double timeTick = 0.05;
        for (Ball b : balls) {
            if (b != null && !b.isStopped()) {
                b.applyGravity();
                b.applyFriction();
                CollisionDetails cd = timeUntilCollision(b);
                double tuc = cd.getTuc();
                if (tuc > timeTick) {
                    b = moveBallForTime(b, timeTick);
                    moveFlippers();
                } else {
                    b = moveBallForTime(b, tuc);
                    b.setVelocity(cd.getVelocity());
                }
            }
            this.setChanged();
            this.notifyObservers();
        }
    }

    private void moveFlippers() {
        for (IGizmo ig : gizmos) {
            if (ig instanceof Flipper) {
                Flipper f = (Flipper) ig;
                if (f.getActive()) {
                    f.rotate();
                }
            }
        }
    }


    private Ball moveBallForTime(Ball ball, double time) {

        double xVel = ball.getVelocity().x();
        double yVel = ball.getVelocity().y();
        double newX = ball.getxCoord() + (xVel * time);
        double newY = ball.getyCoord() + (yVel * time);
        ball.setxCoord(newX);
        ball.setyCoord(newY);
        return ball;
    }

    public void rotate(String id) {

        Triangle tr = null;
        IGizmo iGizmo = getGizmobyID(id);
        if (iGizmo instanceof Triangle) {
            tr = (Triangle) iGizmo;
            rotatedGizmos.add(tr);
            int width = tr.getWidth();
            if (tr.getHypotenuse().p1().x() == tr.getxCoord()
                    && tr.getHypotenuse().p1().y() == (tr.getyCoord() + width)) {
                tr.rotateTriangle();
            } else if (tr.getHypotenuse().p1().x() == tr.getxCoord()
                    && tr.getHypotenuse().p1().y() == tr.getyCoord()) {
                tr.rotateTriangle2();
            } else if (tr.getHypotenuse().p1().x() == (tr.getxCoord() + width)
                    && tr.getHypotenuse().p1().y() == tr.getyCoord()) {
                tr.rotateTriangle3();
            } else {
                tr.rotateTriangle4();
            }
            setChanged();
            notifyObservers();
        } else {
            return;
        }
    }

    public ArrayList<IGizmo> getGizmos() {
        return gizmos;
    }

    public void addSquareGizmo(int x, int y) {
        gizmos.add(new Square("S" + String.valueOf(convertPx(x)) + String.valueOf(convertPx(y)), x, y));
        setChanged();
        notifyObservers();
    }

    public void addBall(Ball b) {
        balls.add(b);
        setChanged();
        notifyObservers();
    }

    public void addCircleGizmo(int x, int y) {
        gizmos.add(new CircleGizmo("C" + String.valueOf(convertPx(x)) + String.valueOf(convertPx(y)), x, y));
        setChanged();
        notifyObservers();
    }

    public void addTriangleGizmo(int x, int y) {
        gizmos.add(new Triangle("T" + String.valueOf(convertPx(x)) + String.valueOf(convertPx(y)), x, y));
        setChanged();
        notifyObservers();
    }

    public void resetBall() {
        for (Ball b : getBalls()) {
            if (b.isStopped()) {
                System.out.println(b.getxCoord() + " " + b.getyCoord());
                if (b.getyCoord() >= 390) {
                    b.setxCoord(390);
                    b.setyCoord(380);
                }
                b.setStopped(false);
                b.setVelocity(new Vect(-20, -200));
                Vect vect = b.getVelocity();
                System.out.println(b.getVelocity());
                System.out.println(vect);
            }
        }
    }

    public void addAbsorber(int x, int y) {
        absorber = new Absorber("A" + String.valueOf(convertPx(x)) + String.valueOf(convertPx(y)), x, y, 400);
        gizmos.add(absorber);
        setChanged();
        notifyObservers();
    }
    public void removeAbsorber() {
        absorber = null;
        setChanged();
        notifyObservers();
    }
    public void addAbsorberBuild(int x, int y, int oldX) {
        absorber = new Absorber("A" + String.valueOf(convertPx(x)) + String.valueOf(convertPx(y)), oldX, y, x - oldX + 20);
        gizmos.add(absorber);
        setChanged();
        notifyObservers();
    }

    public void stopBalls() {
        for (Ball b : balls) {
            b.setStopped(true);
        }
    }

    public void deleteGizmo(int x, int y) {
        gizmos.removeIf(iGizmo -> iGizmo.getxCoord() == x && iGizmo.getyCoord() == y);
        if (!(getGizmobyCoords(x + 10, y) == null)) {
            System.out.println("right flipper is getting deleted");
            gizmos.remove(getGizmobyCoords(x + 10, y));

        }
        setChanged();
        notifyObservers();
    }
    public void deleteBall(int x, int y) {
        balls.removeIf(ball -> ball.getxCoord() == x && ball.getyCoord() == y);
        setChanged();
        notifyObservers();
    }

    public void clearBoard() {

        gizmos.clear();
        balls.clear();
        setChanged();
        notifyObservers();


    }

    public int convertPx(int px) {
        return px / 20;
    }

    public int convertL(int l) {
        return l * 20;
    }

    public int round10th(int x) {
        return x - x % 20;
    }

    public void saveToFile(String fileName) {

        ArrayList<String> content = new ArrayList<>();

        for (IGizmo ig : gizmos) {
            if (ig instanceof Triangle) {
                content.add("Triangle" + " " + ig.getId() + " " + Integer.toString(convertPx(ig.getxCoord())) + " "
                        + Integer.toString(convertPx(ig.getyCoord())));
            } else if (ig instanceof Square) {
                content.add("Square" + " " + ig.getId() + " " + Integer.toString(convertPx(ig.getxCoord())) + " "
                        + Integer.toString(convertPx(ig.getyCoord())));
            } else if (ig instanceof CircleGizmo) {
                content.add("Circle" + " " + ig.getId() + " " + Integer.toString(convertPx(ig.getxCoord())) + " "
                        + Integer.toString(convertPx(ig.getyCoord())));
            } else if (ig instanceof Absorber) {
                content.add("Absorber" + " " + ig.getId() + " " + Integer.toString(convertPx(ig.getxCoord())) + " " + Integer.toString(convertPx(ig.getyCoord())));
            } else if (ig instanceof Flipper) {
                Flipper f = (Flipper) ig;
                if (f.getSide()) {
                    content.add("LeftFlipper" + " " + f.getId() + " " + Integer.toString(convertPx(f.getxCoord())) + " "
                            + Integer.toString(convertPx(f.getyCoord())));
                } else {
                    content.add("RightFlipper" + " " + f.getId() + " " + Integer.toString(convertPx(f.getxCoord()))
                            + " " + Integer.toString(convertPx(f.getyCoord())));
                }

            }
        }

        for (Ball b : balls) {
            content.add("Ball" + " " + b.getId() + " " + Double.toString(b.getxCoord()) + " "
                    + Double.toString(b.getyCoord()) + " " + Double.toString(b.getVelocity().x()) + " "
                    + Double.toString(b.getVelocity().y()));
        }
        // content.add("Absorber" + " " + absorber.getId() + " " +
        // Integer.toString(absorber.getxCoord()) +
        // Integer.toString(absorber.getyCoord()));
        for (IGizmo ig : rotatedGizmos) {
            content.add("Rotate" + " " + ig.getId() + " " + convertPx(ig.getxCoord()) + " " + convertPx(ig.getyCoord()));
        }

        for (String s : connections) {
            content.add("Connect" + " " + s);
        }

        try {

            BufferedWriter printWriter = new BufferedWriter(new FileWriter(fileName));
            for (String line : content) {
                System.out.println(line);
                printWriter.write(line);
                printWriter.newLine();

            }
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile(String fileName) {
        String fn = fileName;
        if (fn == null) {
            return;
        }
        String[] filesuffix = fn.split("\\.");
        if (!filesuffix[1].equals("txt")) {
            System.out.println("File format not valid");
            return;
        }
        String userName = fileName;
        try {
            FileReader fileReader = new FileReader(userName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while (fileReader != null) {
                line = bufferedReader.readLine();
                if (line != null) {
                    String[] lineArray = line.split(" ");
                    switch (lineArray[0]) {
                        case "Square":
                            addSquareGizmo(convertL(Integer.parseInt(lineArray[2])), convertL(Integer.parseInt(lineArray[3])));
                            break;
                        case "Triangle":
                            addTriangleGizmo(convertL(Integer.parseInt(lineArray[2])),
                                    convertL(Integer.parseInt(lineArray[3])));
                            break;
                        case "Circle":
                            addCircleGizmo(convertL(Integer.parseInt(lineArray[2])), convertL(Integer.parseInt(lineArray[3])));
                            break;
                        case "Ball":
                            addBall(new Ball(lineArray[1], (convertL((int) Double.parseDouble(lineArray[2]))) + 10, (convertL((int) Double.parseDouble(lineArray[3]))) + 10,
                                    Double.parseDouble(lineArray[4]), Double.parseDouble(lineArray[5])));
                            break;
                        case "LeftFlipper":
                            addFlipper(convertL(Integer.parseInt(lineArray[2])),
                                    convertL(Integer.parseInt(lineArray[3])), true);
                            break;
                        case "RightFlipper":
                            addFlipper(convertL(Integer.parseInt(lineArray[2])),
                                    convertL(Integer.parseInt(lineArray[3])), false);
                            break;
                        case "Rotate":
                            rotate(lineArray[1]);
                            break;
                        case "Connect":
                            String id1 = lineArray[1];
                            String id2 = lineArray[2];
                            IGizmo iGizmo = getGizmobyID(id1);
                            IGizmo iGizmo2 = getGizmobyID(id2);
                            connectGizmos(iGizmo.getxCoord(), iGizmo.getyCoord(), iGizmo2.getxCoord(), iGizmo2.getyCoord());
                            break;
                        case "Absorber":
                            int x = convertL(Integer.parseInt(lineArray[2]));
                            int y = convertL(Integer.parseInt(lineArray[3]));
                            System.out.println(x + " " + y);
                            addAbsorber(convertL(Integer.parseInt(lineArray[2])), convertL(Integer.parseInt(lineArray[3])));
                            break;
                        case "KeyConnect":
                            if (lineArray[3].equals("down")) {
                                connectKey(Integer.parseInt(lineArray[2]), false, lineArray[4]);
                            } else {
                                connectKey(Integer.parseInt(lineArray[2]), true, lineArray[4]);
                            }
                            break;
                    }
                    for (Ball b : balls) {
                        b.setStopped(true);
                    }

                } else {
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("no such file");
        }
    }


    public void addFlipper(int x, int y, boolean side) {
        if (side) {
            gizmos.add(new Flipper("LF" + String.valueOf(convertPx(x)) + String.valueOf(convertPx(y)), x, y, true));
        } else {
            gizmos.add(new Flipper("RF" + String.valueOf(convertPx(x)) + String.valueOf(convertPx(y)), x, y, false));
        }
        setChanged();
        notifyObservers();
    }

    public Absorber getAbsorber() {
        return absorber;
    }


    public ArrayList<Ball> getBalls() {
        return balls;
    }


    public void setBallSpeed(int x, int y) {
        for (Ball b : balls) {
            b.setVelocity(new Vect(x, y));
        }
    }

    public void moveGizmo(int xCoord1, int yCoord1, int xCoord2, int yCoord2) {
        boolean free = true;
        IGizmo iGizmo = getGizmobyCoords(xCoord1, yCoord1);
        if (iGizmo == null) {
            System.out.println("You can only move existing gizmos");
        } else if (getGizmobyCoords(xCoord2, yCoord2) == null) {
            if (iGizmo instanceof Flipper) {
                System.out.println("moving a flipper from " + xCoord1 + "," + yCoord1 + " to position " + xCoord2 + "," + yCoord2);
                Flipper f = (Flipper) iGizmo;
                f.setBoundaryX(xCoord2);
                f.setBoundaryY(yCoord2);
                for (int i = 0; i < 2; i++) {
                    int x = f.getBoundaryX().get(i);
                    for (int j = 0; j < 2; j++) {
                        int y = f.getBoundaryY().get(j);
                        System.out.println("check x and y : " + x + " " + y);
                        if (!(getGizmobyCoords(x, y) == null)) {
                            free = false;
                        }
                    }
                }
                if (free) {
                    iGizmo.setxCoord(xCoord2);
                    iGizmo.setyCoord(yCoord2);
                } else {
                    f.setBoundaryX(xCoord1);
                    f.setBoundaryY(yCoord1);
                }
            } else {
                for (IGizmo i : gizmos) {
                    if (i instanceof Flipper) {
                        Flipper fl = (Flipper) i;
                        for (int k = 0; k < 2; k++) {
                            int x = fl.getBoundaryX().get(k);
                            for (int l = 0; l < 2; l++) {
                                int y = fl.getBoundaryY().get(l);
                                System.out.println("check x and y : " + x + " " + y);
                                if (x == xCoord2 && y == yCoord2) {
                                    free = false;
                                }
                            }
                        }
                    }
                }
                if (free) {
                    iGizmo.setxCoord(xCoord2);
                    iGizmo.setyCoord(yCoord2);
                }


                for (IGizmo i : gizmos) {
                    System.out.println(i.getxCoord());
                    System.out.println(i.getyCoord());
                }
            }

        }
        IGizmo rf = getGizmobyCoords(xCoord1 + 10, yCoord1);
        if (rf == null) {
            System.out.println("You can only move existing gizmos. That is realy empty");
        } else if (getGizmobyCoords(xCoord2, yCoord2) == null) {
            if (iGizmo instanceof Flipper) {
                System.out.println("moving a flipper from " + xCoord1 + "," + yCoord1 + " to position " + xCoord2 + "," + yCoord2);
                Flipper f = (Flipper) iGizmo;
                f.setBoundaryX(xCoord2);
                f.setBoundaryY(yCoord2);
                for (int i = 0; i < 2; i++) {
                    int x = f.getBoundaryX().get(i);
                    for (int j = 0; j < 2; j++) {
                        int y = f.getBoundaryY().get(j);
                        System.out.println("check x and y : " + x + " " + y);
                        if (!(getGizmobyCoords(x, y) == null)) {
                            free = false;
                        }
                    }
                }
                if (free) {
                    iGizmo.setxCoord(xCoord2);
                    iGizmo.setyCoord(yCoord2);
                } else {
                    f.setBoundaryX(xCoord1);
                    f.setBoundaryY(yCoord1);
                }
            } else {
                for (IGizmo i : gizmos) {
                    if (i instanceof Flipper) {
                        Flipper fl = (Flipper) i;
                        for (int k = 0; k < 2; k++) {
                            int x = fl.getBoundaryX().get(k);
                            for (int l = 0; l < 2; l++) {
                                int y = fl.getBoundaryY().get(l);
                                System.out.println("check x and y : " + x + " " + y);
                                if (x == xCoord2 && y == yCoord2) {
                                    free = false;
                                }
                            }
                        }
                    }
                }
                if (free) {
                    rf.setxCoord(xCoord2);
                    rf.setyCoord(yCoord2);
                }


                for (IGizmo i : gizmos) {
                    System.out.println(i.getxCoord());
                    System.out.println(i.getyCoord());
                }
            }
        } else {
            System.out.println("Empty gizmo cells");
        }
        setChanged();
        notifyObservers();
    }

    public IGizmo getGizmobyCoords(int x, int y) {
        IGizmo gizmo = null;
        for (IGizmo gizmo1 : gizmos) {
            if (gizmo1.getxCoord() == x && gizmo1.getyCoord() == y) {
                gizmo = gizmo1;
            }
        }
        return gizmo;
    }

    public IGizmo getGizmobyID(String id) {
        IGizmo gizmo = null;
        for (IGizmo gizmo1 : gizmos) {
            if (gizmo1.getId().equals(id)) {
                gizmo = gizmo1;
            }
        }
        return gizmo;
    }

    public void connectGizmos(int xCoord1, int yCoord1, int xCoord2, int yCoord2) {
        IGizmo gizmo1 = getGizmobyCoords(xCoord1, yCoord1);
        IGizmo gizmo2 = getGizmobyCoords(xCoord2, yCoord2);
        if (gizmo1 == null || gizmo2 == null) {
            System.out.println("Error gizmos null");
        } else {
            String id2 = gizmo2.getId();
            gizmo1.addTrigger(id2);
        }
    }

    public boolean checkConnections(String id) {
        for (String connection : connections) {
            if (connection.contains(id)) {
                System.out.println("true");
                return true;
            }
        }
        System.out.println("false");
        return false;
    }

    public void disconnectGizmos(int xCoord1, int yCoord1, int xCoord2, int yCoord2) {
        IGizmo gizmo1 = getGizmobyCoords(xCoord1, yCoord1);
        IGizmo gizmo2 = getGizmobyCoords(xCoord2, yCoord2);
        if (gizmo1 == null || gizmo2 == null) {
            System.out.println("Error gizmos null");
        } else {
            String id2 = gizmo2.getId();
            gizmo1.removeTrigger(id2);
        }
    }

    public void connectKey(int keyNo, boolean direction, String id) {
        KeyDetails kd = new KeyDetails(keyNo, id, direction);
        keyDetails.add(kd);
    }

    public void disconnectKey(int keyNo, boolean direction, String id) {
        keyDetails.removeIf(kd -> kd.getId().equals(id) && kd.getKeyNO() == keyNo && kd.getDirection() == direction);
    }

    public boolean checkKeyConnections(int keyNo, String id) {
        for (KeyDetails keyDetail : keyDetails) {
            if (keyDetail.getKeyNO() == keyNo && keyDetail.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void setGravity(double gravity) {
        for (Ball b : balls) {
            b.setGravity(gravity);
        }
    }

    public void setFriction(double friction) {
        for (Ball b : balls) {
            b.setFriction(friction);
        }
    }


}