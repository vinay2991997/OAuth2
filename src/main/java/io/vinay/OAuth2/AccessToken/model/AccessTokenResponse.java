package io.vinay.OAuth2.AccessToken.model;

public class AccessTokenResponse {

    private String accessToken;
    private String message;

    public AccessTokenResponse() {
        this.accessToken = "";
        this.message = "";
    }

    public AccessTokenResponse(String message) {
        this.accessToken = "------";
        this.message = message;
    }

    public AccessTokenResponse(String accessToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
