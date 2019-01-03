package com.miquel.egea.wherevent;

class Usuario {
    String username, usercode;
    Long rango;

    public Long getRango() {
        return rango;
    }

    public void setRango(Long rango) {
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

    public Usuario(String username, String usercode, Long rango) {
        this.rango = rango;
        this.username = username;
        this.usercode = usercode;
    }
}
