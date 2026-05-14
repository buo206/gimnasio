package com.example.gimnasio.Models;

import jakarta.persistence.*;

@Table(name="TipoBono")
@Entity
public class TipoBono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBono")
    private int idBono ;

    @Column(name = "tituloBono" ,length = 50 , nullable = false)
    private String tituloBono ;

    @Column(name = "numeroDeUsos" , nullable = false)
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
