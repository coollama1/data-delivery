import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.control.*;

public class LoopApp extends Application{

    FirstScene firstScene;
    SecondScene secondScene;
    Stage window;

    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
        window = stage;
        firstScene = new FirstScene();
        secondScene = new SecondScene();
        window.setScene(firstScene);
        window.show();
    }

    class FirstScene extends Scene{
        GridPane layout;
        Button button;

        public FirstScene(){
            super(new GridPane(),200,200);
            layout = (GridPane)this.getRoot();
            button = new Button("First Button");
            button.setOnAction(e -> window.setScene(secondScene));
            layout.add(button,0,0);
        }
    }

    class SecondScene extends Scene{
        GridPane layout;
        Button button;

        public SecondScene(){
            super(new GridPane(),200,200);
            layout = (GridPane)this.getRoot();
            button = new Button("SecondButton");
            button.setOnAction(e -> window.setScene(firstScene));
            layout.add(button,0,0);
        }
    }
}