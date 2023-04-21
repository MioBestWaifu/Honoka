package managers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

public class Utils {
    public static HashMap<String,byte[]> pages = new HashMap<String,byte[]>();
    public static void init () throws IOException{
        pages.put("Index", Files.readAllBytes(Paths.get("src/raw/index.html")));
        pages.put("Main", Files.readAllBytes(Paths.get("src/raw/main.js")));
        pages.put("Runtime", Files.readAllBytes(Paths.get("src/raw/runtime.js")));;
        pages.put("Polyfills", Files.readAllBytes(Paths.get("src/raw/polyfills.js")));
        pages.put("Styles", Files.readAllBytes(Paths.get("src/raw/styles.css")));
        pages.put("Favicon", Files.readAllBytes(Paths.get("src/raw/favicon.ico")));
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
            toReturn+= String.format("\"%s\",",pair.getValue());
        }
        toReturn = toReturn.substring(0, toReturn.length()-1);
        toReturn+="}";
        return toReturn;
    }
}
