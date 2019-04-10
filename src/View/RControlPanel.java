package View;

import Controller.KeyListener;
import Controller.RunListener;
import Model.GameBoardModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RControlPanel extends JPanel implements IControlPanel {
    private ActionListener listener;
    private GameBoardModel model;
    private KeyListener keyListener;
    private JTextField vel;
    private JTextField fric;

    public RControlPanel(GameBoardModel gm) {
        model = gm;
        listener = new RunListener(model);
        keyListener = new KeyListener(model);
        vel = new JTextField("0");
        fric = new JTextField("0");
        initUI();

    }
    @Override
    public void initUI() {

        setPreferredSize(new Dimension(200, 600));
        setLayout(new FlowLayout());
        setBackground(Color.PINK);
        vel.setPreferredSize(new Dimension(100,25));
        fric.setPreferredSize(new Dimension(100,25));
        initComponents();
    }
    @Override
    public void initComponents() {
        ImageIcon runIcon = new ImageIcon("src/Icons/run.png");
        ImageIcon stopIcon = new ImageIcon("src/Icons/stop.png");
        ImageIcon tickIcon = new ImageIcon("src/Icons/tick.png");
        ImageIcon velocityIcon = new ImageIcon("src/Icons/velocity.png");
        JButton runButton = new JButton("Run", runIcon);
        JButton tickButton = new JButton("Tick", tickIcon);
        JButton stopButton = new JButton("Stop", stopIcon);



        runButton.addActionListener(listener);
        runButton.addKeyListener(keyListener);
        tickButton.addActionListener(listener);
        stopButton.addActionListener(listener);

        runButton.setPreferredSize(new Dimension(130, 50));
        tickButton.setPreferredSize(new Dimension(130, 50));
        stopButton.setPreferredSize(new Dimension(130, 50));


        add(runButton);
        add(tickButton);
        add(stopButton);
    }
}
