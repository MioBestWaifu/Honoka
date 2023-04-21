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
        //Scanner input = new Scanner(System.in);
        System.out.println("Host:");
        String serverName = "127.0.0.1";
        System.out.println("Database:");
        String mydatabase ="aluguel";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase+"?useSSL=false";
        System.out.println("User:");
        String username = "root";
        System.out.println("Password:");
        String password = "";
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Conexão merda");
            System.out.println(e.getMessage());
        }
        //input.close();
    }

    public static boolean checkLogin(UserInformation info){
        try {
            var st = conn.prepareStatement("SELECT * FROM user WHERE user.email = ? AND user.password = ?");
            st.setString(1, info.getEmail());
            st.setString(2, info.getPassword());
            return st.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public static String getUserInformation(UserInformation info){
        try {
            var st = conn.prepareStatement("SELECT * FROM user WHERE user.email = ?");
            st.setString(1, info.getEmail());
            var result = st.executeQuery();
            result.next();
            info.setId(0);
            info.setBirthday(result.getDate("birthday"));
            info.setGenre(result.getString("genre"));
            info.setProvidingService(result.getBoolean("providingService"));
            return info.toJson();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static boolean tryToRegister(UserInformation info){
        try {
            var st = conn.prepareStatement("SELECT * FROM user WHERE user.email = ?");
            st.setString(1, info.getEmail());
            var result = st.executeQuery();
            if (result.next())
                return false;
        st = conn.prepareStatement("INSERT INTO user (user.name,user.email,user.password,user.genre,user.birthday,user.providingService,user.area)"+
        "VALUES(?,?,?,NULL,?,NULL,NULL)");
        st.setString(1, info.getName());
        st.setString(2, info.getEmail());
        st.setString(3, info.getPassword());
        st.setDate(4, info.getBirthday());
        int rowsAffected = st.executeUpdate();
        System.out.println(rowsAffected);
        return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
