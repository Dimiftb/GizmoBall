package View;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private GameBoard gb;
    private Shape shape;

    public GameView() {
        initUI();
    }

    private void initUI() {
        gb = new GameBoard(400, 400);
        initComponents();
        //runMOde();
        setPreferredSize(new Dimension(800, 600));
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GizmoBall");
        pack();

    }
    private void runMOde() {
        RunMode rm = new RunMode();
        rm.initUI();
        rm.initComponents();
        add(rm);
    }

    private void initComponents() {
        //create panels
        JPanel controlPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel leftMenuSubPanel = new JPanel();
        JPanel leftMenuSubSliderPanel = new JPanel();
        JPanel leftMenuSubLeftPanel = new JPanel();
        JPanel leftMenuSubRightPanel = new JPanel();
        JPanel messagePanel = new JPanel();


        //set panels' attributes
        FlowLayout flowLayout = new FlowLayout();
        BorderLayout borderLayout = new BorderLayout();
        //borderLayout.setVgap(40);
        flowLayout.setVgap(40);


        controlPanel.setLayout(borderLayout);
        controlPanel.setPreferredSize(new Dimension(300, 600));
        controlPanel.setMinimumSize(new Dimension(300, 600));
        controlPanel.setMaximumSize(new Dimension(300, 600));
        controlPanel.setBackground(Color.PINK);



        rightPanel.setLayout(flowLayout);
        rightPanel.setPreferredSize(new Dimension(400, 600));
        rightPanel.setMinimumSize(new Dimension(400, 600));
        rightPanel.setMaximumSize(new Dimension(400, 600));
        //rightPanel.setBackground(Color.MAGENTA);

        leftMenuSubPanel.setLayout(new BorderLayout());
        leftMenuSubPanel.setPreferredSize(new Dimension(300, 300));
        leftMenuSubPanel.setMinimumSize(new Dimension(300, 300));
        leftMenuSubPanel.setMaximumSize(new Dimension(300, 300));



        leftMenuSubLeftPanel.setLayout(new FlowLayout());
        leftMenuSubLeftPanel.setPreferredSize(new Dimension(150, 200));
        leftMenuSubLeftPanel.setMinimumSize(new Dimension(150,  200));
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

        messagePanel.setLayout(new FlowLayout());
        messagePanel.setPreferredSize(new Dimension(400, 30));
        messagePanel.setMinimumSize(new Dimension(400, 30));
        messagePanel.setMaximumSize(new Dimension(400, 30));
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //main menu
        JMenuBar mainMenuBar = new JMenuBar();
        mainMenuBar.setBackground(Color.orange);
        JMenu mainMenu = new JMenu("File");
        JMenuItem saveOption = new JMenuItem("Save");
        JMenuItem loadOption = new JMenuItem("Load");
        JMenuItem helpOption = new JMenuItem("Help");
        JMenuItem exitOption = new JMenuItem("Exit");
        mainMenu.add(saveOption);
        mainMenu.add(loadOption);
        mainMenu.add(helpOption);
        mainMenu.add(exitOption);
        mainMenuBar.add(mainMenu);
        setJMenuBar(mainMenuBar);


        //build gizmo menu

        JLabel jLabel = new JLabel("Build Menu");
        JMenuBar buildMenuBar = new JMenuBar();
        buildMenuBar.setSize(100,100);
        JMenu buildMenu = new JMenu("Choose ▼");
        buildMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JMenuItem triangleOption = new JMenuItem("Triangle");
        JMenuItem circleOption = new JMenuItem("Circle");
        JMenuItem flipperOption = new JMenuItem("Flipper");
        JMenuItem wallOption = new JMenuItem("Outer Wall");
        JMenu bumperMenu = new JMenu("Bumper");
        JMenuItem squareBumperOption = new JMenuItem("Square");
        JMenuItem circularBumperOption = new JMenuItem("Triangular");
        JMenuItem triangularBumperOption = new JMenuItem("Circular");
        buildMenuBar.setPreferredSize(new Dimension(100,40));

        ImageIcon playIcon = new ImageIcon("/home/dimiftb/IdeaProjects/GizmoBall/GizmoBall/src/Icons/play.png");
        ImageIcon connectIcon = new ImageIcon("/home/dimiftb/IdeaProjects/GizmoBall/GizmoBall/src/Icons/connect.png");
        ImageIcon disconnectIcon = new ImageIcon("/home/dimiftb/IdeaProjects/GizmoBall/GizmoBall/src/Icons/disconnect.png");
        ImageIcon rotateIcon = new ImageIcon("/home/dimiftb/IdeaProjects/GizmoBall/GizmoBall/src/Icons/rotate.png");
        ImageIcon deleteIcon = new ImageIcon("/home/dimiftb/IdeaProjects/GizmoBall/GizmoBall/src/Icons/delete.png");
        ImageIcon moveIcon = new ImageIcon("/home/dimiftb/IdeaProjects/GizmoBall/GizmoBall/src/Icons/move.png");
        ImageIcon bindIcon = new ImageIcon("/home/dimiftb/IdeaProjects/GizmoBall/GizmoBall/src/Icons/bind.png");
        ImageIcon unbindIcon = new ImageIcon("/home/dimiftb/IdeaProjects/GizmoBall/GizmoBall/src/Icons/remove.png");
        ImageIcon absorberIcon = new ImageIcon("/home/dimiftb/IdeaProjects/GizmoBall/GizmoBall/src/Icons/play.png");
        JButton addAbsorberButton = new JButton("Absorber",playIcon);
        JButton playButton = new JButton("Play",playIcon);
        playButton.setBackground(Color.ORANGE);
        JButton moveButton = new JButton("Move",moveIcon);
        JButton connectButton = new JButton("Connect",connectIcon);
        JButton disconnectButton = new JButton("Disconnect",disconnectIcon);
        JButton rotateButton = new JButton("Rotate",rotateIcon);
        JButton deleteButton = new JButton("Delete",deleteIcon);
        JButton keyBindButton = new JButton("Bind Key",bindIcon);
        JButton keyUnbindButton = new JButton("Unbind Key",unbindIcon);
        moveButton.setPreferredSize(new Dimension(150,40));
        addAbsorberButton.setPreferredSize(new Dimension(150,40));
        connectButton.setPreferredSize(new Dimension(150,40));
        disconnectButton.setPreferredSize(new Dimension(150,40));
        rotateButton.setPreferredSize(new Dimension(150,40));
        deleteButton.setPreferredSize(new Dimension(150,40));
        keyBindButton.setPreferredSize(new Dimension(150,40));
        keyUnbindButton.setPreferredSize(new Dimension(150,40));


        JLabel ballsLabel = new JLabel("Balls");
        JLabel gravitySLabel = new JLabel("Gravity");
        JLabel frictionSLabel = new JLabel("Friction");
        JLabel velocitySLabel = new JLabel("Velocity");
        JSlider ballsSlider = new JSlider(0,50,10);
        JSlider gravitySlider = new JSlider(0,50,10);
        JSlider frictionSlider = new JSlider(0,50,10);
        JSlider velocitySlider = new JSlider(0,50,10);
        JTextField ballsSTextBox = new JTextField();
        JTextField gravitySTextBox = new JTextField();
        JTextField frictionSTextBox = new JTextField();
        JTextField velocitySTextBox = new JTextField();
        ballsSTextBox.setPreferredSize(new Dimension(35,20));
        gravitySTextBox.setPreferredSize(new Dimension(35,20));
        velocitySTextBox.setPreferredSize(new Dimension(35,20));
        frictionSTextBox.setPreferredSize(new Dimension(35,20));


        JLabel messageLabel = new JLabel("Message successfully generated!!");



        buildMenuBar.add(Box.createHorizontalGlue());
        buildMenuBar.add(buildMenu);
        buildMenu.add(triangleOption);
        buildMenu.add(bumperMenu);
        buildMenu.add(circleOption);
        buildMenu.add(flipperOption);
        buildMenu.add(wallOption);
        bumperMenu.add(squareBumperOption);
        bumperMenu.add(circularBumperOption);
        bumperMenu.add(triangularBumperOption);



        //add components to frame & panels
        Container contents = getContentPane();
        contents.add(controlPanel, BorderLayout.LINE_START);
        contents.add(rightPanel, BorderLayout.CENTER);
        contents.add(playButton, BorderLayout.PAGE_END);


        //leftMenuSubPanel.add(jLabel);
        messagePanel.add(messageLabel);


        leftMenuSubLeftPanel.add(connectButton);
        leftMenuSubLeftPanel.add(rotateButton);
        leftMenuSubLeftPanel.add(deleteButton);
        leftMenuSubLeftPanel.add(keyBindButton);
        leftMenuSubLeftPanel.add(buildMenuBar);


        leftMenuSubRightPanel.add(disconnectButton);
        leftMenuSubRightPanel.add(addAbsorberButton);
        leftMenuSubRightPanel.add(moveButton);
        leftMenuSubRightPanel.add(keyUnbindButton);

        leftMenuSubSliderPanel.add(ballsLabel);
        leftMenuSubSliderPanel.add(ballsSTextBox);
        leftMenuSubSliderPanel.add(ballsSlider);
        leftMenuSubSliderPanel.add(gravitySLabel);
        leftMenuSubSliderPanel.add(gravitySTextBox);
        leftMenuSubSliderPanel.add(gravitySlider);
        leftMenuSubSliderPanel.add(frictionSLabel);
        leftMenuSubSliderPanel.add(frictionSTextBox);
        leftMenuSubSliderPanel.add(frictionSlider);
        leftMenuSubSliderPanel.add(velocitySLabel);
        leftMenuSubSliderPanel.add(velocitySTextBox);
        leftMenuSubSliderPanel.add(velocitySlider);

        leftMenuSubPanel.add(leftMenuSubLeftPanel,BorderLayout.LINE_START);
        leftMenuSubPanel.add(leftMenuSubRightPanel,BorderLayout.LINE_END);
        controlPanel.add(leftMenuSubPanel, BorderLayout.PAGE_START);
        controlPanel.add(leftMenuSubSliderPanel, BorderLayout.LINE_START);
       // gb.paintComponent(null);
        rightPanel.add(gb);
        rightPanel.add(messagePanel);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameView gameView = new GameView();
                gameView.setVisible(true);

            }
        });

    }
}
