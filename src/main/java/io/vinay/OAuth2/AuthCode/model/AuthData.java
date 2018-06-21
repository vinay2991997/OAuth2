package io.vinay.OAuth2.AuthCode.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthData {

    @Id
    private String authCode;
    private String clientId;
    private String phone;

    public AuthData() {
        this.authCode = "";
        this.clientId = "";
        this.phone = "";
    }

    public AuthData(String authCode, String clientId, String phone) {
        this.authCode = authCode;
        this.clientId = clientId;
        this.phone = phone;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
