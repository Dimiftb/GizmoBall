package View;

import javax.swing.*;

public interface IGameView {
    void initUI();

    JLabel getMessageLabel();

    void setMode(String mode);
}
