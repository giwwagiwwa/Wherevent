package com.miquel.egea.wherevent;

import java.util.List;

public class Quedada {
    private String titulo, descripción, ubicacion, fecha, hora,autor;
    private Integer identificador, tipo_evento;
    private List<Confirmacion> confirmaciones;

    public Quedada(String titulo, String descripción, String ubicacion, String fecha, String hora, String autor, Integer identificador, Integer tipo_evento) {
        this.titulo = titulo;
        this.descripción = descripción;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.hora = hora;
        this.autor = autor;
        this.identificador = identificador;
        this.tipo_evento = tipo_evento;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(Integer tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    public List<Confirmacion> getConfirmaciones() {
        return confirmaciones;
    }

    public void setConfirmaciones(List<Confirmacion> confirmaciones) {
        this.confirmaciones = confirmaciones;
    }
}