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
//work in progress

public class SecondScene extends Application{

    public static void main(String[]args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
    	
    	GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.BASELINE_CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 25, 25, 25));
        
        Scene testScene = new Scene(grid2,450,350);
        
        Label Pre_name = new Label("   Name:");
        grid2.add(Pre_name,0,0);
        Label Pre_addr = new Label("Address:");
        grid2.add(Pre_addr,0,1);
        Label packList = new Label("Mail/Packages");
        grid2.add(packList,0,3);
        
        ListView<String> list = new ListView<String>();
        //test code REMOVE AFTERWARDS!!
        ObservableList<String> items =FXCollections.observableArrayList ("SteinsGate","SRCcode","OMEGA","L","UL"); 
        //test code REMOVE AFTERWARDS!!
        list.setItems(items);
        
        //edit at your own risk! the following lines control the appearance of ListView
        list.setPrefWidth(100);
        list.setPrefHeight(70);
        list.setOrientation(Orientation.HORIZONTAL);
        //edit at your own risk!
        
        Button viewPack = new Button("View Package");
        HBox viewP = new HBox(5);
        viewPack.setAlignment(Pos.BOTTOM_LEFT);
        viewP.getChildren().add(viewPack);
        grid2.add(viewP,0,7);
        
        Button addPack = new Button("Add Package");
        HBox addP = new HBox(5);
        addPack.setAlignment(Pos.BOTTOM_RIGHT);
        addP.getChildren().add(addPack);
        grid2.add(addP,1,7);
        
        stage.setScene(testScene);

        stage.show();
    }

}