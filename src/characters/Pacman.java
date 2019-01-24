package characters;


import board.Board;
import board.Element;
import board.ElementArray;
import constants.Constants;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import position.Direction;
import position.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class Pacman {//extends Character{
    private static int pacmanLives;
    protected static Position position;
    private static Node node;
    private static int score;
    private static int collectedCoins;



    public Pacman() {
        this.collectedCoins = 0;
        this.score = 0;
        this.pacmanLives = 3;
        this.position = new Position(1, 1);
        Constants.PACMAN_IMAGE = new ImageView("https://vignette.wikia.nocookie.net/pacman/images/b/b9/Pac8bit.png/revision/latest?cb=20181031080357");

    }


    public static boolean move(Direction direction) {
        switch (direction) {
            case UP:
                if (ElementArray.getElement(position.getRow() - 1, position.getColumn()).getElementType() == Element.ElementType.BARRIER) {
                    return false;
                }
                position = new Position(position.getRow() - 1, position.getColumn());

                if (ElementArray.getElement(position.getRow(), position.getColumn()).getElementType() == Element.ElementType.COIN) {
                    Pacman.score += 1;
                    Pacman.collectedCoins += 1;
                    ElementArray.getElement(position.getRow(), position.getColumn()).setElementType(Element.ElementType.EMPTY);
                }
                Pacman.node.setRotate(90);
                Board.redrawMap();
                if (Pacman.getCollectedCoins() == Constants.COIN_NUMBER) {
                    Board.winGame();
                }
                return true;


            case DOWN:
                if (ElementArray.getElement(position.getRow() + 1, position.getColumn()).getElementType() == Element.ElementType.BARRIER) {
                    return false;
                }
                position = new Position(position.getRow() + 1, position.getColumn());

                if (ElementArray.getElement(position.getRow(), position.getColumn()).getElementType() == Element.ElementType.COIN) {
                    Pacman.score += 1;
                    Pacman.collectedCoins += 1;
                    ElementArray.getElement(position.getRow(), position.getColumn()).setElementType(Element.ElementType.EMPTY);
                }

                Pacman.node.setRotate(270);
                Board.redrawMap();
                if (Pacman.getCollectedCoins() == Constants.COIN_NUMBER) {
                    Board.winGame();
                }
                return true;


            case RIGHT:
                if (ElementArray.getElement(position.getRow(), position.getColumn() + 1).getElementType() == Element.ElementType.BARRIER) {
                    return false;
                }
                position = new Position(position.getRow(), position.getColumn() + 1);

                if (ElementArray.getElement(position.getRow(), position.getColumn()).getElementType() == Element.ElementType.COIN) {
                    Pacman.score += 1;
                    Pacman.collectedCoins += 1;
                    ElementArray.getElement(position.getRow(), position.getColumn()).setElementType(Element.ElementType.EMPTY);
                }
                Pacman.node.setRotate(180);
                Board.redrawMap();
                if (Pacman.getCollectedCoins() == Constants.COIN_NUMBER) {
                    Board.winGame();
                }
                return true;


            case LEFT:
                if (ElementArray.getElement(position.getRow(), position.getColumn() - 1).getElementType() == Element.ElementType.BARRIER) {
                    return false;
                }
                position = new Position(position.getRow(), position.getColumn() - 1);

                if (ElementArray.getElement(position.getRow(), position.getColumn()).getElementType() == Element.ElementType.COIN) {
                    Pacman.score += 1;
                    Pacman.collectedCoins += 1;
                    ElementArray.getElement(position.getRow(), position.getColumn()).setElementType(Element.ElementType.EMPTY);
                }
                Pacman.node.setRotate(270 + 90);
                Board.redrawMap();
                if (Pacman.getCollectedCoins() == Constants.COIN_NUMBER) {
                    Board.winGame();
                }
                return true;
        }
        return true;
    }


    public static void pacmanDeath() {
        if (Pacman.getPacmanLives() > 1) {
            Pacman.setScore(Pacman.getScore() - 15);
            Pacman.setPacmanLives(getPacmanLives() - 1);
        } else {
            Pacman.setPacmanLives(0);
            Board.endGame();
        }
    }

    public static boolean isDead(){
        if(pacmanLives==0){
            return true;
        }
        else return false;
    }


    public static void setScore(int score) {
        Pacman.score = score;
    }

    public static int getScore() {
        return score;
    }

    public static void setPosition(Position position) {
        Pacman.position = position;
    }

    public static int getPacmanLives() {
        return pacmanLives;
    }

    public static void setPacmanLives(int pacmanLives) {
        Pacman.pacmanLives = pacmanLives;
    }


    public static int getCollectedCoins() {
        return collectedCoins;
    }

    public static void setCollectedCoins(int collectedCoins) {
        Pacman.collectedCoins = collectedCoins;
    }

    public Node getNode() {


        double min = position.getHeight();
        if (position.getWidth() < position.getHeight()) {
            min = position.getWidth();
        }

        Constants.PACMAN_IMAGE.setFitWidth(min);
        Constants.PACMAN_IMAGE.setFitHeight(min);

        Constants.PACMAN_IMAGE.setX(position.getX() + position.getWidth() / 2 - min / 2);
        Constants.PACMAN_IMAGE.setY(position.getY() + position.getHeight() / 2 - min / 2);

        node = Constants.PACMAN_IMAGE;

        return node;

    }

}
