package Controller;

import Model.GameBoardModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunListener implements ActionListener {

    private Timer timer;
    private GameBoardModel model;

    public RunListener(GameBoardModel m) {
        model = m;
        timer = new Timer(50, this);
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        if (e.getSource() == timer) {
            model.moveBall();
        } else
            switch (e.getActionCommand()) {
                case "Run":
                    timer.start();
                    break;
                case "Stop":
                    timer.stop();
                    break;
                case "Tick":
                    model.moveBall();
                    break;
                case "Quit":
                    System.exit(0);
                    break;
            }
    }
}
