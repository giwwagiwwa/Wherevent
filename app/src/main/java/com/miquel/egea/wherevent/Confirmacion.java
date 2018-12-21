package com.miquel.egea.wherevent;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

class Confirmacion implements Serializable {

    String codigo_usuario;
    Integer confirma;
    // null no contesta
    // 0 no asiste
    // 1 asiste

    protected Confirmacion(Parcel in) {
        codigo_usuario = in.readString();
        if (in.readByte() == 0) {
            confirma = null;
        } else {
            confirma = in.readInt();
        }
    }

    public String getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(String codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public Integer getConfirma() {
        return confirma;
    }

    public void setConfirma(Integer confirma) {
        this.confirma = confirma;
    }

    public Confirmacion(String codigo_usuario, Integer confirma) {
        this.codigo_usuario = codigo_usuario;
        this.confirma = confirma;
    }

}
