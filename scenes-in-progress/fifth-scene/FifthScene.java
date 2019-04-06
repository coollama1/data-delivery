import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;


public class FifthScene extends Application{
    
    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
        GridPane layout = new GridPane();
        Text trackingTitle = new Text("Enter Tracking Number");
        TextField trackingTextField = new TextField();
        Button trackingEnterButton = new Button("Enter");
        Button trackingCancleButton = new Button("Cancle");
        Scene testingScene = new Scene(layout,350,180);//possible dimensions: 400, 250
        
        trackingTitle.setFont(new Font("georgia", 20));//arial,courier new, tiems new roman
        trackingTextField.setPrefWidth(100);

        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.setPadding(new Insets(25,25,25,25));
        layout.setHgap(10);
        layout.setVgap(10);
        
        layout.add(trackingTitle, 0,0,2,1);
        layout.add(trackingTextField,0,1,2,1);
        layout.add(trackingEnterButton,0,2);
        layout.add(trackingCancleButton,1,2);

        stage.setTitle("Fifth Scene");
        stage.setScene(testingScene);
        stage.show();
    }
}