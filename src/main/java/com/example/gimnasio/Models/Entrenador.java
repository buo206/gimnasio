package com.example.gimnasio.Models;

import jakarta.persistence.*;

import java.util.List;

@Table(name="Entrenador")
@Entity
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrenador")
    private int idEntranador ;

    @Column(name="nombre",length = 100 , nullable = false)
    private String nombre ;

    @Column(name="apellidos",length = 100)
    private String apellidos;

    @Column(name="direccion",length = 100)
    private String direccion;

    @Column(name="email", length = 255 , nullable = false )
    private String email;

    @Column(name="password",length = 50)
    private String password;

    @Column(name="telefono",length = 14)
    private String telefono;

    //relaciones OnoToMany
    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL)
    private List<Clase> clases;

    public int getIdEntranador() {
        return idEntranador;
    }

    public void setIdEntranador(int idEntranador) {
        this.idEntranador = idEntranador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
