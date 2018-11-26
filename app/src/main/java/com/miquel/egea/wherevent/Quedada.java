package com.miquel.egea.wherevent;

import java.util.List;

public class Quedada {
    String Titulo, Descripción, Ubicacion, Fecha, Hora;
    Float Identificador, TipoEvento;
    List<Confirmacion> confirmaciones;

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripción() {
        return Descripción;
    }

    public void setDescripción(String descripción) {
        Descripción = descripción;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public Float getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(Float identificador) {
        Identificador = identificador;
    }

    public Float getTipoEvento() {
        return TipoEvento;
    }

    public void setTipoEvento(Float tipoEvento) {
        TipoEvento = tipoEvento;
    }

    public List<Confirmacion> getConfirmaciones() {
        return confirmaciones;
    }

    public void setConfirmaciones(List<Confirmacion> confirmaciones) {
        this.confirmaciones = confirmaciones;
    }
}
