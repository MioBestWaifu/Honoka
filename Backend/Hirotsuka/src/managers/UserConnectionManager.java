package managers;

import java.util.HashMap;

import info.UserConnection;
import info.UserInformation;

public abstract class UserConnectionManager {
    private static HashMap<String,UserConnection> connections = new HashMap<String,UserConnection>();

    public static void addConnection(String address, UserInformation info){
        connections.put(address, new UserConnection(info));
    }

    public static UserInformation getInformation(String address){
        return connections.get(address).getUserInformation();
    }

    public static boolean hasIp(String host){
        return connections.containsKey(host);
    }
}
