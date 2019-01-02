package com.miquel.egea.wherevent;

public class Usuario {
    private String username, usercode;
    Integer rango;

    public Integer getRango() {
        return rango;
    }

    public void setRango(Integer rango) {
        this.rango = rango;
    }

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

    public Usuario(String username, String usercode, Integer rango) {
        this.rango = rango;
        this.username = username;
        this.usercode = usercode;
    }
}
