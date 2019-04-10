package Controller;

import Model.Ball;
import Model.Flipper;
import Model.GameBoardModel;
import Model.IGizmo;
import View.GameView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PointerListener implements MouseListener {
    private GameBoardModel model;
    private BuildListener buildListener;
    private KeyListener keyListener;
    private int x;
    private int y;
    private int oldX;
    private int oldY;
    private boolean store = true;
    private int keyNo;
    private GameView gameView;

    public PointerListener(GameBoardModel m, BuildListener bl, KeyListener ml, GameView gv) {
        model = m;
        buildListener = bl;
        keyListener = ml;
        gameView = gv;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = model.round10th(e.getX());
        y = model.round10th(e.getY());

        if (buildListener.getAction().equals(" ")) {
            return;
        }
        if (buildListener.isAddGizmo()) {

            for (IGizmo iGizmo : model.getGizmos()) {
                if (iGizmo.getxCoord() == x && iGizmo.getyCoord() == y) {
                    // gameView.getMessageLabel().setText("Connect clicked");
                   gameView.getMessageLabel().setText("New gizmos can only be placed on empty board cells");
                    return;
                } // keeping the bounding box of the flipper clear
                if(iGizmo instanceof Flipper){
                    Flipper f = (Flipper) iGizmo;
                    if(f.getSide()){
                        if((iGizmo.getxCoord()+20 ==x && iGizmo.getyCoord()+20==y) // check the other 3 cells
                                ||(iGizmo.getxCoord() ==x && iGizmo.getyCoord()+20==y)
                                || (iGizmo.getxCoord()+20 ==x && iGizmo.getyCoord()==y))
                        {
                            gameView.getMessageLabel().setText("New gizmos can only be placed on empty board cells");
                            return;
                        }
                    }else{
                        if((iGizmo.getxCoord()-30 == x && iGizmo.getyCoord()+20==y) // check the other 3 cells
                                ||(iGizmo.getxCoord()-10 == x && iGizmo.getyCoord()+20==y)
                                || (iGizmo.getxCoord()-30 == x && iGizmo.getyCoord()==y)
                                ||(iGizmo.getxCoord()-10 == x && iGizmo.getyCoord()==y))
                        {
                            gameView.getMessageLabel().setText("New gizmos can only be placed on empty board cells");
                            return;
                        }
                    }

                }
            }
            if (model.getAbsorber() != null) {
                if (y >= model.getAbsorber().getyCoord()) {
                    gameView.getMessageLabel().setText("New gizmos can only be placed on empty board cells");
                    return;
                }
            }
            for(Ball b: model.getBalls()){
                if (b.getxCoord() == x && b.getyCoord() == y) {
                    gameView.getMessageLabel().setText("New gizmos can only be placed on empty board cells");
                }
            }
            if (buildListener.getAction().equals("Square")) {
                model.addSquareGizmo(x, y);
                gameView.getMessageLabel().setText("Square added successfully");

            }
            if (buildListener.getAction().equals("Absorber")) {
                if (store) {
                    oldX = model.round10th(e.getX());
                    oldY = model.round10th(e.getY());
                    store = false;
                } else {
                    x = model.round10th(e.getX());
                    y = model.round10th(e.getY());
                    if(y<380 || y != oldY){
                        gameView.getMessageLabel().setText("Absorber must be flat and at bottom");
                        store = true;
                    }else{
                        model.addAbsorberBuild(x,y,oldX);
                        gameView.getMessageLabel().setText("Absorber added successfully");
                        store = true;
                    }
                }
            }
            if (buildListener.getAction().equals("Circle")) {
                model.addCircleGizmo(x, y);
                gameView.getMessageLabel().setText("Circle added successfully");

            }
            if (buildListener.getAction().equals("Triangle")) {
                model.addTriangleGizmo(x, y);
                gameView.getMessageLabel().setText("Triangle added successfully");
            }
            if (buildListener.getAction().equals("LF")) {
                if(checkSpaceForLeftFlipper(x,y)){
                    model.addFlipper(x, y, true);
                    gameView.getMessageLabel().setText("Left flipper added successfully");
                }
            }
            if (buildListener.getAction().equals("RF")) {
                if(checkSpaceForRightFlipper(x,y)){
                    model.addFlipper(x, y, false);
                    System.out.println("Right flipper added successfully");
                }
            }
            if (buildListener.getAction().equals("B")){
                model.addBall(new Ball("B" + Integer.toString((int)x) + Integer.toString((int)y), x + 10, y + 10, 0.0, 0.0));
                for (int i = 0; i < model.getBalls().size(); i++){
                    model.getBalls().get(i).setStopped(true);
                }
            }
        }
        if (buildListener.getAction().equals("Delete")) {
            if(y>= 380 && model.getAbsorber() != null) {
                model.removeAbsorber();
            }
            model.deleteGizmo(x, y);
            model.deleteBall(x+ 10,y + 10);
            gameView.getMessageLabel().setText("Gizmo successfully removed");
        }
        if (buildListener.getAction().equals("Rotate")) {
            IGizmo ig= model.getGizmobyCoords(x,y);
            if(ig == null) {
                gameView.getMessageLabel().setText("You can oonly rotate existing gizmos");

            }else{
                model.rotate(ig.getId());
            }

        }
        if (buildListener.getAction().equals("Move")) {
            if (store) {
                oldX = model.round10th(e.getX());
                oldY = model.round10th(e.getY());
                store = false;
            } else {
                x = model.round10th(e.getX());
                y = model.round10th(e.getY());
                gameView.getMessageLabel().setText("pointer" + oldX + " " + oldY + " " + x + " " + y);
                model.moveGizmo(oldX,oldY,x,y);
                store = true;
            }
        }
        if (buildListener.getAction().equals("Connect")) {

            if (store) {
                oldX = model.round10th(e.getX());
                oldY = model.round10th(e.getY());
                store = false;
            } else {
                x = model.round10th(e.getX());
                y = model.round10th(e.getY());
                model.connectGizmos(oldX, oldY, x, y);
                store = true;
            }
        }
        if (buildListener.getAction().equals("Disconnect")) {
            if (store) {
                oldX = model.round10th(e.getX());
                oldY = model.round10th(e.getY());
                store = false;
            } else {
                x = model.round10th(e.getX());
                y = model.round10th(e.getY());
                model.disconnectGizmos(oldX, oldY, x, y);
                store = true;
            }
        }
        if (buildListener.getAction().equals("Bind")) {
            gameView.getMessageLabel().setText(Integer.toString(keyListener.getKey()));
            if (y >= 380 && model.getAbsorber() != null) {
                model.connectKey(keyListener.getKey(), true, model.getAbsorber().getId());
            } else {
                IGizmo iGizmo = model.getGizmobyCoords(x,y);
                if(iGizmo != null){
                    model.connectKey(keyListener.getKey(), true, model.getGizmobyCoords(x, y).getId());
                }
            }
        }
        if (buildListener.getAction().equals("Unbind")) {
            if (y >= 380 & model.getAbsorber() != null) {
                model.disconnectKey(keyListener.getKey(), true, model.getAbsorber().getId());
            } else {
                model.disconnectKey(keyListener.getKey(), true, model.getGizmobyCoords(x, y).getId());
            }
        }
    }

    private boolean checkSpaceForRightFlipper(int x, int y){
        for(IGizmo ig : model.getGizmos()){
            if((x-30==ig.getxCoord()&& y==ig.getyCoord())
            || (x-30==ig.getxCoord() && y+20==ig.getyCoord())
            || (x-10==ig.getxCoord()&& y+20==ig.getyCoord())){
                return false;
            }
            if(ig instanceof Flipper){
                Flipper f = (Flipper) ig;
                if(f.getSide()){ // left flipper
                    if((x-30==ig.getxCoord()+20 && y==ig.getyCoord())
                    || (x-30==ig.getxCoord()+20 && y+20==ig.getyCoord()+20)){
                        return false;
                    }
                }else{//right flipper
                    if( // x-30==ig.getxCoord()&& y+20==ig.getyCoord()+20
                    (x==ig.getxCoord()+10 && y==ig.getyCoord())
                    ||(x==ig.getxCoord()+10 && y+20==ig.getyCoord()+20)
                    ||(x-10==ig.getxCoord()&&y==ig.getyCoord())
                    ||(x-10==ig.getxCoord()&&y+20==ig.getyCoord()+20)){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private boolean checkSpaceForLeftFlipper(int x,int y){
        for(IGizmo ig : model.getGizmos()){
            if((x+20==ig.getxCoord() && y==ig.getyCoord())
             || (x+20 == ig.getxCoord() && y+20==ig.getyCoord())
             || (x==ig.getxCoord()&& y+20 ==ig.getyCoord())){
                return false;
            }
            if(ig instanceof Flipper){
                Flipper f = (Flipper) ig;
                if(f.getSide()){ // left flipper
                    if((x+20==ig.getxCoord()-20 && y==ig.getyCoord())
                            || (x+20 == ig.getxCoord()-20 && y+20==ig.getyCoord()+20)
                            ){
                        return false;
                    }

                }else{ //right flipper
                    if((x+20==ig.getxCoord()-30 && y==ig.getyCoord())
                            || (x+20 == ig.getxCoord()-30 && y+20==ig.getyCoord()+20)
                    ){
                        return false;
                    }

                }
            }
        }
        return true;

    }


    public int getKeyNo(){return keyNo;}
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
