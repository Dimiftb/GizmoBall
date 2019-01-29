package View;

import javax.swing.*;
import java.awt.*;

public class RunMode extends JPanel {

    public RunMode() {

    }
    public void initUI() {
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
    }
    public void initComponents() {
        JPanel controlPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        controlPanel.setLayout(new FlowLayout());
        controlPanel.setPreferredSize(new Dimension(100, 600));
        controlPanel.setMinimumSize(new Dimension(100, 600));
        controlPanel.setMaximumSize(new Dimension(100, 600));
        controlPanel.setBackground(Color.PINK);

        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(600, 200));
        rightPanel.setMinimumSize(new Dimension(600, 200));
        rightPanel.setMaximumSize(new Dimension(600, 200));
        rightPanel.setBackground(Color.PINK);
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
        add(mainMenuBar,BorderLayout.PAGE_START);


        JButton runButton = new JButton("Run");
        JButton tickButton = new JButton("Tick");
        JButton stopButton = new JButton("Stop");
        controlPanel.add(runButton);
        controlPanel.add(tickButton);
        controlPanel.add(stopButton);

        GameBoard gb = new GameBoard(400,400);
        rightPanel.add(gb);
        add(controlPanel,BorderLayout.LINE_START);
        add(rightPanel,BorderLayout.LINE_END);



    }
}
