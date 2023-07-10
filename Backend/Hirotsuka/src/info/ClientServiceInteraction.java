package info;

import java.sql.Date;

import managers.Utils;

public class ClientServiceInteraction {
    private boolean isAccepted, hasFinished;
    private int id, clientId, templateId;
    private Date start, end;
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
        if (map.containsKey("start"))
            start = Date.valueOf(map.get("start"));
        if (map.containsKey("end"))
            end = Date.valueOf(map.get("end"));
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
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }
    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
    }
    public float getCost() {
        return cost;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }

    
}
