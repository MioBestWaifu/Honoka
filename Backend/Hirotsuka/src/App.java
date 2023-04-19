import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

import handlers.InitHandler;
import handlers.LoginHandler;
import handlers.RegisterHandler;
import managers.Utils;
public class App {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 99);
        //Connector.connect();
        Utils.init();
        InitHandler initHandler = new InitHandler();
        server.createContext("/", initHandler);
        server.createContext("/runtime.js", initHandler);
        server.createContext("/main.js", initHandler);
        server.createContext("/polyfills.js", initHandler);
        server.createContext("/styles.css", initHandler);
        server.createContext("/favicon.ico", initHandler);
        server.createContext("/login", new LoginHandler());
        server.createContext("/registering", new RegisterHandler());
        server.start();
    }
}
