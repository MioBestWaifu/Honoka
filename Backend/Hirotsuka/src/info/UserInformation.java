package info;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.cj.xdevapi.JsonString;

import managers.Utils;

public class UserInformation {
    String email, password, genre,name,imageUrl;
    int id;
    boolean providingService;
    Date birthday;
    ArrayList<ServiceBundle> reccomendations;
    public UserInformation(){

    }
    public UserInformation(String json){
    var map = Utils.mapJson(json);
    if (map.containsKey("email"))
        email = map.get("email");
    if (map.containsKey("name"))
        name = map.get("name");
    else if (map.containsKey("username"))
        name = map.get("username");
    if (map.containsKey("password"))
        password = map.get("password");
    if (map.containsKey("id"))
        id = Integer.parseInt(map.get("id"));
    if (map.containsKey("genre"))
        genre = map.get("genre");
    if (map.containsKey("providingService"))
        providingService = Boolean.parseBoolean(map.get("email"));
    if (map.containsKey("birthday"))
        birthday = Date.valueOf(map.get("birthday"));
    }

    public String toJson(){
        HashMap<String,String> mapFields = new HashMap<>();
        if (email != null)
            mapFields.put("Email", email);
        if (imageUrl != null)
            mapFields.put("ImageUrl", imageUrl);
        if (name != null)
            mapFields.put("Name", name);
        if (password != null) 
            mapFields.put("Password", password);
        if (genre != null)
            mapFields.put("Genre", genre);
        mapFields.put("ProvidingService", Boolean.toString(providingService));
        if (birthday != null)
            mapFields.put("Birthday", birthday.toString());
        if (!(reccomendations == null || reccomendations.size() == 0)){
            ArrayList<String> toJoin = new ArrayList<String>();
            for (ServiceBundle si : reccomendations){
                toJoin.add(si.toJson());
            }
            mapFields.put("ServiceRecs", Utils.joinJsonArray(toJoin));
        }
        String toReturn = Utils.toJson(mapFields);
        
        return toReturn;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isProvidingService() {
        return providingService;
    }

    public void setProvidingService(boolean providingService) {
        this.providingService = providingService;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<ServiceBundle> getReccomendations() {
        return reccomendations;
    }
    public void setReccomendations(ArrayList<ServiceBundle> reccomendations) {
        this.reccomendations = reccomendations;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String userCode) {
        this.imageUrl = "http://"+Utils.ipAddress+"/images/"+userCode;
    }

    
    
    
    
}
