package handlers;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import managers.DatabaseConnection;
import managers.UserConnectionManager;
import managers.Utils;
public class NameUpdateHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (UserConnectionManager.hasIp(exchange.getRemoteAddress().getHostName())){
            String newName =  new String (exchange.getRequestBody().readAllBytes());
            if (DatabaseConnection.tryToUpdateUserName(UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostName()).getId(),newName)){
                UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostName()).setName(newName);
                exchange.getResponseHeaders().add("Content-type", "text/plain");
                Utils.sendAndClose(exchange, "OK".getBytes(StandardCharsets.UTF_8));
            } else {
                exchange.getResponseHeaders().add("Content-type", "text/plain");
                Utils.sendAndClose(exchange, "FAIL".getBytes(StandardCharsets.UTF_8));
            }
        } else {
            exchange.getResponseHeaders().add("Content-type", "text/plain");
            Utils.sendAndClose(exchange, "FAIL".getBytes(StandardCharsets.UTF_8));
        }
    }
    
}
