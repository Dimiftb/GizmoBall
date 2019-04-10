package main;

import Model.*;
import View.GameBoard;
import View.GameView;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import javax.swing.*;
import java.awt.*;
import java.sql.Blob;

public class Main {
    public static void main(String[] args) {

        GameBoardModel model = new GameBoardModel();

        GameView view = new GameView(model, "Build");

    }
}
