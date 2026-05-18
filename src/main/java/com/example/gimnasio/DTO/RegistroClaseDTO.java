package com.example.gimnasio.DTO;

import com.example.gimnasio.Models.Estado;

import java.time.LocalDate;
import java.time.LocalTime;

public record RegistroClaseDTO( int idRegistroClase ,int idClase , int idRegistroBono , Estado estado) {
}
