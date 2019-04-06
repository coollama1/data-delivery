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

            createNewUser("jconnor", "bestprofessor","John Connor", "City College of New York");
            //createNewPackage("Package 1","Red Dress, Nintendo Switch", "1234 Long St Ave", "jconnor", "Standard Mail","2019-03-12","2019-03-25","Delivered");
            
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

    //input: name,items,senderAddress,username,mailtype,shippingDate,deliveryDate,currentStatus
    public static void createNewPackage(String name, String items, String senderAddress, String username, String mailtype, String shippingDate, String deliveryDate, String currentStatus){
        try{
            String createPackageQuery = "INSERT IGNORE INTO Package(name,items,sender,user,mailtype,shippingDate,deliveryDate,currentStatus) VALUES(\"" +name+ "\",\"" +items+ "\",\"" +senderAddress+ "\",\"" +username+ "\",\"" +mailtype+ "\",\"" +shippingDate+ "\",\"" +deliveryDate+ "\",\"" +currentStatus+ "\");";   
            statement.executeUpdate(createPackageQuery);

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    //packageID is a string, but must be convertible to an int
    public static boolean isValidPackageID(String packageID){
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

    //true if item is added, false if user already has item on their account
    public static void addPackageForUser(String username, String packageID){
        try{
            String addPackage = "INSERT IGNORE INTO Package(user) VALUES(\"" +username+ "\") WHERE id=" +packageID+ ";";
            
            statement.executeUpdate(addPackage);

        }catch(Exception expt){
            expt.printStackTrace();
        }
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