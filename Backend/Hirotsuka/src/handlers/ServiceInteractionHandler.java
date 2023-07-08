package handlers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import info.ClientServiceInteraction;
import info.UserInformation;
import managers.DatabaseConnection;
import managers.UserConnectionManager;
import managers.Utils;

public class ServiceInteractionHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (UserConnectionManager.hasIp(exchange.getRemoteAddress().getHostString())){
            var params = Utils.queryToMap(exchange.getRequestURI().getQuery());

            //É refresh
            if (params == null){
                new InitHandler().sendInitialContent("/", exchange);
                return;
            }

            switch (params.get("type")){
                case "request":
                    exchange.getResponseHeaders().add("Content-type", "application/json");
                    Utils.sendAndClose(exchange,200, DatabaseConnection.getFullServiceInformation(Integer.parseInt(params.get("id"))).toJson().getBytes(StandardCharsets.UTF_8));
                    break;
                case "schedule":
                    ClientServiceInteraction csInfo = new ClientServiceInteraction(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));
                    if (validateRequest(csInfo, UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostString()))){
                        DatabaseConnection.addNewServiceRequest(csInfo);
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange,200,"".getBytes());
                    } else {
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange,403,"".getBytes());
                    }
                    break;
                case "review":
                    break;
                case "reload":
                    break;
                default:
                    break;
            }
        } else {
            UserConnectionManager.addConnection(exchange.getRemoteAddress().getHostString());
            UserConnectionManager.getConnection(exchange.getRemoteAddress().getHostString()).lastPage = "/pages/login";
            new InitHandler().sendInitialContent("/", exchange);
            return;
        }
    }

    public boolean validateRequest(ClientServiceInteraction info, UserInformation client){
        if (info.getCost() <= DatabaseConnection.getCredits(client.getId())){
            //CHECAR SE O CLIENTE JA TEM UM REQUEST OU INSTANCE PARA AQUELA HORA
            return true;
        } else{
            return false;
        }
    
}
}
