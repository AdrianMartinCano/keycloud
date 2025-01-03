package com.keycloud.keycloud.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="Contrasenas" )
public class Contrasenas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "idusuario")  // Hacer referencia a la columna exacta
    private long idusuario;

    @Column(name="titulo")
    private String titulo;

    @Column(name="nombre_usuario")
    private String nombre_usuario;

    @Column(name="contrasena")
    private String contrasena;

    @Column(name="url")
    private String url;

    @Column(name="notas")
    private String notas;

    @Column(name="fecha_creacion")
    private LocalDateTime fecha_creacion;


    public Contrasenas() {
    }

    public Contrasenas(long idusuario, String titulo, String nombre_usuario, String contrasena, String url, String notas) {

        this.idusuario = idusuario;
        this.titulo = titulo;
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
        this.url = url;
        this.notas = notas;
        this.fecha_creacion = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getidusuario() {
        return idusuario;
    }

    public void setUsuario(long idusuario) {
        this.idusuario = idusuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
