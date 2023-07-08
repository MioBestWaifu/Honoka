package handlers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import managers.DatabaseConnection;
import managers.UserConnectionManager;
import managers.Utils;

public class PersonalInteractionHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (UserConnectionManager.hasIp(exchange.getRemoteAddress().getHostString())) {
            var params = Utils.queryToMap(exchange.getRequestURI().getQuery());
            switch (params.get("type")) {
                case "reload":
                    exchange.getResponseHeaders().add("Content-type", "application/json");
                    var toSend = UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostString()).toJson().getBytes(StandardCharsets.UTF_8);
                    Utils.sendAndClose(exchange,200,toSend);
                    break;
                case "imageUpdate":
                    if (Utils.byteArrayToImage(exchange.getRemoteAddress().getHostString(),"src/raw/images/" + UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostString()).getId(),"png", exchange.getRequestBody().readAllBytes())) {
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange,201, "".getBytes(StandardCharsets.UTF_8));
                    } else {
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange,500, "".getBytes(StandardCharsets.UTF_8));
                    }
                    break;
                case "nameUpdate":
                    String newName = new String(exchange.getRequestBody().readAllBytes());
                    if (DatabaseConnection.tryToUpdateUserName(UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostString()).getId(),newName)) {
                        UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostString()).setName(newName);
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange,201,"".getBytes(StandardCharsets.UTF_8));
                    } else {
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange,500,"FAIL".getBytes(StandardCharsets.UTF_8));
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
