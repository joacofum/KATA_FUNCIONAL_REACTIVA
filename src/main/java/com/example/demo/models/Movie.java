package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Document(collection = "movies")
public class Movie {
    @Id
    private String id = UUID.randomUUID().toString();
    private String nombre;
    private String idioma;
    private List<String> actores;
    private String categoria;
    private String anio;

    private Double presupuesto;

    private Double taquilla;

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public List<String> getActores() {
        return actores;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getAnio() {
        return anio;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public Double getTaquilla() {
        return taquilla;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setActores(List<String> actores) {
        this.actores = actores;
    }

    public void setActor(String actor) {
        this.actores.add(actor);
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public void setTaquilla(Double taquilla) {
        this.taquilla = taquilla;
    }
}
