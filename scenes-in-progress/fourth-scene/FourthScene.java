import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.collections.*;
import javafx.scene.control.*;

public class FourthScene extends Application{

    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
        ObservableList<String> listOfNames = FXCollections.observableArrayList("person 1","person 2","person 3");
        ListView<String> listView = new ListView<>(listOfNames);
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane,400,400);

        gridPane.add(listView,0,0);

        stage.setScene(scene);
        stage.show();

    }
}