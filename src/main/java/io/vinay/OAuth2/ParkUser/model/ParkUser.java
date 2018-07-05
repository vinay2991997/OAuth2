package io.vinay.OAuth2.ParkUser.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParkUser {

    @Id
    private String phone;
    private String name;
    private String email;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ParkUser{" +
                "\nphone='" + phone + '\'' +
                ", \nname='" + name + '\'' +
                ", \nemail='" + email + '\'' +
                ", \npassword='" + password + '\'' +
                "\n}";

    }
}

