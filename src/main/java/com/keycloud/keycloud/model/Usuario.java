package com.keycloud.keycloud.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static java.time.LocalTime.now;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true, name="nombre_Usuario")
    private String nombreUsuario;
    @Column(nullable = false, unique = true, name="email")
    private String email;
    @Column(nullable = false, name="passwd")
    private String passwd;

    @Column(name = "fecha_Creacion")
    private LocalDateTime fechaCreacion;

    public Usuario(String nombreUsuario, String email, String passwd, LocalDateTime fechaCreacion) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.passwd = passwd;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Usuario() {

    }


    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String password) {
        this.passwd = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
