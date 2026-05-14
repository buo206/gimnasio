package com.example.gimnasio.Models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Table
@Entity
public class RegistroUsuarioBono {
    /*CREATE TABLE RegistroUsuarioBono(
	idRegistroBono Int AUTO_INCREMENT PRIMARY KEY ,
	idBono Int ,
    idUsuario Int ,
    fechaCompra Date ,
	usos Int ,

    FOREIGN KEY (idUsuario)
        REFERENCES Usuario(id_usuario)
        ON DELETE CASCADE,

	FOREIGN KEY (idBono)
        REFERENCES TipoBono(idBono)
        ON DELETE CASCADE
);*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRegistroBono;

    @ManyToOne
    @JoinColumn(name = "idBono")
    private TipoBono tipoBono;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaCompra;

    @Column
    private int usos;

    public int getIdRegistroBono() {
        return idRegistroBono;
    }

    public void setIdRegistroBono(int idRegistroBono) {
        this.idRegistroBono = idRegistroBono;
    }

    public TipoBono getTipoBono() {
        return tipoBono;
    }

    public void setTipoBono(TipoBono tipoBono) {
        this.tipoBono = tipoBono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getUsos() {
        return usos;
    }

    public void setUsos(int usos) {
        this.usos = usos;
    }
}
