package handlers;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import managers.UserConnectionManager;
import managers.Utils;

public class ReloadHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().toString();
        switch(path){
            case"/reload/user":
                exchange.getResponseHeaders().add("Content-type","application/json");
                var toSend = UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostString()).toJson().getBytes(StandardCharsets.UTF_8);
                Utils.sendAndClose(exchange, toSend);
                break;
            case"/reload/service":
                break;
        }
    }
    
    
}
