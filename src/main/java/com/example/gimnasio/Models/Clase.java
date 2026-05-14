package com.example.gimnasio.Models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name="Clase")
@Entity
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clase")
    private int idCalse ;

    @Column(name="fecha" ,length = 50 , nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha ;

    @Column(name = "hora", length = 50 , nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalTime hora ;

    @Column(name ="cupo_max" ,nullable = false)
    private int cupoMax ;

    @ManyToOne
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador ;

    @Column(name = "titulo" , nullable = false , length = 90)
    private String titulo ;


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
