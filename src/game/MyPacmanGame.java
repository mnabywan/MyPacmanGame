package game;


import board.Board;
import characters.Ghost;
import characters.Pacman;
import constants.Constants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import position.Position;


public class MyPacmanGame extends Application {

    @Override
    public void start(Stage stage) {

        Position.screenInitializer();
        Board.makeBoard(stage).show();

        Board.moveListener();

        Game game = new Game();
        game.pacman = new Pacman();


        game.ghosts[0] = new Ghost(Constants.GHOST_BLUE_COLOR);
        game.ghosts[1] = new Ghost(Constants.GHOST_PINK_COLOR);
        game.ghosts[2] = new Ghost(Constants.GHOST_YELLOW_COLOR);
        game.ghosts[3] = new Ghost(Constants.GHOST_RED_COLOR);


        for(int i =0; i<4; i++){
            int finalI = i;
            Platform.runLater(new Thread(() -> {
                game.ghosts[finalI].start();
            }));
        }

        for (int i =0; i<4; i++){
            try{
                game.ghosts[i].join();
            } catch (Exception e) {
                System.out.println("Exception has beeen caught");
            }
        }

        Board.redrawMap();
        Board.startTimeline();

    }

}
