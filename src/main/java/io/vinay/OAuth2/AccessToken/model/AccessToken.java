package io.vinay.OAuth2.AccessToken.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Proxy(lazy = false)
public class AccessToken {

    @Id
    private String accessToken;
    private Long expires_at;
    private String phone;

    public AccessToken() {
        this.accessToken = "";
        this.expires_at = 0L;
        this.phone = "";
    }

    public AccessToken(String accessToken, Long expires_at, String phone) {
        this.accessToken = accessToken;
        this.expires_at = expires_at;
        this.phone = phone;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(Long expires_at) {
        this.expires_at = expires_at;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
