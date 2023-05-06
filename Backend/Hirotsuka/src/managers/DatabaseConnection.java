package managers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import info.ServiceBundle;
import info.ServiceInformation;
import info.UserInformation;

public class DatabaseConnection {
    private static Connection conn;
    private static ArrayList<Integer> serviceIds = new ArrayList<>();
    public static String serverPassword;
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
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase+"?useSSL=false&allowPublicKeyRetrieval=true";
        System.out.println("User:");
        String username = "root";
        try {
            conn = DriverManager.getConnection(url, username, serverPassword);
        } catch (SQLException ex) {
            System.out.println("Conexão merda");
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        try{
        var st = conn.prepareStatement("SELECT idServiceTemplates FROM servicetemplates");
        var res = st.executeQuery();
        while(res.next()){
            serviceIds.add(res.getInt(1));
        }
        } catch (SQLException ex){
            System.out.println("Init falhou");
            System.out.println(ex.getMessage());
            System.exit(0);
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

    public static UserInformation getUserInformation(UserInformation info){
        try {
            var st = conn.prepareStatement("SELECT * FROM user WHERE user.email = ?");
            st.setString(1, info.getEmail());
            var result = st.executeQuery();
            result.next();
            info.setId(result.getInt("idUser"));
            info.setName(result.getString("name"));
            info.setBirthday(result.getDate("birthday"));
            info.setGenre(result.getString("genre"));
            info.setEmail(result.getString("email"));
            info.setProvidingService(result.getBoolean("providingService"));
            info.setImageUrl(result.getString("profileUrl"));
            info.setReccomendations(getServiceRecommendations(info.getId()));
            return info;
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
        st = conn.prepareStatement("INSERT INTO user (name,email,password,birthday,profileUrl,genre)"+
        "VALUES(?,?,?,?,?,?)");
        st.setString(1, info.getName());
        st.setString(2, info.getEmail());
        st.setString(3, info.getPassword());
        st.setDate(4, info.getBirthday());
        st.setString(5, info.getId()+".png");
        st.setString(6, info.getGenre());
        int rowsAffected = st.executeUpdate();
        System.out.println(rowsAffected);
        return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean tryToAddServiceTemplate(ServiceInformation info){
        try {
            var st = conn.prepareStatement("INSERT INTO serviceTemplates (idProvider,serviceName,description,costPerHour)"+
            "VALUES (?,?,?,?)");
            st.setInt(1, info.getProviderId());
            st.setString(2, info.getServiceName());
            st.setString(3, info.getDescription());
            st.setFloat(4, info.getCostPerHour());
            return st.executeUpdate() >0;
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    private static ArrayList<ServiceBundle> getServiceRecommendations(int userCode){
        ArrayList<ServiceBundle> toReturn = new ArrayList<>();
        ArrayList<ServiceInformation> buffer = new ArrayList<>();
        ArrayList<Integer> available = new ArrayList<>(serviceIds.size());
        for(int i=0;i<serviceIds.size();i++){
            available.add(i);
        }
        Collections.copy(available, serviceIds);
        PreparedStatement st = null;
        PreparedStatement providerSt = null;
        try {
            st = conn.prepareStatement("SELECT * FROM serviceTemplates WHERE idServiceTemplates = ?");
            providerSt = conn.prepareStatement("SELECT * FROM user WHERE idUser = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 1; i<=12; i++){
            try {
                var x = new Random().nextInt(available.size());
                st.setInt(1, available.get(x));
                var res = st.executeQuery();
                res.next();
                ServiceInformation toAdd = new ServiceInformation();
                toAdd.setTemplateId(res.getInt("idServiceTemplates"));
                toAdd.setCostPerHour(res.getFloat("costPerHour"));
                toAdd.setDescription(res.getString("description"));
                toAdd.setServiceName(res.getString("serviceName"));
                toAdd.setProviderId(res.getInt("idProvider"));
                providerSt.setInt(1, res.getInt("idProvider"));
                var providerRes = providerSt.executeQuery();
                providerRes.next();
                toAdd.setProviderName(providerRes.getString("name"));
                toAdd.setProviderImageUrl(providerRes.getString("profileUrl"));
                toAdd.setProviderUrl(Integer.toString(providerRes.getInt("idUser")));

                var areaSt = conn.prepareStatement("SELECT name FROM area WHERE area.idArea = ?");
                areaSt.setInt(1,providerRes.getInt("area"));
                var areaRes = areaSt.executeQuery();
                areaRes.next();
                toAdd.setProviderArea(areaRes.getString(1));

                var scoreSt = conn.prepareStatement("SELECT score FROM servicereviews WHERE idtemplate = ?");
                scoreSt.setInt(1, toAdd.getTemplateId());
                var scoreRes = scoreSt.executeQuery();
                ArrayList<Integer> scores = new ArrayList<>();
                while(scoreRes.next()){
                    scores.add(scoreRes.getInt(1));
                }
                try{
                    var r = Utils.sumOfArray(scores);
                    var s =(float) r/scores.size();
                    toAdd.setScoreAverage(s);
                } catch (Exception ex){
                    toAdd.setScoreAverage(0);
                    ex.printStackTrace();
                }

                buffer.add(toAdd);
                available.remove(x);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        toReturn.add(new ServiceBundle());
        toReturn.add(new ServiceBundle());
        toReturn.add(new ServiceBundle());
        toReturn.get(0).setServInfos(new ArrayList<ServiceInformation>(buffer.subList(0, 4)));
        toReturn.get(1).setServInfos(new ArrayList<ServiceInformation>(buffer.subList(4, 8)));
        toReturn.get(2).setServInfos(new ArrayList<ServiceInformation>(buffer.subList(8, 12)));
        return toReturn;
    }

    public static boolean tryToUpdateUserName(int id, String newName){
        try{
            var st = conn.prepareStatement("UPDATE user SET name = ? WHERE idUser = ?");
            st.setString(1, newName);
            st.setInt(2, id);
            var rst = st.executeUpdate();
            return rst == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean tryToUpdateUserImageUrl(int id, String newUrl){
        try{
            var st = conn.prepareStatement("UPDATE user SET profileUrl = ? WHERE idUser = ?");
            st.setString(1, newUrl);
            st.setInt(2, id);
            var rst = st.executeUpdate();
            return rst == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean tryToUpdateUserArea(int userId, int areaId){
        try{
            var st = conn.prepareStatement("UPDATE user SET area = ? WHERE idUser = ?");
            st.setInt(1, areaId);
            st.setInt(2, userId);
            var rst = st.executeUpdate();
            return rst == 1;

        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean tryToUpdateUserArea(int userId, String areaName){
        try{
            var st = conn.prepareStatement("UPDATE user SET area = (SELECT idArea FROM area WHERE area.name = ?) WHERE idUser = ?");
            st.setString(1, areaName);
            st.setInt(2, userId);
            var rst = st.executeUpdate();
            return rst == 1;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean tryToAddReviewToService(int reviewer, int target, int score, String comment){
        try{
            var st = conn.prepareStatement("INSERT INTO servicereviews VALUES (?,?,?,?)");
            st.setInt(1, reviewer);
            st.setInt(2, target);
            st.setInt(3, score);
            st.setString(4, comment);
            var rst = st.executeUpdate();
            return rst == 1;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean tryToAddReviewToUser(int reviewer, int target, int score, String comment){
        try{
            var st = conn.prepareStatement("INSERT INTO userreviews VALUES (?,?,?,?)");
            st.setInt(1, reviewer);
            st.setInt(2, target);
            st.setInt(3, score);
            st.setString(4, comment);
            var rst = st.executeUpdate();
            return rst == 1;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }


}


