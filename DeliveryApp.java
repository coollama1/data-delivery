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
import javafx.scene.paint.*;
import javafx.collections.*;
import javafx.scene.control.*;

public class DeliveryApp extends Application{
	
	Stage window;
    FirstScene firstScene;
    SecondScene secondScene;
    ThirdScene thirdScene;
    FourthScene fourthScene;
    FifthScene fifthScene;
    SixthScene sixthScene;
    SeventhScene seventhScene;
    
    public static void main(String [] args){
        launch(args);
    }

    public void start(Stage stage){
        window = stage;

    	firstScene = new FirstScene();
        secondScene = new SecondScene();
        thirdScene = new ThirdScene();
        fourthScene = new FourthScene();
        fifthScene = new FifthScene();
        sixthScene = new SixthScene();

        window.setScene(firstScene);
        window.show();
    }

    class FirstScene extends Scene{
        GridPane layout;
        Text sceneTitle;
        Label userName;
        Label pw;
        TextField userTextField;
        TextField pwTextField;
        Button loginBtn;
        Button signUpBtn;
        HBox lBtn;
        HBox sBtn;

        public FirstScene(){
            super(new GridPane(),400,250);

            layout = (GridPane)this.getRoot();
            window.setTitle("Data Deliverers");
            sceneTitle = new Text("Welcome to Data Deliverers");
            userName = new Label("Username:");
            pw = new Label("Password:");
            userTextField = new TextField();
            pwTextField = new TextField();
            loginBtn = new Button("Login");
            signUpBtn = new Button("Sign Up");
            lBtn = new HBox(5);
            sBtn = new HBox(5);

            sceneTitle.setFont(Font.font("Georgia",25));

            loginBtn.setOnAction(e -> {
                String checkUser = userTextField.getText();
                String checkPW = pwTextField.getText();
                if(true){
                    userTextField.setText("");
                    pwTextField.setText("");
                    window.setScene(secondScene);
                }
            });
            signUpBtn.setOnAction(e -> window.setScene(sixthScene));

            lBtn.setAlignment(Pos.BOTTOM_RIGHT);
            lBtn.getChildren().add(loginBtn);

            sBtn.setAlignment(Pos.BOTTOM_RIGHT);
            sBtn.getChildren().add(signUpBtn);

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(userName, 0, 1);
            layout.add(userTextField,1,1);
            layout.add(pw, 0, 2);
            layout.add(pwTextField, 1, 2);
            layout.add(sBtn, 0, 4);
            layout.add(lBtn, 1, 4);
        }
    }
    
    class SecondScene extends Scene{
        GridPane layout;
        Button editPersonalInfoButton;
        Button viewPackageDetailsButton;
        Button addPackageButton;
        ObservableList<String> listOfNames;
        ListView<String> listView;
        
        public SecondScene(){
            super(new GridPane(),550,400);

            layout = (GridPane)this.getRoot();
            listOfNames = FXCollections.observableArrayList("person 1","person 2","person 3");
            listView = new ListView<>(listOfNames);
            editPersonalInfoButton = new Button("Edit Personal Info");
            viewPackageDetailsButton = new Button("View Package Details");
            addPackageButton = new Button ("Add Package");

            editPersonalInfoButton.setOnAction(e -> window.setScene(thirdScene));
            viewPackageDetailsButton.setOnAction(e -> window.setScene(fourthScene));
            addPackageButton.setOnAction(e -> window.setScene(fifthScene));

            layout.add(listView,0,0);
            layout.add(editPersonalInfoButton,0,1);
            layout.add(viewPackageDetailsButton,1,1);
            layout.add(addPackageButton,2,1);
        }
    }

    class ThirdScene extends Scene{
        GridPane layout;
        Text changeInfoTitle;
        Label newFirstName;
        TextField newFTextField;
        Label newLastName;
        TextField newLTextField;
        Label changeAddress;
        TextField newAddressField;
        Button saveBtn;
        HBox sveBtn;
        Button cancelBtn;
        HBox cnclBtn;

        public ThirdScene(){
            super(new GridPane(),450,350);

            layout = (GridPane)this.getRoot();
            changeInfoTitle = new Text("Change Personal Info");
            newFirstName = new Label("First Name:");
            newFTextField = new TextField();
            newLastName = new Label("Last Name:");
            newLTextField = new TextField();
            changeAddress = new Label("Address:");
            newAddressField = new TextField();
            saveBtn = new Button("Save");
            sveBtn = new HBox(5);
            cancelBtn = new Button("Cancel");
            cnclBtn = new HBox(5);

            changeInfoTitle.setFont(Font.font("Georgia",20));

            saveBtn.setAlignment(Pos.BOTTOM_RIGHT);
            cnclBtn.setAlignment(Pos.BOTTOM_LEFT);
            sveBtn.getChildren().add(saveBtn);
            cnclBtn.getChildren().add(cancelBtn);

            saveBtn.setOnAction(e -> window.setScene(secondScene));
            cancelBtn.setOnAction(e -> window.setScene(secondScene));

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(changeInfoTitle,0,0);
            layout.add(newFirstName, 0, 1);
            layout.add(newFTextField,1,1);
            layout.add(newLastName, 0, 2);
            layout.add(newLTextField, 1, 2);
            layout.add(changeAddress,0,3);
            layout.add(newAddressField,1,3);
            layout.add(cnclBtn,0,7);
            layout.add(sveBtn,1,7);
        }
    }

    class FourthScene extends Scene{
        GridPane layout;
        Label item;
        Label itemOutput;
        Label sender;
        Label senderOutput;
        Label receiver;
        Label receiverOutput;
        Label mailType;
        Label mailOutput;
        Label shipDate;
        Label shipOutput;
        Label deliverDate;
        Label deliverOutput;
        Label currentStatus;
        Label currentOutput;
        Button backBtn;
        HBox bBtn;

        public FourthScene(){
            super(new GridPane(),400,400);

            layout = (GridPane)this.getRoot();
            item = new Label("Items:");
            sender = new Label("Sender:");
            receiver = new Label("Receiver:");
            mailType = new Label("Mailtype:");
            shipDate = new Label("Shipping Date:");
            deliverDate = new Label("Delivery Date:");
            currentStatus = new Label("Current Status:");
            itemOutput = new Label();
            senderOutput = new Label();
            receiverOutput = new Label();
            mailOutput = new Label();
            shipOutput = new Label();
            deliverOutput = new Label();
            currentOutput = new Label();

            backBtn = new Button("Back");
            bBtn = new HBox(5);

            bBtn.setAlignment(Pos.BOTTOM_LEFT);
            bBtn.getChildren().add(backBtn);
            layout.add(bBtn, 0, 8);

            backBtn.setOnAction(e -> window.setScene(secondScene));

            layout.setAlignment(Pos.BASELINE_LEFT);
            layout.setHgap(20);
            layout.setVgap(20);
            layout.setPadding(new Insets(25, 25, 25, 25));
            
            layout.add(item, 0, 1);
            layout.add(sender, 0, 2);
            layout.add(receiver, 0, 3);
            layout.add(mailType, 0, 4);
            layout.add(shipDate, 0, 5);
            layout.add(deliverDate, 0, 6);
            layout.add(currentStatus, 0, 7);
            layout.add(itemOutput, 1, 1);
            layout.add(senderOutput, 1, 2);
            layout.add(receiverOutput, 1, 3);
            layout.add(mailOutput, 1, 4);
            layout.add(shipOutput, 1, 5);
            layout.add(deliverOutput, 1, 6);
            layout.add(currentOutput, 1, 7);
        }

    }

    class FifthScene extends Scene{
        GridPane layout;
        Text trackingTitle;
        Text errorMessage;
        TextField trackingTextField;
        Button trackingEnterButton;
        Button trackingCancleButton;
        
        public FifthScene(){
            super(new GridPane(),350,180);
            
            layout = (GridPane)this.getRoot();
            trackingTitle = new Text("Enter Tracking Number");
            errorMessage = new Text("Wrong Number");
            trackingTextField = new TextField();
            trackingEnterButton = new Button("Enter");
            trackingCancleButton = new Button("Cancle");

            trackingTitle.setFont(new Font("georgia", 20));
            errorMessage.setFill(Color.CRIMSON);
            trackingTextField.setPrefWidth(100);
            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setPadding(new Insets(25,25,25,25));
            layout.setHgap(10);
            layout.setVgap(10);
            
            trackingEnterButton.setOnAction(e -> {
                if(true){
                    trackingTextField.setText("");
                    layout.getChildren().remove(errorMessage);
                    window.setScene(secondScene);
                }
                else{
                    if(!layout.getChildren().contains(errorMessage))
                        layout.add(errorMessage,2,2);
                }
            });
            trackingCancleButton.setOnAction(e -> window.setScene(secondScene));

            layout.add(trackingTitle, 0,0,2,1);
            layout.add(trackingTextField,0,1,2,1);
            layout.add(trackingEnterButton,0,2);
            layout.add(trackingCancleButton,1,2);
        }
    }

    class SixthScene extends Scene{
        GridPane layout;
        Text signUpTitle;
        Label newUserName;
        TextField newUserTextField;
        Label newPW;
        TextField newPWTextField;
        TextField fNameTextField;
        Label fName;
        Label lName;
        TextField lNameTextField;
        Label newAddress;
        TextField newAddressTextField;
        Button createBtn;
        HBox creBtn;
        Button returnBtn;
        HBox retBtn;

        public SixthScene(){
            super(new GridPane(),350,450);

            layout = (GridPane)this.getRoot();
            signUpTitle = new Text("Sign Up");
            newUserName = new Label("Username");
            newUserTextField = new TextField();
            newPW = new Label("Password");
            newPWTextField = new TextField();
            fName = new Label("First Name");
            lName = new Label("Last Name");
            lNameTextField = new TextField();
            fNameTextField = new TextField();
            newAddress = new Label("Address");
            newAddressTextField = new TextField();
            createBtn = new Button("Create");
            creBtn = new HBox(5);
            returnBtn = new Button("Back");
            retBtn = new HBox(5);

            signUpTitle.setFont(Font.font("Georgia",25));
            
            createBtn.setOnAction(e -> {
                String tempUserName = newUserTextField.getText();
                String tempPW = newPWTextField.getText();
                String tempFName = fNameTextField.getText();
                String tempLName = lNameTextField.getText();
                String tempAddress = newAddressTextField.getText();
            });

            returnBtn.setOnAction(e -> window.setScene(firstScene));

            creBtn.setAlignment(Pos.BOTTOM_RIGHT);
            creBtn.getChildren().add(createBtn);

            retBtn.setAlignment(Pos.BOTTOM_LEFT);
            retBtn.getChildren().add(returnBtn);

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(8);
            layout.setVgap(8);
            layout.setPadding(new Insets(25, 25, 25, 25));
            
            layout.add(signUpTitle,0,0);
            layout.add(newUserName, 0, 1);
            layout.add(newUserTextField,0,2);
            layout.add(newPW, 0, 3);
            layout.add(newPWTextField,0,4);  
            layout.add(fName, 0, 5);
            layout.add(fNameTextField, 0, 6);
            layout.add(lName, 0, 7);
            layout.add(lNameTextField, 0, 8);
            layout.add(newAddress, 0, 9);
            layout.add(newAddressTextField, 0, 10);
            layout.add(retBtn, 0, 11);
            layout.add(creBtn, 1, 11);
        }
    }

    class SeventhScene extends Scene{

        public SeventhScene(){
            super(new GridPane(),300,300);
        }

    }
    
}