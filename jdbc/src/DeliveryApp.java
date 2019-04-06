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
	
	Stage window;
	Scene signUpScene,loginScene,scene4;
    
    public static void main(String [] args){
    	
        launch(args);
    }

    public void start(Stage stage){
    	window = stage;
    	stage.setTitle("Data Deliverers");
    	
    	GridPane grid1 = new GridPane();
    	grid1.setAlignment(Pos.BASELINE_CENTER);
    	grid1.setHgap(10);
    	grid1.setVgap(10);
    	grid1.setPadding(new Insets(25, 25, 25, 25));
    	
    	Text scenetitle = new Text("Welcome to Data Deliverers");
    	scenetitle.setFont(Font.font("Georgia",25));
    	grid1.add(scenetitle, 0, 0, 2, 1);
    	
    	Label userName = new Label("Username:");
    	grid1.add(userName, 0, 1);
    	TextField userTextField = new TextField();
    	grid1.add(userTextField,1,1);
    	
    	Label pw = new Label("Password:");
    	grid1.add(pw, 0, 2);
    	TextField pwTextField = new TextField();
    	grid1.add(pwTextField, 1, 2);
    	
    	Button loginBtn = new Button("Login");
    	HBox lBtn = new HBox(5);
    	lBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	lBtn.getChildren().add(loginBtn);
    	grid1.add(lBtn, 1, 4);
    	loginBtn.setOnAction(e -> {
    		String checkUser = userTextField.getText();
    		String checkPW = pwTextField.getText();
    		System.out.println(checkUser);
    		System.out.println(checkPW);
    		//stage.setScene(scene1)
    		});
    	
    	Button signUpBtn = new Button("Sign Up");
    	HBox sBtn = new HBox(5);
    	sBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	sBtn.getChildren().add(signUpBtn);
    	grid1.add(sBtn, 0, 4);
    	signUpBtn.setOnAction(e -> window.setScene(signUpScene));
    	
    	/////////////////////////////////////////////////////////
    	
    	GridPane grid2 = new GridPane();
    	grid2.setAlignment(Pos.BASELINE_CENTER);
    	grid2.setHgap(8);
    	grid2.setVgap(8);
    	grid2.setPadding(new Insets(25, 25, 25, 25));
    	
    	Text signUpTitle = new Text("Sign Up");
    	signUpTitle.setFont(Font.font("Georgia",25));
    	grid2.add(signUpTitle,0,0);
    	
    	Label newUserName = new Label("Username");
    	grid2.add(newUserName, 0, 1);
    	TextField newUserTextField = new TextField();
    	grid2.add(newUserTextField,0,2);
    	
    	Label newPW = new Label("Password");
    	grid2.add(newPW, 0, 3);
    	TextField newPWTextField = new TextField();
    	grid2.add(newPWTextField,0,4);
    	
    	Label fName = new Label("First Name");
    	grid2.add(fName, 0, 5);
    	TextField fNameTextField = new TextField();
    	grid2.add(fNameTextField, 0, 6);
    	
    	Label lName = new Label("Last Name");
    	grid2.add(lName, 0, 7);
    	TextField lNameTextField = new TextField();
    	grid2.add(lNameTextField, 0, 8);
    	
    	Label newAddress = new Label("Address");
    	grid2.add(newAddress, 0, 9);
    	TextField newAddressTextField = new TextField();
    	grid2.add(newAddressTextField, 0, 10);
    	
    	Button createBtn = new Button("Create");
    	HBox creBtn = new HBox(5);
    	creBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	creBtn.getChildren().add(createBtn);
    	grid2.add(creBtn, 1, 11);
    	createBtn.setOnAction(e -> {
    		String tempUserName = newUserTextField.getText();
    		String tempPW = newPWTextField.getText();
    		String tempFName = fNameTextField.getText();
    		String tempLName = lNameTextField.getText();
    		String tempAddress = newAddressTextField.getText();
    		System.out.println(tempUserName);
    		System.out.println(tempPW);
    		System.out.println(tempFName);
    		System.out.println(tempLName);
    		System.out.println(tempAddress);
    		});
    	
    	Button returnBtn = new Button("Back");
    	HBox retBtn = new HBox(5);
    	retBtn.setAlignment(Pos.BOTTOM_LEFT);
    	retBtn.getChildren().add(returnBtn);
    	grid2.add(retBtn, 0, 11);
    	returnBtn.setOnAction(e -> window.setScene(loginScene));
    	
    	///////////////////////////////////////////////////////////
    	
    	GridPane grid4 = new GridPane();
    	grid4.setAlignment(Pos.BASELINE_LEFT);
    	grid4.setHgap(20);
    	grid4.setVgap(20);
    	grid4.setPadding(new Insets(25, 25, 25, 25));
    	
    	Label item = new Label("Items:");
    	grid4.add(item, 0, 1);
    	Label itemOutput = new Label();
    	grid4.add(itemOutput, 1, 1);
    	
    	Label sender = new Label("Sender:");
    	grid4.add(sender, 0, 2);
    	Label senderOutput = new Label();
    	grid4.add(senderOutput, 1, 2);
    	
    	Label receiver = new Label("Receiver:");
    	grid4.add(receiver, 0, 3);
    	Label receiverOutput = new Label();
    	grid4.add(receiverOutput, 1, 3);

    	Label mailType = new Label("Mailtype:");
    	grid4.add(mailType, 0, 4);
    	Label mailOutput = new Label();
    	grid4.add(mailOutput, 1, 4);
    	
    	Label shipDate = new Label("Shipping Date:");
    	grid4.add(shipDate, 0, 5);
    	Label shipOutput = new Label();
    	grid4.add(shipOutput, 1, 5);
    	
    	Label deliverDate = new Label("Delivery Date:");
    	grid4.add(deliverDate, 0, 6);
    	Label deliverOutput = new Label();
    	grid4.add(deliverOutput, 1, 6);
    	
    	Label currentStatus = new Label("Current Status:");
    	grid4.add(currentStatus, 0, 7);
    	Label currentOutput = new Label();
    	grid4.add(currentOutput, 1, 7);
    	
    	Button backBtn = new Button("Back");
    	HBox bBtn = new HBox(5);
    	bBtn.setAlignment(Pos.BOTTOM_LEFT);
    	bBtn.getChildren().add(backBtn);
    	grid4.add(bBtn, 0, 8);
    	
    	
    	loginScene = new Scene(grid1, 400, 250);
    	signUpScene = new Scene(grid2,350,450);
    	scene4 = new Scene(grid4,400,400);

    	stage.setScene(scene4);
    	
        stage.show();
    }   
    
}