package com.example.gimnasio.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Table
@Entity
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clase")
    private int idCalse ;

    @Column(length = 50 , nullable = false)
    private LocalDate fecha ;

    @Column(length = 50 , nullable = false)
    private LocalTime hora ;

    @Column(name ="cupo_max" ,nullable = false)
    private int cupoMax ;

    @ManyToOne
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador ;


    public int getIdCalse() {
        return idCalse;
    }

    public void setIdCalse(int idCalse) {
        this.idCalse = idCalse;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getCupoMax() {
        return cupoMax;
    }

    public void setCupoMax(int cupoMax) {
        this.cupoMax = cupoMax;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
}
