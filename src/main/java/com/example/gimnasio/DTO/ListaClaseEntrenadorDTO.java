package com.example.gimnasio.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public record ListaClaseEntrenadorDTO(
        int idClase ,
        String titulo,
        LocalDate fecha,
        LocalTime hora,
        int cupoMax
) {

}
