package View;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {


    public GameView() {
        initUI();
    }

    private void initUI() {
        initComponents();
        setPreferredSize(new Dimension(800, 800));
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GizmoBall");
        pack();
        setVisible(true);

    }

    private void initComponents() {
        //create panels
        JPanel leftPanel = new JPanel();
        JPanel leftMenuSubPanel = new JPanel();
        JPanel leftMainButtonsSubPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel gridPanel = new JPanel();

        //set panels' attributes
        FlowLayout flowLayout = new FlowLayout();
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(40);
        flowLayout.setVgap(20);


        leftPanel.setLayout(borderLayout);
        leftPanel.setPreferredSize(new Dimension(100, 600));
        leftPanel.setMinimumSize(new Dimension(100, 600));
        leftPanel.setMaximumSize(new Dimension(100, 600));


        leftMenuSubPanel.setLayout(flowLayout);
        leftMenuSubPanel.setPreferredSize(new Dimension(100, 150));
        leftMenuSubPanel.setMinimumSize(new Dimension(100, 150));
        leftMenuSubPanel.setMaximumSize(new Dimension(100, 150));

        leftMainButtonsSubPanel.setLayout(new FlowLayout());
        leftMainButtonsSubPanel.setPreferredSize(new Dimension(100, 300));
        leftMainButtonsSubPanel.setMinimumSize(new Dimension(100, 300));
        leftMainButtonsSubPanel.setMaximumSize(new Dimension(100, 300));


        rightPanel.setLayout(new FlowLayout());
        rightPanel.setPreferredSize(new Dimension(700, 700));
        rightPanel.setMinimumSize(new Dimension(700, 700));
        rightPanel.setMaximumSize(new Dimension(700, 700));

        gridPanel.setPreferredSize(new Dimension(500, 500));
        gridPanel.setMinimumSize(new Dimension(500, 500));
        gridPanel.setMaximumSize(new Dimension(500, 500));
        gridPanel.setBackground(Color.BLACK);


        //main menu
        JMenuBar mainMenuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("File");
        JMenuItem saveOption = new JMenuItem("Save");
        JMenuItem loadOption = new JMenuItem("Load");
        JMenuItem helpOption = new JMenuItem("Help");
        JMenuItem exitOption = new JMenuItem("Exit");
        mainMenu.add(saveOption);
        mainMenu.add(loadOption);
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
        JMenuItem absorberOption = new JMenuItem("Absorber");
        JMenuItem wallOption = new JMenuItem("Outer Wall");
        JMenu bumperMenu = new JMenu("Bumper");
        JMenuItem squareBumperOption = new JMenuItem("Square");
        JMenuItem circularBumperOption = new JMenuItem("Triangular");
        JMenuItem triangularBumperOption = new JMenuItem("Circular");
        buildMenuBar.add(Box.createHorizontalGlue());
        buildMenuBar.add(buildMenu);
        buildMenu.add(triangleOption);
        buildMenu.add(bumperMenu);
        buildMenu.add(circleOption);
        buildMenu.add(flipperOption);
        buildMenu.add(absorberOption);
        buildMenu.add(wallOption);
        bumperMenu.add(squareBumperOption);
        bumperMenu.add(circularBumperOption);
        bumperMenu.add(triangularBumperOption);

        //init buttons for main functions
        JButton playButton = new JButton("Play");
        JButton moveButton = new JButton("Move");
        JButton connectButton = new JButton("Connect");
        JButton rotateButton = new JButton("Rotate");
        JButton deleteButton = new JButton("Delete");


        //add components to frame & panels
        Container contents = getContentPane();
        contents.add(leftPanel, BorderLayout.LINE_START);
        contents.add(rightPanel, BorderLayout.CENTER);
        contents.add(playButton, BorderLayout.PAGE_END);


        leftMenuSubPanel.add(jLabel);
        leftMenuSubPanel.add(buildMenuBar);
        leftMainButtonsSubPanel.add(moveButton);
        leftMainButtonsSubPanel.add(connectButton);
        leftMainButtonsSubPanel.add(rotateButton);
        leftMainButtonsSubPanel.add(deleteButton);
        leftPanel.add(leftMenuSubPanel, BorderLayout.PAGE_START);
        leftPanel.add(leftMainButtonsSubPanel, BorderLayout.LINE_START);
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
