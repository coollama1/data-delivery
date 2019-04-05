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
	
	Scene signUpScene;
	Scene scene1;
    
    public static void main(String [] args){
    	
        launch(args);
    }

    public void start(Stage stage){
    	stage.setTitle("Data Deliverers");
    	
    	GridPane loginScene = new GridPane();
    	loginScene.setAlignment(Pos.BASELINE_CENTER);
    	loginScene.setHgap(10);
    	loginScene.setVgap(10);
    	loginScene.setPadding(new Insets(25, 25, 25, 25));
    	
    	Text scenetitle = new Text("Welcome to Data Deliverers");
    	scenetitle.setFont(Font.font("Impact",25));
    	loginScene.add(scenetitle, 0, 0, 2, 1);
    	
    	Label userName = new Label("Username:");
    	loginScene.add(userName, 0, 1);
    	TextField userTextField = new TextField();
    	loginScene.add(userTextField,1,1);
    	
    	Label pw = new Label("Password:");
    	loginScene.add(pw, 0, 2);
    	TextField pwTextField = new TextField();
    	loginScene.add(pwTextField, 1, 2);
    	
    	Button loginBtn = new Button("Login");
    	HBox lBtn = new HBox(5);
    	lBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	lBtn.getChildren().add(loginBtn);
    	loginScene.add(lBtn, 1, 4);
    	loginBtn.setOnAction(e -> stage.setScene(scene1));
    	
    	Button signUpBtn = new Button("Sign Up");
    	HBox sBtn = new HBox(5);
    	sBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	sBtn.getChildren().add(signUpBtn);
    	loginScene.add(sBtn, 0, 4);
    	signUpBtn.setOnAction(e -> stage.setScene(signUpScene));
    	
    	
    	
    	Scene scene = new Scene(loginScene, 400, 250);
    	
    	stage.setScene(scene);
    	
        stage.show();
    }   
    
}