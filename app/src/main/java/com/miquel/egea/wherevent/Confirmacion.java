package com.miquel.egea.wherevent;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

class Confirmacion implements Serializable, Map<String, Object> {

    String codigo_usuario;
    Integer confirma;

    // 0 no asiste
    // 1 asiste
    // 2 no contesta

    public Confirmacion(Parcel in) {
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

    public Confirmacion(Map<String , Object> fields){
        this.confirma = (Integer) fields.get("confirma");
        this.codigo_usuario = (String) fields.get("codigo_usuario");
    }

    public Confirmacion(String codigo_usuario, Integer confirma) {
        this.codigo_usuario = codigo_usuario;
        this.confirma = confirma;
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(String key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(@NonNull Map<? extends String, ?> m) {

    }

    @Override
    public void clear() {

    }

    @NonNull
    @Override
    public Set<String> keySet() {
        return null;
    }

    @NonNull
    @Override
    public Collection<Object> values() {
        return null;
    }

    @NonNull
    @Override
    public Set<Entry<String, Object>> entrySet() {
        return null;
    }
}
