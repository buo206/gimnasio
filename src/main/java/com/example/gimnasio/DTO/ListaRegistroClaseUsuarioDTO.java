package com.example.gimnasio.DTO;

import com.example.gimnasio.Models.Estado;

import java.time.LocalDate;
import java.time.LocalTime;

public record ListaRegistroClaseUsuarioDTO(int idClase , String titulo , LocalDate fecha , LocalTime hora , Estado estado) {
}
