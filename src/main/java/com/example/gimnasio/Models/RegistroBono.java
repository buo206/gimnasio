package com.example.gimnasio.Models;

import jakarta.persistence.*;

@Table
@Entity
public class RegistroBono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bono")
    private int idBono;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", unique = true)
    private Usuario idUsuario;

    @Column(nullable = false)
    private int bonosUno;

    @Column(nullable = false)
    private int bonosDos;

    @Column(nullable = false)
    private int usosUno;

    @Column(nullable = false)
    private int usosDos;

}
