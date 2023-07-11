package info;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;

import managers.Utils;

public class ClientServiceInteraction {
    private boolean isAccepted, hasFinished;
    private int id, clientId, templateId;
    private Date startDate, endDate;
    private Time startTime, endTime;
    private float cost;

    public ClientServiceInteraction(){}

    public ClientServiceInteraction(String json){
        var map = Utils.mapJson(json, this.getClass());
        if (map.containsKey("isAccepted"))
            isAccepted = Boolean.parseBoolean(map.get("isAccepted"));
        if (map.containsKey("hasFinished"))
            hasFinished = Boolean.parseBoolean(map.get("hasFinished"));
        if (map.containsKey("id"))
            id = Integer.parseInt(map.get("id"));
        if (map.containsKey("clientId"))
            clientId = Integer.parseInt(map.get("clientId"));
        if (map.containsKey("templateId"))
            templateId = Integer.parseInt(map.get("templateId"));
        if (map.containsKey("cost"))
            cost = Float.parseFloat(map.get("cost"));
        if (map.containsKey("startDate"))
            startDate = Date.valueOf(map.get("startDate"));
        if (map.containsKey("endDate"))
            endDate = Date.valueOf(map.get("endDate"));
        if (map.containsKey("startTime"))
            startTime = Time.valueOf(map.get("startTime"));
        if (map.containsKey("endTime"))
            endTime = Time.valueOf(map.get("endTime"));
    }

    public String toJson(){
        HashMap<String,String> mapFields = new HashMap<>();
        mapFields.put("clientId", Integer.toString(clientId));
        mapFields.put("templateId", Integer.toString(templateId));
        mapFields.put("cost", Float.toString(cost));
        mapFields.put("startDate", Long.toString(startDate.getTime()));
        mapFields.put("endDate", Long.toString(endDate.getTime()));
        mapFields.put("startTime", startTime.toString());
        mapFields.put("endTime", endTime.toString());

        return Utils.toJson(mapFields);
    }

    public boolean isAccepted() {
        return isAccepted;
    }
    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
    public boolean isHasFinished() {
        return hasFinished;
    }
    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    public int getTemplateId() {
        return templateId;
    }
    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date start) {
        this.startDate = start;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date end) {
        this.endDate = end;
    }
    public float getCost() {
        return cost;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time starTime) {
        this.startTime = starTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    
}
