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
            String password = "@Fcrt39jiv9";
            
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
            + "deliveryAddress VARCHAR(128),"
            + "mailtype VARCHAR(128),"
            + "shippingDate DATE,"
            + "deliveryDate DATE,"
            + "FOREIGN KEY(user) REFERENCES User(username) ON DELETE CASCADE ON UPDATE CASCADE);";

            String createStatusTable = "CREATE TABLE IF NOT EXISTS status("
            + "id INT PRIMARY KEY,"
            + "name VARCHAR(128));";

            String createHistoryTable = "CREATE TABLE IF NOT EXISTS history("
            + "package_id INT PRIMARY KEY,"
            + "status_id INT,"
            + "delivered_date DATE,"
            + "FOREIGN KEY (package_id) REFERENCES Package(id),"
            + "FOREIGN KEY (status_id) REFERENCES status(id));";

            String createAdminTable = "CREATE TABLE IF NOT EXISTS Admin("
            + "username VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "password VARCHAR(128),"
            + "name VARCHAR(128));";

            
            //String shippingCheck = "CREATE TRIGGER shippingCheck BEFORE INSERT ON Package";

            /*String createCurrentStatusFunction = "CREATE FUNCTION CurrentStatus(integer packageid) RETURNS integer " +
                    "deterministic SELECT status_id FROM history WHERE package_id = packageid;";*/

            String createUserInfoView = "CREATE OR REPLACE VIEW user_info AS SELECT username, name, address FROM User;";

            String alterPackageTable = "ALTER TABLE Package AUTO_INCREMENT=123456789;";
            
            String insertAdmin = "INSERT IGNORE INTO Admin VALUES(\"mestime\",\"database\", \"Marvin The Martian\");";
            String insertSecondAdmin = "INSERT IGNORE INTO Admin VALUES(\"steinsgate\",\"database\", \"Dai\");";

            String insertStatus = "INSERT IGNORE INTO Status(id,name) VALUE(\"0\",\"processing\"),(\"1\",\"shipping\")," +
                    "(\"2\",\"outfordelivery\"),(\"3\",\"delivered\");";

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
            statement.executeUpdate(insertSecondAdmin);
            statement.executeUpdate(createUserInfoView);
            statement.executeUpdate(createStatusTable);
            statement.executeUpdate(insertStatus);
            statement.executeUpdate(createHistoryTable);
            //statement.executeUpdate(createCurrentStatusFunction);

            createProcedures();

            createNewUser("jconnor", "bestprofessor","John Connor", "City College of New York");
            createNewUser("person", "password", "Generic Person", "Generic Address");
            //createNewPackage("Red Dress, Nintendo Switch", "1234 Long St Ave", "jconnor", "Standard Mail","2019-03-12","2019-03-25","Delivered");
            
        }catch(Exception expt){
            expt.printStackTrace();
        }

    }

    private static void createProcedures(){
        try{
            String createNewUserProcedure = "CREATE PROCEDURE insert_user "
            + "(In username char(128), In password char(128), In name char(128), In address char(128)) "
            + "INSERT IGNORE INTO User VALUES(username,password,name,address)";

            String createUpdateUserProcedure = "CREATE PROCEDURE update_user "
            + "(In inputUsername char(128), In newName char(128), In newAddress char(128)) "
            + "UPDATE User SET name=newName, address=newAddress WHERE username=inputUsername";

            String createAddPackageProcedure = "CREATE PROCEDURE add_package_for_user "
            + "(In inputPackageID int, In inputUsername char(128)) "
            + "UPDATE Package SET user=inputUsername WHERE id=inputPackageID";

            String createInsertPackageProcedure = "CREATE PROCEDURE insert_package "
            + "(In inputItems char(128), In inputSender char(128), In inputUser char(128), In inputDeliveryAddress char(128),"
            + "In inputMailtype char(128), In inputShippingDate char(128), In inputDeliveryDate char(128), In inputCurrentStatus char(128)) "
            + "INSERT IGNORE INTO Package(items,sender,user,deliveryAddress,mailtype,shippingDate,deliveryDate,currentStatus) "
            + "VALUES(inputItems,inputSender,inputUser,inputDeliveryAddress,inputMailtype,inputShippingDate,inputDeliveryDate,inputCurrentStatus) ";

            String createUpdateCurrentStatus = "CREATE PROCEDURE update_status "
            + "(In inputPackageID int, In inputStatusID int) "
            + "UPDATE history SET status_id=inputStatusID WHERE package_id=inputPackageID";

            String dropNewUserProcedure = "DROP PROCEDURE IF EXISTS insert_user";
            String dropUpdateUserProcedure = "DROP PROCEDURE IF EXISTS update_user";
            String dropAddPackageProcedure = "DROP PROCEDURE IF EXISTS add_package_for_user";
            String dropInsertPackageProcedure = "DROP PROCEDURE IF EXISTS insert_package";
            String dropUpdateCurrentStatus = "DROP PROCEDURE IF EXISTS update_status";

            statement.executeUpdate(dropNewUserProcedure);
            statement.executeUpdate(dropUpdateUserProcedure);
            statement.executeUpdate(dropAddPackageProcedure);
            statement.executeUpdate(dropInsertPackageProcedure);
            statement.executeUpdate(dropUpdateCurrentStatus);
            statement.executeUpdate(createNewUserProcedure);
            statement.executeUpdate(createUpdateUserProcedure);
            statement.executeUpdate(createAddPackageProcedure);
            statement.executeUpdate(createInsertPackageProcedure);
            statement.executeUpdate(createUpdateCurrentStatus);
        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    //reutrns [name,address] based on username inpuit
    public static String [] getPersonalInfo(String username){
            String [] personalInfo = {"",""};
            
            try{
                String selectUserInfo = "SELECT * FROM user_info WHERE username=\"" +username+ "\";";

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
                PreparedStatement preparedStatement = connection.prepareStatement("CALL insert_user(?,?,?,?)");
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,password);
                preparedStatement.setString(3,name);
                preparedStatement.setString(4,address);
                preparedStatement.executeUpdate();

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
            PreparedStatement preparedStatement = connection.prepareStatement("CALL update_user(?,?,?)");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,newName);
            preparedStatement.setString(3,newAddress);
            preparedStatement.executeUpdate();

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    //input: name,items,senderAddress,username,mailtype,shippingDate,deliveryDate,currentStatus
    public static void createNewPackage(String items, String senderAddress, String username, String mailtype, String shippingDate, String deliveryDate, String currentStatus){
        try{
            String deliveryAddress = getPersonalInfo(username)[1];
            PreparedStatement preparedStatement = connection.prepareStatement("CALL insert_package(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,items);
            preparedStatement.setString(2,senderAddress);
            preparedStatement.setString(3,username);
            preparedStatement.setString(4,deliveryAddress);
            preparedStatement.setString(5,mailtype);
            preparedStatement.setString(6,shippingDate);
            preparedStatement.setString(7,deliveryDate);
            preparedStatement.setString(8,currentStatus);
            preparedStatement.executeUpdate();
            preparedStatement.close();

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
        String [] packageDetails = {"","","","","","","",""};

        if(packageID.equals(""))
            return packageDetails;
        try{
            String selectPackage = "SELECT * FROM Package WHERE id=" +packageID+ ";";
            ResultSet packageInfo = statement.executeQuery(selectPackage);

            if(packageInfo.next()){
                packageDetails[0] = packageInfo.getString("items");
                packageDetails[1] = packageInfo.getString("sender");
                packageDetails[2] = packageInfo.getString("user");
                packageDetails[3] = packageInfo.getString("mailType");
                packageDetails[4] = packageInfo.getDate("shippingDate").toString();
                packageDetails[5] = packageInfo.getDate("deliveryDate").toString();
                packageDetails[6] = packageInfo.getString("currentStatus");
                packageDetails[7] = packageInfo.getString("deliveryAddress");
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
            PreparedStatement preparedStatement = connection.prepareStatement("CALL add_package_for_user(?,?)");
            preparedStatement.setString(1,packageID);
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
            preparedStatement.close();

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