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


public class ThirdScene extends Application{

    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){

        GridPane grid3 = new GridPane();
        grid3.setAlignment(Pos.BASELINE_CENTER);
        grid3.setHgap(10);
        grid3.setVgap(10);
        grid3.setPadding(new Insets(25, 25, 25, 25));

        Scene testScene = new Scene(grid3,450,350);

        Text changeInfoTitle = new Text("Change Personal Info");
        changeInfoTitle.setFont(Font.font("Georgia",20));
        grid3.add(changeInfoTitle,0,0);

        Label newFirstName = new Label("First Name:");
        grid3.add(newFirstName, 0, 1);
        TextField newFTextField = new TextField();
        grid3.add(newFTextField,1,1);

        Label newLastName = new Label("Last Name:");
        grid3.add(newLastName, 0, 2);
        TextField newLTextField = new TextField();
        grid3.add(newLTextField, 1, 2);

        Label changeAddress = new Label("Address:");
        grid3.add(changeAddress,0,3);
        TextField newAddressField = new TextField();
        grid3.add(newAddressField,1,3);

        Button saveBtn = new Button("Save");
        HBox sveBtn = new HBox(5);
        saveBtn.setAlignment(Pos.BOTTOM_RIGHT);
        sveBtn.getChildren().add(saveBtn);
        grid3.add(sveBtn,2,7);
        //saveBtn.setOnAction(e -> )

        Button cancelBtn = new Button("Cancel");
        HBox cnclBtn = new HBox(5);
        cnclBtn.setAlignment(Pos.BOTTOM_LEFT);
        cnclBtn.getChildren().add(cancelBtn);
        grid3.add(cnclBtn,1,7);
        //cancelBtn.setOnAction(e -> window.setScene(loginScene));

        //changeInfoScene = new Scene(grid3,350,350);

        stage.setScene(testScene);

        stage.show();
    }

}