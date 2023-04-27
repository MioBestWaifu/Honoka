package managers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.sun.net.httpserver.HttpExchange;

public class Utils {
    public static HashMap<String,byte[]> pages = new HashMap<String,byte[]>();
    public static String ipAddress = "127.0.0.1";
    public static void init () throws IOException{
        pages.put("Index", Files.readAllBytes(Paths.get("src/raw/dist/index.html")));
        pages.put("Main", Files.readAllBytes(Paths.get("src/raw/dist/main.js")));
        pages.put("Runtime", Files.readAllBytes(Paths.get("src/raw/dist/runtime.js")));;
        pages.put("Polyfills", Files.readAllBytes(Paths.get("src/raw/dist/polyfills.js")));
        pages.put("Styles", Files.readAllBytes(Paths.get("src/raw/dist/styles.css")));
        pages.put("Favicon", Files.readAllBytes(Paths.get("src/raw/dist/favicon.ico")));
        System.out.println("IP ADDRESS:");
        //Scanner input = new Scanner(System.in);
        //ipAddress = input.nextLine();
        //input.close();
    }
    public static void sendAndClose(HttpExchange exchange,byte[] toSend) throws IOException{
        exchange.sendResponseHeaders(200, toSend.length);
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
            else
                toReturn+= String.format("\"%s\",",pair.getValue());
            } catch (IllegalStateException | StringIndexOutOfBoundsException ex){
            }
        }
        toReturn = toReturn.substring(0, toReturn.length()-1);
        toReturn+="}";
        return toReturn;
    }

    public static byte[] imageToByteArray(String address, String format)
        throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(ImageIO.read(new File(address)), format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

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
}
