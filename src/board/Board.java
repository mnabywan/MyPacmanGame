package board;

import characters.Pacman;
import constants.Constants;
import game.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import position.Direction;
import position.Position;
import position.TextInformation;


public class Board {
    protected static Pane root;
    private static ElementArray elementArray;
    private static Timeline timeLine;
    private static Scene scene;
    private static Stage stage;


    public static Stage makeBoard(Stage stage) {

        Board.stage = stage;
        root = new Pane();
        root.setStyle("-fx-background-color: black");
        scene = new Scene(root, Constants.screenWidth, Constants.screenHeight);
        elementArray = new ElementArray();


        Element element1;
        for (int i = 0; i < Constants.ROWELEMENTS; i++) {
            for (int j = 0; j < Constants.COLUMNELEMENTS; j++) {
                Position position = new Position(i, j);
                if (position.getRow() == 1 && position.getColumn() == 1) {
                    element1 = new Element(Element.ElementType.EMPTY, position);
                } else if (i == Constants.ROWELEMENTS - 1 || j == Constants.COLUMNELEMENTS - 1 || j == 0 || i == 0 || (i == 3 && (j > 3 && j < 13)) ||
                        i == 12 && (j > 4 && j < 13) || j == 3 && (i > 5 && i < 16) || ((i > 2 && i < 13) && j == 13)) {
                    element1 = new Element(Element.ElementType.BARRIER, position);
                } else {
                    element1 = new Element(Element.ElementType.COIN, position);
                    Constants.COIN_NUMBER += 1;
                }
                ElementArray.addElement(element1);
                root.getChildren().add(element1.getNode());
            }


        }
        stage.setScene(scene);
        return stage;

    }

    public static void redrawMap() {

        Position.screenInitializer();

        root.getChildren().clear();

        for (int i = 0; i < Constants.ROWELEMENTS; i++) {
            for (int j = 0; j < Constants.COLUMNELEMENTS; j++) {
                root.getChildren().add(ElementArray.getElement(i, j).getNode());
            }
        }
        root.getChildren().add(Game.pacman.getNode());

        for (int i =0; i<4; i++) {
            root.getChildren().add(Game.ghosts[i].getNode());
        }

        Position livesPosition = new Position(1, 9);
        Text livesText = new Text(livesPosition.getX() - 10, livesPosition.getY() - 10, "LIVES : " + Pacman.getPacmanLives());
        TextInformation livesTextInformation = new TextInformation(livesPosition, livesText);

        Position scorePosition = new Position(1, Constants.COLUMNELEMENTS - 3);
        Text scoreText = new Text(scorePosition.getX() - 10, scorePosition.getY() - 10, "SCORE : " + Pacman.getScore());
        TextInformation scoreTextInformation = new TextInformation(scorePosition, scoreText );


        Position coinsPosition = new Position(1, 3);
        Text coinsText = new Text(coinsPosition.getX() - 10, coinsPosition.getY() - 10, "COINS : " + Pacman.getCollectedCoins());
        TextInformation coinsTextInformation = new TextInformation(coinsPosition, coinsText );



        root.getChildren().add(livesTextInformation.getText());
        root.getChildren().add(coinsTextInformation.getText());
        root.getChildren().add(scoreTextInformation.getText());

        root.requestFocus();

    }


    public static void startTimeline() {

        timeLine = new Timeline(new KeyFrame(Duration.millis(200), event -> {
            for(int i =0; i<4; i++){
                Game.ghosts[i].moveGhost();
            }
        }));

        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }



    public static void moveListener() {

        Board.root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP)
                Pacman.move(Direction.UP);
            else if (event.getCode() == KeyCode.DOWN)
                Pacman.move(Direction.DOWN);
            else if (event.getCode() == KeyCode.LEFT)
                Pacman.move(Direction.LEFT);
            else if (event.getCode() == KeyCode.RIGHT)
                Pacman.move(Direction.RIGHT);

        });
    }

    public static void replay() {

        Constants.COIN_NUMBER=0;
        Board.makeBoard(stage);
        Board.moveListener();
        Pacman pacman = new Pacman();
        pacman.setScore(0);
        pacman.setCollectedCoins(0);
        Board.redrawMap();
        Board.startTimeline();

    }


    public static void restartClickListener() {
        Board.root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                replay();
            }
        });
    }

    public static void winGame(){
        Board.restartClickListener();
        timeLine.stop();
        Text text = new Text(" YOU WON! \n YOUR SCORE \n\t    " + Pacman.getScore());
        text.setFill(Paint.valueOf(Constants.TEXT_RED_COLOR));
        text.setFont(Font.font("Ubuntu", Constants.screenWidth / 20));

        Text replayText = new Text("Press Space to Replay");
        replayText.setFill(Paint.valueOf(Constants.TEXT_RED_COLOR));
        replayText.setFont(Font.font("Ubuntu", Constants.screenWidth / 40));
        replayText.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(Constants.screenWidth / 8.7));
        replayText.layoutYProperty().bind(scene.heightProperty().divide(1.2));


        root.getChildren().add(text);
        root.getChildren().add(replayText);
    }




    public static void endGame() {
        Board.restartClickListener();
        timeLine.stop();

        Text endText = new Text(" GAME OVER! \n YOUR SCORE \n\t    " + Pacman.getScore());
        endText.setFill(Paint.valueOf(Constants.TEXT_RED_COLOR));
        endText.setFont(Font.font(Constants.TEXT_FONT, Constants.screenWidth / 40));
        endText.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(Constants.screenWidth / 10));
        endText.layoutYProperty().bind(scene.heightProperty().divide(2).subtract(Constants.screenHeight / 10));

        Text replayText = new Text("Press Space to Replay");
        replayText.setFill(Paint.valueOf(Constants.TEXT_RED_COLOR));
        replayText.setFont(Font.font(Constants.TEXT_FONT, Constants.screenWidth / 40));
        replayText.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(Constants.screenWidth / 8.7));
        replayText.layoutYProperty().bind(scene.heightProperty().divide(1.2));


        Rectangle rectangle = new Rectangle(Constants.screenWidth / 2, Constants.screenHeight / 2);
        rectangle.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(rectangle.getWidth() / 2));
        rectangle.layoutYProperty().bind(scene.heightProperty().divide(2).subtract(rectangle.getHeight() / 2));
        rectangle.setFill(Paint.valueOf(Constants.GHOST_BLUE_COLOR));


        root.getChildren().add(rectangle);
        root.getChildren().add(endText);
        root.getChildren().add(replayText);
    }
}









