package managers;

import java.util.HashMap;

import info.UserInformation;

public class UserConnectionManager {
    private static HashMap<String,UserInformation> connections = new HashMap<String,UserInformation>();

    public static void addConnection(String address, UserInformation info){
        connections.put(address, info);
    }

    public static UserInformation getInformation(String address){
        return connections.get(address);
    }

    public static boolean hasIp(String host){
        return connections.containsKey(host);
    }
}
