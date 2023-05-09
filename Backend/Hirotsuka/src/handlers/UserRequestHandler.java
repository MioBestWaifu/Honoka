package handlers;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import info.UserInformation;
import managers.DatabaseConnection;
import managers.UserConnectionManager;
import managers.Utils;
public class UserRequestHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Content-type", "application/json");
        Utils.sendAndClose(exchange, DatabaseConnection.getRequestedUserInformation(Integer.parseInt(Utils.queryToMap(exchange.getRequestURI().getQuery()).get("id"))).toJson().getBytes(StandardCharsets.UTF_8));
    }
    
}
