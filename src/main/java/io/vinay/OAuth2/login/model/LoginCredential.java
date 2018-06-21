package io.vinay.OAuth2.login.model;

public class LoginCredential {

    private String clientId;
    private String phone;
    private String password;

    public LoginCredential() {
        this.clientId = "";
        this.phone = "";
        this.password = "";
    }

    public LoginCredential(String clientId, String phone, String password) {
        this.clientId = clientId;
        this.phone = phone;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
