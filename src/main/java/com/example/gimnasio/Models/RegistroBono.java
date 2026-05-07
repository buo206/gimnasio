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

    public int getIdBono() {
        return idBono;
    }

    public void setIdBono(int idBono) {
        this.idBono = idBono;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getBonosUno() {
        return bonosUno;
    }

    public void setBonosUno(int bonosUno) {
        this.bonosUno = bonosUno;
    }

    public int getBonosDos() {
        return bonosDos;
    }

    public void setBonosDos(int bonosDos) {
        this.bonosDos = bonosDos;
    }

    public int getUsosUno() {
        return usosUno;
    }

    public void setUsosUno(int usosUno) {
        this.usosUno = usosUno;
    }

    public int getUsosDos() {
        return usosDos;
    }

    public void setUsosDos(int usosDos) {
        this.usosDos = usosDos;
    }
}
