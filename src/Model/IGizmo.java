package Model;

import java.awt.*;
import java.util.ArrayList;

public interface IGizmo {

    int getxCoord();

    int getyCoord();

    void setxCoord(int xCoord);

    void setyCoord(int yCoord);

    int getWidth();

    String getId();

    ArrayList<String> getTriggers();

    void addTrigger(String id);
    void removeTrigger(String id);
    Color getColour();
    void setColour(Color color);
}
