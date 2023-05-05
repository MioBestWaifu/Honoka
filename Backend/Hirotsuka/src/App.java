import java.net.InetSocketAddress;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

import com.sun.net.httpserver.HttpServer;
import handlers.ImageRequestHandler;
import handlers.ImageUpdateHandler;
import handlers.InitHandler;
import handlers.LoginHandler;
import handlers.NameUpdateHandler;
import handlers.RegisterHandler;
import handlers.ReloadHandler;
import info.ServiceInformation;
import info.UserInformation;
import managers.DatabaseConnection;
import managers.Utils;
public class App {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 99);
        DatabaseConnection.connect();
        Utils.init();
        InitHandler initHandler = new InitHandler();
        server.createContext("/", initHandler);
        server.createContext("/login", new LoginHandler());
        server.createContext("/registering", new RegisterHandler());
        server.createContext("/images", new ImageRequestHandler());
        server.createContext("/update/userimage", new ImageUpdateHandler());
        server.createContext("/update/username",new NameUpdateHandler());
        server.createContext("/reload",new ReloadHandler());
        server.start(); 
        
    }
}
