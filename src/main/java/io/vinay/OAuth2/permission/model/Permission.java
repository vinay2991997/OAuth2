package io.vinay.OAuth2.permission.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Permission {
    @Id
    private String type;

    public Permission() {
    }

    public Permission(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
