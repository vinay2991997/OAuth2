package io.vinay.OAuth2.AuthCode.model;

public class AuthCode {

    private String authCode;
    private String message;

    public AuthCode() {
        this.authCode = "";
        this.message = "";
    }

    public AuthCode(String message) {
        this.authCode = "";
        this.message = message;
    }

    public AuthCode(String authCode, String message) {
        this.authCode = authCode;
        this.message = message;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
