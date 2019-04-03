import java.sql.*;
import java.util.*;

public class DataHandler{

    private Connection connection;
    private Statement statement;

    static{


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

}