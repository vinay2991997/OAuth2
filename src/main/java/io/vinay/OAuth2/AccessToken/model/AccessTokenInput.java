package io.vinay.OAuth2.AccessToken.model;

public class AccessTokenInput {
    private String accessToken;

    public AccessTokenInput() {
        this.accessToken = "";
    }

    public AccessTokenInput(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
