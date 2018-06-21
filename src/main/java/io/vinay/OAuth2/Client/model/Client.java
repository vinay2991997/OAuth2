package io.vinay.OAuth2.Client.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    private String clientId;
    private String clientSecret;
    private String appName;

    public Client() {
        this.clientId = "";
        this.clientSecret = "";
        this.appName = "";
    }

    public Client(String clientId, String clientSecret, String appName) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.appName = appName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
