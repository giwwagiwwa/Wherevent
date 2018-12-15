package com.miquel.egea.wherevent;

public class Usuario {
    private String username, usercode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public Usuario(String username, String usercode) {

        this.username = username;
        this.usercode = usercode;
    }
}
