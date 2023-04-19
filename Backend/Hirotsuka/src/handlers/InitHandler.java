package handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import managers.Utils;

public class InitHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getHttpContext().getPath();
        byte[] toSend;
        switch (path){
            case "/":
                exchange.getResponseHeaders().add("Content-type", "text/html");
                Utils.sendAndClose(exchange, Utils.pages.get("Index"));
                break;
            case "/main.js":
                exchange.getResponseHeaders().add("Content-type", "text/javascript");
                Utils.sendAndClose(exchange, Utils.pages.get("Main"));
                break;
            case "/runtime.js":
                exchange.getResponseHeaders().add("Content-type", "text/javascript");
                Utils.sendAndClose(exchange, Utils.pages.get("Runtime"));
                break;
            case "/polyfills.js":
                exchange.getResponseHeaders().add("Content-type", "text/javascript");
                Utils.sendAndClose(exchange, Utils.pages.get("Polyfills"));
                break;
            case "/styles.css":
                exchange.getResponseHeaders().add("Content-type", "text/css");
                Utils.sendAndClose(exchange, Utils.pages.get("Styles"));
                break;
            case "/favicon.ico":
                exchange.getResponseHeaders().add("Content-type", "image/ico");
                Utils.sendAndClose(exchange, Utils.pages.get("Favicon"));
                break;
        }
    }
    
}