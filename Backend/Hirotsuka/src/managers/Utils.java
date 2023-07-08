package managers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.STRING;

import com.sun.net.httpserver.HttpExchange;

public abstract class Utils {
    public static HashMap<String,byte[]> pages = new HashMap<String,byte[]>();
    public static String ipAddress;
    public static void init () throws IOException{
        pages.put("Index", Files.readAllBytes(Paths.get("src/raw/dist/index.html")));
        pages.put("Main", Files.readAllBytes(Paths.get("src/raw/dist/main.js")));
        pages.put("Runtime", Files.readAllBytes(Paths.get("src/raw/dist/runtime.js")));;
        pages.put("Polyfills", Files.readAllBytes(Paths.get("src/raw/dist/polyfills.js")));
        pages.put("Styles", Files.readAllBytes(Paths.get("src/raw/dist/styles.css")));
        pages.put("Favicon", Files.readAllBytes(Paths.get("src/raw/dist/favicon.ico")));
        var x = Files.readAllLines((Paths.get("src/raw/info.txt")));
        ipAddress = x.get(0);
        DatabaseConnection.serverPassword = x.get(1);
        System.out.println(ipAddress);
        System.out.println(DatabaseConnection.serverPassword);
        //Scanner input = new Scanner(System.in);
        //ipAddress = input.nextLine();
        //input.close();
    }
    public static void sendAndClose(HttpExchange exchange,int code,byte[] toSend) throws IOException{
        exchange.sendResponseHeaders(code,toSend.length);
        exchange.getResponseBody().write(toSend);
        exchange.getResponseBody().flush();
        exchange.getResponseBody().close();
    }

    public static HashMap<String,String> mapJson(String json){
        HashMap<String,String> toReturn = new HashMap<>();
        json = json.replace("\"","");
        json = json.replace("{", "");
        json = json.replace("}", "");
        var pairs = json.split(",");
        String[] buffer;
        for (String s : pairs){
            buffer = s.split(":");
            toReturn.put(buffer[0], buffer[1]);
        }
        return toReturn;
    }

    public static String toJson(HashMap<String,String> map){
        String toReturn = "{";
        for (Map.Entry<String, String> pair: map.entrySet()) {
            toReturn += String.format("\"%s\":",pair.getKey());
            try{
            if (pair.getValue().charAt(0) == '[')
                toReturn += pair.getValue()+",";
            else if (pair.getValue().charAt(0) == '{')
                toReturn += pair.getValue()+",";
            else
                toReturn+= String.format("\"%s\",",pair.getValue());
            } catch (IllegalStateException | StringIndexOutOfBoundsException ex){
            }
        }
        toReturn = toReturn.substring(0, toReturn.length()-1);
        toReturn+="}";
        return toReturn;
    }

    public static byte[] imageToByteArray(String address, String format) throws IOException {
        if (Files.notExists(Paths.get(address))){
            address = "src/raw/images/0.png";
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(ImageIO.read(new File(address)), format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    public static boolean byteArrayToImage(String host,String address, String format,byte[]data){
        try{
        String time = Long.toString(System.currentTimeMillis());
        BufferedImage bImage2 = ImageIO.read(new ByteArrayInputStream(data));
        //BufferedImage bImage2 = ImageIO.read(bis);
        String name = address+time+"."+format;

        if (DatabaseConnection.tryToUpdateUserImageUrl(Integer.parseInt(String.valueOf(address.charAt(address.length()-1))), String.valueOf(address.charAt(address.length()-1))+time+"."+format)){
            ImageIO.write(bImage2, format, new File(name) );
            var x = UserConnectionManager.getInformation(host).getImageUrl();
            UserConnectionManager.getInformation(host).setImageUrl(String.valueOf(address.charAt(address.length()-1))+time+"."+format);
            new File("src/raw/images/"+x.split("/")[4]).delete();
            return true;
        }

        return false;
        } catch (IOException ex){
            System.out.println("Exceção update imagem");
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static String joinJsonArray(ArrayList<String> toJoin){
        String toReturn = "[";
        for (String s : toJoin){
            toReturn+=s;
            toReturn+=",";
        }
        toReturn = toReturn.substring(0, toReturn.length()-1);
        toReturn+="]";
        return toReturn;
    }

    public static int sumOfArray(ArrayList<Integer> array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }

    public static Map<String, String> queryToMap(String query) {
        if(query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }
}
