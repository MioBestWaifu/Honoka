package info;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;

import managers.Utils;

public class ServiceInformation {
    private String serviceName,description, providerName, providerUrl, providerImageUrl, providerArea, templateImageUrl;
    private float costPerHour;
    private float scoreAverage;
    private int providerId, templateId, modality, category;
    private boolean[] availableDays;
    private Time[] from, to;
    private ArrayList<ReviewInfomation> reviews;
    
    public ServiceInformation(){}
    
    public ServiceInformation(String json){
        var map = Utils.mapJson(json);
        
        if(map.containsKey("providerId")){
            providerId = Integer.parseInt(map.get("providerId"));
        }

        if(map.containsKey("modality")){
            modality = Integer.parseInt(map.get("modality"));
        }

        if(map.containsKey("category")){
            category = Integer.parseInt(map.get("category"));
        }

        if(map.containsKey("serviceName")){
            serviceName = map.get("serviceName");
        }

        if(map.containsKey("description")){
            description = map.get("description");
        }

        if(map.containsKey("costPerHour")){
            costPerHour = Float.parseFloat(map.get("costPerHour"));
        }

        if(map.containsKey("availableDays")){
            availableDays = new boolean[7];
            String[] s = Utils.breakJsonArray(map.get("availableDays"));
            for (int a = 0; a<=6;a++){
                availableDays[a] = Boolean.parseBoolean(s[a]);
            }
        }

        if(map.containsKey("availableFroms")){
            from = new Time[7];
            String[] s = Utils.breakJsonArray(map.get("availableFroms"));
            for (int a = 0; a<=6;a++){
                if (!s[a].equals("null"))
                    s[a] = s[a]+":00";
                else 
                    s[a] = "00:00:00";
                from[a] = Time.valueOf((s[a]));
            }
        }

        if(map.containsKey("availableTos")){
            to = new Time[7];
            String[] s = Utils.breakJsonArray(map.get("availableTos"));
            for (int a = 0; a<=6;a++){
                if (!s[a].equals("null"))
                    s[a] = s[a]+":00";
                else 
                    s[a] = "00:00:00";
                to[a] = Time.valueOf((s[a]));
            }
        }
    }

    public String toJson(){
        HashMap<String,String> mapFields = new HashMap<>();
        mapFields.put("ServiceName", serviceName);
        mapFields.put("ServiceId", Integer.toString(templateId));
        mapFields.put("ProviderId", Integer.toString(providerId));
        if (serviceName.length()>25)
            mapFields.put("ShortServiceName", serviceName.substring(0, 23)+"...");
        else
            mapFields.put("ShortServiceName", serviceName);
        if (description.isBlank()){
                description = "DESC";
        }
        mapFields.put("AverageScore", Float.toString(scoreAverage));
        if (! (providerArea == null || providerArea.isBlank()))
            mapFields.put("ProviderArea", providerArea);
        mapFields.put("Description", description);
        if (! (providerName == null || providerName.isBlank()))
            mapFields.put("ProviderName", providerName);
        if (! (providerUrl == null || providerUrl.isBlank()))
            mapFields.put("ProviderUrl", providerUrl);
        if (! (templateImageUrl == null || templateImageUrl.isBlank()))
            mapFields.put("TemplateImageUrl", templateImageUrl);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(symbols);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        if (! (providerImageUrl == null || providerImageUrl.isBlank()))
            mapFields.put("ProviderImageUrl", providerImageUrl);
        if (!(reviews == null || reviews.size() == 0)){
            ArrayList<String> toJoin = new ArrayList<String>();
            for (ReviewInfomation si : reviews){
                toJoin.add(si.toJson());
            }
            mapFields.put("Reviews", Utils.joinJsonArray(toJoin));
        }
        mapFields.put("CostPerHour", "R$"+format.format(costPerHour)+"/hr");
        return Utils.toJson(mapFields);
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getProviderName() {
        return providerName;
    }
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
    public String getProviderUrl() {
        return providerUrl;
    }
    public void setProviderUrl(String providerCode) {
        this.providerUrl = "http://"+Utils.ipAddress+"/users/"+providerCode;
    }
    public String getProviderImageUrl() {
        return providerImageUrl;
    }
    public void setProviderImageUrl(String providerCode) {
        this.providerImageUrl = "http://"+Utils.ipAddress+"/images/"+providerCode;
    }
    public float getCostPerHour() {
        return costPerHour;
    }
    public void setCostPerHour(float costPerHour) {
        this.costPerHour = costPerHour;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public int getProviderId() {
        return providerId;
    }
    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }
    public String getProviderArea() {
        return providerArea;
    }
    public void setProviderArea(String providerArea) {
        this.providerArea = providerArea;
    }
    public float getScoreAverage() {
        return scoreAverage;
    }
    public void setScoreAverage(float scoreAverage) {
        DecimalFormat format = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        format.setDecimalFormatSymbols(symbols);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(1);   
        this.scoreAverage = Float.parseFloat(format.format(scoreAverage));
    }
    public int getTemplateId() {
        return templateId;
    }
    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }
    public String getTemplateImageUrl() {
        return templateImageUrl;
    }
    public void setTemplateImageUrl(String templateImageUrl) {
        this.templateImageUrl = "http://"+Utils.ipAddress+"/images/services/"+templateImageUrl;
    }
    public ArrayList<ReviewInfomation> getReviews() {
        return reviews;
    }
    public void setReviews(ArrayList<ReviewInfomation> reviews) {
        this.reviews = reviews;
        if (reviews.size()>0){
            int tot = 0;
            for (ReviewInfomation reviewInfomation : reviews) {
                tot += reviewInfomation.getScore();
            }
            var x = ((float)tot) / reviews.size();
            setScoreAverage(x);
        }
    }

    public int getModality() {
        return modality;
    }

    public void setModality(int modality) {
        this.modality = modality;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean[] getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(boolean[] availableDays) {
        this.availableDays = availableDays;
    }

    public Time[] getFrom() {
        return from;
    }

    public void setFrom(Time[] from) {
        this.from = from;
    }

    public Time[] getTo() {
        return to;
    }

    public void setTo(Time[] to) {
        this.to = to;
    }

    
}
