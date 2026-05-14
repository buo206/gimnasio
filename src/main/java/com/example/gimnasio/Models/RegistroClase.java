package com.example.gimnasio.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name="RegistroClases")
@Entity
public class RegistroClase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRegistroClases")
    private int idRegistroClase ;

    @ManyToOne
    @JoinColumn(name = "idRegistroBono")
    private RegistroUsuarioBono registroUsuarioBono ;

    @ManyToOne
    @JoinColumn(name = "idClase")
    private Clase clase ;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "estado", length = 15, nullable = false)
    private Estado estado;

    public int getIdRegistroClase() {
        return idRegistroClase;
    }

    public void setIdRegistroClase(int idRegistroClase) {
        this.idRegistroClase = idRegistroClase;
    }

    public RegistroUsuarioBono getRegistroUsuarioBono() {
        return registroUsuarioBono;
    }

    public void setRegistroUsuarioBono(RegistroUsuarioBono registroUsuarioBono) {
        this.registroUsuarioBono = registroUsuarioBono;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
