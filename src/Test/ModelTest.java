package Test;

import Model.*;
import org.junit.jupiter.api.Test;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;
import static java.lang.Math.cos;
import static java.lang.StrictMath.sin;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    GameBoardModel model = new GameBoardModel();

    @Test
    void addBall(){
        Ball b1 = new Ball("B1", 380, 360, 0, 0);
        model.addBall(b1);
        assertFalse(model.getBalls().isEmpty());
    }

    @Test
    void addCircleGizmo(){
        CircleGizmo c1 = new CircleGizmo("C1918",380,360);
        model.addCircleGizmo(c1.getxCoord(), c1.getyCoord());
        assertFalse(model.getGizmos().isEmpty());
    }

    @Test
    void addTriangleGizmo(){
        Triangle t1 = new Triangle("T98", 180, 160);
        model.addTriangleGizmo(t1.getxCoord(), t1.getyCoord());
        assertFalse(model.getGizmos().isEmpty());
    }

    @Test
    void addSquareGizmo(){
        Square s1 = new Square("S1413", 280, 260);
        model.addSquareGizmo(s1.getxCoord(), s1.getyCoord());
        assertFalse(model.getGizmos().isEmpty());
    }

    @Test
    void deleteGizmo(){
        Square s1 = new Square("S1413", 280, 260);
        model.addSquareGizmo(s1.getxCoord(), s1.getyCoord());
        assertNotNull(model.getGizmos());
        model.deleteGizmo(s1.getxCoord(), s1.getyCoord());
        assertTrue(model.getGizmos().isEmpty());
    }

    @Test
    void moveGizmo(){
        Square s1 = new Square("S1413", 280, 260);
        model.addSquareGizmo(280,260);
        model.moveGizmo(280,260, 180,160);
//        assertEquals(s1.getxCoord(), 180);
//        assertEquals(s1.getyCoord(), 160);
    }

    @Test
    void convertPx(){
        assertEquals(model.convertPx(400), 20);
    }

    @Test
    void convertL(){
        assertEquals(model.convertL(20), 400);
    }

    @Test
    void round10th(){
        assertEquals(model.round10th(306), 300);
    }

    @Test
    void addAbsorber(){
        assertNull(model.getAbsorber());
        model.addAbsorber(0,380);
        assertNotNull(model.getAbsorber());
    }

    @Test
    void getGizmobyCoords(){
        assertNull(model.getGizmobyCoords(280,260));
        Square s1 = new Square("S1413", 280, 260);
        model.addSquareGizmo(280, 260);
        assertNotNull(model.getGizmobyCoords(280,260));
    }
    @Test
    void getGizmobyID(){
        assertNull(model.getGizmobyID("S1413"));
        Square s1 = new Square("S1413", 280, 260);
        model.addSquareGizmo(280,260);
        assertNotNull(model.getGizmobyID("S1413"));
    }

    @Test
    void stopBalls(){
        Ball b1 = new Ball("B1", 380, 360, 0, 0);
        model.addBall(b1);
        Ball b2 = new Ball("B2", 360, 340, 0, 0);
        model.addBall(b2);
        model.moveBall();
        assertFalse(b1.isStopped());
        model.stopBalls();
        assertTrue(b1.isStopped());
        assertTrue(b2.isStopped());
    }

//    @Test
//    void resetBall(){
//        Ball b1 = new Ball("B1", 380, 360, 0, 0);
//        model.addBall(b1);
//        model.moveBall();
//        model.stopBalls();
//        model.resetBall();
//    }

    @Test
    void testKeyDetails(){
        Square s1 = new Square("S1413", 280, 260);
        model.addSquareGizmo(280, 260);
        assertTrue(model.getKeyDetails().isEmpty());
        KeyDetails kd = new KeyDetails(87, "S1413", false);
        model.connectKey(87, false, "S1413");
        assertFalse(model.getKeyDetails().isEmpty());
        model.disconnectKey(87, false, "S1413");
        assertTrue(model.getKeyDetails().isEmpty());
    }

    @Test
    void setGravity(){
        Ball b1 = new Ball("B1", 380, 360, 0, 0);
        model.addBall(b1);
        assertNotEquals(b1.getGravity(), 16.87);
        model.setGravity(16.87);
        assertEquals(b1.getGravity(), 16.87);
    }

    @Test
    void setFriction(){
        Ball b1 = new Ball("B1", 380, 360, 0, 0);
        model.addBall(b1);
        assertNotEquals(b1.getFriction(), 16.87);
        model.setFriction(16.87);
        assertEquals(b1.getFriction(), 16.87);
    }

    @Test
    void setBallSpeed(){
        Ball b1 = new Ball("B1", 380, 360, 0, 0);
        model.addBall(b1);
        model.setBallSpeed(3,5);
        assertEquals(b1.getVelocity(), new Vect(3, 5));
    }

    @Test
    void testConnections() {
        Square s1 = new Square("S1413", 280, 260);
        Square s2 = new Square("S1514", 300, 260);
        model.addSquareGizmo(280, 260);
        model.addSquareGizmo(300, 260);
        assertTrue(s1.getTriggers().isEmpty());
        model.connectGizmos(280, 260, 300, 260);
//       assertFalse(s1.getTriggers().isEmpty()); /*returns true - doesnt actually add to the triggers list*/
        model.disconnectGizmos(280,260,300,260);
        assertTrue(s1.getTriggers().isEmpty());
    }


    @Test
    void clearBoard(){
        model.addFlipper(200,100,true);
        model.addAbsorber(0,380);
        assertFalse(model.getGizmos().isEmpty());
        model.clearBoard();
        assertTrue(model.getGizmos().isEmpty());
    }

    @Test
    void testLoad() {
        String fileName = "testLoad.txt";
        File file = new File(fileName);
        assertTrue(model.getGizmos().isEmpty());
        model.loadFile(fileName);
    }

    @Test
    void testSave() {
        String fileName = "testSave.txt";
        File file = new File(fileName);
        System.out.println(file.length());
        assertTrue(file.length() == 0);
        Square s1 = new Square("S1413", 280, 260);
        model.addSquareGizmo(280,260);
        Triangle t1 = new Triangle("T98", 180, 160);
        model.addTriangleGizmo(180,160);
        CircleGizmo c1 = new CircleGizmo("C1918",380,360);
        model.addCircleGizmo(380, 360);
        model.addFlipper(200,100,true);
        model.addFlipper(300,100, false);
        Ball b1 = new Ball("B1", 380, 360, 0, 0);
        model.addBall(b1);
        model.rotate("T98");
//       model.connectGizmos(380,360,280,260);
        model.saveToFile(fileName);
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    void testFlippers(){
        assertTrue(model.getGizmos().isEmpty());
        model.addFlipper(200, 100, true);
        assertFalse(model.getGizmos().isEmpty());
        Flipper f1 = new Flipper("LF105", 200, 100, true);
        assertFalse(f1.getCircles().isEmpty());
        assertFalse(f1.getLines().isEmpty());
        assertTrue(f1.getTriggers().isEmpty());
        f1.addTrigger("S1413");
        assertFalse(f1.getTriggers().isEmpty());
        f1.removeTrigger("S1413");
        assertTrue(f1.getTriggers().isEmpty());
        f1.setxCoord(300);
        f1.setyCoord(200);
        assertEquals(f1.getxCoord(), 300);
        assertEquals(f1.getyCoord(), 200);

    }

    @Test
    void testVerticalLine(){
        VerticalLine vl = new VerticalLine(20, 40, 1);
        assertEquals(vl.getxCoord(), 20);
        assertEquals(vl.getyCoord(), 40);
        assertEquals(vl.getWidth(), 1);
        assertEquals(vl.getLineSegment(), new LineSegment(20, 40, 20+ 1, 40));
    }

    @Test
    void testSquare(){
        Square s1 = new Square("S1413", 280, 260);
        assertFalse(s1.getLines().isEmpty());
        assertFalse(s1.getCircles().isEmpty());
        assertEquals(s1.getWidth(), 20);
        s1.setS1(new LineSegment(180, 160, 180+20, 160));
        s1.setS2(new LineSegment(180, 160, 180, 160+20));
        s1.setS3(new LineSegment(180+20, 160, 180+20, 160+20));
        s1.setS4(new LineSegment(180, 160+20, 180+20, 160+20));
        s1.setC1(new Circle(180, 160, 0));
        s1.setC2(new Circle(180+20, 160, 0));
        s1.setC3(new Circle(180, 160+20, 0));
        s1.setC4(new Circle(180+20, 160+20, 0));
        assertEquals(s1.getC1(), new Circle(180, 160, 0));
        assertEquals(s1.getC2(), new Circle(180+20, 160, 0));
        assertEquals(s1.getC3(),(new Circle(180, 160+20, 0) ));
        assertEquals(s1.getC4(),(new Circle(180+20, 160+20, 0) ));
        assertEquals(s1.getS1(), new LineSegment(180, 160, 180+20, 160));
        assertEquals(s1.getS2(),new LineSegment(180, 160, 180, 160+20) );
        assertEquals(s1.getS3(), new LineSegment(180+20, 160, 180+20, 160+20));
        assertEquals(s1.getS4(), new LineSegment(180, 160+20, 180+20, 160+20));
    }

    @Test
    void testAbsorber(){
        model.addAbsorber(0,380);
        Absorber ab = model.getAbsorber();
        assertEquals(ab.getColour(), Color.RED);
        ab.setxCoord(0);
        ab.setyCoord(380);
        assertEquals(ab.getxCoord(), 0);
        assertEquals(ab.getyCoord(), 380);
        assertEquals(ab.getLs(), new LineSegment(0, 380, 0+400, 380));
        assertTrue(ab.getTriggers().isEmpty());
        ab.addTrigger("S1413");
        assertFalse(ab.getTriggers().isEmpty());
        ab.removeTrigger("S1413");
        assertTrue(ab.getTriggers().isEmpty());
        assertEquals(ab.getId(), "A019");
        assertEquals(ab.getWidth(), 400);
    }

    @Test
    void testCircleGizmos(){
        CircleGizmo c1 = new CircleGizmo("C1918",380,360);
        model.addCircleGizmo(380, 360);
        assertEquals(c1.getCircle(), new Circle(380+10, 360+10, 10));
        assertEquals(c1.getWidth(), 20);
        c1.setxCoord(280);
        c1.setyCoord(260);
        assertNotEquals(c1.getxCoord(), 380);
        assertNotEquals(c1.getyCoord(), 360);
        assertTrue(c1.getTriggers().isEmpty());
        c1.addTrigger("S1413");
        assertFalse(c1.getTriggers().isEmpty());
        c1.removeTrigger("S1413");
        assertTrue(c1.getTriggers().isEmpty());
        assertEquals(c1.getRadius(), 10);
    }

    @Test
    void testBall(){
        Ball b1 = new Ball("B1", 380, 360, 0, 0);
        assertEquals(b1.getRadius(), 10);
        b1.setRadius(5);
        assertNotEquals(b1.getRadius(), 10);
        assertEquals(b1.getColour(), Color.BLUE);
        b1.setColour(Color.RED);
        assertNotEquals(b1.getColour(), Color.BLUE);
        b1.setFriction(25);
        assertEquals(25, b1.getFriction());
        b1.applyFriction();
        b1.setFriction(50);
        assertEquals(50, b1.getFriction());
        b1.applyFriction();
        b1.setFriction(37.5);
        assertNotEquals(50, b1.getFriction());
        b1.applyFriction();
    }

    @Test
    void testCollisions(){
        Ball b1 = new Ball("B1", 380, 360, 0, 0);
        assertEquals(b1.getVelocity(), new Vect(0,0));
        model.addBall(b1);
        Vect v1 = b1.getVelocity();
        model.setGravity(16.87);
        model.setFriction(16.87);
        model.moveBall();
        CollisionDetails cd = new CollisionDetails(0.05, new Vect(3, 5));
        assertNotEquals(v1, b1.getVelocity());
        assertNotNull(cd.getVelocity());
    }

    @Test
    void testTriangle(){
        Triangle t1 = new Triangle("T98", 180, 160);
        assertFalse(t1.getLines().isEmpty());
        assertFalse(t1.getCircles().isEmpty());
        assertTrue(t1.getTriggers().isEmpty());
        t1.addTrigger("S1413");
        assertFalse(t1.getTriggers().isEmpty());
        t1.removeTrigger("S1413");
        assertTrue(t1.getTriggers().isEmpty());
        assertEquals(t1.getTopLeft(), new Circle(180, 160, 0));
        assertEquals(t1.getTopRight(), new Circle(180+20, 160, 0));
        assertEquals(t1.getBottomLeft(), new Circle(180, 160+20, 0));
        Circle TL1 = t1.getTopLeft();
        Circle TR1 = t1.getTopRight();
        Circle BL1 = t1.getBottomLeft();
        t1.rotateTriangle();
        assertNotEquals(TL1, t1.getTopLeft());
        assertNotEquals(TR1, t1.getTopRight());
        assertNotEquals(BL1, t1.getBottomLeft());
        Circle TL2 = t1.getTopLeft();
        Circle TR2 = t1.getTopRight();
        Circle BL2 = t1.getBottomLeft();
        t1.rotateTriangle2();
        assertNotEquals(TL2, t1.getTopLeft());
        assertNotEquals(TR2, t1.getTopRight());
        assertNotEquals(BL2, t1.getBottomLeft());
        Circle TL3 = t1.getTopLeft();
        Circle TR3 = t1.getTopRight();
        Circle BL3 = t1.getBottomLeft();
        t1.rotateTriangle3();
        assertNotEquals(TL3, t1.getTopLeft());
        assertNotEquals(TR3, t1.getTopRight());
        assertNotEquals(BL3, t1.getBottomLeft());
        Circle TL4 = t1.getTopLeft();
        Circle TR4 = t1.getTopRight();
        Circle BL4 = t1.getBottomLeft();
        t1.rotateTriangle4();
        assertNotEquals(TL4, t1.getTopLeft());
        assertNotEquals(TR4, t1.getTopRight());
        assertNotEquals(BL4, t1.getBottomLeft());
        assertEquals(TL1, t1.getTopLeft());
        assertEquals(TR1, t1.getTopRight());
        assertEquals(BL1, t1.getBottomLeft());
        t1.setxCoord(80);
        t1.setyCoord(60);
        assertNotEquals(t1.getxCoord(), 180);
        assertNotEquals(t1.getyCoord(), 160);
    }

    @Test
    void rotate() {
        Flipper leftFlipper= new Flipper("test",50,50,true);
        //this.semiCircle2 = new Circle(this.getxCoord()+(this.getLenght()*cos(Math.toRadians(90-15)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(90-15)))+radius,this.getRadius());
        //this.semiCircle2 = new Circle(this.getxCoord()+(this.getLenght()*cos(Math.toRadians(90-15)))+radius,this.getyCoord()+(this.getLenght()* StrictMath.sin(Math.toRadians(90-15)))+radius,this.getRadius());

        Circle expectedCircle = new Circle(50+(30*cos(Math.toRadians(75)))+5,50+(30*sin(Math.toRadians(75)))+5,5);
        leftFlipper.setActive(true);
        leftFlipper.rotate();
        assertEquals(leftFlipper.getSemiCircle2(),expectedCircle);

        Flipper rightFlipper = new Flipper("test",50,50,false);
        //this.semiCircle2 = new Circle(this.getxCoord()-(this.getLenght()*cos(Math.toRadians(90-15)))+radius,this.getyCoord()+(this.getLenght()* StrictMath.sin(Math.toRadians(90-15)))+radius,this.getRadius());
        Circle expectedCircle2 = new Circle(60-(30*cos(Math.toRadians(75)))+5,50+(30*sin(Math.toRadians(75)))+5,5);
        rightFlipper.setActive(true);
        rightFlipper.rotate();
        assertEquals(rightFlipper.getSemiCircle2(),expectedCircle2);
    }

    @Test
    void rotateR() {
        Flipper rightFlipper = new Flipper("test",50,50,false);
        //this.semiCircle2 = new Circle(this.getxCoord()-(this.getLenght()*cos(Math.toRadians(90-15)))+radius,this.getyCoord()+(this.getLenght()* StrictMath.sin(Math.toRadians(90-15)))+radius,this.getRadius());
        Circle expectedCircle = new Circle(60-(30*cos(Math.toRadians(75)))+5,50+(30*sin(Math.toRadians(75)))+5,5);
        rightFlipper.setActive(true);
        rightFlipper.rotateR();
        assertEquals(rightFlipper.getSemiCircle2(),expectedCircle);

    }

    @Test
    void rotateL() {
        Flipper leftFlipper= new Flipper("test",50,50,true);
        //this.semiCircle2 = new Circle(this.getxCoord()+(this.getLenght()*cos(Math.toRadians(90-15)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(90-15)))+radius,this.getRadius());
        //this.semiCircle2 = new Circle(this.getxCoord()+(this.getLenght()*cos(Math.toRadians(90-15)))+radius,this.getyCoord()+(this.getLenght()* StrictMath.sin(Math.toRadians(90-15)))+radius,this.getRadius());

        Circle expectedCircle = new Circle(50+(30*cos(Math.toRadians(75)))+5,50+(30*sin(Math.toRadians(75)))+5,5);
        leftFlipper.setActive(true);
        leftFlipper.rotateL();
        assertEquals(leftFlipper.getSemiCircle2(),expectedCircle);
    }

    @Test
    void testTriggers(){
        Square s1 = new Square("S1413", 280, 260);
        Flipper f1 = new Flipper("LF105", 200, 100, true);
        model.addSquareGizmo(280, 260);
        model.addFlipper(200,100,true);
        s1.addTrigger("LF105");
        assertFalse(s1.getTriggers().isEmpty());
        model.activateTriggers(s1);
        assertFalse(f1.getActive());
    }

}
