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
            String password = "3821";
            
            String createDatabase = "CREATE DATABASE IF NOT EXISTS Delivery;";

            String createUserTable = "CREATE TABLE IF NOT EXISTS User("
            + "username VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "password VARCHAR(128),"
            + "name VARCHAR(128),"
            + "address VARCHAR(128));";
            
            String createPackageTable = "CREATE TABLE IF NOT EXISTS Package("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "items VARCHAR(128),"
            + "sender VARCHAR(128),"
            + "user VARCHAR(128),"
            + "mailtype VARCHAR(128),"
            + "shippingDate DATE,"
            + "deliveryDate DATE,"
            + "currentStatus VARCHAR(128),"
            + "FOREIGN KEY(user) REFERENCES User(username) ON DELETE CASCADE ON UPDATE CASCADE);"; 

            String createAdminTable = "CREATE TABLE IF NOT EXISTS Admin("
            + "username VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "password VARCHAR(128),"
            + "name VARCHAR(128));";

            String alterPackageTable = "ALTER TABLE Package AUTO_INCREMENT=123456789;";
            
            String insertAdmin = "INSERT IGNORE INTO ADMIN VALUES(\"mestime\",\"database\", \"Marvin The Martian\");";

            connection = DriverManager.getConnection(host,user,password);
            statement = connection.createStatement();

            statement.executeUpdate(createDatabase);

            connection = DriverManager.getConnection(host+"Delivery",user,password);
            statement = connection.createStatement();

            statement.executeUpdate(createUserTable);
            statement.executeUpdate(createAdminTable);
            statement.executeUpdate(createPackageTable);
            statement.executeUpdate(alterPackageTable);
            statement.executeUpdate(insertAdmin);

            createNewUser("jconnor", "bestprofessor","John Connor", "City College of New York");
            createNewUser("person", "password", "Generic Person", "Generic Address");
            //createNewPackage("Red Dress, Nintendo Switch", "1234 Long St Ave", "jconnor", "Standard Mail","2019-03-12","2019-03-25","Delivered");
            
        }catch(Exception expt){
            expt.printStackTrace();
        }

    }

    //reutrns [name,address] based on username inpuit
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
            if(username.equals(""))
                return;
            try{
                String insertNewUser = "INSERT IGNORE INTO User VALUES(\"" + username + "\",\"" + password + "\",\"" + name + "\",\"" + address + "\");";
                statement.executeUpdate(insertNewUser);

            }catch(Exception expt){
                expt.printStackTrace();
            }
    }

    public static boolean isValidUsername(String username){
        if(isValidAdminName(username))
            return true;
        if(username.equals(""))
            return false;
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

    //input: name,items,senderAddress,username,mailtype,shippingDate,deliveryDate,currentStatus
    public static void createNewPackage(String items, String senderAddress, String username, String mailtype, String shippingDate, String deliveryDate, String currentStatus){
        try{
            String createPackageQuery = "INSERT IGNORE INTO Package(items,sender,user,mailtype,shippingDate,deliveryDate,currentStatus) VALUES(\"" +items+ "\",\"" +senderAddress+ "\",\"" +username+ "\",\"" +mailtype+ "\",\"" +shippingDate+ "\",\"" +deliveryDate+ "\",\"" +currentStatus+ "\");";   
            statement.executeUpdate(createPackageQuery);

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    //packageID is a string, but must be convertible to an int
    public static boolean isValidPackageID(String packageID){
        if(packageID.equals(""))
            return false;
        try{
            int numberOfPackages = 0;
            String countPackages = "SELECT COUNT(1) FROM Package WHERE id=" +packageID+ ";";
            ResultSet countInfo = statement.executeQuery(countPackages);

            if(countInfo.next()){
                numberOfPackages = countInfo.getInt("COUNT(1)");
                countInfo.close();
            }

            return numberOfPackages > 0;

        }catch(Exception expt){
            expt.printStackTrace();
        }
        return false;
    }

    public static ArrayList<String> getListOfPackages(String username){
        ArrayList<String> listOfPackageIDs = new ArrayList<>();
        try{
            String selectPackages = "SELECT id FROM Package WHERE user=\"" +username+ "\";";
            ResultSet packageInfo = statement.executeQuery(selectPackages);

            while(packageInfo.next()){
                listOfPackageIDs.add(Integer.toString(packageInfo.getInt("id")));
            }
            packageInfo.close();

        }catch(Exception expt){
            expt.printStackTrace();
        }

        return listOfPackageIDs;
    }

    //name,items,senderAddress, mailtype, shippingDate, deliveryDate, currentStatus
    public static String [] getPackageDetails(String packageID){
        String [] packageDetails = {"","","","","","",""};

        if(packageID.equals(""))
            return packageDetails;
        try{
            String selectPackage = "SELECT * FROM Package WHERE id=" +packageID+ ";";
            ResultSet packageInfo = statement.executeQuery(selectPackage);

            if(packageInfo.next()){
                packageDetails[0] = packageInfo.getString("name");
                packageDetails[1] = packageInfo.getString("items");
                packageDetails[2] = packageInfo.getString("sender");
                packageDetails[3] = packageInfo.getString("mailType");
                packageDetails[4] = packageInfo.getDate("shippingDate").toString();
                packageDetails[5] = packageInfo.getDate("deliveryDate").toString();
                packageDetails[6] = packageInfo.getString("currentStatus");
            }

            packageInfo.close();

        }catch(Exception expt){
            expt.printStackTrace();
        }
        return packageDetails;
    }

    
    public static void addPackageForUser(String username, String packageID){
        if(packageID.equals(""))
            return;
        try{
            String addPackage = "UPDATE Package SET user=\"" +username+ "\" WHERE id=" +packageID+ ";";
            
            statement.executeUpdate(addPackage);

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    public static boolean isValidAdmin(String username, String password){
        try{
            int numberOfAdmins = 0;
            String countAdminQuery = "SELECT COUNT(1) FROM Admin WHERE username=\"" +username+ "\" AND password=\"" +password+ "\";";
            ResultSet countInfo = statement.executeQuery(countAdminQuery);

            if(countInfo.next()){
                numberOfAdmins = countInfo.getInt("COUNT(1)");
            }
            countInfo.close();
            return numberOfAdmins > 0;

        }catch(Exception expt){
            expt.printStackTrace();
        }
        return false;
    }

    public static boolean isValidAdminName(String username){
        if(username.equals(""))
            return false;
        try{
            int numberOfUsernames = 0;
            String countUsernames = "SELECT COUNT(1) FROM Admin WHERE username=\"" + username + "\";";
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

    public static String getNextPackageID(){
        int lastPackageID = 0;
        try{
            String selectLastPackageID = "SELECT id FROM Package ORDER BY id DESC LIMIT 1";
            ResultSet packageInfo = statement.executeQuery(selectLastPackageID);

            if(packageInfo.next())
                lastPackageID = packageInfo.getInt("id");
            
            packageInfo.close();

        }catch(Exception expt){
            expt.printStackTrace();
        }
        if(lastPackageID == 0)
            return 123456789 + "";
        else
            return (lastPackageID + 1) + "";
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