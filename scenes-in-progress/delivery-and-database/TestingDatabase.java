
public class TestingDatabase{

    public static void main(String [] args){
        System.out.println(DataHandler.isValidUser("jconnor","bestprofessor"));
    }
}
/*
            trackingEnterButton.setOnAction(e -> {
                String trackingNumber = trackingTextField.getText();
                if(DataHandler.isValidPackageID(trackingNumber)){
                    System.out.println(trackingTextField.getText() + " " + DataHandler.isValidPackageID(trackingTextField.getText()));
                    trackingTextField.setText("");
                    layout.getChildren().remove(errorMessage);
                    window.setScene(secondScene);
                }
                else{
                    if(!layout.getChildren().contains(errorMessage))
                        layout.add(errorMessage,2,2);
                }
            });
*/

/*
    public static String getPackageName(String packageID){
        try{
            String name = "";
            String selectPackageNameQuery = "SELECT name FROM Package WHERE id=" +packageID+ ";";
            ResultSet nameInfo = statement.executeQuery(selectPackageNameQuery);

            if(nameInfo.next())
                name = nameInfo.getString("name");

                nameInfo.close();
                return name;
            

        }catch(Exception expt){
            expt.printStackTrace();
        }

        return "";
    }
*/

/*
        //in package table
        //+ "FOREIGN KEY(mailtype) REFERENCES Mailtype(name) ON UPDATE CASCADE

            String createMailtypeTable = "CREATE TABLE IF NOT EXISTS Mailtype("
            + "name VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "description VARCHAR(1280));";

            statement.executeUpdate(createMailtypeTable);

            String insertStandardMail = "INSERT IGNORE INTO Mailtype VALUES(\"Standard Mail\", \"Standard Mail is less expensive but it takes a little while to get anywhere. Anything can be shipped with parcel post.\");";
            String insertExpressMail = "INSERT IGNORE INTO Mailtype VALUES(\"Express Mail\", \"Express mail is not frequently used because it is more expensive but may be used for urgent deliveries. This option offers overnight and flat rate envelopes.\");";
            String insertFirstClassMail = "INSERT IGNORE INTO Mailtype VALUES(\"First Class Mail\", \"First class mail is an inexpensive way to send any item that weighs 13 ounces or less. This includes mail such as greeting cards and regular stamped mail.\");";
            String insertPriorityMail = "INSERT IGNORE INTO Mailtype VALUES(\"Priority Mail\", \"The starting rate for Priority mail is no less than five dollars. Any item can be shipped this way to ensure fast delivery, generally within 2-3 days.\");";

            statement.executeUpdate(insertStandardMail);
            statement.executeUpdate(insertExpressMail);
            statement.executeUpdate(insertFirstClassMail);
            statement.executeUpdate(insertPriorityMail);
            statement.executeUpdate(insertAdmin);
*/
    