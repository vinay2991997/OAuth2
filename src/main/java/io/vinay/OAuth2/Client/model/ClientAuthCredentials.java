package io.vinay.OAuth2.Client.model;

public class ClientAuthCredentials {

    private String clientId;
    private String clientSecret;
    private String authCode;

    public ClientAuthCredentials() {
        this.clientId = "";
        this.clientSecret = "";
        this.authCode = "";
    }

    public ClientAuthCredentials(String clientId, String clientSecret, String authCode) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authCode = authCode;
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

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
