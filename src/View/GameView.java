package View;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {


    public GameView() {
        initUI();
    }

    private void initUI() {
        initComponents();
        setPreferredSize(new Dimension(800, 600));
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GizmoBall");
        pack();
        setVisible(true);

    }

    private void initComponents() {
        //create panels
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel gridPanel = new JPanel();
        JPanel leftMenuSubPanel = new JPanel();
        JPanel leftMenuSubSliderPanel = new JPanel();
        JPanel leftMenuSubLeftPanel = new JPanel();
        JPanel leftMenuSubRightPanel = new JPanel();


        //set panels' attributes
        FlowLayout flowLayout = new FlowLayout();
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(80);
        flowLayout.setVgap(20);


        leftPanel.setLayout(borderLayout);
        leftPanel.setPreferredSize(new Dimension(200, 600));
        leftPanel.setMinimumSize(new Dimension(200, 600));
        leftPanel.setMaximumSize(new Dimension(200, 600));

        rightPanel.setLayout(new FlowLayout());
        rightPanel.setPreferredSize(new Dimension(700, 700));
        rightPanel.setMinimumSize(new Dimension(700, 700));
        rightPanel.setMaximumSize(new Dimension(700, 700));

        gridPanel.setPreferredSize(new Dimension(500, 500));
        gridPanel.setMinimumSize(new Dimension(500, 500));
        gridPanel.setMaximumSize(new Dimension(500, 500));
        gridPanel.setBackground(Color.BLACK);


        leftMenuSubPanel.setLayout(new BorderLayout());
        leftMenuSubPanel.setPreferredSize(new Dimension(200, 150));
        leftMenuSubPanel.setMinimumSize(new Dimension(200, 150));
        leftMenuSubPanel.setMaximumSize(new Dimension(200, 150));

        leftMenuSubLeftPanel.setLayout(new FlowLayout());
        leftMenuSubLeftPanel.setPreferredSize(new Dimension(100, 150));
        leftMenuSubLeftPanel.setMinimumSize(new Dimension(100,  150));
        leftMenuSubLeftPanel.setMaximumSize(new Dimension(100, 150));

        leftMenuSubRightPanel.setLayout(new FlowLayout());
        leftMenuSubRightPanel.setPreferredSize(new Dimension(100, 150));
        leftMenuSubRightPanel.setMinimumSize(new Dimension(100, 150));
        leftMenuSubRightPanel.setMaximumSize(new Dimension(100, 150));

        leftMenuSubSliderPanel.setLayout(new FlowLayout());
        leftMenuSubSliderPanel.setPreferredSize(new Dimension(200, 300));
        leftMenuSubSliderPanel.setMinimumSize(new Dimension(200, 300));
        leftMenuSubSliderPanel.setMaximumSize(new Dimension(200, 300));


        //main menu
        JMenuBar mainMenuBar = new JMenuBar();
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
        JMenu buildMenu = new JMenu("Choose..");
        buildMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JMenuItem triangleOption = new JMenuItem("Triangle");
        JMenuItem circleOption = new JMenuItem("Circle");
        JMenuItem flipperOption = new JMenuItem("Flipper");
        JMenuItem wallOption = new JMenuItem("Outer Wall");
        JMenu bumperMenu = new JMenu("Bumper");
        JMenuItem squareBumperOption = new JMenuItem("Square");
        JMenuItem circularBumperOption = new JMenuItem("Triangular");
        JMenuItem triangularBumperOption = new JMenuItem("Circular");

        JButton addAbsorberButton = new JButton("Add Absorber");
        JButton playButton = new JButton("Play");
        JButton moveButton = new JButton("Move");
        JButton connectButton = new JButton("Connect");
        JButton disconnectButton = new JButton("Disconnect");
        JButton rotateButton = new JButton("Rotate");
        JButton deleteButton = new JButton("Delete");
        JButton keyBindButton = new JButton("Bind Key");
        JButton keyUnbindButton = new JButton("Unbind Key");

        JLabel gravitySLabel = new JLabel("Gravity");
        JLabel frictionSLabel = new JLabel("Friction");
        JLabel velocitySLabel = new JLabel("Velocity");
        JSlider gravitySlider = new JSlider(0,50,10);
        JSlider frictionSlider = new JSlider(0,50,10);
        JSlider velocitySlider = new JSlider(0,50,10);
        JTextField gravitySTextBox = new JTextField();
        JTextField frictionSTextBox = new JTextField();
        JTextField velocitySTextBox = new JTextField();
        gravitySTextBox.setPreferredSize(new Dimension(35,20));
        velocitySTextBox.setPreferredSize(new Dimension(35,20));
        frictionSTextBox.setPreferredSize(new Dimension(35,20));


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
        contents.add(leftPanel, BorderLayout.LINE_START);
        contents.add(rightPanel, BorderLayout.CENTER);
        contents.add(playButton, BorderLayout.PAGE_END);


        //leftMenuSubPanel.add(jLabel);


        leftMenuSubLeftPanel.add(buildMenuBar);
        leftMenuSubLeftPanel.add(connectButton);
        leftMenuSubLeftPanel.add(rotateButton);
        leftMenuSubLeftPanel.add(deleteButton);
        leftMenuSubLeftPanel.add(keyBindButton);


        leftMenuSubRightPanel.add(addAbsorberButton);
        leftMenuSubRightPanel.add(disconnectButton);
        leftMenuSubRightPanel.add(moveButton);
        leftMenuSubRightPanel.add(keyUnbindButton);


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
        leftPanel.add(leftMenuSubPanel, BorderLayout.PAGE_START);
        leftPanel.add(leftMenuSubSliderPanel, BorderLayout.LINE_START);
        rightPanel.add(gridPanel);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameView gameView = new GameView();

            }
        });

    }
}
