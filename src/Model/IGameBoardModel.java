package Model;

import java.util.ArrayList;

public interface IGameBoardModel {
    CollisionDetails timeUntilCollision(Ball b);

    void moveBall();

    void moveBallForTime(Ball ball, double time);

    //void ArrayList<IGizmo> getGizmos();
    void addBall(Ball b);

    void addSquareGizmo(int x, int y);

    void addCircleGizmo(int x, int y);

    void addTriangleGizmo(int x, int y);

    void addAbsorber();

    void deleteGizmo(int x, int y);

    int convertPx(int px);

    int convertL(int l);

    int round10th(int x);

    void saveToFile();

    void loadFile();

    void addFlipper(int x, int y, boolean side);

    Absorber getAbsorber();

    ArrayList<Ball> getBalls();

    void moveGizmo(int xCoord1, int yCoord1, int xCoord2, int yCoord2);

    IGizmo getGizmobyCoords(int x, int y);
}
