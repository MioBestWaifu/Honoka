package handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import info.UserInformation;
import managers.DatabaseConnection;
import managers.Utils;

public class RegisterHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        byte[] toSend;
        String x = new String(exchange.getRequestBody().readAllBytes());
        if (DatabaseConnection.tryToRegister(new UserInformation(x))){
            //Checar esse krl
            toSend = "DONE".getBytes();
            //Esse tbm
        } else{
            toSend = "FAILED".getBytes();
        }

        exchange.getResponseHeaders().add("Content-type", "text/plain");
        Utils.sendAndClose(exchange, toSend);
    }
    
}
