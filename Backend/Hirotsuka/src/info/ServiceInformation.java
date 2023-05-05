package info;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;

import managers.Utils;

public class ServiceInformation {
    private String serviceName,description, providerName, providerUrl, providerImageUrl, providerArea;
    private float costPerHour;
    private float scoreAverage;
    private int providerId, templateId;

    public String toJson(){
        HashMap<String,String> mapFields = new HashMap<>();
        mapFields.put("ServiceName", serviceName);
        mapFields.put("ServiceId", Integer.toString(templateId));
        if (serviceName.length()>25)
            mapFields.put("ShortServiceName", serviceName.substring(0, 23)+"...");
        else
            mapFields.put("ShortServiceName", serviceName);
        System.out.println(mapFields.get("ShortServiceName"));
        if (description.isBlank()){
                description = "DESC";
        }
        mapFields.put("ScoreAverage", Float.toString(scoreAverage));
        mapFields.put("ProviderArea", providerArea);
        mapFields.put("Description", description);
        mapFields.put("ProviderName", providerName);
        mapFields.put("ProviderUrl", providerUrl);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(symbols);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        mapFields.put("ProviderImageUrl", providerImageUrl);
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

    
    

    
}
