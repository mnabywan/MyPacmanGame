package characters;


import board.Board;
import board.Element;
import board.ElementArray;
import constants.Constants;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import position.Position;

import java.util.Random;

public class Ghost extends Thread {
    private Position position;
    private Node node;
    private String color;


    public Ghost(String color) {
        this.position = new Position((Constants.ROWELEMENTS - 2), (Constants.COLUMNELEMENTS - 2));
        this.color = color;
    }

    public void run() throws IllegalStateException {
        try {
            moveGhost();
        } catch (IllegalStateException e) {
            System.out.println("Illegal state exception");
           // e.printStackTrace();
        }
    }


    public Node getNode() {

        Circle circle = new Circle(position.getX() + position.getWidth() / 2, position.getY() + position.getHeight() / 2, position.getWidth() / 4);
        circle.setFill(Paint.valueOf(this.color));

        this.node = circle;
        return node;
    }


    public void moveGhost() {

        Random random = new Random();
        int RandNum;

        if (Pacman.position.getRow() > this.position.getRow()) {
            if (Pacman.position.getColumn() > this.position.getColumn()) {
                RandNum = random.nextInt(2);
                if (RandNum == 0)
                    moveDown();
                else
                    moveRight();

            }

            if (Pacman.position.getColumn() <= this.position.getColumn()) {
                RandNum = random.nextInt(2);
                if (RandNum == 0)
                    moveDown();
                else
                    moveLeft();

            }

        } else if (Pacman.position.getRow() <= this.position.getRow()) {
            if (Pacman.position.getColumn() >= this.position.getColumn()) {
                RandNum = random.nextInt(2);
                if (RandNum == 0)
                    moveUp();
                else
                    moveRight();

            }

            if (Pacman.position.getColumn() < this.position.getColumn()) {
                RandNum = random.nextInt(2);
                if (RandNum == 0)
                    moveUp();
                else
                    moveLeft();

            }

        }

        if (this.position.equals(Pacman.position)) {
            Pacman.pacmanDeath();
            if (Pacman.isDead()) {
                Board.endGame();
            }
            Position newPacmanPosition = new Position(1, 1);
            Position newGhostPosition = new Position(Constants.ROWELEMENTS - 2, Constants.COLUMNELEMENTS - 2);
            this.setPosition(newGhostPosition);
            Pacman.setPosition(newPacmanPosition);
        } else {
            Board.redrawBoard();
        }
    }


    public boolean moveUp() {
        if (ElementArray.getElement(position.getRow() - 1, position.getColumn()).getElementType() == Element.ElementType.BARRIER) {
            return false;
        }
        this.position = new Position(position.getRow() - 1, position.getColumn());
        Board.redrawBoard();
        return true;
    }

    public boolean moveDown() {
        if (ElementArray.getElement(position.getRow() + 1, position.getColumn()).getElementType() == Element.ElementType.BARRIER) {
            return false;
        }
        this.position = new Position(position.getRow() + 1, position.getColumn());
        Board.redrawBoard();
        return true;
    }


    public boolean moveLeft() {
        if (ElementArray.getElement(position.getRow(), position.getColumn() - 1).getElementType() == Element.ElementType.BARRIER) {
            return false;
        }
        this.position = new Position(position.getRow(), position.getColumn() - 1);
        Board.redrawBoard();
        return true;
    }


    public boolean moveRight() {
        if (ElementArray.getElement(position.getRow(), position.getColumn() + 1).getElementType() == Element.ElementType.BARRIER) {
            return false;
        }
        this.position = new Position(position.getRow(), position.getColumn() + 1);
        Board.redrawBoard();
        return true;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
