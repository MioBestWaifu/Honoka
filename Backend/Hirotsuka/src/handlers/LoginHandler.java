package handlers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import info.UserInformation;
import managers.ConnectionManager;
import managers.Utils;

public class LoginHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String x = new String(exchange.getRequestBody().readAllBytes(),StandardCharsets.UTF_8);
        byte[] toSend;
        if (ConnectionManager.checkLogin(new UserInformation(x))){
            toSend = ConnectionManager.getUserInformation(new UserInformation(x)).getBytes();
            exchange.getResponseHeaders().add("Content-type", "application/json");
            Utils.sendAndClose(exchange, toSend);
        }
        System.out.println(x);
    }
    
}
