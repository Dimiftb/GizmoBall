package Controller;

import Model.GameBoardModel;
import View.IGameView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener {

    private GameBoardModel model;
    private IGameView gameView;

    public SliderListener(GameBoardModel m, IGameView view) {
        model = m;
        gameView = view;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();

        if (!source.getValueIsAdjusting()) {

            if (source.getName().equals("gravitySlider")){
                int gravityValue = (int) source.getValue();
                model.setGravity(gravityValue);
                gameView.getMessageLabel().setText("Gravity has been adjusted to " + gravityValue);
            }

            else if (source.getName().equals("frictionSlider")){
                int frictionValue = (int) source.getValue();
                model.setFriction(frictionValue);
                gameView.getMessageLabel().setText("Friction has been adjusted to " + frictionValue);
            }

            else if (source.getName().equals("velocitySlider")){
                int velocityValue = (int) source.getValue();
                model.setBallSpeed(velocityValue,velocityValue);
                gameView.getMessageLabel().setText("Velocity has been adjusted to " + velocityValue);
            }
        }
    }
}
