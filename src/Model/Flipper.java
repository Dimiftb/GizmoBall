package Model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.StrictMath.sin;

public class Flipper implements IGizmo{

    private int xpos;
    private int ypos;
    private int radius =5;
    private int lenght = 30;
    private int rotatedBy = 0;
    private int rotatedBy2 =0;
    private ArrayList<String> triggers;
    private double deltaX;
    private double deltaY;
    private LineSegment line1;
    private LineSegment line2;
    private Circle semiCircle1;
    private Circle semiCircle2;
    private ArrayList<LineSegment> lines;
    private ArrayList<Circle> circles;
    private ArrayList<Integer> boundaryX;
    private ArrayList<Integer> boundaryY;
    private double angle;
    private Color colour;
    private boolean side;
    private boolean active;
    private boolean keyIsPressed;
    private boolean triggered;
    private String id;

    public Flipper(String id,int x, int y, boolean leftSide) {
        colour = Color.green;
        boundaryX= new ArrayList<>();
        boundaryY= new ArrayList<>();
        if(leftSide){
            xpos=x;
            boundaryX.add(x);
            boundaryX.add(x+20);
        }else{
            xpos=x+10;
            boundaryX.add(xpos-10);
            boundaryX.add(xpos-30);
        }
        boundaryY.add(ypos);
        boundaryY.add(ypos+20);
        //xpos = x;
        ypos = y;
        lines = new ArrayList<>();
        circles =new ArrayList<>();
        triggers= new ArrayList<>();
        /*
        line1 = new LineSegment(x,y+radius,x,y+radius+20);
        line2 = new LineSegment(x+(2*radius),y+radius,x+(2*radius),y+radius+20);
        semiCircle2 = new Circle(x+radius,y+radius+20,radius);
        */


        semiCircle1 = new Circle(xpos+radius,y+radius,radius);
        initialState();
        circles.add(semiCircle1);
        saveToArrayList();
        this.angle = 0;
        this.side = leftSide;
        colour = Color.GREEN;
        active=false;
        keyIsPressed=false;
        triggered=false;
        this.id = id;
    }
    private void saveToArrayList(){
        lines.add(line1);
        lines.add(line2);
        circles.add(semiCircle2);
    }

    public void rotate() { //time>=remainder move for the remainder else move for time

            if(getSide()){
                rotateL();
            } else{
                rotateR();
            }

        saveToArrayList();
    }

    private void getDeltaXandY(int degree){
        deltaX =  this.radius-radius*cos(Math.toRadians(degree));
        deltaY = radius*sin(Math.toRadians(degree));
    }

    private void initialState(){
        line1 = new LineSegment(xpos,ypos+radius,xpos,ypos+radius+lenght);
        line2 = new LineSegment(xpos+(2*radius),ypos+radius,xpos+(2*radius),ypos+radius+lenght);
        semiCircle2 = new Circle(xpos+radius,ypos+radius+lenght,radius);
    }



    public void rotateR(){
        if((rotatedBy2==0 || rotatedBy2==-30)&& active){// the flipper is in a horizontal position
            if(rotatedBy2==0){
                rotatedBy2=15;
            }else{rotatedBy2=-15;}
            getDeltaXandY(15);
            this.line1 = new LineSegment(this.getxCoord()+deltaX,this.getyCoord()+radius-deltaY,
                    this.getxCoord()+radius-(this.getLenght()*cos(Math.toRadians(90-15)))-(radius*cos(Math.toRadians(15))),this.getyCoord()+radius-deltaY+(this.getLenght()*sin(Math.toRadians(90-15))));
            this.line2= new LineSegment(this.getxCoord()+ radius+(this.getRadius()*cos(Math.toRadians(15))),this.getyCoord()+radius+(this.getRadius()*sin(Math.toRadians(15))),
                    this.getxCoord()+radius-(this.getLenght()*cos(Math.toRadians(90-15)))+(radius*cos(Math.toRadians(15))),this.getyCoord()+ radius+(this.getLenght()*sin(Math.toRadians(90-15)))+(this.getRadius()*sin(Math.toRadians(15))));
            this.semiCircle2 = new Circle(this.getxCoord()-(this.getLenght()*cos(Math.toRadians(90-15)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(90-15)))+radius,this.getRadius());

        }
        else if( (rotatedBy2==15 || rotatedBy2==-45)){ //&&active
            if(rotatedBy2==15){
                rotatedBy2 = 30; // flipper rotated through 30
            }else {rotatedBy2=-30;}
            getDeltaXandY(30);
            this.line1 = new LineSegment(this.getxCoord()+deltaX,this.getyCoord()+radius-deltaY,
                    this.getxCoord()+radius-(this.getLenght()*cos(Math.toRadians(60)))-(radius*cos(Math.toRadians(30))),this.getyCoord()+radius-deltaY+(this.getLenght()*sin(Math.toRadians(60))));

            // the centre of the second circle after rotation through x degrees is:
            //x= xPos+radius+ length*cos(90-x)
            //y= yPos+radius + radius*sin(x)+length*sin(90-x)
            this.line2= new LineSegment(this.getxCoord()+ radius+(this.getRadius()*cos(Math.toRadians(30))),this.getyCoord()+radius+(this.getRadius()*sin(Math.toRadians(30))),
                    this.getxCoord()+radius-(this.getLenght()*cos(Math.toRadians(60)))+(radius*cos(Math.toRadians(30))),this.getyCoord()+ radius+(this.getLenght()*sin(Math.toRadians(60)))+(this.getRadius()*sin(Math.toRadians(30))));
            this.semiCircle2 = new Circle(this.getxCoord()-(this.getLenght()*cos(Math.toRadians(60)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(60)))+radius,this.getRadius());
        }
        else if((rotatedBy2==30 || rotatedBy2==-60)){
            if(rotatedBy2==30){
                rotatedBy2=45;
            }else{
                rotatedBy2=-45;
            }
            getDeltaXandY(45);
            this.line1 = new LineSegment(this.getxCoord()+deltaX,this.getyCoord()+radius-deltaY,
                    this.getxCoord()+radius-(this.getLenght()*cos(Math.toRadians(90-45)))-(radius*cos(Math.toRadians(90-45))),this.getyCoord()+radius-deltaY+(this.getLenght()*sin(Math.toRadians(90-45))));

            this.line2= new LineSegment(this.getxCoord()+ radius+(this.getRadius()*cos(Math.toRadians(45))),this.getyCoord()+radius+(this.getRadius()*sin(Math.toRadians(45))),
                    this.getxCoord()+radius-(this.getLenght()*cos(Math.toRadians(90-45)))+(radius*cos(Math.toRadians(45))),this.getyCoord()+ radius+(this.getLenght()*sin(Math.toRadians(90-45)))+(this.getRadius()*sin(Math.toRadians(45))));
            this.semiCircle2 = new Circle(this.getxCoord()-(this.getLenght()*cos(Math.toRadians(90-45)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(90-45)))+radius,this.getRadius());

        }
        else if((rotatedBy2==45 || rotatedBy2==-75)){
            if(rotatedBy2==45){
                rotatedBy2=60;
            }else{rotatedBy2=-60;}
            getDeltaXandY(60);
            this.line1 = new LineSegment(this.getxCoord()+deltaX,this.getyCoord()+radius-deltaY, //MAYBE a redundant -1
                    this.getxCoord()+radius-1-(this.getLenght()*cos(Math.toRadians(30)))-(radius*cos(Math.toRadians(60))),this.getyCoord()+radius-deltaY+(this.getLenght()*sin(Math.toRadians(30))));

            this.line2= new LineSegment(this.getxCoord()+ radius+(this.getRadius()*cos(Math.toRadians(60))),this.getyCoord()+radius+(this.getRadius()*sin(Math.toRadians(60))),
                    this.getxCoord()+radius- (this.getLenght()*cos(Math.toRadians(30)))+(radius*cos(Math.toRadians(60))),this.getyCoord()+ radius+(this.getLenght()*sin(Math.toRadians(30)))+(this.getRadius()*sin(Math.toRadians(60))));
            this.semiCircle2 = new Circle(this.getxCoord()-(this.getLenght()*cos(Math.toRadians(30)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(30)))+radius,this.getRadius());
        }

        else if((rotatedBy2==60 || rotatedBy2==90)){
            if(rotatedBy2==60){
                rotatedBy2=75;
            }else {rotatedBy2=-75;}
            getDeltaXandY(75);
            this.line1 = new LineSegment(this.getxCoord()+deltaX,this.getyCoord()+radius-deltaY,
                    this.getxCoord()+radius-(this.getLenght()*cos(Math.toRadians(90-75)))-(radius*cos(Math.toRadians(75))),this.getyCoord()+radius-deltaY+(this.getLenght()*sin(Math.toRadians(90-75))));
            this.line2= new LineSegment(this.getxCoord()+ radius+(this.getRadius()*cos(Math.toRadians(75))),this.getyCoord()+radius+(this.getRadius()*sin(Math.toRadians(75))),
                    this.getxCoord()+radius -(this.getLenght()*cos(Math.toRadians(90-75)))+(radius*cos(Math.toRadians(75))),this.getyCoord()+ radius+(this.getLenght()*sin(Math.toRadians(90-75)))+(this.getRadius()*sin(Math.toRadians(75))));
            this.semiCircle2 = new Circle(this.getxCoord()-(this.getLenght()*cos(Math.toRadians(90-75)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(90-75)))+radius,this.getRadius());

        }
        else if(rotatedBy2==75 ){
            rotatedBy2=90;
            if(triggered){
                active =false;
            }
            this.line1 = new LineSegment(xpos+radius,ypos,xpos+radius-lenght,ypos);
            this.line2 = new LineSegment(xpos+radius,ypos+2*radius,xpos+radius-lenght,ypos+2*radius);
            this.semiCircle2 = new Circle(xpos+radius-lenght,ypos+radius,radius);
        }
        else if(rotatedBy2==-15 ){
            rotatedBy2=0;
            active=false;
            initialState();
        }
        saveToArrayList();
    }



    //ROTATING LEFT FLIPPER
    public void rotateL(){ // try moving flipper for time (double time)
        // CHECKING HOW MUCH THE FLIPPER MOVES
        if((rotatedBy==0  || rotatedBy==-30)&& active){// the flipper is in a horizontal position
            if(rotatedBy==0){
                rotatedBy=15;
            }else{rotatedBy=-15;}
            getDeltaXandY(15);
            this.line1 = new LineSegment(this.getxCoord()+deltaX,this.getyCoord()+radius+deltaY,
                    this.getxCoord()+radius+(this.getLenght()*cos(Math.toRadians(90-15)))-(radius*cos(Math.toRadians(15))),this.getyCoord()+radius+deltaY+(this.getLenght()*sin(Math.toRadians(90-15))));

            // the centre of the second circle after rotation through x degree is:
            //x= xPos+radius+ length*cos(90-x)
            //y= yPos+radius + radius*sin(x)+length*sin(90-x)
            this.line2= new LineSegment(this.getxCoord()+ radius+(this.getRadius()*cos(Math.toRadians(15))),this.getyCoord()+radius-(this.getRadius()*sin(Math.toRadians(15))),
                    this.getxCoord()+radius+ (this.getLenght()*cos(Math.toRadians(90-15)))+(radius*cos(Math.toRadians(15))),this.getyCoord()+ radius+(this.getLenght()*sin(Math.toRadians(90-15)))-(this.getRadius()*sin(Math.toRadians(15))));
            this.semiCircle2 = new Circle(this.getxCoord()+(this.getLenght()*cos(Math.toRadians(90-15)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(90-15)))+radius,this.getRadius());

        }
        else if( (rotatedBy==15 || rotatedBy==-45)){
            if(rotatedBy==15){
                rotatedBy = 30; // flipper rotated through 30
            }else {rotatedBy=-30;}
            getDeltaXandY(30);
            this.line1 = new LineSegment(this.getxCoord()+deltaX,this.getyCoord()+radius+deltaY,
                    this.getxCoord()+radius+(this.getLenght()*cos(Math.toRadians(60)))-(radius*cos(Math.toRadians(30))),this.getyCoord()+radius+deltaY+(this.getLenght()*sin(Math.toRadians(60))));

            this.line2= new LineSegment(this.getxCoord()+ radius+(this.getRadius()*cos(Math.toRadians(30))),this.getyCoord()+radius-(this.getRadius()*sin(Math.toRadians(30))),
                    this.getxCoord()+radius+ (this.getLenght()*cos(Math.toRadians(60)))+(radius*cos(Math.toRadians(30))),this.getyCoord()+ radius+(this.getLenght()*sin(Math.toRadians(60)))-(this.getRadius()*sin(Math.toRadians(30))));
            this.semiCircle2 = new Circle(this.getxCoord()+(this.getLenght()*cos(Math.toRadians(60)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(60)))+radius,this.getRadius());
        }
        else if((rotatedBy==30 || rotatedBy==-60)){
            if(rotatedBy==30){
                rotatedBy=45;
            }else{
                rotatedBy=-45;
            }
            getDeltaXandY(45);
            this.line1 = new LineSegment(this.getxCoord()+deltaX,this.getyCoord()+radius+deltaY,
                    this.getxCoord()+radius+(this.getLenght()*cos(Math.toRadians(90-45)))-(radius*cos(Math.toRadians(90-45))),this.getyCoord()+radius+deltaY+(this.getLenght()*sin(Math.toRadians(90-45))));

            this.line2= new LineSegment(this.getxCoord()+ radius+(this.getRadius()*cos(Math.toRadians(45))),this.getyCoord()+radius-(this.getRadius()*sin(Math.toRadians(45))),
                    this.getxCoord()+radius+ (this.getLenght()*cos(Math.toRadians(90-45)))+(radius*cos(Math.toRadians(45))),this.getyCoord()+ radius+(this.getLenght()*sin(Math.toRadians(90-45)))-(this.getRadius()*sin(Math.toRadians(45))));
            this.semiCircle2 = new Circle(this.getxCoord()+(this.getLenght()*cos(Math.toRadians(90-45)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(90-45)))+radius,this.getRadius());

        }
        else if((rotatedBy==45 || rotatedBy==-75)){
            if(rotatedBy==45){
                rotatedBy=60;
            }else{rotatedBy=-60;}
            getDeltaXandY(60);
            this.line1 = new LineSegment(this.getxCoord()+deltaX,this.getyCoord()+radius+deltaY,
                    this.getxCoord()+radius+(this.getLenght()*cos(Math.toRadians(30)))-(radius*cos(Math.toRadians(60))),this.getyCoord()+radius+deltaY+(this.getLenght()*sin(Math.toRadians(30))));

            this.line2= new LineSegment(this.getxCoord()+ radius+(this.getRadius()*cos(Math.toRadians(60))),this.getyCoord()+radius-(this.getRadius()*sin(Math.toRadians(60))),
                    this.getxCoord()+radius+ (this.getLenght()*cos(Math.toRadians(30)))+(radius*cos(Math.toRadians(60))),this.getyCoord()+ radius+(this.getLenght()*sin(Math.toRadians(30)))-(this.getRadius()*sin(Math.toRadians(60))));
            this.semiCircle2 = new Circle(this.getxCoord()+(this.getLenght()*cos(Math.toRadians(30)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(30)))+radius,this.getRadius());
        }

        else if((rotatedBy==60 || rotatedBy==90)){
            if(rotatedBy==60){
                rotatedBy=75;
            }else {rotatedBy=-75;}
            getDeltaXandY(75);
            this.line1 = new LineSegment(this.getxCoord()+deltaX,this.getyCoord()+radius+deltaY,
                    this.getxCoord()+radius+(this.getLenght()*cos(Math.toRadians(90-75)))-(radius*cos(Math.toRadians(75))),this.getyCoord()+radius+deltaY+1+(this.getLenght()*sin(Math.toRadians(90-75))));

            this.line2= new LineSegment(this.getxCoord()+ radius+(this.getRadius()*cos(Math.toRadians(75))),this.getyCoord()+radius-(this.getRadius()*sin(Math.toRadians(75))),
                    this.getxCoord()+radius+ (this.getLenght()*cos(Math.toRadians(90-75)))+(radius*cos(Math.toRadians(75))),this.getyCoord()+ radius+(this.getLenght()*sin(Math.toRadians(90-75)))-(this.getRadius()*sin(Math.toRadians(75))));
            this.semiCircle2 = new Circle(this.getxCoord()+(this.getLenght()*cos(Math.toRadians(90-75)))+radius,this.getyCoord()+(this.getLenght()*sin(Math.toRadians(90-75)))+radius,this.getRadius());

        }
        else if(rotatedBy==75 ){
            rotatedBy=90;
            active =false;
           if(triggered && keyIsPressed){
               active =true;
           }

            this.line1 = new LineSegment(xpos+radius,ypos+2*radius,xpos+radius+lenght,ypos+2*radius);
            this.line2 = new LineSegment(xpos+radius,ypos,xpos+radius+lenght,ypos);
            this.semiCircle2 = new Circle(xpos+radius+lenght,ypos+radius,radius);
        }
        else if(rotatedBy==-15 ){
            rotatedBy=0;
            initialState();
            active=false;

        }
        saveToArrayList();
    }

    //Getters

    public double getAngle() {
        return angle;
    }

    public Color getColour() {
        return colour;
    }

    @Override
    public void setColour(Color color) {
        colour = color;
    }

    @Override
    public int getxCoord() {
        return xpos;
    }

    @Override
    public int getyCoord() {
        return ypos;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public boolean getKeyIsPressed(){return keyIsPressed;}

    public boolean getTriggered(){return triggered;}



    public void setTriggered(boolean b){triggered=b;}
    public void setKeyIsPressed(boolean v){keyIsPressed=v;}
    public boolean getSide() { return side; }

    public boolean getActive(){return active;}

    public int getRadius(){return radius;}

    public int getLenght(){return lenght;}

    public int getRotatedBy(){return rotatedBy;}

    public LineSegment getLine1(){return line1;}

    public LineSegment getLine2(){return line2;}

    public Circle getSemiCircle1(){return semiCircle1;}

    public Circle getSemiCircle2(){return semiCircle2;}

    public ArrayList<LineSegment> getLines(){return lines;}

    public ArrayList<Circle> getCircles(){return circles;}

    public ArrayList<Integer> getBoundaryX(){return boundaryX;}

    public ArrayList<Integer> getBoundaryY(){return boundaryY;}

    public void setBoundaryX(int newx){
        if(side){
            boundaryX.clear();
            boundaryX.add(newx);
            boundaryX.add(newx+20);
        }else{
            boundaryX.clear();
            boundaryX.add(newx);
            boundaryX.add(newx-20);
        }

    }

    public void setBoundaryY(int newx){
        boundaryY.clear();
        boundaryY.add(newx);
        boundaryY.add(newx+20);
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




    //Setters

    @Override
    public void setxCoord(int xCoord){
        if(side){
            xpos=xCoord;
        }else{
            xpos = xCoord+10;
        }
    }

    @Override
    public  void setyCoord(int yCoord){ypos =yCoord;
        semiCircle1 = new Circle(xpos+radius,ypos+radius,radius);
        initialState();
    }

    public void setRotatedBy(int angle){rotatedBy=angle;}

    public void setLine1(int x,int y,int x2, int y2){ this.line1 = new LineSegment(x,y,x2,y2);}

    public void setLine2(int x,int y,int x2, int y2){ this.line2 = new LineSegment(x,y,x2,y2);}

    public void setSemiCircle1(int x, int y, int r){this.semiCircle1= new Circle(x,y,r);}

    public void setSemiCircle2(int x, int y, int r){this.semiCircle2= new Circle(x,y,r);}

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setSide(boolean side) { this.side = side; }

    public void setActive(boolean active){this.active= active;}

}