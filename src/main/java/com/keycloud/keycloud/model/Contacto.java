package com.keycloud.keycloud.model;

import jakarta.persistence.*;

@Entity
@Table(name="contacto")
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="email")
    private String email;

    @Column(name="nombre")
    private String nombre;

    @Column(name="asunto")
    private String asunto;


    @Column(name="mensaje", length=900)
    private String mensaje;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Contacto() {
    }

    public Contacto(long id, String email, String nombre, String asunto, String mensaje) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }
}
