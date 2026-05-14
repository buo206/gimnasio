package com.example.gimnasio.Models;

import jakarta.persistence.*;

@Table
@Entity
public class TipoBono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBono ;

    @Column(length = 50 , nullable = false)
    private String tituloBono ;

    @Column(nullable = false)
    private int numeroDeUsos ;

    public int getIdBono() {
        return idBono;
    }

    public void setIdBono(int idBono) {
        this.idBono = idBono;
    }

    public String getTituloBono() {
        return tituloBono;
    }

    public void setTituloBono(String tituloBono) {
        this.tituloBono = tituloBono;
    }

    public int getNumeroDeUsos() {
        return numeroDeUsos;
    }

    public void setNumeroDeUsos(int numeroDeUsos) {
        this.numeroDeUsos = numeroDeUsos;
    }
}
