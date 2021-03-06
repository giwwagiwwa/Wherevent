package com.miquel.egea.wherevent;

import java.io.Serializable;
import java.util.Map;


class Confirmacion implements Serializable {

    String codigo_usuario;
    Long confirma;

    // 0 no asiste
    // 1 asiste

    public Confirmacion(String codigo_usuario, Long confirma) {
        this.codigo_usuario = codigo_usuario;
        this.confirma = confirma;
    }

    public Confirmacion(Map<String , Object> fields){
        this.confirma = (Long) fields.get("confirma");
        this.codigo_usuario = (String) fields.get("codigo_usuario");
    }

    public String getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(String codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public Long getConfirma() {
        return confirma;
    }

    public void setConfirma(Long confirma) {
        this.confirma = confirma;
    }



}
