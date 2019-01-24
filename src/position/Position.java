package position;

import constants.Constants;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class Position {
    private double x;
    private double y;

    private int row;
    private int column;

    private double width;
    private double height;

    public static void screenInitializer() {

        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        Constants.screenWidth = visualBounds.getWidth();
        Constants.screenHeight = visualBounds.getHeight();

        Constants.screenWidth = 1200;
        Constants.screenHeight = 800;
    }


    public Position(int row, int column) {
        this.row = row;
        this.column = column;


        this.width = Constants.screenWidth / Constants.ROWELEMENTS;
        this.height = Constants.screenHeight / Constants.ROWELEMENTS;

        this.x = (Constants.screenWidth / Constants.COLUMNELEMENTS) * column;
        this.y = (Constants.screenHeight / Constants.ROWELEMENTS) * row;


    }

    public boolean equals(Position other){
        if (this.column == other.column && this.row == other.row){
            return true;
        }

        else return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
