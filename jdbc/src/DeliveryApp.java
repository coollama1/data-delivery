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
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class DeliveryApp extends Application{
    
    public static void main(String [] args){
    	
        launch(args);
    }

    public void start(Stage stage){
    	stage.setTitle("Data Deliverers Login");
    	
    	GridPane layout = new GridPane();
    	layout.setAlignment(Pos.BASELINE_CENTER);
    	layout.setHgap(10);
    	layout.setVgap(10);
    	layout.setPadding(new Insets(25, 25, 25, 25));
    	
    	Text scenetitle = new Text("Welcome to Data Deliverers");
    	scenetitle.setFont(Font.font("Impact",25));
    	layout.add(scenetitle, 0, 0, 2, 1);
    	
    	Label userName = new Label("User Name:");
    	layout.add(userName, 0, 1);
    	TextField userTextField = new TextField();
    	layout.add(userTextField,1,1);
    	
    	Label pw = new Label("Password:");
    	layout.add(pw, 0, 2);
    	TextField pwTextField = new TextField();
    	layout.add(pwTextField, 1, 2);
    	
    	Button loginBtn = new Button("Login");
    	HBox lBtn = new HBox(10);
    	lBtn.setAlignment(Pos.BOTTOM_LEFT);
    	lBtn.getChildren().add(loginBtn);
    	layout.add(lBtn, 1, 4);
    	
    	Button signUpBtn = new Button("Sign Up");
    	HBox sBtn = new HBox(10);
    	sBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	sBtn.getChildren().add(signUpBtn);
    	layout.add(sBtn, 1, 4);
    	
    	
    	Scene scene = new Scene(layout, 350, 250);
    	stage.setScene(scene);
    	
        stage.show();
    }   
}