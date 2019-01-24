package position;

import constants.Constants;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextInformation{
    private Position position;
    private Text text;



    public TextInformation(Position position, Text text){
        this.position = position;
        this.text = text;

        text.setFill(Paint.valueOf(Constants.TEXT_RED_COLOR));
        text.setFont(Font.font(Constants.TEXT_FONT, Constants.screenWidth / 40));
    }


    public Text getText() {
        return text;
    }
}
