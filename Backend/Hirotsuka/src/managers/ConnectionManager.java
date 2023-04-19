package managers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import info.UserInformation;

public class ConnectionManager {
    private static Connection conn;
    public static void connect(){
        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Host:");
        String serverName = input.nextLine();
        System.out.println("Database:");
        String mydatabase =input.nextLine();
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        System.out.println("User:");
        String username = input.nextLine();
        System.out.println("Password:");
        String password = input.nextLine();
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Conexão merda");
            System.exit(0);
        }
        input.close();
    }

    public static boolean checkLogin(UserInformation info){
        return false;
    }

    public static String getUserInformation(UserInformation info){
        return null;
    }

    public static boolean tryToRegister(UserInformation info){
        return false;
    }
}
