package handlers;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import managers.UserConnectionManager;
import managers.Utils;
public class ImageUpdateHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (UserConnectionManager.hasIp(exchange.getRemoteAddress().getHostString())){
            if (Utils.byteArrayToImage(exchange.getRemoteAddress().getHostString(),"src/raw/images/"+UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostString()).getId(), "png", exchange.getRequestBody().readAllBytes())){
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
