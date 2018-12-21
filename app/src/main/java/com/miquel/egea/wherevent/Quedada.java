package com.miquel.egea.wherevent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Quedada {
    private String identificador;
    private String titulo, descripción, ubicacion, autor;
    private Long tipo_evento;
    private Date fechaconhora;

    private ArrayList<Confirmacion> confirmaciones;

    public Quedada() {
    }

    public Quedada(String identificador, String titulo, String descripción, String ubicacion, String autor, Long tipo_evento, Date fechaconhora, ArrayList<Confirmacion> confirmaciones) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.descripción = descripción;
        this.ubicacion = ubicacion;
        this.autor = autor;

        this.tipo_evento = tipo_evento;
        this.fechaconhora = fechaconhora;
        this.confirmaciones = confirmaciones;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Long getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(Long tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    public Date getFechaconhora() {
        return fechaconhora;
    }

    public void setFechaconhora(Date fechaconhora) {
        this.fechaconhora = fechaconhora;
    }

    public List<Confirmacion> getConfirmaciones() {
        return confirmaciones;
    }

    public void setConfirmaciones(ArrayList<Confirmacion> confirmaciones) {
        this.confirmaciones = confirmaciones;
    }
}




