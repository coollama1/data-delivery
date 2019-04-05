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
            
            String createDatabase = "CREATE DATABASE IF NOT EXISTS Delivery;";

            String createUserTable = "CREATE TABLE IF NOT EXISTS User("
            + "username VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "password VARCHAR(128),"
            + "name VARCHAR(128),"
            + "address VARCHAR(128));";

            String createMailtypeTable = "CREATE TABLE IF NOT EXISTS Mailtype("
            + "name VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "description VARCHAR(1280));";

            String createPackageTable = "CREATE TABLE IF NOT EXISTS Package("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "name VARCHAR(128),"
            + "items VARCHAR(128),"
            + "sender VARCHAR(128),"
            + "user VARCHAR(128) NOT NULL,"
            + "mailtype VARCHAR(128),"
            + "shippingDate DATE,"
            + "deliveryDate DATE,"
            + "currentStatus VARCHAR(128),"
            + "FOREIGN KEY(user) REFERENCES User(username) ON DELETE CASCADE ON UPDATE CASCADE,"
            + "FOREIGN KEY(mailtype) REFERENCES Mailtype(name) ON UPDATE CASCADE);"; 

            String createAdminTable = "CREATE TABLE IF NOT EXISTS Admin("
            + "username VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "password VARCHAR(128),"
            + "name VARCHAR(128));";

            String alterPackageTable = "ALTER TABLE Package AUTO_INCREMENT=12345678;";

            String insertStandardMail = "INSERT IGNORE INTO Mailtype VALUES(\"Standard Mail\", \"Standard Mail is less expensive but it takes a little while to get anywhere. Anything can be shipped with parcel post.\");";
            String insertExpressMail = "INSERT IGNORE INTO Mailtype VALUES(\"Express Mail\", \"Express mail is not frequently used because it is more expensive but may be used for urgent deliveries. This option offers overnight and flat rate envelopes.\");";
            String insertFirstClassMail = "INSERT IGNORE INTO Mailtype VALUES(\"First Class Mail\", \"First class mail is an inexpensive way to send any item that weighs 13 ounces or less. This includes mail such as greeting cards and regular stamped mail.\");";
            String insertPriorityMail = "INSERT IGNORE INTO Mailtype VALUES(\"Priority Mail\", \"The starting rate for Priority mail is no less than five dollars. Any item can be shipped this way to ensure fast delivery, generally within 2-3 days.\");";
            
            String insertAdmin = "INSERT IGNORE INTO ADMIN VALUES(\"mestime\",\"database\", \"Marvin The Martian\");";

            connection = DriverManager.getConnection(host,user,password);
            statement = connection.createStatement();

            statement.executeUpdate(createDatabase);

            connection = DriverManager.getConnection(host+"Delivery",user,password);
            statement = connection.createStatement();

            statement.executeUpdate(createUserTable);
            statement.executeUpdate(createAdminTable);
            statement.executeUpdate(createMailtypeTable);
            statement.executeUpdate(createPackageTable);
            statement.executeUpdate(alterPackageTable);
            statement.executeUpdate(insertStandardMail);
            statement.executeUpdate(insertExpressMail);
            statement.executeUpdate(insertFirstClassMail);
            statement.executeUpdate(insertPriorityMail);
            statement.executeUpdate(insertAdmin);
            
        }catch(Exception expt){
            expt.printStackTrace();
        }

    }

    //[name,address]
    public static String [] getPersonalInfo(String username){
            String [] personalInfo = {"",""};
            
            try{
                String selectUserInfo = "SELECT name,address FROM USER WHERE username=\"" +username+ "\";";

                ResultSet userInfo = statement.executeQuery(selectUserInfo);

                if(userInfo.next()){
                    personalInfo[0] = userInfo.getString("name");
                    personalInfo[1] = userInfo.getString("address");

                    userInfo.close();

                    return personalInfo;
                }

            }catch(Exception expt){
                expt.printStackTrace();
            }

        return (personalInfo);
    }

    public static void createNewUser(String username, String password, String name, String address){
            try{
                String insertNewUser = "INSERT IGNORE INTO User VALUES(\"" + username + "\",\"" + password + "\",\"" + name + "\",\"" + address + "\");";
                statement.executeUpdate(insertNewUser);

            }catch(Exception expt){
                expt.printStackTrace();
            }
    }

    public static boolean isValidUsername(String username){
        try{
            int numberOfUsernames = 0;
            String countUsernames = "SELECT COUNT(1) FROM User WHERE username=\"" + username + "\";";
            ResultSet countInfo = statement.executeQuery(countUsernames);

            if(countInfo.next()){
                numberOfUsernames = countInfo.getInt("COUNT(1)");
            }

            return numberOfUsernames > 0;
            
        }catch(Exception expt){
            expt.printStackTrace();
        }
        return false;
    }

    public static boolean isValidUser(String username, String password){
        try{
            int numberOfUsers = 0;
            String countUsers = "SELECT COUNT(1) FROM User WHERE username=\"" +username+ "\" AND password= \"" +password+ "\";";
            ResultSet countInfo = statement.executeQuery(countUsers);

            if(countInfo.next()){
                numberOfUsers = countInfo.getInt("COUNT(1)");
            }

            return numberOfUsers > 0;

        }catch(Exception expt){
            expt.printStackTrace();
        }

        return false;
    }

    public static void updatePersonalInfo(String username, String newName, String newAddress){
        try{
            String updateQuery = "UPDATE User SET name=\"" +newName+ "\", address=\"" +newAddress+ "\" WHERE username=\"" +username+ "\";";
            statement.executeUpdate(updateQuery);

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    public static ArrayList<Integer> getListOfPackages(String username){
        return null;
    }

    public static String getPackageName(String itemID){
        return null;
    }

    public static boolean isValidPackageID(String itemID){
        return false;
    }


    //itemName,senderAddress,receiverAdress, mailtype, postOffice, shippingDate, deliveryDate
    public static String [] getPackageDetails(String itemID){
        return null;
    }

    //true if item is added, false if user already has item on their account
    public static boolean addPackageForUser(String userID, String itemID){
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