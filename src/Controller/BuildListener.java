package Controller;

import Model.Ball;
import Model.GameBoardModel;
import View.IGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildListener implements ActionListener {
    private GameBoardModel model;
    private boolean addGizmo = false;
    private int whichFlipper; // whichFlipper=0  means that the gizmo is not a flipper, 1 means its a left flipper 2 right
    private String action = " ";
    private IGameView gameView;


    public BuildListener(GameBoardModel m, IGameView view) {
        model = m;
        gameView = view;
        whichFlipper=0;
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Connect":
                gameView.getMessageLabel().setText("Connect clicked");
                action = "Connect";
                addGizmo = false;
                break;
            case "Disconnect":
                gameView.getMessageLabel().setText("Please select gizmos to disconnect");
                addGizmo = false;
                action = "Disconnect";
                break;
            case "Rotate":
                gameView.getMessageLabel().setText("Please select gizmo to be rotated");
                addGizmo = false;
                action = "Rotate";
                break;
            case "Absorber":
                gameView.getMessageLabel().setText("Please select a cell on the board");
                action = "Absorber";
                addGizmo = true;
                break;
            case "Delete":
                gameView.getMessageLabel().setText("Please select the gizmo to be removed");
                addGizmo = false;
                action = "Delete";
                break;
            case "Move":
                gameView.getMessageLabel().setText("Please select gizmo to be moved");
                addGizmo = false;
                action = "Move";
                break;
            case "Bind Key":
                gameView.getMessageLabel().setText("Please press a key to bind");
                addGizmo = false;
                action = "Bind";
                break;
            case "Unbind Key":
                gameView.getMessageLabel().setText("Please press a key to unbind");
                addGizmo = false;
                action = "Unbind";
                break;
            case "Square":
                gameView.getMessageLabel().setText("Please select a cell on the board");
                addGizmo = true;
                action = "Square";
                break;
            case "Triangle":
                gameView.getMessageLabel().setText("Please select a cell on the board");
                addGizmo = true;
                action = "Triangle";
                break;
            case "Circle":
                gameView.getMessageLabel().setText("Please select a cell on the board");
                addGizmo = true;
                action = "Circle";
                break;
            case "Left Flipper":
                gameView.getMessageLabel().setText("Please select a cell on the board");
                addGizmo = true;
                action = "LF";
                whichFlipper=1;
                break;
            case "Right Flipper":
                gameView.getMessageLabel().setText("Please click on the board");
                addGizmo = true;
                action = "RF";
                whichFlipper=2;
                break;
            case "Clear Board":
                model.clearBoard();
                break;
            case "Add Ball":
                gameView.getMessageLabel().setText("Please select a cell on the board");
                addGizmo = true;
                action = "B";
                break;
        }

    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isAddGizmo() {
        return addGizmo;
    }

    public int getWhichFlipper(){return whichFlipper;}

    public void setWhichFlipper(int i){whichFlipper=i;}

    public void setAddGizmo(boolean addGizmo) {
        this.addGizmo = addGizmo;
    }

    public String getAction() {
        return action;
    }
}
