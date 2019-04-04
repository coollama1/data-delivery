import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class DeliveryApp extends Application{
    
    public static void main(String [] args){
    	
        launch(args);
    }

    public void start(Stage stage){
    	stage.setTitle("Login to Account");
    	
    	GridPane layout = new GridPane();
    	layout.setAlignment(Pos.BASELINE_CENTER);
    	layout.setHgap(10);
    	layout.setVgap(10);
    	layout.setPadding(new Insets(25, 25, 25, 25));
    	
    	Label userName = new Label("User Name:");
    	layout.add(userName, 0, 1);
    	TextField userTextField = new TextField();
    	layout.add(userTextField,1,1);
    	
    	Label pw = new Label("Password:");
    	layout.add(pw, 0, 2);
    	TextField pwTextField = new TextField();
    	layout.add(pwTextField, 1, 2);
    	
    	
    	Button btn = new Button("Login");
    	HBox hButton = new HBox(10);
    	hButton.setAlignment(Pos.BOTTOM_RIGHT);
    	hButton.getChildren().add(btn);
    	layout.add(hButton, 1, 4);
    	
    	
    	
    	Scene scene = new Scene(layout, 350, 250);
    	stage.setScene(scene);
    	
        stage.show();
    }   
}