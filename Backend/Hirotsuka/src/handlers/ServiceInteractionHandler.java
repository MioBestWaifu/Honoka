package handlers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import info.ClientServiceInteraction;
import info.ServiceInformation;
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
                    csInfo.setClientId(UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostString()).getUserId());
                    if (validateRequest(csInfo, UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostString()))){
                        DatabaseConnection.addNewServiceRequest(csInfo);
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange,200,"".getBytes());
                    } else {
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange,403,"".getBytes());
                    }
                    break;
                case "create":
                    var x = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                    ServiceInformation servInfo = new ServiceInformation(x);
                    servInfo.setProviderId(UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostString()).getUserId());
                    if(DatabaseConnection.tryToAddServiceTemplate(servInfo)){
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange,201,"".getBytes());
                    } else {
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange,500,"".getBytes());
                    }
                    break;
                case "review":
                    break;
                case "reload":
                    break;
                case "imageUpdate":
                    int id = Integer.parseInt(params.get("id"));
                    if(id==-1){
                        id = DatabaseConnection.getLastCreatedService(UserConnectionManager.getInformation(exchange.getRemoteAddress().getHostString()).getUserId());
                    }

                    //A função acima retorna -1 se houver erro
                    if (id!=-1 && Utils.updateServicePicture(exchange.getRemoteAddress().getHostString(), "src/raw/images/services/"+id, "png",exchange.getRequestBody().readAllBytes())){
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange, 201, "".getBytes());
                    } else {
                        exchange.getResponseHeaders().add("Content-type", "text/plain");
                        Utils.sendAndClose(exchange, 500, "".getBytes());
                    }
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
        if (info.getCost() <= DatabaseConnection.getCredits(client.getUserId())){
            //CHECAR SE O CLIENTE JA TEM UM REQUEST OU INSTANCE PARA AQUELA HORA
            return true;
        } else{
            return false;
        }
    
}
}
