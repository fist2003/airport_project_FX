package dao;

/**
 * Created by ПК on 22.12.2016.
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by ПК on 08.12.2016.
 */
public class ConnectToMySQLDAO {
    public ConnectToMySQLDAO(){}

    private static Connection connection = null;

    public static Connection getConnection() {return connection;}

    private String[] takeProperties(){
        String host = null;
        String login = null;
        String emptyHost = null;
        Properties properties = new Properties();
        try(InputStream fis = ClassLoader.getSystemResourceAsStream("config.properties")){
            properties.load(fis);
            host = properties.getProperty("dbhost");
            login = properties.getProperty("dblogin");
            emptyHost = properties.getProperty("dbempty");
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Eror in properties file");
        }
        String[] propertiesArr = {host,login,emptyHost};
        return propertiesArr;
    }

    public boolean createSQLDB(String password) {
        String[] arr = takeProperties();
        try {
            if((arr[1]!= null)&&(arr[2]!= null)) {
                connection = DriverManager.getConnection(arr[2], arr[1], password);
                System.out.println("Connection ok");
            }
            else{
                System.out.println("Check properties file");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return false;
        }
        try {
            Statement st = connection.createStatement();
            String str = "CREATE DATABASE shevchenko_final_airport";
            st.execute(str);
        }
        catch (SQLException e){
            System.out.println("DataBase is already exist");;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean connectToDB(String password){
        String[] arr = takeProperties();
        try {
            if((arr[0]!= null)&&(arr[1]!= null)) {
                connection = DriverManager.getConnection(arr[0], arr[1], password);
            }
            else{
                System.out.println("Check properties file");
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
            return true;
        } else {
            System.out.println("Failed to make connection!");
            return false;
        }
    }

    public void addTablesToDataBase() {
        try {
            Statement st = connection.createStatement();
            st.execute("CREATE TABLE if not exists `Users` (`id` INT NOT NULL AUTO_INCREMENT,`login` VARCHAR(50) NOT NULL," +
                    "`password` NVARCHAR(50)NOT NULL,`email` NVARCHAR(50)NOT NULL,`lastName` VARCHAR(45)NULL," +
                    "`firstName` VARCHAR(45)NULL,`sex`  VARCHAR(50)NULL,`isAdmin` TINYINT(1)NOT NULL, PRIMARY KEY(`id`)," +
                    " UNIQUE INDEX `login_UNIQUE`(`login`ASC),UNIQUE INDEX `email_UNIQUE`(`email`ASC));");
        } catch (SQLException e) {
            System.out.println("table users is already exist");
            ;
        }
        try{
            Statement st = connection.createStatement();
            st.execute("CREATE TABLE if not exists `Airlines` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(50) NOT NULL," +
                    "`adress` VARCHAR(100) NOT NULL,`telephone` VARCHAR(50) NOT NULL,`website` VARCHAR(50) NOT NULL," +
                    " PRIMARY KEY (`id`),UNIQUE INDEX `name_UNIQUE` (`name` ASC));");
        }
        catch (SQLException e){
            System.out.println("table airlines is already exist");;
        }
        try{
            Statement st = connection.createStatement();
            st.execute("CREATE TABLE if not exists `Airplanes` (`id` INT NOT NULL AUTO_INCREMENT,`manufacturer` VARCHAR(50) " +
                    "NOT NULL,`model` VARCHAR(50) NOT NULL,`numberISO` VARCHAR(50) NOT NULL,`year` INT NOT NULL," +
                    "`economPlaces`  INT NOT NULL,`businessPlaces` INT NOT NULL,`airline_id`  INT NOT NULL, PRIMARY KEY (`id`)," +
                    "UNIQUE INDEX `numberISO_UNIQUE` (`numberISO` ASC));");
        }
        catch (SQLException e){
            System.out.println("table airplanes is already exist");;
        }
        try{
            Statement st = connection.createStatement();
            st.execute("CREATE TABLE if not exists `Flights` (`id` INT NOT NULL AUTO_INCREMENT,`number` VARCHAR(50) NOT NULL," +
                    "`departPort` VARCHAR(50) NOT NULL,`destinationPort` VARCHAR(50) NOT NULL,`dateDepart` DATE NOT NULL," +
                    "`dateArrive` DATE NOT NULL,`timeDepart` TIME (6) NOT NULL,`timeArrive`  TIME (6) NOT NULL," +
                    "`priceEconom` INT NOT NULL,`priceBusiness` INT NOT NULL,`airplane_id` INT NOT NULL,`status` VARCHAR(50)," +
                    "`gate` VARCHAR(50),`currentTime`  TIME (6), PRIMARY KEY (`id`),UNIQUE INDEX `number_UNIQUE` (`number` ASC));");
        }
        catch (SQLException e){
            System.out.println("table flights is already exist");;
        }
        try{
            Statement st = connection.createStatement();
            st.execute("CREATE TABLE if not exists `Passengers` (`id` INT NOT NULL AUTO_INCREMENT,`lastName` VARCHAR(50) NOT NULL," +
                    "`firstName` VARCHAR(50) NOT NULL,`passport` VARCHAR(50) NOT NULL,`sex` VARCHAR(50) NOT NULL," +
                    "`birthday` DATE NOT NULL,`country` VARCHAR(50) NOT NULL,`classTicket` VARCHAR(50) NOT NULL, `flight_id`" +
                    "  INT NOT NULL, PRIMARY KEY (`id`));");
        }
        catch (SQLException e){
            System.out.println("table passengers is already exist");;
        }
    }
}
