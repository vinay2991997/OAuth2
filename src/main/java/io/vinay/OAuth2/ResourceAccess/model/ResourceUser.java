package io.vinay.OAuth2.ResourceAccess.model;

import io.vinay.OAuth2.user.model.User;

public class ResourceUser {

    private String phone;
    private String email;
    private String name;
    private String message;

    public ResourceUser(String message) {

        this.phone = "";
        this.email = "";
        this.name = "";
        this.message = message;
    }

    public ResourceUser(User user, String message) {

        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.name = user.getName();
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
