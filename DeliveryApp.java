import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.*;
import javafx.collections.*;
import javafx.scene.control.*;

public class DeliveryApp extends Application{
	
	private Stage window;
    private FirstScene firstScene;
    private SecondScene secondScene;
    private ThirdScene thirdScene;
    private FourthScene fourthScene;
    private FifthScene fifthScene;
    private SixthScene sixthScene;
    private SeventhScene seventhScene;

    private String currentUsername;
    private String currentAdmin;
    private String currentPackageID;
    private ObservableList<String> listOfPackageIDs;
    
    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
        window = stage;
        initilizeValues();

        window.setScene(firstScene);
        window.show();
    }

    @Override
    public void stop(){
        DataHandler.closeEverything();
    }

    public void initilizeValues(){
        currentUsername = "";
        currentAdmin = "";
        currentPackageID = "0";
    	firstScene = new FirstScene();
        secondScene = new SecondScene();
        thirdScene = new ThirdScene();
        fourthScene = new FourthScene();
        fifthScene = new FifthScene();
        sixthScene = new SixthScene();
        seventhScene = new SeventhScene();
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
            super(new GridPane(),390,200);

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

            sceneTitle.setFont(Font.font("Segoe UI Bold",25));

            loginBtn.setOnAction(e -> {
                String tempUsername = userTextField.getText();
                String tempPassword = pwTextField.getText();
                if(DataHandler.isValidUser(tempUsername,tempPassword)){
                    currentUsername = tempUsername;
                    userTextField.setText("");
                    pwTextField.setText("");
                    secondScene = new SecondScene();
                    window.setScene(secondScene);
                }
                if(DataHandler.isValidAdmin(tempUsername,tempPassword)){
                    userTextField.setText("");
                    pwTextField.setText("");
                    seventhScene = new SeventhScene();
                    window.setScene(seventhScene);
                }
            });
            signUpBtn.setOnAction(e -> {
                userTextField.setText("");
                pwTextField.setText("");
                window.setScene(sixthScene);
            });
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
        Button logoutButton;
        Label preName;
        Label preAddr;
        Label packList;
        Label nameLabel;
        Label addressLabel;
        String [] personalInfo;
        ObservableList<String> listOfPack;
        ListView<String> listView;
        
        public SecondScene(){
            super(new GridPane(),300,400);

            layout = (GridPane)this.getRoot();
            listOfPack = FXCollections.observableArrayList(DataHandler.getListOfPackages(currentUsername));
            listView = new ListView<>(listOfPack);
            personalInfo = DataHandler.getPersonalInfo(currentUsername);
            
            editPersonalInfoButton = new Button("Edit Personal Info");
            viewPackageDetailsButton = new Button("Package Details");
            addPackageButton = new Button ("Add Package");
            logoutButton = new Button("Logout");
            preName = new Label("Name:");
            preAddr = new Label("Address:");
            packList = new Label("Mail/Packages");
            nameLabel = new Label(personalInfo[0]);
            addressLabel = new Label(personalInfo[1]);

            packList.setFont(Font.font("Segoe UI", 15));

            editPersonalInfoButton.setOnAction(e -> window.setScene(thirdScene));
            addPackageButton.setOnAction(e -> window.setScene(fifthScene));
            logoutButton.setOnAction(e -> window.setScene(firstScene));

            viewPackageDetailsButton.setOnAction(e ->{
                if(listView.getSelectionModel().getSelectedItem() != null)
                    currentPackageID = listView.getSelectionModel().getSelectedItem().toString();
                if(DataHandler.isValidPackageID(currentPackageID)){
                    fourthScene = new FourthScene();
                    window.setScene(fourthScene);
                }
            });

            listView.setPrefWidth(300);
            listView.setPrefHeight(400);
            listView.setOrientation(Orientation.VERTICAL);

            layout.setHgap(10);
            layout.setVgap(10);

            layout.setPadding(new Insets(20,25,25,25));

            layout.add(listView,0,5,2,1);
            layout.add(editPersonalInfoButton,0,3,2,1);
            layout.add(viewPackageDetailsButton,0,6);
            layout.add(logoutButton,0,8);
            layout.add(addPackageButton,1,6);
            layout.add(preName,0,0);
            layout.add(nameLabel,1,0);
            layout.add(preAddr,0,2);
            layout.add(addressLabel,1,2);
            layout.add(packList,0,4);
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
            super(new GridPane(),300,220);

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

            changeInfoTitle.setFont(Font.font("Segoe UI Bold",23));

            sveBtn.setAlignment(Pos.BOTTOM_RIGHT);
            cnclBtn.setAlignment(Pos.BOTTOM_LEFT);
            sveBtn.getChildren().add(saveBtn);
            cnclBtn.getChildren().add(cancelBtn);

            saveBtn.setOnAction(e -> {
                DataHandler.updatePersonalInfo(currentUsername, newFTextField.getText() +" "+ newLTextField.getText(), newAddressField.getText());
                secondScene = new SecondScene();
                window.setScene(secondScene);
            });
            cancelBtn.setOnAction(e -> {
                window.setScene(secondScene);
            });

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(changeInfoTitle,0,0,2,1);
            layout.add(newFirstName, 0, 1);
            layout.add(newFTextField,1,1,2,1);
            layout.add(newLastName, 0, 2);
            layout.add(newLTextField, 1, 2,2,1);
            layout.add(changeAddress,0,3);
            layout.add(newAddressField,1,3,2,1);
            layout.add(cnclBtn,0,5);
            layout.add(sveBtn,2,5);
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
            super(new GridPane(),350,350);

            String [] itemDetails = DataHandler.getPackageDetails(currentPackageID);

            layout = (GridPane)this.getRoot();
            item = new Label("Items:");
            sender = new Label("Sender:");
            receiver = new Label("Receiver:");
            mailType = new Label("Mailtype:");
            shipDate = new Label("Shipping Date:");
            deliverDate = new Label("Delivery Date:");
            currentStatus = new Label("Current Status:");
            itemOutput = new Label(itemDetails[0]);
            senderOutput = new Label(itemDetails[1]);
            receiverOutput = new Label(DataHandler.getPersonalInfo(itemDetails[2])[1]);
            mailOutput = new Label(itemDetails[3]);
            shipOutput = new Label(itemDetails[4]);
            deliverOutput = new Label(itemDetails[5]);
            currentOutput = new Label(itemDetails[6]);

            backBtn = new Button("Back");
            bBtn = new HBox(5);

            bBtn.setAlignment(Pos.BOTTOM_LEFT);
            bBtn.getChildren().add(backBtn);
            layout.add(bBtn, 0, 8);

            backBtn.setOnAction(e -> window.setScene(secondScene));

            layout.setAlignment(Pos.BASELINE_LEFT);
            layout.setHgap(20);
            layout.setVgap(20);
            layout.setPadding(new Insets(10, 25, 25, 25));
            
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
            super(new GridPane(),275,150);
            
            layout = (GridPane)this.getRoot();
            trackingTitle = new Text("Enter tracking number");
            errorMessage = new Text("Wrong number.");
            trackingTextField = new TextField();
            trackingEnterButton = new Button("Enter");
            trackingCancleButton = new Button("Cancel");

            trackingTitle.setFont(new Font("Segoe UI", 20));
            errorMessage.setFill(Color.CRIMSON);
            trackingTextField.setPrefWidth(100);
            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setPadding(new Insets(25,25,25,25));
            layout.setHgap(10);
            layout.setVgap(10);
            
            trackingEnterButton.setOnAction(e -> {
                String trackingNumber = trackingTextField.getText();
                if(DataHandler.isValidPackageID(trackingNumber)){
                    DataHandler.addPackageForUser(currentUsername,trackingNumber);
                    trackingTextField.setText("");
                    secondScene = new SecondScene();
                    window.setScene(secondScene);
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
                if(!tempUserName.equals("") && !DataHandler.isValidUsername(tempUserName)){
                    DataHandler.createNewUser(tempUserName, tempPW, tempFName +" "+ tempLName, tempAddress);
                    newUserTextField.setText("");
                    newPWTextField.setText("");
                    lNameTextField.setText("");
                    fNameTextField.setText("");
                    newAddressTextField.setText("");
                    window.setScene(firstScene);
                }
            });

            returnBtn.setOnAction(e -> {
                newUserTextField.setText("");
                newPWTextField.setText("");
                lNameTextField.setText("");
                fNameTextField.setText("");
                newAddressTextField.setText("");
                window.setScene(firstScene);
            });

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
    	GridPane layout;
    	Text title;
    	Label receivingUser;
    	TextField receiverTextField;
    	Label itemLabel;
    	TextField itemTextField;
    	Label senderLabel;
    	TextField senderTextField;
    	Label mailLabel;
    	TextField mailTextField;
    	Label shippingDate;
    	TextField shippingTextField;
    	Label deliveryDate;
    	TextField deliveryTextField;
    	Label statusLabel;
    	TextField statusTextField;
    	Label tracking;
    	Label trackingLabel;
    	Button enterBtn;
    	HBox eBtn;
    	Button cancelBtn;
    	HBox cBtn;
    	
        public SeventhScene(){
            super(new GridPane(),350,500);
            
            layout = (GridPane)this.getRoot();
            title = new Text("Add Package");
            receivingUser = new Label("Receiving User:");
            receiverTextField = new TextField();
            itemLabel = new Label("Items:");
            itemTextField = new TextField();
            senderLabel = new Label("Sender:");
            senderTextField = new TextField();
            mailLabel = new Label("Mail Type:");
            mailTextField = new TextField();
            shippingDate = new Label("Shipping Date:");
            shippingTextField = new TextField();
            deliveryDate = new Label("Delivery Date:");
            deliveryTextField = new TextField();
            statusLabel = new Label("Current Status:");
            statusTextField = new TextField();
            tracking = new Label("Tracking #:");
            trackingLabel = new Label(DataHandler.getNextPackageID());
            enterBtn = new Button("Enter");
            eBtn = new HBox(5);
            cancelBtn = new Button("Cancel");
            cBtn = new HBox(5);

            title.setFont(Font.font("Segoe UI Bold",25));
            
            enterBtn.setOnAction(e -> {
            	String user = receiverTextField.getText();
                String items = itemTextField.getText();
                String sender = senderTextField.getText();
                String mailtype = mailTextField.getText();
                String shippingDate = shippingTextField.getText();
                String deliveryDate = deliveryTextField.getText();
                String currentStatus = statusTextField.getText();
                DataHandler.createNewPackage(items,sender,user,mailtype,shippingDate,deliveryDate,currentStatus);
                clearTextFields();
            });
            
            cancelBtn.setOnAction(e -> {
                clearTextFields();
                window.setScene(firstScene);
            
            });
            
            eBtn.setAlignment(Pos.BOTTOM_RIGHT);
        	eBtn.getChildren().add(enterBtn);
        	
        	cBtn.setAlignment(Pos.BOTTOM_LEFT);
        	cBtn.getChildren().add(cancelBtn);
        	
        	layout.setAlignment(Pos.BASELINE_LEFT);
        	layout.setHgap(15);
        	layout.setVgap(20);
        	layout.setPadding(new Insets(25, 25, 25, 25));
        	
        	layout.add(title, 0, 0, 2, 1);
        	layout.add(receivingUser, 0, 1);
        	layout.add(receiverTextField,1,1);
        	layout.add(itemLabel, 0, 2);
        	layout.add(itemTextField,1,2);
        	layout.add(senderLabel, 0, 3);
        	layout.add(senderTextField,1,3);
        	layout.add(mailLabel, 0, 4);
        	layout.add(mailTextField,1,4);
        	layout.add(shippingDate, 0, 5);
        	layout.add(shippingTextField,1,5);
        	layout.add(deliveryDate, 0, 6);
        	layout.add(deliveryTextField,1,6);
        	layout.add(statusLabel, 0, 7);
        	layout.add(statusTextField,1,7);
        	layout.add(tracking, 0, 8);
        	layout.add(trackingLabel, 1, 8);
        	layout.add(eBtn, 1, 9);
        	layout.add(cBtn, 0, 9);
        }

        public void clearTextFields(){
            receiverTextField.setText("");
            itemTextField.setText("");
            senderTextField.setText("");
            mailTextField.setText("");
            shippingTextField.setText("");
            deliveryTextField.setText("");
            statusTextField.setText("");
            trackingLabel.setText(DataHandler.getNextPackageID());
        }

    }
    
}