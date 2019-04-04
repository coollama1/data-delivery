import java.sql.*;
import java.util.*;

public class DataHandler{

    private static Connection connection;
    private static Statement statement;

    static{
        //make a connection when class is loaded
        try{
            String host = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "209539352";
            
            connection = DriverManager.getConnection(host,user,password);
            statement = connection.createStatement();

            //if tables dont exist, create tables
            //... put in the code here ...

            String createDatabase = "CREATE DATABASE IF NOT EXISTS Delivery";
            String useDatabase = "USE Delivery";

            String createUserTable = "CREATE TABLE IF NOT EXISTS User("
            + "username VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "password VARCHAR(128),"
            + "name VARCHAR(128),"
            + "address VARCHAR(128));";

            String createMailTable = "CREATE TABLE IF NOT EXISTS Mail("
            + "id INT PRIMARY KEY NOT NULL,"
            + "user VARCHAR(128),"
            + "sender VARCHAR(128),"
            + "receiver VARCHAR(128),"
            + "mailtype VARCHAR(128),"
            + "shippingDate VARCHAR(128),"
            + "deliveryDate VARCHAR(128),"
            + "currentStatus VARCHAR(128),"
            + "FOREIGN KEY(user) REFERENCES User(username) ON DELETE CASCADE ON UPDATE CASCADE);"; 

            String createPackagelTable = "CREATE TABLE IF NOT EXISTS Package("
            + "id INT PRIMARY KEY NOT NULL,"
            + "user VARCHAR(128),"
            + "sender VARCHAR(128),"
            + "receiver VARCHAR(128),"
            + "mailtype VARCHAR(128),"
            + "shippingDate VARCHAR(128),"
            + "deliveryDate VARCHAR(128),"
            + "currentStatus VARCHAR(128));";

            String createAdminTable = "CREATE TABLE IF NOT EXISTS Admin("
            + "username VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "password VARCHAR(128));"

            String createMailtypeTable = "CREATE TABLE IF NOT EXISTS Mailtype("
            + "name VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "description VARCHAR(128));";

            String createPostOfficeTable = "CREATE TABLE IF NOT EXISTS PostOffice("
            + "name VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "address VARCHAR(128));"

            statement.executeUpdate(createDatabase);
            statement.executeUpdate(useDatabase);
            statement.executeUpdate(createUserTable);
            statement.executeUpdate(createMailTable);
            statement.executeUpdate(createPackagelTable);
            statement.executeUpdate(createMailTable);
            statement.executeUpdate(createPostOfficeTable);

        }catch(Exception expt){
            expt.printStackTrace();
        }

    }

    //[name,address]
    public static String [] getPersonlInfo(String username){
        return null;
    }

    public static ArrayList<String> getListOfMail(String username){
        return null;
    }

    public static String getItemName(String itemID){
        return null;
    }

    public static boolean isValidItemID(String itemID){
        return false;
    }

    public static boolean isValidUser(String username, String password){
        return false;
    }

    public static boolean isValidUsername(String userName){
        return false;
    }

    public static String getUserID(String username, String password){
        return null;
    }

    public static void updatePersonalInfo(String newName, String password){

    }

    //itemName,senderAddress,receiverAdress, mailtype, postOffice, shippingDate, deliveryDate
    public static String [] getItemDetails(String itemID){
        return null;
    }

    //true if item is added, false if user already has item on their account
    public static boolean addItemForUser(String userID, String itemID){
        return false;
    }

    public static void closeEverything(){
        try{
            if(connection!= null)
                connection.close();
            if(connection!=null)
                statement.close();

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

}