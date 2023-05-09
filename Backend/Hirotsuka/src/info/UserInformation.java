package info;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.cj.xdevapi.JsonString;

import managers.Utils;

public class UserInformation {
    String email, password, gender,name,imageUrl;
    int id, area;
    boolean providingService;
    Date birthday;
    ArrayList<ServiceBundle> reccomendations;
    ArrayList<ServiceInformation> services;
    ArrayList<ReviewInfomation> reviews;
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
    if (map.containsKey("gender"))
        gender = map.get("gender");
    if (map.containsKey("area"))
        area = Integer.parseInt(map.get("area"));
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
        if (gender != null)
            mapFields.put("Gender", gender);
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
        if (!(services == null || services.size() == 0)){
            ArrayList<String> toJoin = new ArrayList<String>();
            for (ServiceInformation si : services){
                toJoin.add(si.toJson());
            }
            mapFields.put("Services", Utils.joinJsonArray(toJoin));
        }
        if (!(reviews == null || reviews.size() == 0)){
            ArrayList<String> toJoin = new ArrayList<String>();
            for (ReviewInfomation si : reviews){
                toJoin.add(si.toJson());
            }
            mapFields.put("Reviews", Utils.joinJsonArray(toJoin));
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

    public String getGender() {
        return gender;
    }

    public void setGender(String genre) {
        this.gender = genre;
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
    public int getArea() {
        return area;
    }
    public void setArea(int area) {
        this.area = area;
    }
    public ArrayList<ServiceInformation> getServices() {
        return services;
    }
    public void setServices(ArrayList<ServiceInformation> services) {
        this.services = services;
    }
    public ArrayList<ReviewInfomation> getReviews() {
        return reviews;
    }
    public void setReviews(ArrayList<ReviewInfomation> reviews) {
        this.reviews = reviews;
    }
    
    

    
    
    
    
}
