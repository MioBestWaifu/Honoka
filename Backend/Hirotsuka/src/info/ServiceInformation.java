package info;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;

import managers.Utils;

public class ServiceInformation {
    private String serviceName,description, providerName, providerUrl, providerImageUrl;
    private float costPerHour;
    private int providerId;

    public String toJson(){
        HashMap<String,String> mapFields = new HashMap<>();
        mapFields.put("ServiceName", serviceName);
        if (serviceName.length()>26)
            mapFields.put("ShortServiceName", serviceName.substring(0, 24)+"...");
        else
            mapFields.put("ShortServiceName", serviceName);
        mapFields.put("Description", description);
        if (description.isBlank()){
            description = "DESC";
        }
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
        this.providerImageUrl = "http://"+Utils.ipAddress+"/images/"+providerCode+".png";
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

    
}
