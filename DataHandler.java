import java.sql.*;
import java.util.*;

public class DataHandler{

    private static Connection connection;
    private static Statement statement;

    static{
        //make a connection when class is loaded
        try{
            String driver = "org.mariadb.jdbc.Driver";
            String host = "jdbc:mariadb://localhost:3306/DB";//try not to specify which database
            String user = "root";
            String password = "209539352";
            
            Class.forName(driver);//this line may not be necessary
            
            connection = DriverManager.getConnection(host,user,password);
            statement = connection.createStatement();

            //if tables dont exist, create tables
            //... put in the code here ...

        }catch(Exception expt){
            expt.printStackTrace();
        }

    }

    //[name,address]
    public static String [] getPersonlInfo(String userID){
        return null;
    }

    public static ArrayList<String> getListOfItems(String userID){
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