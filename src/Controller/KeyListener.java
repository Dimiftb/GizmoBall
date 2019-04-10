package Controller;

import Model.*;

import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class KeyListener implements java.awt.event.KeyListener {
    private GameBoardModel model;
    private int keyCode;
    //private PointerListener pl;
    private ArrayList<KeyDetails> keyDetails;


     public KeyListener(GameBoardModel m) {
      model = m;
      keyDetails = model.getKeyDetails();
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
         keyCode=e.getKeyCode();
         for(KeyDetails kd: keyDetails) {
             if(kd.getKeyNO() == e.getKeyCode()) {
                IGizmo iGizmo = model.getGizmobyID(kd.getId());
                 if(iGizmo instanceof Flipper) {
                     ((Flipper) iGizmo).setActive(true);
                     ((Flipper) iGizmo).setTriggered(true);
                 }else if(iGizmo instanceof Absorber) {
                     model.resetBall();
                 }
             }
         }

         if (e.getKeyCode() == KeyEvent.VK_A) {

            model.resetBall();

        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            for(IGizmo ig : model.getGizmos()){
                if(ig instanceof Flipper){
                    Flipper fl =(Flipper) ig;
                    fl.setKeyIsPressed(true);
                    if(fl.getRotatedBy()==90){
                        fl.setActive(false);
                    }else{
                        fl.setActive(true);
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
         for(KeyDetails kd: keyDetails) {
             if(kd.getKeyNO() == e.getKeyCode()) {
                 IGizmo iGizmo = model.getGizmobyID(kd.getId());
                 if(iGizmo != null) {
                     if(iGizmo instanceof Flipper){
                         Flipper f = (Flipper) iGizmo;
                         if(f.getRotatedBy() == 90){
                             f.setActive(true);
                         }else{
                             f.setRotatedBy(-f.getRotatedBy());
                         }
                         f.setKeyIsPressed(false);
                     }
                 }
             }
         }
    }

    public int getKey() {
        return keyCode;
    }

    public void setKey(int keyCode) {
        this.keyCode = keyCode;
    }
}
