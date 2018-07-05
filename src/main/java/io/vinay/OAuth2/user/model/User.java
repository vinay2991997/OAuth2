package io.vinay.OAuth2.user.model;

import io.vinay.OAuth2.ParkUser.model.ParkUser;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO : Add role attribute to the user

@Entity
@Table(name = "users")
@Proxy(lazy = false)
public class User {

    @Id
    private String phone;
    private String name;
    private String email;
    private String password;
    private String role;

    public User(){
        this.phone = "";
        this.name = "";
        this.email = "";
        this.password = "";
        this.role = "user";
    }

    public User(ParkUser parkUser, String role) {
        this.phone = parkUser.getPhone();
        this.name = parkUser.getName();
        this.email = parkUser.getEmail();
        this.password = parkUser.getPassword();
        this.role = role;
    }

    public User(String phone, String name, String email, String password, String role) {
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
