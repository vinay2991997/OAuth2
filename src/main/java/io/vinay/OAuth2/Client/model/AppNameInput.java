package io.vinay.OAuth2.Client.model;

public class AppNameInput {
    private String appName;

    public AppNameInput() {
        this.appName = "";
    }

    public AppNameInput(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
