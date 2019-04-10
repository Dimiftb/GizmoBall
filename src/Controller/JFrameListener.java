package Controller;

import Model.GameBoardModel;
import View.IGameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameListener implements ActionListener {
    private IGameView gameView;
    private BuildListener buildListener;
    private GameBoardModel model;

    public JFrameListener(IGameView view, BuildListener bl, GameBoardModel m) {
        gameView = view;
        buildListener = bl;
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Play")) {
            gameView.setMode("Run");
            buildListener.setAction("none");
        } else if (e.getActionCommand().equals("Build")) {
            gameView.setMode("Build");
            model.getBalls().clear();
        } else if (e.getActionCommand().equals("Save")) {
            String userString = JOptionPane.showInputDialog(null, new JLabel("Enter File Name"), "FileName",
                    JOptionPane.QUESTION_MESSAGE);
            gameView.getMessageLabel().setText("You have saved to file " + userString);
            model.saveToFile(userString);

        } else if (e.getActionCommand().equals("Load")) {
            model.clearBoard();
            String LoadName = JOptionPane.showInputDialog(null, new JLabel("Enter File Name"), "FileName",
                    JOptionPane.QUESTION_MESSAGE);

            model.loadFile(LoadName);

            gameView.getMessageLabel().setText("You have loaded the file " + LoadName);

        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }
}
