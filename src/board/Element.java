package board;

import constants.Constants;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import position.Position;



public class Element {

    public enum ElementType {
        EMPTY, BARRIER, COIN
    }

    private ElementType elementType;
    private Position position;
    private Node node;


    public Element(ElementType elementType, Position position) {
        this.elementType = elementType;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }


    public Node getNode() {
        if (elementType == ElementType.BARRIER) {
            Rectangle rectangle = new Rectangle(position.getX(), position.getY(), position.getWidth(), position.getHeight());
            rectangle.setFill(Paint.valueOf(Constants.BARRIER_COLOR));
            this.node = rectangle;
        }

        else if (elementType == ElementType.COIN) {

            Circle circle = new Circle(position.getX() + position.getWidth() /2, position.getY() + position.getHeight() /2, position.getWidth() /8);
            circle.setFill(Paint.valueOf(Constants.COIN_COLOR));

            this.node= circle;
        }

        else if (elementType == ElementType.EMPTY) {
            Rectangle rectangle =  new Rectangle(position.getX(), position.getY(), position.getWidth(), position.getHeight());
            rectangle.setFill(Paint.valueOf(Constants.EMPTY_COLOR));
            this.node = rectangle;

        }

        return node;

    }



}
