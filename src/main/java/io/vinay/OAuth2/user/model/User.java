package io.vinay.OAuth2.user.model;

import io.vinay.OAuth2.ParkUser.model.ParkUser;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String phone;
    private String name;
    private String email;
    private String password;

    public User(){
        this.phone = "";
        this.name = "";
        this.email = "";
        this.password = "";
    }

    public User(ParkUser parkUser) {
        this.phone = parkUser.getPhone();
        this.name = parkUser.getName();
        this.email = parkUser.getEmail();
        this.password = parkUser.getPassword();
    }

    public User(String phone, String name, String email, String password) {
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.password = password;
    }

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
        return "User{" +
                "\nphone='" + phone + '\'' +
                ", \nname='" + name + '\'' +
                ", \nemail='" + email + '\'' +
                ", \npassword='" + password + '\'' +
                "\n}";

    }
}
