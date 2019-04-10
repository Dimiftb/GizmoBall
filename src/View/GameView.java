package View;

import Controller.*;
import Model.GameBoardModel;

import javax.swing.*;
import java.awt.*;


public class GameView extends JFrame implements IGameView {
    private GameBoard gb;
    private GameBoardModel gameBoardModel;
    private JLabel messageLabel;
    private JButton switchButton;
    private String mode;
    private BuildListener buildListener;
    private JFrameListener jFrameListener;
    private IControlPanel controlPanel;
    private ImageIcon playIcon;
    private ImageIcon buildIcon;
    private KeyListener keyListener;
    private SliderListener sliderListener;
    private PointerListener pointerListener;


    public GameView(GameBoardModel gm, String m) {
        gameBoardModel = gm;
        keyListener = new KeyListener(gameBoardModel);
        buildListener = new BuildListener(gameBoardModel, this);
        jFrameListener = new JFrameListener( this, buildListener, gameBoardModel);
        sliderListener = new SliderListener(gameBoardModel, this);
        pointerListener = new PointerListener(gameBoardModel,buildListener,keyListener, this);

        mode = m;
        initUI();
    }

    @Override
    public void initUI() {
        controlPanel = new BControlPanel(gameBoardModel,buildListener, sliderListener, keyListener);
        add((Component) controlPanel, BorderLayout.LINE_START);
        gb = new GameBoard(400, 400, gameBoardModel, mode, pointerListener);
        initComponents();
        setPreferredSize(new Dimension(800, 600));
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setFocusTraversalKeysEnabled(false);
        setTitle("GizmoBall");
        pack();
        setVisible(true);

    }


    private void initComponents() {
        //create panels
        JPanel rightPanel = new JPanel();
        JPanel messagePanel = new JPanel();


        //set panels' attributes
        FlowLayout flowLayout = new FlowLayout();
        BorderLayout borderLayout = new BorderLayout();
        //borderLayout.setVgap(40);
        flowLayout.setVgap(40);


        rightPanel.setLayout(flowLayout);
        rightPanel.setPreferredSize(new Dimension(400, 600));
        rightPanel.setMinimumSize(new Dimension(400, 600));
        rightPanel.setMaximumSize(new Dimension(400, 600));
        //rightPanel.setBackground(Color.MAGENTA);


        messagePanel.setLayout(new FlowLayout());
        messagePanel.setPreferredSize(new Dimension(400, 33));
        messagePanel.setMinimumSize(new Dimension(400, 33));
        messagePanel.setMaximumSize(new Dimension(400, 33));
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //main menu
        JMenuBar mainMenuBar = new JMenuBar();
        mainMenuBar.setBackground(Color.orange);
        JMenu mainMenu = new JMenu("File");
        JMenuItem saveOption = new JMenuItem("Save");
        JMenuItem loadOption = new JMenuItem("Load");
        JMenuItem helpOption = new JMenuItem("Help");
        JMenuItem exitOption = new JMenuItem("Exit");
        saveOption.addActionListener(jFrameListener);
        loadOption.addActionListener(jFrameListener);
        helpOption.addActionListener(jFrameListener);
        exitOption.addActionListener(jFrameListener);
        mainMenu.add(saveOption);
        mainMenu.add(loadOption);
        mainMenu.add(helpOption);
        mainMenu.add(exitOption);
        mainMenuBar.add(mainMenu);
        setJMenuBar(mainMenuBar);

         buildIcon = new ImageIcon("src/Icons/build.png");
         playIcon = new ImageIcon("src/Icons/play.png");
        switchButton = new JButton("Play", playIcon);
        switchButton.addActionListener(jFrameListener);
        switchButton.setBackground(Color.ORANGE);
        messageLabel = new JLabel("Welcome to Gizmoball!!");


//        cp.initComponents();
        //add components to frame & panels
        Container contents = getContentPane();
        // contents.add(cp, BorderLayout.LINE_START);
        contents.add(rightPanel, BorderLayout.CENTER);
        contents.add(switchButton, BorderLayout.PAGE_END);
        messagePanel.add(messageLabel);
        rightPanel.add(gb);
        rightPanel.add(messagePanel);

    }

    @Override
    public JLabel getMessageLabel() {
        return messageLabel;
    }


    @Override
    public void setMode(String mode) {
        gb.setMode(mode);
        switch (mode) {
            case "Build":
                gameBoardModel.stopBalls();
                remove((Component) controlPanel);
                switchButton.setText("Play");
                switchButton.setIcon(playIcon);
                controlPanel = new BControlPanel(gameBoardModel, buildListener, sliderListener, keyListener);
                add((Component) controlPanel, BorderLayout.LINE_START);
                break;
            case "Run":
                switchButton.setText("Build");
                switchButton.setIcon(buildIcon);
                remove((Component) controlPanel);
                controlPanel = new RControlPanel(gameBoardModel);
                add((Component) controlPanel, BorderLayout.LINE_START);
                break;
            default:
                System.out.println("Error loading GameView");
                break;
        }
    }
}
