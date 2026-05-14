package com.example.gimnasio.Models;

import jakarta.persistence.*;

@Table(name = "Usuario")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private int idUsuario;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre ;

    @Column(name = "apellidos" ,length = 100)
    private String apellidos;

    @Column(name = "direccion" ,length = 100)
    private String direccion;

    @Column(name = "email" ,length = 255 , nullable = false)
    private String email;

    @Column(name = "password" ,length = 50)
    private String password;

    @Column(name = "dni" ,length = 9)
    private String dni;

    @Column(name = "telefono" ,length = 14)
    private String telefono;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
