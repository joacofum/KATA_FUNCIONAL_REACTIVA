package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "movies")
public class Movie {
    @Id
    private String id;
    private String nombre;
    private String idioma;
    private List<String> actores;
    private String categoria;

    private String anio;

    public Movie(String nombre, String idioma, List<String> actores, String categoria, String anio) {
        this.anio = anio;
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.idioma = idioma;
        this.actores = actores;
        this.categoria = categoria;
    }

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
}
