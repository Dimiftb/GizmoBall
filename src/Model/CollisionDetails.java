package Model;

import physics.Angle;
import physics.Vect;

public class CollisionDetails {

    private double tuc;
    private Vect velocity;
    private IGizmo gizmo;

    public CollisionDetails(double time, Vect vel) {
            tuc = time;
            velocity = vel;
            //gizmo = g;
    }

    public double getTuc() {
        return tuc;
    }

    public Vect getVelocity() {
        return velocity;
    }

    public IGizmo getGizmo() {
        return gizmo;
    }

    public void setTime(double time) {
        this.tuc = time;
    }

    public void setVelocity(Vect velocity) {
        this.velocity = velocity;
    }

    public void setGizmo(IGizmo gizmo) {
        this.gizmo = gizmo;
    }

}
