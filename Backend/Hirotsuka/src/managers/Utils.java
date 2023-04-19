package managers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

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
}
