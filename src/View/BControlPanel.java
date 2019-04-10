package View;

import Controller.BuildListener;
import Controller.SliderListener;
import Model.GameBoardModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class BControlPanel extends JPanel implements IControlPanel {

    private ActionListener listener;
    private GameBoardModel model;
    private KeyListener keyListener;
    private SliderListener sliderListener;

    public BControlPanel(GameBoardModel m, BuildListener buildListener, SliderListener sl, KeyListener kl) {
        model = m;
        keyListener = kl;
        listener = buildListener;
        sliderListener = sl;
        initUI();
    }
    @Override
    public void initUI() {
        FlowLayout flowLayout = new FlowLayout();
        BorderLayout borderLayout = new BorderLayout();
        //borderLayout.setVgap(40);
        flowLayout.setVgap(40);

        setFocusable(true);
        setLayout(borderLayout);
        setPreferredSize(new Dimension(300, 600));
        setMinimumSize(new Dimension(300, 600));
        setMaximumSize(new Dimension(300, 600));
        setBackground(Color.PINK);
        initComponents();
        addKeyListener(keyListener);

    }

    @Override
    public void initComponents() {
        JPanel leftMenuSubPanel = new JPanel();
        JPanel leftMenuSubSliderPanel = new JPanel();
        JPanel leftMenuSubLeftPanel = new JPanel();
        JPanel leftMenuSubRightPanel = new JPanel();


        leftMenuSubPanel.setLayout(new BorderLayout());
        leftMenuSubPanel.setPreferredSize(new Dimension(300, 300));
        leftMenuSubPanel.setMinimumSize(new Dimension(300, 300));
        leftMenuSubPanel.setMaximumSize(new Dimension(300, 300));


        leftMenuSubLeftPanel.setLayout(new FlowLayout());
        leftMenuSubLeftPanel.setPreferredSize(new Dimension(150, 200));
        leftMenuSubLeftPanel.setMinimumSize(new Dimension(150, 200));
        leftMenuSubLeftPanel.setMaximumSize(new Dimension(150, 200));
        leftMenuSubLeftPanel.setBackground(Color.PINK);

        leftMenuSubRightPanel.setLayout(new FlowLayout());
        leftMenuSubRightPanel.setPreferredSize(new Dimension(150, 200));
        leftMenuSubRightPanel.setMinimumSize(new Dimension(150, 200));
        leftMenuSubRightPanel.setMaximumSize(new Dimension(150, 200));
        leftMenuSubRightPanel.setBackground(Color.PINK);

        leftMenuSubSliderPanel.setLayout(new FlowLayout());
        leftMenuSubSliderPanel.setPreferredSize(new Dimension(200, 300));
        leftMenuSubSliderPanel.setMinimumSize(new Dimension(200, 300));
        leftMenuSubSliderPanel.setMaximumSize(new Dimension(200, 300));
        leftMenuSubSliderPanel.setBackground(Color.PINK);



        //build gizmo menu
        ImageIcon connectIcon = new ImageIcon("src/Icons/connect.png");
        ImageIcon disconnectIcon = new ImageIcon("src/Icons/disconnect.png");
        ImageIcon rotateIcon = new ImageIcon("src/Icons/rotate.png");
        ImageIcon deleteIcon = new ImageIcon("src/Icons/delete.png");
        ImageIcon moveIcon = new ImageIcon("src/Icons/move.png");
        ImageIcon bindIcon = new ImageIcon("src/Icons/bind.png");
        ImageIcon unbindIcon = new ImageIcon("src/Icons/remove.png");
        ImageIcon absorberIcon = new ImageIcon("src/Icons/absorber.png");
        ImageIcon velocityIcon = new ImageIcon("src/Icons/velocity.png");
        ImageIcon frictionIcon = new ImageIcon("src/Icons/friction.png");
        ImageIcon gravityIcon = new ImageIcon("src/Icons/gravity.png");
        ImageIcon squareIcon = new ImageIcon("src/Icons/square.png");
        ImageIcon circleIcon = new ImageIcon("src/Icons/circle.png");
        ImageIcon triangleIcon = new ImageIcon("src/Icons/triangle.png");
        ImageIcon lfIcon = new ImageIcon("src/Icons/LF.png");
        ImageIcon rfIcon = new ImageIcon("src/Icons/RF.png");
        ImageIcon ballsIcon = new ImageIcon("src/Icons/balls.png");
        ImageIcon shootup = new ImageIcon("src/Icons/shootup.png");


        JLabel jLabel = new JLabel("Build Menu");
        JMenuBar buildMenuBar = new JMenuBar();

        JMenu buildMenu = new JMenu("Add Gizmo ▼");
        buildMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JMenu flipper = new JMenu("Flipper");
        JMenuItem leftFlipper = new JMenuItem("Left Flipper",lfIcon);
        JMenuItem rightFlipper = new JMenuItem("Right Flipper",rfIcon);
        JMenu bumperMenu = new JMenu("Bumper");
        JMenuItem squareBumperOption = new JMenuItem("Square",squareIcon);
        JMenuItem triangleOption = new JMenuItem("Triangle",triangleIcon);
        JMenuItem circleOption = new JMenuItem("Circle",circleIcon);
        buildMenuBar.setPreferredSize(new Dimension(100, 40));

        triangleOption.addActionListener(listener);
        circleOption.addActionListener(listener);
        leftFlipper.addActionListener(listener);
        rightFlipper.addActionListener(listener);
        squareBumperOption.addActionListener(listener);

        JButton addAbsorberButton = new JButton("Absorber", absorberIcon);
        JButton moveButton = new JButton("Move", moveIcon);
        JButton connectButton = new JButton("Connect", connectIcon);
        JButton disconnectButton = new JButton("Disconnect", disconnectIcon);
        JButton rotateButton = new JButton("Rotate", rotateIcon);
        JButton deleteButton = new JButton("Delete", deleteIcon);
        JMenuBar keyBindBar = new JMenuBar();
        keyBindBar.setPreferredSize(new Dimension(100, 40));
        JMenu keyBind = new JMenu(" Gizmo Key ▼ ");
        JMenuItem keyBindButton = new JMenuItem("Bind Key",bindIcon);
        JMenuItem keyUnbindButton = new JMenuItem("Unbind Key",unbindIcon);
        JButton clearBoardButton = new JButton("Clear Board");
        JButton addBallButton = new JButton("Add Ball", ballsIcon);

        moveButton.setPreferredSize(new Dimension(150, 40));
        addAbsorberButton.setPreferredSize(new Dimension(150, 40));
        connectButton.setPreferredSize(new Dimension(150, 40));
        disconnectButton.setPreferredSize(new Dimension(150, 40));
        rotateButton.setPreferredSize(new Dimension(150, 40));
        deleteButton.setPreferredSize(new Dimension(150, 40));
        clearBoardButton.setPreferredSize(new Dimension(150,40));
        addBallButton.setPreferredSize(new Dimension(150,40));


        addAbsorberButton.addActionListener(listener);
        moveButton.addActionListener(listener);
        connectButton.addActionListener(listener);
        disconnectButton.addActionListener(listener);
        rotateButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        keyBindButton.addActionListener(listener);
        keyUnbindButton.addActionListener(listener);
        clearBoardButton.addActionListener(listener);
        addBallButton.addActionListener(listener);


        JLabel gravitySLabel = new JLabel(gravityIcon);
        JLabel frictionSLabel = new JLabel(frictionIcon);
        JLabel velocitySLabel = new JLabel(velocityIcon);
        gravitySLabel.setText("Gravity");
        frictionSLabel.setText("Friction");
        velocitySLabel.setText("Velocity");
        JSlider gravitySlider = new JSlider(0, 100, 25);
        JSlider frictionSlider = new JSlider(0, 50, 25);
        JSlider velocitySlider = new JSlider(0, 500, 100);
        gravitySlider.setName("gravitySlider");
        velocitySlider.setName("velocitySlider");
        frictionSlider.setName("frictionSlider");

        gravitySlider.addChangeListener(sliderListener);
        frictionSlider.addChangeListener(sliderListener);
        velocitySlider.addChangeListener(sliderListener);


        buildMenuBar.add(Box.createHorizontalGlue());
        buildMenuBar.add(buildMenu);
        buildMenu.add(bumperMenu);
        buildMenu.add(flipper);
        flipper.add(leftFlipper);
        flipper.add(rightFlipper);
        bumperMenu.add(squareBumperOption);
        bumperMenu.add(circleOption);
        bumperMenu.add(triangleOption);
        keyBindBar.add(Box.createHorizontalGlue());
        keyBindBar.add(keyBind);
        keyBind.add(keyBindButton);
        keyBind.add(keyUnbindButton);

        leftMenuSubLeftPanel.add(connectButton);
        leftMenuSubLeftPanel.add(rotateButton);
        leftMenuSubLeftPanel.add(deleteButton);
        leftMenuSubLeftPanel.add(addBallButton);
        leftMenuSubLeftPanel.add(buildMenuBar);


        leftMenuSubRightPanel.add(disconnectButton);
        leftMenuSubRightPanel.add(addAbsorberButton);
        leftMenuSubRightPanel.add(moveButton);
        leftMenuSubRightPanel.add(clearBoardButton);
        leftMenuSubRightPanel.add(keyBindBar);

        leftMenuSubSliderPanel.add(gravitySLabel);
        leftMenuSubSliderPanel.add(gravitySlider);
        leftMenuSubSliderPanel.add(frictionSLabel);
        leftMenuSubSliderPanel.add(frictionSlider);
        leftMenuSubSliderPanel.add(velocitySLabel);
        leftMenuSubSliderPanel.add(velocitySlider);


        leftMenuSubPanel.add(leftMenuSubLeftPanel, BorderLayout.LINE_START);
        leftMenuSubPanel.add(leftMenuSubRightPanel, BorderLayout.LINE_END);
        add(leftMenuSubPanel, BorderLayout.PAGE_START);
        add(leftMenuSubSliderPanel, BorderLayout.LINE_START);

    }

}


