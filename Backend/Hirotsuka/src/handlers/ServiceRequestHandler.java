package handlers;
import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import info.UserInformation;
import info.ServiceInformation;
import managers.DatabaseConnection;
import managers.UserConnectionManager;
import managers.Utils;

public class ServiceRequestHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Content-type", "application/json");
        Utils.sendAndClose(exchange, DatabaseConnection.getFullServiceInformation(Integer.parseInt(Utils.queryToMap(exchange.getRequestURI().getQuery()).get("id"))).toJson().getBytes());
    }
    
}
