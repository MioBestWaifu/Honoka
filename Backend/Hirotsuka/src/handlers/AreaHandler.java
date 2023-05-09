package handlers;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import managers.DatabaseConnection;
import managers.Utils;

public class AreaHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        byte[] toSend = DatabaseConnection.GetAreas().getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-type", "application/json");
        Utils.sendAndClose(exchange, toSend);
    }
    
}
