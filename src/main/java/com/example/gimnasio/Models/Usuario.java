package com.example.gimnasio.Models;

import jakarta.persistence.*;

@Table
@Entity
public class Usuario {
    /*
    CREATE TABLE Usuario (
    id_usuario Int AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos varchar(100) ,
    direccion varchar(100) ,
    email VARCHAR(255) UNIQUE not null ,
    password varchar(50) ,
    dni varchar(9) ,
    telefono varchar(14)
);
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private int idUsuario;

    private String nombre ;
}
