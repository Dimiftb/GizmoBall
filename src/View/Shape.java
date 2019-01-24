package View;

public class Shape {

    protected enum GeometricShape {square, triangle, circle, bumper_square, bumper_triangular, bumper_circular, absorber, outer_wall}

    private GeometricShape piece_shape;
    private int[][] coordsTable;
    private int coords_location[][];
    private int shape_id;

    public Shape() {

    }


    public GeometricShape getPiece_shape() {
        return piece_shape;
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
