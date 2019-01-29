package Model;

public abstract class Shape {




    private int[][] coordsTable;
    private int coords_location[][];
    private int shape_id;

    public Shape() {

    }

    public int[][] getCoords_location() {
        return coords_location;
    }

    public int getShape_id() {
        return shape_id;
    }

    public void setCoords_location(int[][] coords_location) {
        this.coords_location = coords_location;
    }

    public void setShape_id(int shape_id) {
        this.shape_id = shape_id;
    }


    private void move() {

    }

    private void rotate() {

    }
}
